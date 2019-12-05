package gameboard;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class NodeTest {

  @Test
  public void testAddChildren() {
    int[] gameBoard = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 16, 12, 13, 14, 15, 10};
    Node node = new Node(gameBoard, null);
    node.addChildren();
    for (Node child: node.getChildren()) {
      if (child == null) {
        fail("Failed when empty piece is on the middle of the board and parent is null.");
      }
    }
    int[] childGameBoard = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 16, 13, 14, 15, 10};
    Node childNode = new Node(childGameBoard, node);
    childNode.addChildren();
    for (Node child: childNode.getChildren()) {
      if (child != null && Arrays.equals(node.getBoard(), child.getBoard())) {
        fail("Failed when empty piece is on the middle of the board and has parent node.");
      }
    }
  }

  @Test
  public void testIsSolved() {
    int[] solvedBoard = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    Node solvedNode = new Node(solvedBoard, null);
    assertEquals(true, solvedNode.isSolved());
    int[] unsolvedGameBoard = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 15};
    Node unsolvedNode = new Node(unsolvedGameBoard, null);
    assertEquals(false, unsolvedNode.isSolved());
  }

  @Test
  public void testGetBoard() {
    int[] board = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 15};
    Node node = new Node(board, null);
    assertArrayEquals(board, node.getBoard());
  }

  @Test
  public void testGetParent() {
    int[] board1 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    int[] board2 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 15};
    Node node1 = new Node(board1, null);
    Node node2 = new Node(board2, node1);
    assertEquals(node1, node2.getParent());
  }

  @Test
  public void testGetChildren() {
    int[] board = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 16, 12, 13, 14, 11, 15};
    Node node = new Node(board, null);
    node.addChildren();
    for (Node child: node.getChildren()) {
      assertEquals(node, child.getParent());
    }
  }

  @Test
  public void testGetDepth() {
    int[] board = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 16, 12, 13, 14, 11, 15};
    Node node = new Node(board, null);
    Node childNode = new Node(board, node);
    assertEquals(0, node.getDepth());
    assertEquals(1, childNode.getDepth());
  }

  @Test
  public void testGetHeuristicValue() {
    int[] board1 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    int[] board2 = new int[] {3, 1, 2, 8, 5, 6, 7, 4, 9, 10, 11, 12, 13, 14, 15, 16};
    Node node1 = new Node(board1, null);
    Node node2 = new Node(board2, null);
    assertEquals(0, node1.getHeuristicValue());
    assertEquals(10, node2.getHeuristicValue());
  }
  
  @Test
  public void testToString() {
    int[] board1 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    int[] board2 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 15};
    Node node1 = new Node(board1, null);
    Node node2 = new Node(board2, node1);
    assertEquals(
      "Current depth: 0\n1\t2\t3\t4\t\n5\t6\t7\t8\t\n9\t10\t11\t12\t\n13\t14\t15\t\t\n",
      node1.toString()
    );
    assertEquals(
      "Current depth: 1\n1\t2\t3\t4\t\n5\t6\t7\t8\t\n9\t10\t11\t12\t\n13\t14\t\t15\t\n",
      node2.toString()
    );
  }
}
