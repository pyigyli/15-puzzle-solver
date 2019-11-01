package application;

public class BoardPiece {
    
  private final int size;
  private int x;
  private int y;
  private int color;
  private final int number;
  private final int numberOffsetX; // Offset number to place it
  private final int numberOffsetY; // in the middle of the piece

  public BoardPiece(int x, int y, int number) {
    this.size = 200;
    this.x = x;
    this.y = y;
    this.color = color;
    this.number = number;
    this.numberOffsetX = number < 10 ? 80 : 55; // Single and double digit values have different width
    this.numberOffsetY = 130;
  }

  public int getSize() {
    return this.size;
  }

  public void setX(int position) {
    this.x = position;
  }

  public int getX() {
    return this.x;
  }

  public void setY(int position) {
    this.y = position;
  }

  public int getY() {
    return this.y;
  }

  public int getNumber() {
    return this.number;
  }

  public int getNumberOffsetX() {
    return this.numberOffsetX;
  }

  public int getNumberOffsetY() {
    return this.numberOffsetY;
  }
}
