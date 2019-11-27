package gameboard;

import java.util.Arrays;
import java.util.Random;
import lists.BoardListElement;
import lists.BoardList;
import lists.Heap;

/**
 * This class contains the logic for the board of the
 * 15-puzzle game and any search algorithms implemented.
 */
public class Board {
  
  private int[] gameBoard;
  
  /**
   * Create a new object that handles the logic for the board of
   * the 15-puzzle game and any search algorithms implemented.
   */
  public Board() {
    this.gameBoard = new int[16];
    for (int i = 0; i < 16; i++) {
      this.gameBoard[i] = i + 1;
    }
  }
  
  /**
   * Shuffles the board into a random layout. Makes sure that
   * the puzzle is in solvable state, since only half of the
   * possible layouts are solvable due to parity.
   */
  public void shuffleBoard() {
    Random rand = new Random();
    while (true) {
      int[] newBoard = new int[16];
      int piece = 1;
      while (piece <= 16) {
        int i = rand.nextInt(16);
        if (newBoard[i] == 0) { // Check if index is still empty
          newBoard[i] = piece;
          if (piece == 16) { // Last piece has been placed in the board
            this.gameBoard = newBoard;
            if (this.getInversionCount() % 2 != (i / 4) % 2) { // Ensure solvability
              return;
            }
          }
          piece++;
        }
      }
    }
  }
  
  /**
   * Calculates the amount of inversions or the parity this
   * layout of the board has. Every time a bigger number comes
   * before a smaller number, one inversion is added. Inversion
   * is used to check if layout of the board is solvable.
   * 
   * @return  Inversion count of the game board.
   */
  public int getInversionCount() {
    int inversionCount = 0;
    for (int i = 0; i < 15; i++) { // Smaller piece
      for (int j = i + 1; j < 16; j++) { // Bigger piece
        if (
          this.gameBoard[i] > this.gameBoard[j] &&
          this.gameBoard[i] < 16 &&
          this.gameBoard[j] < 16
        ) {
          inversionCount++;
        }
      }
    }
    return inversionCount; 
  }
  
  /**
   * Find the path of nodes that solve the 15-puzzle using A* search
   * algorithm. Open list prioritizes nodes with the lowest heuristic value.
   * 
   * @return  The final node of the path of nodes the solution took.
   */
  public Node aStar() {
    BoardList closedList = new BoardList(); // Visited nodes
    Heap openList = new Heap();
    openList.add(new Node(this.gameBoard, null)); // Add root node
    while (true) {
      Node current = openList.pollFirst();
      if (current.isSolved()) {
        return current;
      }
      closedList.add(current.getBoard());
      current.addChildren();
      for (Node child: current.getChildren()) {
        if (child != null) {
          boolean newLayout = true;
          BoardListElement element = closedList.getHead();
          while (element != null) { // Make sure child node isn't already checked layout
            if (Arrays.equals(element.getBoard(), child.getBoard())) {
              newLayout = false;
              break;
            }
            element = element.getNext();
          }
          if (newLayout) { // Add to open list if new unchecked board layout
            openList.add(child);
          }
        }
      }
    }
  }
  
  /**
   * Set the game board. Used to help with tests.
   * 
   * @param board The new layout of the Board.
   */
  public void setGameBoard(int[] board) {
    this.gameBoard = board;
  }

  /**
   * Get the game board.
   * 
   * @return  Array containing integers that represent the pieces.
   */
  public int[] getGameBoard() {
    return this.gameBoard;
  }

  /**
   * Get the number of a piece by its location on the board.
   *
   * @param   pos Position of the piece.
   * 
   * @return  The number that is positioned in the specified location.
   */
  public int getPieceNumber(int pos) {
    return this.gameBoard[pos];
  }

  /**
   * Get the string representation of the board.
   * 
   * @return  The string representation of the board.
   */
  @Override
  public String toString() {
    String string = "";
    for (int y = 0; y < 4; y++) {
      for (int x = 0; x < 4; x++) {
        if (this.gameBoard[y * 4 + x] != 16) {
          string += this.gameBoard[y * 4 + x];
        }
        string += "\t";
      }
      string += "\n";
    }
    return string;
  }
}
