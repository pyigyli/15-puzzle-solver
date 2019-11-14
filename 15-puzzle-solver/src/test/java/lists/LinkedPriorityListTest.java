package lists;

import application.Node;
import org.junit.Test;
import static org.junit.Assert.*;

public class LinkedPriorityListTest {

  @Test
  public void testAddAndPollFirst() {
    int[][] lowHeuristic = new int[][] {{6,5,9,13},{2,1,10,14},{3,7,11,15},{4,8,12,16}};
    int[][] mediumHeuristic = new int[][] {{2,6,9,13},{1,5,10,14},{3,7,11,15},{4,8,12,16}};
    int[][] highHeuristic = new int[][] {{12,5,13,9},{3,10,6,14},{2,7,11,15},{4,8,1,16}};
    LinkedPriorityList list1 = new LinkedPriorityList();
    list1.add(new Node(lowHeuristic, null));
    list1.add(new Node(mediumHeuristic, null));
    list1.add(new Node(highHeuristic, null));
    LinkedPriorityList list2 = new LinkedPriorityList();
    list2.add(new Node(highHeuristic, null));
    list2.add(new Node(mediumHeuristic, null));
    list2.add(new Node(lowHeuristic, null));
    assertEquals(list1.pollFirst().getHeuristicValue(), list2.pollFirst().getHeuristicValue());
    assertEquals(list1.pollFirst().getHeuristicValue(), list2.pollFirst().getHeuristicValue());
  }

  @Test
  public void testPollFirstOnEmptyList() {
    LinkedPriorityList list = new LinkedPriorityList();
    assertEquals(null, list.pollFirst());
  }

  @Test
  public void testIsEmpty() {
    LinkedPriorityList list = new LinkedPriorityList();
    assertEquals(true, list.isEmpty());
    int[][] board = new int[][] {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
    list.add(new Node(board, null));
    assertEquals(false, list.isEmpty());
  }
}
