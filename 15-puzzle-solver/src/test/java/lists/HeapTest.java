package lists;

import gameboard.Node;
import org.junit.Test;
import static org.junit.Assert.*;

public class HeapTest {

  @Test
  public void testAddAndPollFirst() {
    int[] board = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    Node node = new Node(board, null);
    Heap heap = new Heap();
    heap.add(node);
    assertEquals(node, heap.pollFirst());
  }
}
