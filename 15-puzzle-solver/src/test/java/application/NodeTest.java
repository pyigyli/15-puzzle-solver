package application;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class NodeTest {

  @Test
  public void testAddChildren() {
    int[][] gameBoard = new int[][] {{1,5,9,13},{2,6,16,14},{3,7,11,15},{4,8,12,10}};
    Node node = new Node(gameBoard, null);
    node.addChildren();
    for (Node child: node.getChildren()) {
      if (child == null) {
        fail("Failed when empty piece is on the middle of the board and parent is null.");
      }
    }
    int[][] childGameBoard = new int[][] {{1,5,9,13},{2,6,11,14},{3,7,16,15},{4,8,12,10}};
    Node childNode = new Node(childGameBoard, node);
    childNode.addChildren();
    for (Node child: childNode.getChildren()) {
      if (child == null && Arrays.deepEquals(node.getBoard(), child.getBoard())) {
        fail("Failed when empty piece is on the middle of the board and has parent node.");
      }
    }
  }

  @Test
  public void testIsSolved() {
    int[][] solvedBoard = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    Node solvedNode = new Node(solvedBoard, null);
    assertEquals(true, solvedNode.isSolved());
    int[][] unsolvedGameBoard = new int[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
    Node unsolvedNode = new Node(unsolvedGameBoard, null);
    assertEquals(false, unsolvedNode.isSolved());
  }

  @Test
  public void testGetBoard() {
    int[][] board = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    Node node = new Node(board, null);
    assertArrayEquals(board, node.getBoard());
  }

  @Test
  public void testGetParent() {
    int[][] board1 = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    int[][] board2 = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,16,12}};
    Node node1 = new Node(board1, null);
    Node node2 = new Node(board2, node1);
    assertEquals(node1, node2.getParent());
  }

  @Test
  public void testGetChildren() {
    int[][] board = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,16,11},{4,8,12,15}};
    Node node = new Node(board, null);
    node.addChildren();
    for (Node child: node.getChildren()) {
      assertEquals(node, child.getParent());
    }
  }

  @Test
  public void testGetDepth() {
    int[][] board = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    Node node = new Node(board, null);
    Node childNode = new Node(board, node);
    assertEquals(0, node.getDepth());
    assertEquals(1, childNode.getDepth());
  }

  @Test
  public void testGetHeuristicValue() {
    int[][] board1 = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    int[][] board2 = new int[][] {{3,5,9,13},{1,6,10,14},{2,7,11,15},{4,8,12,16}};
    Node node1 = new Node(board1, null);
    Node node2 = new Node(board2, null);
    assertEquals(0, node1.getHValue());
    assertEquals(6, node2.getHValue());
  }
}
