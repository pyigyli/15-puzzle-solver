package gameboard;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

  @Test
  public void testShuffleBoard() {
    Board board = new Board();
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

  @Test(timeout=10000)
  public void testAStar() {
    Board board = new Board();
    board.shuffleBoard();
    assertArrayEquals(
      new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16},
      board.aStar().getBoard()
    );
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
}
