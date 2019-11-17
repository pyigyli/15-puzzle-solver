package application;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

  @Test
  public void testShuffleBoard() {
    Board board = new Board();
    board.shuffleBoard();
    int[] numbers = new int[16];
    for (int x = 0; x < 4; x++) {
      for (int y = 0; y < 4; y++) {
        numbers[y * 4 + x] = board.getPieceNumber(x, y);
      }
    }
    for (int number: numbers) {
      for (int i = 0; i < 16; i++) {
        if (number == i) {
          break;
        }
        if (i == 16) {
          fail(("Number " + number + " not found after shuffling the board."));
        }
      }
    }
  }

  @Test
  public void testGetInversionCount() {
    Board board = new Board();
    int[][] layout1 = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    board.setGameBoard(layout1);
    assertEquals(0, board.getInversionCount());
    int[][] layout2 = new int[][] {{16,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,1}};
    board.setGameBoard(layout2);
    assertEquals(14, board.getInversionCount());
  }

  @Test(timeout=10000)
  public void testAStar() {
    Board board = new Board();
    board.shuffleBoard();
    assertArrayEquals(
      new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}},
      board.aStar().getBoard()
    );
  }

  @Test
  public void testSetGameBoard() {
    Board board = new Board();
    int[][] testLayout = new int[][] {{0}};
    board.setGameBoard(testLayout);
    assertArrayEquals(testLayout, board.getGameBoard());
  }

  @Test
  public void testGetGameBoard() {
    Board board = new Board();
    assertArrayEquals(
      new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}},
      board.getGameBoard()
    );
  }

  @Test
  public void testGetPieceNumber() {
    Board board = new Board();
    assertEquals(1, board.getPieceNumber(0, 0));
    assertEquals(4, board.getPieceNumber(3, 0));
    assertEquals(10, board.getPieceNumber(1, 2));
    assertEquals(16, board.getPieceNumber(3, 3));
  }
}
