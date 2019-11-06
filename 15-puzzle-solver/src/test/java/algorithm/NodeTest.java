package algorithm;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class that tests the functionality of Node class.
 */
public class NodeTest {
  
  public NodeTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of addChildren method, of class Node.
   */
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

  /**
   * Test of isSolved method, of class Node.
   */
  @Test
  public void testIsSolved() {
    int[][] solvedGameBoard = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    Node solvedNode = new Node(solvedGameBoard, null);
    assertEquals(solvedNode.isSolved(), true);
    int[][] unsolvedGameBoard = new int[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
    Node unsolvedNode = new Node(unsolvedGameBoard, null);
    assertEquals(unsolvedNode.isSolved(), false);
  }

  /**
   * Test of compareTo method, of class Node.
   */
  @Test
  public void testCompareTo() {
    int[][] solvedGameBoard = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    int[][] unsolvedGameBoard = new int[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
    Node solvedNode = new Node(solvedGameBoard, null);
    Node unsolvedNode = new Node(unsolvedGameBoard, null);
    assertEquals(solvedNode.compareTo(unsolvedNode), 1);
    assertEquals(solvedNode.compareTo(solvedNode), 0);
    assertEquals(unsolvedNode.compareTo(solvedNode), -1);
  }

  /**
   * Test of isSameBoard method, of class Node.
   */
  @Test
  public void testHasSameBoard() {
    int[][] solvedGameBoard = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    int[][] unsolvedGameBoard = new int[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
    Node solvedNode = new Node(solvedGameBoard, null);
    Node unsolvedNode = new Node(unsolvedGameBoard, null);
    assertEquals(solvedNode.hasSameBoard(solvedNode, solvedNode), true);
    assertEquals(solvedNode.hasSameBoard(solvedNode, unsolvedNode), false);
  }
}
