package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestLinkedListDeque {
    @Test
    public void testSizeAndIsEmpty() {
        LinkedListDeque<Integer> list0 = new LinkedListDeque<>();

        int size = list0.size();
        assertEquals(0, size);
        assertTrue(list0.isEmpty());

        list0.addFirst(1);
        size = list0.size();
        assertEquals(1, size);
        assertFalse(list0.isEmpty());
    }
    @Test
    public void testAddAndGet() {
        LinkedListDeque<Integer> aDeque = new LinkedListDeque<>();
        aDeque.addFirst(2);
        aDeque.addFirst(1);
        aDeque.addLast(3); // 1 2 3

        int index0 = aDeque.get(0);
        int index1 = aDeque.get(1);
        int index2 = aDeque.get(2);
        assertEquals(1, index0);
        assertEquals(2, index1);
        assertEquals(3, index2);
        assertNull(aDeque.get(3));

        index0 = aDeque.getRecursive(0);
        index1 = aDeque.getRecursive(1);
        index2 = aDeque.getRecursive(2);
        assertEquals(1, index0);
        assertEquals(2, index1);
        assertEquals(3, index2);
        assertNull(aDeque.get(3));
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> aDeque = new LinkedListDeque<>();
        aDeque.addFirst(2);
        aDeque.addFirst(1);
        aDeque.addLast(3); // 1 2 3

        aDeque.printDeque(); // 1 2 3

        LinkedListDeque<Integer> aDeque1 = new LinkedListDeque<>();
        aDeque1.printDeque();
    }

    @Test
    public void testCompareTo() {
        LinkedListDeque<Integer> a = new LinkedListDeque<>();
        a.addFirst(1);
        a.addFirst(2);
        a.addFirst(3);

        LinkedListDeque<Integer> b = new LinkedListDeque<>();
        b.addFirst(1);
        b.addFirst(2);

        assertTrue(a.compareTo(b) > 0);
    }
}
