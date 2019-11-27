package lists;

import gameboard.Node;
import org.junit.Test;
import static org.junit.Assert.*;

public class NodeListTest {

  @Test
  public void testAddFirstAndPollFirst() {
    int[] board1 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    int[] board2 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 15};
    NodeList list = new NodeList();
    list.addFirst(new Node(board1, null));
    list.addFirst(new Node(board2, null));
    assertArrayEquals(board2, list.pollFirst().getBoard());
    assertArrayEquals(board1, list.pollFirst().getBoard());
  }

  @Test
  public void testPollFirstOnEmptyList() {
    NodeList list = new NodeList();
    assertEquals(null, list.pollFirst());
  }

  @Test
  public void testIsEmpty() {
    NodeList list = new NodeList();
    assertEquals(true, list.isEmpty());
    int[] board = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    list.addFirst(new Node(board, null));
    assertEquals(false, list.isEmpty());
  }
}
