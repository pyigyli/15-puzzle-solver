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
   * Check if the list contains a given board.
   * 
   * @param   board The game board that list will be compared against.
   * 
   * @return  Boolean value indicating whether or
   *          not list contains a given board.
   */
  public boolean contains(int[] board) {
    BoardListElement element = this.getHead();
    while (element != null) { // Iterate through the list
      boolean sameBoard = true;
      int i = board.length;
      while (--i >= 0) {
        if (board[i] != element.getBoard()[i]) {
          sameBoard = false;
          break;
        }
      }
      if (sameBoard) {
        return true;
      }
      element = element.getNext();
    }
    return false;
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
