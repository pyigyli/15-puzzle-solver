package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameCanvas {

  private final int width;
  private final int height;
  private final Canvas canvas;
  private final GraphicsContext gc;
  private BoardPiece[] gameBoard;

  public GameCanvas() {
    this.width = 830;
    this.height = 830;
    this.canvas = new Canvas(this.width, this.height);
    this.gc = this.canvas.getGraphicsContext2D();
    this.gc.setFont(new Font("Arial", 80));

    // Create pieces for the board
    this.gameBoard = new BoardPiece[15];
    for (int x = 0; x < 4; x++) {
      for (int y = 0; y < 4; y++) {
        if (x < 3 || y < 3) { // Leave piece 16 empty
          this.gameBoard[y * 4 + x] = new BoardPiece(
            x * (this.width - 30) / 4 + (x * 10),
            y * (this.height - 30) / 4 + (y * 10),
            y * 4 + x + 1
          );
        }
      }
    }
  }

  public void drawBoard() {
    // Draw white background
    this.gc.setFill(Color.LIGHTGRAY);
    this.gc.fillRect(0, 0, this.width, this.height);
    this.gc.setLineWidth(3);
    
    // Draw pieces for gameboard
    for (BoardPiece piece: this.gameBoard) {
      this.gc.setFill(Color.DIMGREY);
      this.gc.fillRect(
        piece.getX(),
        piece.getY(),
        piece.getSize(),
        piece.getSize()
      );
      this.gc.setFill(Color.WHITE);
      this.gc.fillText(
        Integer.toString(piece.getNumber()),
        piece.getX() + piece.getNumberOffsetX(),
        piece.getY() + piece.getNumberOffsetY()
      );
    }
  }
  
  public int getWidth() {
    return this.width;
  }
  
  public int getHeight() {
    return this.height;
  }

  public Canvas getCanvas() {
    return this.canvas;
  }
  
  public BoardPiece[] getBoard() {
    return this.gameBoard;
  }
  
  public BoardPiece getPieceByNumber(int number) {
    return this.gameBoard[number - 1];
  }
}
