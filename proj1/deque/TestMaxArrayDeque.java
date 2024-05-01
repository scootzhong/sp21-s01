package deque;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Comparator;

public class TestMaxArrayDeque {
    /** Dog类用于测试
     */
    public static class Dog {
        public int size;
        public String name;

        public Dog(int s, String n) {
            size = s;
            name = n;
        }
    }

    /** SizeComparator用于比较Dog类，比较Size
     */
    public static class SizeComparator implements Comparator<Dog> {
        public SizeComparator() {
            super();
        }

        @Override
        public int compare(Dog o1, Dog o2) {
            return o1.size - o2.size;
        }
    }

    /** NameComparator用于比较Dog类，比较Name
     */
    public static class NameComparator implements Comparator<Dog> {
        @Override
        public int compare(Dog o1, Dog o2) {
            return o1.name.compareTo(o2.name);
        }
    }

    @Test
    public void testMax() {
        Dog a = new Dog(5, "smallDog");
        Dog b = new Dog(10, "middleDog");
        Dog c = new Dog(15, "largeDog");

        MaxArrayDeque<Dog> deque = new MaxArrayDeque(new SizeComparator());
        deque.addFirst(a);
        deque.addFirst(b);
        deque.addFirst(c);
        assertEquals(c, deque.max());
        assertEquals(a, deque.max(new NameComparator()));
    }
}
