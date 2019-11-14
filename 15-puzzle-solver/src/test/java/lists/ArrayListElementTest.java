package lists;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayListElementTest {

  @Test
  public void testGetBoard() {
    int[][] board = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    ArrayListElement element = new ArrayListElement(board);
    assertArrayEquals(board, element.getBoard());
  }

  @Test
  public void testSetNext() {
    int[][] board1 = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    int[][] board2 = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,16,12}};
    ArrayListElement element = new ArrayListElement(board1);
    ArrayListElement next = new ArrayListElement(board2);
    element.setNext(next);
    assertEquals(next, element.getNext());
  }

  @Test
  public void testGetNext() {
    int[][] board1 = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    ArrayListElement element = new ArrayListElement(board1);
    assertEquals(null, element.getNext());
    int[][] board2 = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,16,12}};
    ArrayListElement next = new ArrayListElement(board2);
    element.setNext(next);
    assertEquals(next, element.getNext());
  }
}
