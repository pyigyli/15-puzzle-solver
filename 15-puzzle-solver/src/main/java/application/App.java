package application;

import algorithm.AStar;
import algorithm.Node;
import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {
    
  @Override
  public void start(Stage window) throws ClassNotFoundException {
    
    // Window settings
    final int screenWidth = 830;
    final int screenHeight = 900;

    // Iterative deepening depth-first search
    AStar aStar = new AStar();

    // Canvas element
    GameCanvas canvas = new GameCanvas();
    canvas.drawBoard();
    
    // Menu buttons
    Button shuffleButton = new Button("Shuffle");
    Button solveButton = new Button("Solve");
    HBox menu = new HBox();
    menu.getChildren().addAll(shuffleButton, solveButton);
    menu.setPadding(new Insets(20, 0, 0, 0));
    menu.setSpacing(20);
    menu.setAlignment(Pos.CENTER);
    
    // Shuffle button
    shuffleButton.setOnAction((event) -> {
      aStar.shuffleBoard();
      for (int x = 0; x < 4; x++) {
        for (int y = 0; y < 4; y++) {
          int number = aStar.getPieceNumber(x, y);
          if (number != 16) { // Ignore empty piece
            BoardPiece piece = canvas.getPieceByNumber(number);
            piece.setX(x * (canvas.getWidth() - 30) / 4 + (x * 10));
            piece.setY(y * (canvas.getHeight() - 30) / 4 + (y * 10));
          }
        }
      }
      canvas.drawBoard();
    });
    
    // Solve Button
    solveButton.setOnAction((event) -> {
      long startTime = System.nanoTime();
      Node node = aStar.findSolution();
      long elapsedTime = System.nanoTime() - startTime;
      System.out.println("solved in " + elapsedTime / 1_000_000_000 + " seconds.");
      ArrayDeque<Node> solutionNodes = new ArrayDeque<>();
      while (node.getParent() != null) {
        solutionNodes.add(node);
        node = node.getParent();
      }
      while (!solutionNodes.isEmpty()) {
        node = solutionNodes.removeLast();
        for (int x = 0; x < 4; x++) {
          for (int y = 0; y < 4; y++) {
            if (node.getBoard()[x][y] != 16) {
              BoardPiece piece = canvas.getPieceByNumber(node.getBoard()[x][y]);
              piece.setX(x * 200 + (x * 10));
              piece.setY(y * 200 + (y * 10));
              canvas.drawBoard();
            }
          }
        }
      }
    });

    // Compose full scene
    BorderPane borderPane = new BorderPane();
    borderPane.setTop(menu);
    borderPane.setBottom(canvas.getCanvas());

    window.setScene(new Scene(borderPane, screenWidth, screenHeight));
    window.setTitle("15 Puzzle Solver");
    window.show();
  }

  public static void main(String[] args) {
    launch(App.class);
  }
}
