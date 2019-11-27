package lists;

import gameboard.Node;
import org.junit.Test;
import static org.junit.Assert.*;

public class NodeElementTest {

  @Test
  public void testGetNode() {
    int[] board = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    Node node = new Node(board, null);
    NodeListElement element = new NodeListElement(node);
    assertEquals(node, element.getNode());
  }

  @Test
  public void testGetHeuristicValue() {
    int[] board = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    NodeListElement element = new NodeListElement(new Node(board, null));
    assertEquals(0, element.getHValue());
  }

  @Test
  public void testSetNext() {
    int[] board1 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    int[] board2 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 15};
    NodeListElement element = new NodeListElement(new Node(board1, null));
    NodeListElement next = new NodeListElement(new Node(board2, null));
    element.setNext(next);
    assertEquals(next, element.getNext());
  }

  @Test
  public void testGetNext() {
    int[] board1 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    NodeListElement element = new NodeListElement(new Node(board1, null));
    assertEquals(null, element.getNext());
    int[] board2 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 15};
    NodeListElement next = new NodeListElement(new Node(board2, null));
    element.setNext(next);
    assertEquals(next, element.getNext());
    
  }
}
