package lists;

import application.Node;
import org.junit.Test;
import static org.junit.Assert.*;

public class NodeElementTest {

  @Test
  public void testGetNode() {
    int[][] board = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    Node node = new Node(board, null);
    PriorityListElement element = new PriorityListElement(node);
    assertEquals(node, element.getNode());
  }

  @Test
  public void testGetHeuristicValue() {
    int[][] board = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    PriorityListElement element = new PriorityListElement(new Node(board, null));
    assertEquals(0, element.getHeuristicValue());
  }

  @Test
  public void testSetNext() {
    int[][] board1 = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    int[][] board2 = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,16,12}};
    PriorityListElement element = new PriorityListElement(new Node(board1, null));
    PriorityListElement next = new PriorityListElement(new Node(board2, null));
    element.setNext(next);
    assertEquals(next, element.getNext());
  }

  @Test
  public void testGetNext() {
    int[][] board1 = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    PriorityListElement element = new PriorityListElement(new Node(board1, null));
    assertEquals(null, element.getNext());
    int[][] board2 = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,16,12}};
    PriorityListElement next = new PriorityListElement(new Node(board2, null));
    element.setNext(next);
    assertEquals(next, element.getNext());
    
  }
}
