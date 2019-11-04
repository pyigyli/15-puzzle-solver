package algorithm;

import java.util.Random;

/**
 * This class contains the logic for iterative deepening depth-first algorithm.
 */
public class IDDFS {
  
  private int[][] gameBoard;
  
  /**
   * Create a new object that handles the logic
   * for iterative deepening depth-first algorithm.
   */
  public IDDFS() {
    this.gameBoard = new int[4][4];
    for (int x = 0; x < 4; x++) {
      for (int y = 0; y < 4; y++) {
        this.gameBoard[x][y] = y * 4 + x + 1;
      }
    }
  }
  
  /**
   * Shuffles the board into a random layout.
   */
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

  /**
   * Get the game board.
   * 
   * @return Two dimensional array containing
   * integers that represent the pieces.
   */
  public int[][] getGameBoard() {
    return this.gameBoard;
  }

  /**
   * Get the number of a piece by its location on the board.
   *
   * @param  x  vertical position starting from 0
   * @param  y  horizontal position starting from 0
   * 
   * @return The BoardPiece object that corresponds to the parameter.
   */
  public int getPieceNumber(int x, int y) {
    return this.gameBoard[x][y];
  }
}
