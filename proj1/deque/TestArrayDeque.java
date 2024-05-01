package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDeque {
    @Test
    public void testAddFirstNoRaise() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(1);
        a.addFirst(2);
        a.addFirst(3);
        a.addFirst(4);
        a.addFirst(5);
        a.addFirst(6);

        System.out.println(a.removeFirst());
        System.out.println(a.removeFirst());
        System.out.println(a.removeFirst());
        System.out.println(a.removeFirst());
        System.out.println(a.removeFirst());
        System.out.println(a.removeFirst());
        System.out.println(a.removeFirst());
    }

    @Test
    public void testAddLastNoRaise() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addLast(1);
        a.addLast(2);
        a.addLast(3);
        a.addLast(4);
        a.addLast(5);
        a.addLast(6);

        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
    }

    @Test
    public void testRaiseItemsInMiddle() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(1);
        a.addFirst(2);
        a.addLast(3);
        a.addLast(4);
        a.raise(a.arrayLength * 2);

        System.out.println(a.get(0)); // 2
        System.out.println(a.get(2)); // 3
    }

    @Test
    public void testRaiseItemsInRight() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addLast(1);
        a.addLast(2);
        a.addLast(3);
        a.addLast(4);

        System.out.println(a.get(0)); // 1
        System.out.println(a.get(3)); // 4

        a.raise(a.arrayLength * 2);
    }

    @Test
    public void testRaiseItemsInLeft() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(1);
        a.addFirst(2);
        a.addFirst(3);
        a.addFirst(4);

        System.out.println(a.get(0)); // 4
        System.out.println(a.get(3)); // 1
        System.out.println(a.get(4)); // null

        a.raise(a.arrayLength * 2);
    }

    @Test
    public void testRaiseItemsInLeftAndRight() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addLast(1);
        a.addLast(2);
        a.addLast(3);
        a.addLast(4);
        a.addLast(5);
        a.addLast(6);

        System.out.println(a.get(0)); // 1
        System.out.println(a.get(1)); // 2
        System.out.println(a.get(2)); // 3
        System.out.println(a.get(3)); // 4
        System.out.println(a.get(4)); // 5
        System.out.println(a.get(5)); // 6
        System.out.println(a.get(6)); // null

        a.raise(a.arrayLength * 2);
    }

    @Test
    public void testAddFirstRaise() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(1);
        a.addFirst(2);
        a.addFirst(3);
        a.addFirst(4);
        a.addFirst(5);
        a.addFirst(6);
        a.addFirst(7);
        a.addFirst(8);
    }

    @Test
    public void testAddLastRaise() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addLast(1);
        a.addLast(2);
        a.addLast(3);
        a.addLast(4);
        a.addLast(5);
        a.addLast(6);
        a.addLast(7);
        a.addLast(8);
        a.addLast(9);
    }

    @Test
    public void testRemoveFirstRaise() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addLast(1);
        a.addLast(2);
        a.addLast(3);
        a.addLast(4);
        a.addLast(5);
        a.addLast(6);
        a.addLast(7);
        a.addLast(8);
        a.addLast(9);

        System.out.println(a.removeFirst());
        System.out.println(a.removeFirst());
        System.out.println(a.removeFirst());
        System.out.println(a.removeFirst());
        System.out.println(a.removeFirst());
        System.out.println(a.removeFirst());
        System.out.println(a.removeFirst());
        System.out.println(a.removeFirst());
        System.out.println(a.removeFirst());
        System.out.println(a.removeFirst());
    }

    @Test
    public void testRemoveLastRaise() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(1);
        a.addLast(2);
        a.addLast(3);
        a.addLast(4);
        a.addLast(5);
        a.addLast(6);
        a.addLast(7);
        a.addLast(8);
        a.addLast(9);

        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
    }

    @Test
    public void testIterator() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(1);
        a.addLast(2);
        a.addLast(3);
        for (int item : a) {
            System.out.println(item);
        }
    }

    @Test
    public void testEquals() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(1);
        a.addLast(2);
        a.addLast(3);

        ArrayDeque<Integer> b = new ArrayDeque<>();
        b.addFirst(1);
        b.addLast(2);
        b.addLast(3);

        ArrayDeque<Integer> c = new ArrayDeque<>();
        c.addFirst(1);
        c.addLast(2);

        assertTrue(a.equals(b));
        assertFalse(a.equals(c));
    }

    @Test
    public void testToString() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(1);
        a.addLast(2);
        a.addLast(3);

        assertEquals("[1, 2, 3]", a.toString());
    }
}
