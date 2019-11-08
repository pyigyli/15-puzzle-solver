package application;

import java.util.ArrayDeque;
import java.util.Scanner;

public class App {

  public static void main(String[] args) throws InterruptedException {
    Board board = new Board();
    board.shuffleBoard();
    System.out.println("Starting the puzzle with board\n" + board.toString());
    
    System.out.print("Solving...");
    double startTime = System.nanoTime();
    Node node = board.aStar();
    double elapsedTime = (System.nanoTime() - startTime) / 1_000_000_000;
    System.out.print("\b"); // Remove the "solving..." text when done
    
    System.out.println("Solved in " + String.format("%.2f", elapsedTime) + " seconds.");
    System.out.println("Found solution has depth " + node.getDepth());
    
    ArrayDeque<Node> solutionNodes = new ArrayDeque<>();
    while (node != null) {
      solutionNodes.add(node);
      node = node.getParent();
    }
    
    Scanner input = new Scanner(System.in);
    System.out.println("\nDo you want to iterate through all board layouts of the solution? (y/n)");
    String response = input.nextLine();
    if ("y".equals(response) || "Y".equals(response)) {
      while (!solutionNodes.isEmpty()) {
        node = solutionNodes.removeLast();
        System.out.println(node.toString());
        Thread.sleep(200);
      }
    }
  }
}
