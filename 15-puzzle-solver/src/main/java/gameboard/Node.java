package gameboard;

/**
 * Nodes are used in search algorithms that find the solution to solve
 * the 15-puzzle game. One node represents one layout of the game board.
 */
public class Node {
  
  private final int[][] boardLayout;
  private final Node parent;
  private final Node[] children;
  private final int depth;
  private final int heuristicValue;
  
  /**
   * Create a new node.
   * 
   * @param boardLayout The layout of the game board that this node represents.
   * @param parent      The parent node.
   */
  public Node(int[][] boardLayout, Node parent) {
    this.boardLayout = boardLayout;
    this.parent = parent;
    this.children = new Node[4];
    this.depth = parent == null ? 0 : parent.getDepth() + 1;
    this.heuristicValue = this.getManhattanDistance() + this.getLinearConflictCount() * 2;
  }

  /**
   * Adds neighbor nodes to the node by finding the empty
   * piece and swapping it with all adjacent pieces, creating
   * 2-4 new nodes of these new layouts. If there is no piece
   * in a direction, it will cause that child to stay null.
   */
  public void addChildren() {
    int x = 0;
    int y = 0;
    for (int i = 0; i < 16; i++) {
      if (this.boardLayout[i % 4][i / 4] == 16) {
        x = i % 4;
        y = i / 4;
      }
    }
    this.children[0] = this.makeChild(x, y, x, y - 1);
    this.children[1] = this.makeChild(x, y, x, y + 1);
    this.children[2] = this.makeChild(x, y, x - 1, y);
    this.children[3] = this.makeChild(x, y, x + 1, y);
  }

  /**
   * Create one child node where piece at location [swapX, swapY]
   * is swapped with empty piece that should be at location [x, y].
   * Swapped piece should be adjacent to the empty piece.
   * 
   * @param   x     The column where empty piece is located.
   * @param   y     The row where empty piece is located.
   * @param   swapX The column where swapped piece is located.
   * @param   swapY The row where swapped piece is located.
   * 
   * @return  A new child node for this piece.
   */
  private Node makeChild(int x, int y, int swapX, int swapY) {
    if (swapX < 0 || swapY < 0 || swapX > 3 || swapY > 3) {
      return null;
    }
    int[][] childBoard = this.copyBoard();
    childBoard[x][y] = childBoard[swapX][swapY];
    childBoard[swapX][swapY] = 16;
    return new Node(childBoard, this);
  }

  /**
   * Creates a pure copy of the board without references to the original.
   * 
   * @return  Two dimensional array identical to this.boardLayout.
   */
  private int[][] copyBoard() {
    int[][] newBoard = new int[4][4];
    for (int x = 0; x < 4; x++) {
      for (int y = 0; y < 4; y++) {
        newBoard[x][y] = this.boardLayout[x][y];
      }
    }
    return newBoard;
  }
  
  /**
   * Checks every piece and returns an integer 0-16 that indicates the
   * heuristic value for the A* algorithm. Heuristic value is based
   * on the amount of pieces in correct places on the board.
   * 
   * @return  The number of pieces in the correct places on the board.
   */
  private int getManhattanDistance() {
    int manhattanDistance = 0;
    for (int piece = 1; piece < 16; piece++) { // Don't include empty piece
      for (int x = 0; x < 4; x++) {
        for (int y = 0; y < 4; y++) {
          if (this.boardLayout[x][y] == piece) {
            int xDistance = Math.abs((piece - 1) % 4 - x);
            int yDistance = Math.abs((piece - 1) / 4 - y);
            manhattanDistance += xDistance + yDistance; // Add Manhattan distance
          }
        }
      }
    }
    return manhattanDistance;
  }
  
  /**
   * Takes every vertical and horizontal line in the board
   * and targeted solution layout of the board. Then checks
   * if they contain linear conflicts. Line can have 0-2
   * linear conflicts, but only one will be counted per line.
   * 
   * @return  The amount of lines that contain linear conflicts in the board.
   */
  private int getLinearConflictCount() {
    int linearConflicts = 0;
    int[][] targetBoard = new int[][] {{1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 15}, {4, 8, 12, 16}};
    for (int x = 0; x < 4; x++) { // Checks every row for conflicts
      if (this.hasLinearConflict(this.boardLayout[x], targetBoard[x])) {
        linearConflicts++;
      }
    }
    for (int y = 0; y < 4; y++) { // Checks every column for conflicts
      int[] column = new int[4];
      int[] targetColumn = new int[4];
      for (int x = 0; x < 4; x++) { // Build columns for checking
        column[x] = this.boardLayout[x][y];
        targetColumn[x] = targetBoard[x][y];
      }
      if (this.hasLinearConflict(column, targetColumn)) {
        linearConflicts++;
      }
    }
    return linearConflicts;
  }
  
  /**
   * Checks a line for any linear conflicts. If line contains
   * values A and B that also appear in the targetLine and
   * both A and B need to get around each other to reach their
   * target positions, that line contains a linear conflict.
   * 
   * @param   line        Line that will be checked.
   * @param   targetLine  The solution layout of the line.
   * 
   * @return  Boolean value that tells if line has a linear conflict.
   */
  private boolean hasLinearConflict(int[] line, int[] targetLine) {
    boolean smallPieceFoundAfterBigTarget = false;
    boolean bigPieceFoundBeforeSmallTarget = false;
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        if (line[i] == targetLine[j] && i < j) {
          bigPieceFoundBeforeSmallTarget = true;
        } else if (line[i] == targetLine[j] && i > j) {
          smallPieceFoundAfterBigTarget = true;
        }
      }
    }
    if (smallPieceFoundAfterBigTarget && bigPieceFoundBeforeSmallTarget) {
      return true;
    }
    return false;
  }
  
  /**
   * Checks every piece and returns a boolean value indicating
   * whether or not the game board is in a solved state.
   * 
   * @return  Boolean value indicating whether or not
   *          the game board is in a solved state.
   */
  public boolean isSolved() {
    boolean solved = true;
    for (int x = 0; x < 4; x++) {
      for (int y = 0; y < 4; y++) {
        if (this.boardLayout[x][y] != y * 4 + x + 1) {
          solved = false;
        }
      }
    }
    return solved;
  }
  
  /**
   * Get the game board.
   * 
   * @return  Two dimensional array of integers that represent the game board.
   */
  public int[][] getBoard() {
    return this.boardLayout;
  }

  /**
   * Get the neighbor node that represent the game
   * board before the latest move was executed.
   * 
   * @return  The parent node.
   */
  public Node getParent() {
    return this.parent;
  }
  
  /**
   * Get the neighbor nodes that represent
   * possible moves current board layout has.
   * 
   * @return  Neighboring nodes excluding the parent node.
   */
  public Node[] getChildren() {
    return this.children;
  }
  
  /**
   * Get the amount of moves used to get to
   * this board arrangement from the root node.
   * 
   * @return  The depth of the node in the graph.
   */
  public int getDepth() {
    return this.depth;
  }
  
  /**
   * Get the heuristic value of the node.
   * 
   * @return  The heuristic value of the node.
   */
  public int getHValue() {
    return this.heuristicValue;
  }
  
  /**
   * Get the string representation of the board layout.
   * 
   * @return  The string representation of the game board.
   */
  @Override
  public String toString() {
    String string = "Current depth: " + this.depth + "\n";
    for (int y = 0; y < 4; y++) {
      for (int x = 0; x < 4; x++) {
        if (this.boardLayout[x][y] != 16) {
          string += this.boardLayout[x][y];
        }
        string += "\t";
      }
      string += "\n";
    }
    return string;
  }
}
