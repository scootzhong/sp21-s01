package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
  public void testThreeAddThreeRemove() {
      BuggyAList aList = new BuggyAList<>();
      aList.addLast(4); // 4
      aList.addLast(5); // 4 5
      aList.addLast(6); // 4 5 6
      assertEquals(aList.removeLast(), 6);
      assertEquals(aList.removeLast(), 5);
      assertEquals(aList.removeLast(), 4);
  }

  @Test
  public void randomizedTest() {
      AListNoResizing<Integer> L = new AListNoResizing<>();
      BuggyAList<Integer> L1 = new BuggyAList<>();

      int N = 5000;
      for (int i = 0; i < N; i += 1) {
          int operationNumber = StdRandom.uniform(0, 3);
          if (operationNumber == 0) {
              // addLast
              int randVal = StdRandom.uniform(0, 100);
              L.addLast(randVal);
              L1.addLast(randVal);
              System.out.println("addLast(" + randVal + ")");
          } else if (operationNumber == 1 && L.size() > 0) {
              // getLast
              int last = L.getLast();
              int last1 = L1.getLast();
              assertEquals(last, last1);
              System.out.println("getlast: " + last);
              System.out.println("getlast1: " + last1);
          } else if (operationNumber == 2 && L.size() > 0) {
              // removeLast
              int last = L.removeLast();
              int last1 = L1.removeLast();
              assertEquals(last, last1);
              System.out.println("removelast: " + last);
              System.out.println("removelast1: " + last1);
          }
      }
  }
}
