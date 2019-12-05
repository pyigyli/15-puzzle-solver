package lists;

import gameboard.Node;

/**
 * Class used as open list for the A* algorithm.
 */
public class Heap {

  private final Node[] heap;
  private int size;
  private final int maxSize;

  /**
   * Initialize a new Heap with hard coded maximum
   * size. The maximum size of 1000 was decided after
   * testing the solve speeds with various maximum sizes.
   */
  public Heap() {
    this.size = 0;
    this.maxSize = 1000;
    this.heap = new Node[this.maxSize + 1];
  }

  /**
   * Add a new node to the heap. Added node will sort itself
   * according to its heuristic value. If the list is full,
   * the element with biggest heuristic value will be discarded.
   * 
   * @param node  The node to be added.
   */
  public void add(Node node) {
    if (this.size < this.maxSize) {
      this.size += 1;
    }
    this.heap[this.size] = node; 
    int current = this.size; 
    while (
      this.heap[this.getParent(current)] != null &&
      this.heap[current].getHeuristicValue() < this.heap[this.getParent(current)].getHeuristicValue()
    ) {
      this.swap(current, this.getParent(current));
      current = this.getParent(current);
    }
  }

  /**
   * Removes and returns the node with the lowest heuristic value.
   * 
   * @return  The node with the lowest heuristic value.
   */
  public Node pollFirst() {
    Node result = this.heap[1];
    this.heap[1] = heap[this.size--];
    this.sortHeap(1);
    return result;
  }
  
  /**
   * Recursively sorts the heap starting from the given position.
   * 
   * @param pos The position to start recursively sorting from.
   */
  private void sortHeap(int pos) {
    if (this.hasChildren(pos)) {
      Node leftChild = this.heap[this.getLeftChild(pos)];
      Node rightChild = this.heap[this.getRightChild(pos)];
      if (leftChild == null && rightChild == null) {
        return;
      }
      if (
        this.heap[pos].getHeuristicValue() > leftChild.getHeuristicValue() ||
        this.heap[pos].getHeuristicValue() > rightChild.getHeuristicValue()
      ) {
        if (leftChild.getHeuristicValue() < rightChild.getHeuristicValue()) {
          this.swap(pos, this.getLeftChild(pos));
          this.sortHeap(this.getLeftChild(pos));
        } else {
          this.swap(pos, this.getRightChild(pos));
          this.sortHeap(this.getRightChild(pos));
        }
      }
    }
  }
  
  /**
   * Swap the positions of two nodes in the heap.
   * 
   * @param a The first node.
   * @param b The second node.
   */
  private void swap(int a, int b) {
    Node tmp = this.heap[a];
    this.heap[a] = this.heap[b];
    this.heap[b] = tmp;
  }
  
  /**
   * Returns the position of this heap elements parent.
   * 
   * @param   pos The position of the heap element.
   * @return  The position of the parent of the heap element.
   */
  private int getParent(int pos) {
    return pos / 2;
  }
  
  /**
   * Returns the position of this heap elements left child.
   * 
   * @param   pos The position of the heap element.
   * @return  The position of the left child of the heap element.
   */
  private int getLeftChild(int pos) {
    return 2 * pos;
  }

  /**
   * Returns the position of this heap elements right child.
   * 
   * @param   pos The position of the heap element.
   * @return  The position of the right child of the heap element.
   */
  private int getRightChild(int pos) {
    return 2 * pos + 1;
  }

  /**
   * Returns a boolean value indicating if the given element has children.
   * 
   * @param   pos The position of the heap element.
   * @return  Boolean value indicating if the given element has children.
   */
  private boolean hasChildren(int pos) {
    return !(pos >= (this.size / 2) && pos <= this.size);
  }
}
