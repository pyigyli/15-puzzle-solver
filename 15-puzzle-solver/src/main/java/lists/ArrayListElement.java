package lists;

/**
 * This class is used in LinkedArrayList.
 */
public class ArrayListElement {
  
  private final int[][] board;
  private ArrayListElement next;
  
  /**
   * Create a new node that is not associated with the rest of the list yet.
   * 
   * @param board  The game board we want to store in the list.
   */
  public ArrayListElement(int[][] board) {
    this.board = board;
    this.next = null;
  }
  
  /**
   * Get the game board.
   * 
   * @return  The game board.
   */
  public int[][] getBoard() {
    return this.board;
  }
  
  /**
   * Give this element a new child, so a new
   * element can be added to the tail of the list.
   * 
   * @param element The new child of this element.
   */
  public void setNext(ArrayListElement element) {
    this.next = element;
  }
  
  /**
   * Returns the child element of this element.
   * 
   * @return  The next node in the list this element is contained in.
   */
  public ArrayListElement getNext() {
    return this.next;
  }
}
