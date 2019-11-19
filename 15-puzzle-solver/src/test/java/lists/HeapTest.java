package lists;

import gameboard.Node;
import org.junit.Test;
import static org.junit.Assert.*;

public class HeapTest {

  @Test
  public void testAddAndPollFirst() {
    int[][] board = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    Node node = new Node(board, null);
    Heap heap = new Heap();
    heap.add(node);
    assertEquals(node, heap.pollFirst());
  }
}
