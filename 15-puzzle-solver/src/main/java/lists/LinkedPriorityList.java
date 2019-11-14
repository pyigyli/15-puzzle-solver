package lists;

import application.Node;

/**
 * Class used as open list for the A* algorithm.
 */
public class LinkedPriorityList {
  
  private PriorityListElement head;
  
  /**
   * Initialize a new LinkedPriorityList.
   */
  public LinkedPriorityList() {
    this.head = null;
  }
  
  /**
   * Add a node to the list. Added element sorts itself where
   * every element after it has the same or larger heuristic value.
   * 
   * @param node  The node that will be added.
   */
  public void add(Node node) {
    PriorityListElement newElement = new PriorityListElement(node);
    if (this.head == null) {
      this.head = newElement;
      return;
    }
    PriorityListElement element = this.head;
    while (element.getHeuristicValue() < newElement.getHeuristicValue()) {
      if (element.getNext() == null) {
        element.setNext(newElement);
        return;
      }
      element = element.getNext();
    }
    newElement.setNext(element);
    if (this.head == element) {
      this.head = newElement;
    }
  }
  
  /**
   * Returns and removes the first element from the list. That would
   * be one of the lowest heuristic values that the list contains.
   * 
   * @return  The first element of the list.
   */
  public Node pollFirst() {
    PriorityListElement element = this.head;
    if (this.head == null) {
      return null;
    }
    this.head = this.head.getNext();
    return element.getNode();
  }
  
  /**
   * Checks if the list is empty.
   * 
   * @return  Boolean value indicating whether or not the list is empty.
   */
  public boolean isEmpty() {
    return this.head == null;
  }
}
