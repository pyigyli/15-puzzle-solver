package gameboard;

import java.util.Arrays;
import java.util.Random;
import lists.BoardListElement;
import lists.BoardList;
import lists.Heap;

/**
 * This class contains the logic for the board of the
 * n-puzzle and any search algorithms implemented.
 */
public class Board {
  
  private int size;
  private int[] gameBoard;
  
  /**
   * Create a new object that handles the logic for the board of
   * the n-puzzle game and any search algorithms implemented.
   */
  public Board() {
    this.size = 4;
    this.gameBoard = new int[this.size * this.size];
    for (int i = 0; i < this.size * this.size; i++) {
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
      int[] newBoard = new int[this.size * this.size];
      int piece = 1;
      while (piece <= this.size * this.size) {
        int i = rand.nextInt(this.size * this.size);
        if (newBoard[i] == 0) { // Check if index is still empty
          newBoard[i] = piece;
          if (piece == this.size * this.size) { // Last piece has been placed in the board
            this.gameBoard = newBoard;
            if (this.size % 2 == 1 && this.getInversionCount() % 2 == 0 ||
                this.size % 2 == 0 && this.getInversionCount() % 2 != (i / this.size) % 2) {
              return; // Done if the new layout is solvable.
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
    for (int i = 0; i < this.size * this.size - 1; i++) { // Smaller piece
      for (int j = i + 1; j < this.size * this.size; j++) { // Bigger piece
        if (
          this.gameBoard[i] > this.gameBoard[j] &&
          this.gameBoard[i] < this.size * this.size &&
          this.gameBoard[j] < this.size * this.size
        ) {
          inversionCount++;
        }
      }
    }
    return inversionCount; 
  }
  
  /**
   * Find the path of nodes that solve the n-puzzle using A* search
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
        if (child != null && !closedList.contains(child.getBoard())) {
          openList.add(child); // Add to open list if new unchecked board layout
        }
      }
    }
  }
  
  /**
   * Set the size of the puzzle.
   * 
   * @param size  The new size of the both edges of the board.
   */
  public void setSize(int size) {
    this.size = size;
  }
  
  
  /**
   * Get the size of the puzzle.
   * 
   * @return  The size of the edges of the board.
   */
  public int getSize() {
    return this.size;
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
    for (int y = 0; y < this.size; y++) {
      for (int x = 0; x < this.size; x++) {
        if (this.gameBoard[y * this.size + x] != this.size * this.size) {
          string += this.gameBoard[y * this.size + x];
        }
        string += "\t";
      }
      string += "\n";
    }
    return string;
  }
}
