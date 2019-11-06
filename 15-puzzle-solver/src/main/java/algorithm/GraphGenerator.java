package algorithm;

import java.util.ArrayList;

/**
 * Class that generates directed graphs.
 */
public class GraphGenerator {
  
  private ArrayList<Node> nodes;
  
  /**
   * Create a generator for directed graphs.
   */
  public GraphGenerator() {
    this.nodes = new ArrayList<>();
  }
  
  /**
   * Add a node into the graph with specified
   * depth counted from the starting node.
   * 
   * @param boardPermutation  The permutation of the board
   *                          that the node represents
   * @param depth             The depth counted from the starting node
   */
  public void addNode(int[][] boardPermutation, int depth, Node parent) {
    this.nodes.add(new Node(boardPermutation, parent));
  }
  
  /**
   * Get the neighbor nodes that represent
   * possible moves current board permutation has.
   * 
   * @param   node  The node who's neighbors we want to get
   * 
   * @return  Neighboring nodes of the parameter node.
   */
  public Node[] getChildren(Node node) {
    return node.getChildren();
  }
  
  /**
   * Get the node that represents the game boards starting permutation.
   * 
   * @return  Graphs beginning node.
   */
  public Node getStartNode() {
    return this.nodes.get(0);
  }
}
