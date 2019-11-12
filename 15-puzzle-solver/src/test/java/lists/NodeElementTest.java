package lists;

import application.Node;
import org.junit.Test;
import static org.junit.Assert.*;

public class NodeElementTest {

  @Test
  public void testGetNode() {
    int[][] board = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    Node node = new Node(board, null);
    NodeElement element = new NodeElement(node);
    assertEquals(node, element.getNode());
  }

  @Test
  public void testGetHeuristicValue() {
    int[][] board = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    NodeElement element = new NodeElement(new Node(board, null));
    assertEquals(0, element.getHeuristicValue());
  }

  @Test
  public void testSetNext() {
    int[][] board = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    NodeElement element = new NodeElement(new Node(board, null));
    NodeElement next = new NodeElement(new Node(board, null));
    element.setNext(next);
    assertEquals(next, element.getNext());
  }

  @Test
  public void testGetNext() {
    int[][] board = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    NodeElement element = new NodeElement(new Node(board, null));
    assertEquals(null, element.getNext());
  }
}
