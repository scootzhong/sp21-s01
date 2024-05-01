package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

public class RandomArrayDeque {
    @Test
    public void randomizedTest() {
        ArrayDeque<Integer> L = new ArrayDeque<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 6);
            if (operationNumber == 0) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                L.addFirst(randVal);
                System.out.println("addFirst(" + randVal + ")");
            } else if (operationNumber == 1 && L.size() > 0) {
                // getFirst
                int first = L.get(0);
                System.out.println("getfirst: " + first);
            } else if (operationNumber == 2 && L.size() > 0) {
                // removeFirst
                int first = L.removeFirst();
                System.out.println("removeFirst: " + first);
            } else if (operationNumber == 3) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 4 && L.size() > 0) {
                // getLast
                int last = L.get(L.size() - 1);
                System.out.println("getLast: " + last);
            } else if (operationNumber == 5 && L.size() > 0) {
                // removeLast
                int last = L.removeLast();
                System.out.println("removeLast: " + last);
            }
            L.printDeque();
        }
    }
}
