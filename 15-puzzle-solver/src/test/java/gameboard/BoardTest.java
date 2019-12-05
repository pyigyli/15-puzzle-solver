package gameboard;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

  @Test
  public void testShuffleBoard() {
    Board board = new Board();
    for (int i = 0; i < 100; i++) { // To eliminate random elements, shuffle multiple times
      board.shuffleBoard();
      for (int piece = 1; piece <= 16; piece++) {
        for (int pos = 0; pos < 16; pos++) {
          if (piece == board.getPieceNumber(pos)) {
            break;
          }
          if (pos == 16) {
            fail("Number " + piece + " not found after shuffling the board.");
          }
        }
      }
    }
  }

  @Test
  public void testGetInversionCount() {
    Board board = new Board();
    int[] layout1 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    board.setGameBoard(layout1);
    assertEquals(0, board.getInversionCount());
    int[] layout2 = new int[] {16, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 1};
    board.setGameBoard(layout2);
    assertEquals(14, board.getInversionCount());
  }

  @Test(timeout=60000)
  public void testAStarWithSize4() {
    Board board = new Board();
    board.shuffleBoard();
    assertArrayEquals(
      new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16},
      board.aStar().getBoard()
    );
  }

  @Test(timeout=60000)
  public void testAStarWithSize5() {
    Board board = new Board();
    board.setSize(5);
    board.shuffleBoard();
    assertArrayEquals(
      new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25},
      board.aStar().getBoard()
    );
  }

  @Test
  public void testSetSize() {
    Board board = new Board();
    board.setSize(5);
    assertEquals(5, board.getSize());
  }

  @Test
  public void testGetSize() {
    Board board = new Board();
    assertEquals(4, board.getSize());
  }

  @Test
  public void testSetGameBoard() {
    Board board = new Board();
    int[] testLayout = new int[] {0};
    board.setGameBoard(testLayout);
    assertArrayEquals(testLayout, board.getGameBoard());
  }

  @Test
  public void testGetGameBoard() {
    Board board = new Board();
    assertArrayEquals(
      new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16},
      board.getGameBoard()
    );
  }

  @Test
  public void testGetPieceNumber() {
    Board board = new Board();
    assertEquals(1, board.getPieceNumber(0));
    assertEquals(4, board.getPieceNumber(3));
    assertEquals(10, board.getPieceNumber(9));
    assertEquals(16, board.getPieceNumber(15));
  }
  
  @Test
  public void testToString() {
    Board board = new Board();
    assertEquals(
      "1\t2\t3\t4\t\n5\t6\t7\t8\t\n9\t10\t11\t12\t\n13\t14\t15\t\t\n",
      board.toString()
    );
    board.setGameBoard(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 15});
    assertEquals(
      "1\t2\t3\t4\t\n5\t6\t7\t8\t\n9\t10\t11\t12\t\n13\t14\t\t15\t\n",
      board.toString()
    );
  }
}
