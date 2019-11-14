package lists;

/**
 * Class used as closed list for the A* algorithm.
 */
public class LinkedArrayList {
  
  private ArrayListElement head;
  private ArrayListElement tail;
  
  /**
   * Initialize a new LinkedArrayList.
   */
  public LinkedArrayList() {
    this.head = null;
    this.tail = null;
  }
  
  /**
   * Add a game board to the tail of the list.
   * 
   * @param board The game board that will be added.
   */
  public void add(int[][] board) {
    ArrayListElement newElement = new ArrayListElement(board);
    if (this.head == null) {
      this.head = newElement;
      this.tail = newElement;
    } else {
      this.tail.setNext(newElement);
      this.tail = newElement;
    }
  }
  
  /**
   * Returns the head of the list. The whole
   * list can then be iterated through it.
   * 
   * @return  The first element of the list.
   */
  public ArrayListElement getHead() {
    return this.head;
  }
}
