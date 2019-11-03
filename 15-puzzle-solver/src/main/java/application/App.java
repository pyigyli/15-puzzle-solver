package application;

import algorithm.IDDFS;
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
    IDDFS iddfs = new IDDFS();

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
      iddfs.shuffleBoard();
      for (int x = 0; x < 4; x++) {
        for (int y = 0; y < 4; y++) {
          int number = iddfs.getPieceNumber(x, y);
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
