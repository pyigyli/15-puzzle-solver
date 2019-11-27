package gameboard;

/**
 * Nodes are used in search algorithms that find the solution to solve
 * the 15-puzzle game. One node represents one layout of the game board.
 */
public class Node {
  
  private final int[] boardLayout;
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
  public Node(int[] boardLayout, Node parent) {
    this.boardLayout = boardLayout;
    this.parent = parent;
    this.children = new Node[4];
    this.depth = parent == null ? 0 : parent.getDepth() + 1;
    this.heuristicValue = this.getManhattanDistance() + this.getLinearConflictCount() * 2;
  }

  /**
   * Adds neighbor nodes to the node by finding the
   * empty piece and swapping it with all adjacent pieces,
   * creating 1-3 new nodes of these new layouts. If
   * there is no piece in a direction or is is the parent
   * of this node, it will cause that child to stay null.
   */
  public void addChildren() {
    int position = 0;
    for (int i = 0; i < 16; i++) {
      if (this.boardLayout[i] == 16) {
        position = i;
      }
    }
    this.children[0] = this.makeChild(position, position - 4);
    this.children[1] = this.makeChild(position, position + 4);
    this.children[2] = this.makeChild(position, position - 1);
    this.children[3] = this.makeChild(position, position + 1);
  }

  /**
   * Create one child node where piece at specified
   * location is swapped with empty piece. Swapped
   * piece should be adjacent to the empty piece.
   * 
   * @param   pos     The position where empty piece is located.
   * @param   swapPos The position where swapped piece is located.
   * 
   * @return  A new child node for this piece.
   */
  private Node makeChild(int pos, int swapPos) {
    if (swapPos < 0 || swapPos >= 16) {
      return null;
    }
    if (this.parent != null && this.parent.getBoard()[swapPos] == 16) {
      return null; // Don't include parent node as a child
    }
    int[] childBoard = this.copyBoard();
    childBoard[pos] = childBoard[swapPos];
    childBoard[swapPos] = 16;
    return new Node(childBoard, this);
  }

  /**
   * Creates a pure copy of the board without references to the original.
   * 
   * @return  Two dimensional array identical to this.boardLayout.
   */
  private int[] copyBoard() {
    int[] newBoard = new int[16];
    for (int i = 0; i < 16; i++) {
      newBoard[i] = this.boardLayout[i];
    }
    return newBoard;
  }
  
  /**
   * Checks every piece and returns an integer that
   * adds to the heuristic value for the A* algorithm.
   * 
   * @return  The number of pieces in the correct places on the board.
   */
  private int getManhattanDistance() {
    int manhattanDistance = 0;
    for (int piece = 1; piece < 16; piece++) { // Don't include empty piece
      for (int i = 0; i < 16; i++) {
        if (this.boardLayout[i] == piece) {
          int xDistance = Math.abs((piece - 1) % 4 - i % 4);
          int yDistance = Math.abs((piece - 1) / 4 - i / 4);
          manhattanDistance += xDistance + yDistance; // Add Manhattan distance
        }
      }
    }
    return manhattanDistance;
  }
  
  /**
   * Takes every vertical and horizontal line in the board
   * and targeted solution layout of the board. Then checks
   * if they contain linear conflicts. Line can have up to 2
   * linear conflicts, but only one will be counted per line.
   * 
   * @return  The amount of lines that contain linear conflicts in the board.
   */
  private int getLinearConflictCount() {
    int linearConflicts = 0;
    for (int i = 0; i < 4; i++) {
      int[] row = new int[4];
      int[] column = new int[4];
      int[] targetRow = new int[4];
      int[] targetColumn = new int[4];
      for (int j = 0; j < 4; j++) {
        row[j] = this.boardLayout[i * 4 + j];
        column[j] = this.boardLayout[j * 4 + i];
        targetRow[j] = i * 4 + j + 1;
        targetColumn[j] = j * 4 + i + 1;
      }
      if (this.hasLinearConflict(row, targetRow)) {
        linearConflicts++;
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
    return smallPieceFoundAfterBigTarget && bigPieceFoundBeforeSmallTarget;
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
    for (int i = 0; i < 16; i++) {
      if (this.boardLayout[i] != i + 1) {
        solved = false;
      }
    }
    return solved;
  }
  
  /**
   * Get the game board.
   * 
   * @return  Array of integers that represent the game board.
   */
  public int[] getBoard() {
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
        if (this.boardLayout[y * 4 + x] != 16) {
          string += this.boardLayout[y * 4 + x];
        }
        string += "\t";
      }
      string += "\n";
    }
    return string;
  }
}
