package algorithm;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class to test iterative deepening depth-first search
 * algorithm and other functions related to its class.
 */
public class IDDFSTest {
  
  public IDDFSTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test that shuffling does not lose or copy any pieces of the board.
   */
  @Test
  public void testShuffleBoard() {
    IDDFS iddfs = new IDDFS();
    iddfs.shuffleBoard();
    int[] numbers = new int[16];
    for (int x = 0; x < 4; x++) {
      for (int y = 0; y < 4; y++) {
        numbers[y * 4 + x] = iddfs.getPieceNumber(x, y);
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

  /**
   * Test of getGameBoard method, of class IDDFS.
   */
  @Test
  public void testGetGameBoard() {
    IDDFS iddfs = new IDDFS();
    assertEquals(
      iddfs.getGameBoard(),
      new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}}
    );
  }

  /**
   * Test of getPieceNumber method, of class IDDFS.
   */
  @Test
  public void testGetPieceNumber() {
    IDDFS iddfs = new IDDFS();
    assertEquals(iddfs.getPieceNumber(0, 0), 1);
    assertEquals(iddfs.getPieceNumber(3, 0), 4);
    assertEquals(iddfs.getPieceNumber(1, 2), 10);
    assertEquals(iddfs.getPieceNumber(3, 3), 16);
  }
}
