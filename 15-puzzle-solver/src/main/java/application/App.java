package application;

import gameboard.Board;
import gameboard.Node;
import java.util.Scanner;
import lists.NodeList;

public class App {

  public static void main(String[] args) throws InterruptedException {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter a number to select an option:");
    System.out.println("\t1. Solve a single board.");
    System.out.println("\t2. Test algorithm with selected amount of randomized boards.");
    String response = input.nextLine();
    if (response.equals("1")) {
      singleSolve();
    } else if (response.equals("2")) {
      System.out.println("How many different boards should be solved?");
      solveTests(input.nextInt());
    }
  }

  static void singleSolve() throws InterruptedException {
    Board board = new Board();
    board.shuffleBoard();
    int starterHeuristic = new Node(board.getGameBoard(), null).getHValue();
    System.out.println("\nStarting the puzzle with board\n" + board.toString());

    System.out.println("Solving...");
    double startTime = System.nanoTime();
    Node node = board.aStar();
    double elapsedTime = (System.nanoTime() - startTime) / 1_000_000_000;

    System.out.println("Started with heuristic value of " + starterHeuristic);
    System.out.println("Solved in " + String.format("%.3f", elapsedTime) + " seconds.");
    System.out.println("Found solution has depth " + node.getDepth());

    NodeList solutionNodes = new NodeList();
    while (node != null) {
      solutionNodes.addFirst(node);
      node = node.getParent();
    }

    System.out.println("\nDo you want to iterate through all board layouts of the solution? (y/n)");
    Scanner input = new Scanner(System.in);
    String response = input.nextLine();
    if ("y".equalsIgnoreCase(response)) {
      while (!solutionNodes.isEmpty()) {
        node = solutionNodes.pollFirst();
        System.out.println(node.toString());
        Thread.sleep(300);
      }
    }
  }
  
  static void solveTests(int solveAmount) throws InterruptedException {
    Board board = new Board();
    double minTime = Double.MAX_VALUE;
    double maxTime = 0;
    double sumTime = 0;
    int minTimeDepth = Integer.MAX_VALUE;
    int maxTimeDepth = 0;
    int minTimeHeuristic = Integer.MAX_VALUE;
    int maxTimeHeuristic = 0;
    int minDepth = Integer.MAX_VALUE;
    int maxDepth = 0;
    int sumDepth = 0;
    double minDepthTime = Double.MAX_VALUE;
    double maxDepthTime = 0;
    int minDepthHeuristic = Integer.MAX_VALUE;
    int maxDepthHeuristic = 0;
    int minHeuristic = Integer.MAX_VALUE;
    int maxHeuristic = 0;
    int sumHeuristic = 0;
    double minHeuristicTime = Double.MAX_VALUE;
    double maxHeuristicTime = 0;
    int minHeuristicDepth = Integer.MAX_VALUE;
    int maxHeuristicDepth = 0;
    
    System.out.println("Solving...");
    for (int solveCount = 1; solveCount <= solveAmount; solveCount++) {
      board.shuffleBoard();
      int starterHeuristic = new Node(board.getGameBoard(), null).getHValue();
      
      double startTime = System.nanoTime();
      Node node = board.aStar();
      double elapsedTime = (System.nanoTime() - startTime) / 1_000_000_000;
      
      if (minTime > elapsedTime) {
        minTime = elapsedTime;
        minTimeDepth = node.getDepth();
        minTimeHeuristic = starterHeuristic;
      }
      if (maxTime < elapsedTime) {
        maxTime = elapsedTime;
        maxTimeDepth = node.getDepth();
        maxTimeHeuristic = starterHeuristic;
      }
      if (minDepth > node.getDepth()) {
        minDepth = node.getDepth();
        minDepthTime = elapsedTime;
        minDepthHeuristic = starterHeuristic;
      }
      if (maxDepth < node.getDepth()) {
        maxDepth = node.getDepth();
        maxDepthTime = elapsedTime;
        maxDepthHeuristic = starterHeuristic;
      }
      if (minHeuristic > starterHeuristic) {
        minHeuristic = starterHeuristic;
        minHeuristicTime = elapsedTime;
        minHeuristicDepth = node.getDepth();
      }
      if (maxHeuristic < starterHeuristic) {
        maxHeuristic = starterHeuristic;
        maxHeuristicTime = elapsedTime;
        maxHeuristicDepth = node.getDepth();
      }
      sumTime += elapsedTime;
      sumDepth += node.getDepth();
      sumHeuristic += starterHeuristic;
    }
    System.out.println();
    System.out.println("Minimum solve time:\t" + String.format("%.3f", minTime) + " sec\twith solve depth:\t" + minTimeDepth + "\twith starter heuristic value:\t" + minTimeHeuristic);
    System.out.println("Maximum solve time:\t" + String.format("%.3f", maxTime) + " sec\twith solve depth:\t" + maxTimeDepth + "\twith starter heuristic value:\t" + maxTimeHeuristic);
    System.out.println("Average solve time:\t" + String.format("%.3f", (sumTime / solveAmount)) + " sec\n");
    System.out.println("Minimum solve depth:\t" + minDepth + "\twith solve time:\t" + String.format("%.3f", minDepthTime) + " sec\twith starter heuristic value:\t" + minDepthHeuristic);
    System.out.println("Maximum solve depth:\t" + maxDepth + "\twith solve time:\t" + String.format("%.3f", maxDepthTime) + " sec\twith starter heuristic value:\t" + maxDepthHeuristic);
    System.out.println("Average solve depth:\t" + (sumDepth / solveAmount) + "\n");
    System.out.println("Minimum starter heuristic:\t" + minHeuristic + "\twith solve time:\t" + String.format("%.3f", minHeuristicTime) + " sec\tand solve depth:\t" + minHeuristicDepth);
    System.out.println("Maximum starter heuristic:\t" + maxHeuristic + "\twith solve time:\t" + String.format("%.3f", maxHeuristicTime) + " sec\tand solve depth:\t" + maxHeuristicDepth);
    System.out.println("Average starter heuristic:\t" + (sumHeuristic / solveAmount) + "\n");
    System.out.println("Total time taken to solve:\t" + String.format("%.3f", sumTime) + " sec\n");
  }
}
