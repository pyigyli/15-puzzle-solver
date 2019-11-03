package application;

import javafx.scene.canvas.Canvas;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class to test GameCanvas.
 */
public class GameCanvasTest {
  
  public GameCanvasTest() {
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
   * Test of drawBoard method, of class GameCanvas.
   */
  @Test
  public void testDrawBoard() {
    System.out.println("drawBoard");
    GameCanvas instance = new GameCanvas();
    instance.drawBoard();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getWidth method, of class GameCanvas.
   */
  @Test
  public void testGetWidth() {
    System.out.println("getWidth");
    GameCanvas instance = new GameCanvas();
    int expResult = 0;
    int result = instance.getWidth();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getHeight method, of class GameCanvas.
   */
  @Test
  public void testGetHeight() {
    System.out.println("getHeight");
    GameCanvas instance = new GameCanvas();
    int expResult = 0;
    int result = instance.getHeight();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getCanvas method, of class GameCanvas.
   */
  @Test
  public void testGetCanvas() {
    System.out.println("getCanvas");
    GameCanvas instance = new GameCanvas();
    Canvas expResult = null;
    Canvas result = instance.getCanvas();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getBoard method, of class GameCanvas.
   */
  @Test
  public void testGetBoard() {
    System.out.println("getBoard");
    GameCanvas instance = new GameCanvas();
    BoardPiece[] expResult = null;
    BoardPiece[] result = instance.getBoard();
    assertArrayEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getPieceByNumber method, of class GameCanvas.
   */
  @Test
  public void testGetPieceByNumber() {
    System.out.println("getPieceByNumber");
    int number = 0;
    GameCanvas instance = new GameCanvas();
    BoardPiece expResult = null;
    BoardPiece result = instance.getPieceByNumber(number);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
