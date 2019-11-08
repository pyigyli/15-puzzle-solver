package application;

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
      if (child == null && node.hasSameBoard(node, child)) {
        fail("Failed when empty piece is on the middle of the board and has parent node.");
      }
    }
  }

  @Test
  public void testMakeChild() {
    int[][] board = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    Node node = new Node(board, null);
    Node childNode = node.makeChild(3, 3, 3, 2);
    int[][] expectation = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,16,12}};
    assertArrayEquals(expectation, childNode.getBoard());
  }

  @Test
  public void testCopyBoard() {
    int[][] board = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    Node node = new Node(board, null);
    int[][] newBoard = node.copyBoard();
    assertArrayEquals(board, newBoard);
  }

  @Test
  public void testGetManhattanDistance() {
    int[][] board = new int[][] {{16,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,1}};
    Node node = new Node(board, null);
    assertEquals(6, node.getManhattanDistance());
  }

  @Test
  public void testGetLinearConflictCount() {
    int[][] board = new int[][] {{2,5,9,13},{1,6,10,14},{3,7,11,15},{4,12,8,16}};
    Node node = new Node(board, null);
    assertEquals(2, node.getLinearConflictCount());
  }

  @Test
  public void testHasLinearConflict() {
    int[] targetLine1 = new int[] {1,2,3,4};
    int[] targetLine2 = new int[] {1,5,9,13};
    int[] line1 = new int[] {0,3,0,2};
    int[] line2 = new int[] {3,4,0,0};
    int[] line3 = new int[] {4,3,2,1};
    int[] line4 = new int[] {1,6,5,4};
    int[] line5 = new int[] {1,5,9,13};
    int[] line6 = new int[] {1,9,5,13};
    int[] line7 = new int[] {5,1,9,13};
    Node node = new Node(new int[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}}, null);
    assertEquals(false, node.hasLinearConflict(targetLine1, targetLine1));
    assertEquals(true, node.hasLinearConflict(line1, targetLine1));
    assertEquals(false, node.hasLinearConflict(line2, targetLine1));
    assertEquals(true, node.hasLinearConflict(line3, targetLine1));
    assertEquals(false, node.hasLinearConflict(line4, targetLine2));
    assertEquals(false, node.hasLinearConflict(line5, targetLine2));
    assertEquals(true, node.hasLinearConflict(line6, targetLine2));
    assertEquals(true, node.hasLinearConflict(line7, targetLine2));
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
  public void testCompareTo() {
    int[][] solvedBoard = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    int[][] unsolvedBoard = new int[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
    Node solvedNode = new Node(solvedBoard, null);
    Node unsolvedNode = new Node(unsolvedBoard, null);
    assertEquals(-1, solvedNode.compareTo(unsolvedNode));
    assertEquals(0, solvedNode.compareTo(solvedNode));
    assertEquals(1, unsolvedNode.compareTo(solvedNode));
  }

  @Test
  public void testHasSameBoard() {
    int[][] solvedBoard = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    int[][] unsolvedBoard = new int[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
    Node solvedNode = new Node(solvedBoard, null);
    Node unsolvedNode = new Node(unsolvedBoard, null);
    assertEquals(true, solvedNode.hasSameBoard(solvedNode, solvedNode));
    assertEquals(false, solvedNode.hasSameBoard(solvedNode, unsolvedNode));
  }

  @Test
  public void testGetBoard() {
    int[][] board = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    Node node = new Node(board, null);
    assertArrayEquals(board, node.getBoard());
  }

  @Test
  public void testGetParent() {
    int[][] board = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    Node node = new Node(board, null);
    Node childNode = node.makeChild(0, 0, 0, 0);
    assertEquals(node, childNode.getParent());
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
    assertEquals(0, node1.getHeuristicValue());
    assertEquals(6, node2.getHeuristicValue());
  }
}
