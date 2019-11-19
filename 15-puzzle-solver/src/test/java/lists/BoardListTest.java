package lists;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoardListTest {

  @Test
  public void testList() {
    int[][] board1 = new int[][] {{1},{0}};
    int[][] board2 = new int[][] {{2},{0}};
    int[][] board3 = new int[][] {{3},{0}};
    BoardList list = new BoardList();
    list.add(board1);
    list.add(board2);
    list.add(board3);
    BoardListElement element = list.getHead();
    assertArrayEquals(board1, element.getBoard());
    element = element.getNext();
    assertArrayEquals(board2, element.getBoard());
    element = element.getNext();
    assertArrayEquals(board3, element.getBoard());
  }
}