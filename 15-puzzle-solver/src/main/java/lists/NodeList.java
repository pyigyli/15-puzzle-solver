package lists;

import application.Node;

/**
 * Class used to iterate through the solution nodes.
 */
public class NodeList {
  
  private NodeListElement head;
  
  /**
   * Initialize a new NodeList.
   */
  public NodeList() {
    this.head = null;
  }
  
  /**
   * Add a node to the start of the list.
   * 
   * @param node  The node that will be added.
   */
  public void addFirst(Node node) {
    NodeListElement newElement = new NodeListElement(node);
    if (this.head != null) {
      newElement.setNext(this.head);
    }
    this.head = newElement;
  }
  
  /**
   * Returns and removes the first element from the list.
   * 
   * @return  The first element of the list.
   */
  public Node pollFirst() {
    NodeListElement element = this.head;
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
