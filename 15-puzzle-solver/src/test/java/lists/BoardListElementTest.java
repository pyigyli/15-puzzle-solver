package lists;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoardListElementTest {

  @Test
  public void testGetBoard() {
    int[] board = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    BoardListElement element = new BoardListElement(board);
    assertArrayEquals(board, element.getBoard());
  }

  @Test
  public void testSetNext() {
    int[] board1 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    int[] board2 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 15};
    BoardListElement element = new BoardListElement(board1);
    BoardListElement next = new BoardListElement(board2);
    element.setNext(next);
    assertEquals(next, element.getNext());
  }

  @Test
  public void testGetNext() {
    int[] board1 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    BoardListElement element = new BoardListElement(board1);
    assertEquals(null, element.getNext());
    int[] board2 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 15};
    BoardListElement next = new BoardListElement(board2);
    element.setNext(next);
    assertEquals(next, element.getNext());
  }
}
