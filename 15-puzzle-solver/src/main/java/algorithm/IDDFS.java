package algorithm;

import java.util.Random;

public class IDDFS {
  
  private int[][] gameBoard;
  
  public IDDFS() {
    this.gameBoard = new int[4][4];
    for (int x = 0; x < 4; x++) {
      for (int y = 0; y < 4; y++) {
        this.gameBoard[x][y] = y * 4 + x + 1;
      }
    }
  }
  
  public void shuffleBoard() {
    Random rand = new Random();
    int[][] newBoard = new int[4][4];
    for (int i = 1; i <= 16; i++) {
      int randInt = rand.nextInt(16);
      if (newBoard[randInt % 4][randInt / 4] == 0) { // Check if index is still empty
        newBoard[randInt % 4][randInt / 4] = i;
      } else {
        i--; // Try another index if random int was not empty.
      }
    }
    this.gameBoard = newBoard;
  }
  
  public int getPieceNumber(int x, int y) {
    return this.gameBoard[x][y];
  }
}
