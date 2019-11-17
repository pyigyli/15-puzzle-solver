package application;

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
    System.out.println("\nStarting the puzzle with board\n" + board.toString());

    System.out.println("Solving...");
    double startTime = System.nanoTime();
    Node node = board.aStar();
    double elapsedTime = (System.nanoTime() - startTime) / 1_000_000_000;

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
    double minSolveTime = Double.MAX_VALUE;
    double maxSolveTime = Double.MIN_VALUE;
    double sumSolveTime = 0;
    int minSolveTimeDepth = 0;
    int maxSolveTimeDepth = 0;
    int minSolveDepth = Integer.MAX_VALUE;
    int maxSolveDepth = Integer.MIN_VALUE;
    int sumSolveDepth = 0;
    double minSolveDepthTime = 0;
    double maxSolveDepthTime = 0;
    
      System.out.println("Solving...");
    for (int solveCount = 1; solveCount <= solveAmount; solveCount++) {
      board.shuffleBoard();
      
      double startTime = System.nanoTime();
      Node node = board.aStar();
      double elapsedTime = (System.nanoTime() - startTime) / 1_000_000_000;
      
      if (minSolveTime > elapsedTime) {
        minSolveTime = elapsedTime;
        minSolveTimeDepth = node.getDepth();
      }
      if (maxSolveTime < elapsedTime) {
        maxSolveTime = elapsedTime;
        maxSolveTimeDepth = node.getDepth();
      }
      sumSolveTime += elapsedTime;
      if (minSolveDepth > node.getDepth()) {
        minSolveDepth = node.getDepth();
        minSolveDepthTime = elapsedTime;
      }
      if (maxSolveDepth < node.getDepth()) {
        maxSolveDepth = node.getDepth();
        maxSolveDepthTime = elapsedTime;
      }
      sumSolveDepth += node.getDepth();
    }
    System.out.println();
    System.out.println("Minimum solve time:\t" + String.format("%.3f", minSolveTime) + "\twith depth:\t" + minSolveTimeDepth);
    System.out.println("Maximum solve time:\t" + String.format("%.3f", maxSolveTime) + "\twith depth:\t" + maxSolveTimeDepth);
    System.out.println("Average solve time:\t" + String.format("%.3f", (sumSolveTime / solveAmount)) + "\n");
    System.out.println("Minimum solve depth:\t" + minSolveDepth + "\twith time:\t" + String.format("%.3f", minSolveDepthTime));
    System.out.println("Maximum solve depth:\t" + maxSolveDepth + "\twith time:\t" + String.format("%.3f", maxSolveDepthTime));
    System.out.println("Average solve depth:\t" + (sumSolveDepth / solveAmount));
    System.out.println("\nTotal time taken:\t" + String.format("%.3f", sumSolveTime) + "\n");
  }
}
