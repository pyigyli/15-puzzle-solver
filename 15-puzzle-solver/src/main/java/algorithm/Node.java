package algorithm;

import java.util.HashMap;

/**
 * Nodes are used in graphs and they contain the
 * information of their neighbors. One node also
 * represent a single permutation of the game board.
 */
public class Node implements Comparable<Node> {
  
  private final int[][] boardPermutation;
  private final Node parent;
  private Node[] children;
  private final int depth;
  private final int heuristicValue;
  
  /**
   * Create a new node for the graph.
   * 
   * @param boardPermutation  The permutation of the game
   *                          board that this node represents.
   * @param parent            The parent node
   */
  public Node(int[][] boardPermutation, Node parent) {
    this.boardPermutation = boardPermutation;
    this.parent = parent;
    this.children = null; // Children will be added as needed
    this.depth = parent == null ? 0 : parent.getDepth() + 1;
    this.heuristicValue = this.calculateHeuristicValue();
  }
  
  /**
   * Adds neighbor nodes to the node by finding the empty
   * piece and swapping it with all adjacent pieces, creating
   * 2-4 new nodes of these new permutations, then removing
   * the parent node to prevent going backwards on the graph.
   */
  public void addChildren() {
    int[][] upChildBoard = new int[4][4];
    int[][] downChildBoard = new int[4][4];
    int[][] leftChildBoard = new int[4][4];
    int[][] rightChildBoard = new int[4][4];
    int emptyPieceX = 0;
    int emptyPieceY = 0;
    for (int x = 0; x < 4; x++) {
      for (int y = 0; y < 4; y++) {
        upChildBoard[x][y] = this.boardPermutation[x][y];
        downChildBoard[x][y] = this.boardPermutation[x][y];
        leftChildBoard[x][y] = this.boardPermutation[x][y];
        rightChildBoard[x][y] = this.boardPermutation[x][y];
        if (this.boardPermutation[x][y] == 16) {
          emptyPieceX = x;
          emptyPieceY = y;
        }
      }
    }
    Node    upChild = this.makeChild(   upChildBoard, emptyPieceX, emptyPieceY, emptyPieceX, emptyPieceY - 1);
    Node  downChild = this.makeChild( downChildBoard, emptyPieceX, emptyPieceY, emptyPieceX, emptyPieceY + 1);
    Node  leftChild = this.makeChild( leftChildBoard, emptyPieceX, emptyPieceY, emptyPieceX - 1, emptyPieceY);
    Node rightChild = this.makeChild(rightChildBoard, emptyPieceX, emptyPieceY, emptyPieceX + 1, emptyPieceY);
    this.children = new Node[4]; // Amount of possible moves for each node is 2-4
    if (upChild != null && !this.hasSameBoard(upChild, this.parent)) {
      this.children[0] = upChild;
    }
    if (downChild != null && !this.hasSameBoard(downChild, this.parent)) {
      this.children[1] = downChild;
    }
    if (leftChild != null && !this.hasSameBoard(leftChild, this.parent)) {
      this.children[2] = leftChild;
    }
    if (rightChild != null && !this.hasSameBoard(rightChild, this.parent)) {
      this.children[3] = rightChild;
    }
  }
  
  public Node makeChild(int[][] gameBoard, int x, int y, int swapX, int swapY) {
    if (swapX < 0 || swapY < 0 || swapX > 3 || swapY > 3) {
      return null;
    }
    gameBoard[x][y] = gameBoard[swapX][swapY];
    gameBoard[swapX][swapY] = 16;
    return new Node(gameBoard, this);
  }
  
  /**
   * Checks every piece and returns an integer 0-16 that indicates the
   * heuristic value for the A* algorithm. Heuristic value is based
   * on the amount of pieces in correct places on the board.
   * 
   * @return  The number of pieces in the correct places on the board.
   */
  private int calculateHeuristicValue() {
    int totalHeuristic = 0;
    for (int piece = 1; piece < 16; piece++) { // Don't include empty piece
      for (int x = 0; x < 4; x++) {
        for (int y = 0; y < 4; y++) {
          if (this.boardPermutation[x][y] == piece) {
            int xDistance = Math.abs((piece - 1) % 4 - x);
            int yDistance = Math.abs((piece - 1) / 4 - y);
            totalHeuristic += xDistance + yDistance; // Add Manhattan distance
          }
          if (this.boardPermutation[x][y] != y * 4 + x + 1) {
            totalHeuristic += 2; // Add Hamming distance
          }
        }
      }
    }
    return totalHeuristic + this.depth;
  }
  
  /**
   * Checks every piece and returns a boolean value indicating
   * whether or not the game board is in a solved state.
   * 
   * @return  Boolean value indicating whether or not
   *          the game board is in a solved state
   */
  public boolean isSolved() {
    boolean solved = true;
    for (int x = 0; x < 4; x++) {
      for (int y = 0; y < 4; y++) {
        if (this.boardPermutation[x][y] != y * 4 + x + 1) {
          solved = false;
        }
      }
    }
    return solved;
  }
  
  /**
   * Overridden comparison method.
   * Compares based on the heuristic value.
   * 
   * @param   other The node to compare to.
   * 
   * @return  Integer based on the result of the comparison.
   */
  @Override
  public int compareTo(Node other) {
    double a = this.getHeuristicValue();
    double b = other.getHeuristicValue();
    if (a < b) return -1;
    if (a > b) return 1;
    return 0;
  }
  
  /**
   * Overridden comparison method.
   * Compares based on the heuristic value.
   * 
   * @param   other The node to compare to.
   * 
   * @return  Integer based on the result of the comparison.
   */
  public boolean hasSameBoard(Node a, Node b) {
    if (a == null || b == null) {
      return false;
    }
    for (int x = 0; x < 4; x++) {
      for (int y = 0; y < 4; y++) {
        if (a.getBoard()[x][y] != b.getBoard()[x][y]) {
          return false;
        }
      }
    }
    return true;
  }
  
  /**
   * Get the neighbor nodes that represent
   * possible moves current board permutation has.
   * 
   * @return  Neighboring nodes of the parameter node.
   */
  public int[][] getBoard() {
    return this.boardPermutation;
  }
  
  /**
   * Get the neighbor node that represent the game board
   * before this current permutation of the board came to be.
   * 
   * @return  The parent node.
   */
  public Node getParent() {
    return this.parent;
  }
  
  /**
   * Get the neighbor nodes that represent
   * possible moves current board permutation has.
   * 
   * @return  Neighboring nodes excluding the parent node.
   */
  public Node[] getChildren() {
    return this.children;
  }
  
  /**
   * Get the amount of moves used to get to
   * this board arrangement from the start node.
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
  public int getHeuristicValue() {
    return this.heuristicValue;
  }
}
