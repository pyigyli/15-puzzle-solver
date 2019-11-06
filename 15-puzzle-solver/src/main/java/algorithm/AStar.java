package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeSet;

/**
 * This class contains the logic for A* search algorithm.
 */
public class AStar {
  
  private int[][] gameBoard;
  
  /**
   * Create a new object that handles the logic
   * for A* search algorithm.
   */
  public AStar() {
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
    int[][] newBoard = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    int x = 3;
    int y = 3;
    Random rand = new Random();
    for (int i = 0; i < 1000000; i++) {
      switch (rand.nextInt(4)) {
        case 0:
          if (x + 1 < 4) {
            newBoard[x][y] = newBoard[x + 1][y];
            newBoard[x + 1][y] = 16;
            x++;
          }
          break;
        case 1:
          if (x - 1 >= 0) {
            newBoard[x][y] = newBoard[x - 1][y];
            newBoard[x - 1][y] = 16;
            x--;
          }
          break;
        case 2:
          if (y + 1 < 4) {
            newBoard[x][y] = newBoard[x][y + 1];
            newBoard[x][y + 1] = 16;
            y++;
          }
          break;
        case 3:
          if (y - 1 >= 0) {
            newBoard[x][y] = newBoard[x][y - 1];
            newBoard[x][y - 1] = 16;
            y--;
          }
          break;
      }
    }
    this.gameBoard = newBoard;
  }
  
  /**
   * Find the path of nodes that solve the 15-puzzle.
   * 
   * @return The final node that represents the solution.
   */
  public Node findSolution() {
    ArrayList<int[][]> closedList = new ArrayList<>();
    PriorityQueue<Node> openList = new PriorityQueue<>();
    openList.add(new Node(this.gameBoard, null));
    while (!openList.isEmpty()) {
      Node current = openList.remove();
      System.out.println(
        current.getBoard()[0][0]+"\t"+current.getBoard()[1][0]+"\t"+current.getBoard()[2][0]+"\t"+current.getBoard()[3][0]+"\n"+
        current.getBoard()[0][1]+"\t"+current.getBoard()[1][1]+"\t"+current.getBoard()[2][1]+"\t"+current.getBoard()[3][1]+"\n"+
        current.getBoard()[0][2]+"\t"+current.getBoard()[1][2]+"\t"+current.getBoard()[2][2]+"\t"+current.getBoard()[3][2]+"\n"+
        current.getBoard()[0][3]+"\t"+current.getBoard()[1][3]+"\t"+current.getBoard()[2][3]+"\t"+current.getBoard()[3][3]+"\n"
      );
      if (current.isSolved()) {
        return current;
      }
      closedList.add(current.getBoard());
      current.addChildren();
      for (Node child: current.getChildren()) {
        if (child != null) {
          boolean newPermutation = true;
          for (int[][] permutation: closedList) {
            for (int x = 0; x < 4; x++) {
              for (int y = 0; y < 4; y++) {
                if (Arrays.deepEquals(permutation, child.getBoard())) {
                  newPermutation = false;
                }
              }
            }
          }
          if (newPermutation) {
            openList.add(child);
          }
        }
      }
    }
    return null;
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
