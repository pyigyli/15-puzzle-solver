package lists;

/**
 * Class used as closed list for the A* algorithm.
 */
public class BoardList {
  
  private BoardListElement head;
  private BoardListElement tail;
  
  /**
   * Initialize a new BoardList.
   */
  public BoardList() {
    this.head = null;
    this.tail = null;
  }
  
  /**
   * Add a game board to the tail of the list.
   * 
   * @param board The game board that will be added.
   */
  public void add(int[] board) {
    BoardListElement newElement = new BoardListElement(board);
    if (this.head == null) {
      this.head = newElement;
    } else {
      this.tail.setNext(newElement);
    }
    this.tail = newElement;
  }
  
  /**
   * Returns the head of the list. The whole
   * list can then be iterated through it.
   * 
   * @return  The first element of the list.
   */
  public BoardListElement getHead() {
    return this.head;
  }
}
