package lists;

import application.Node;

/**
 * This class is used in LinkedPriorityList.
 */
public class NodeElement {
  
  private final Node node;
  private NodeElement next;
  
  /**
   * Create a new node that is not associated with the rest of the list yet.
   * 
   * @param node  The Node object we want to store in the list.
   */
  public NodeElement(Node node) {
    this.node = node;
    this.next = null;
  }
  
  /**
   * Get the Node object.
   * 
   * @return  The Node object.
   */
  public Node getNode() {
    return this.node;
  }
  
  /**
   * Returns the heuristic value of the node of this list element.
   * 
   * @return  The heuristic value of the node.
   */
  public int getHeuristicValue() {
    return this.node.getHeuristicValue();
  }
  
  /**
   * Give this element a new child, so the
   * list can be iterated in the correct order.
   * 
   * @param element The new child of this element.
   */
  public void setNext(NodeElement element) {
    this.next = element;
  }
  
  /**
   * Returns the child element of this element.
   * 
   * @return  The next node in the list this element is contained in.
   */
  public NodeElement getNext() {
    return this.next;
  }
}
