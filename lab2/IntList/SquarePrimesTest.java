package IntList;

import static org.junit.Assert.*;

import jh61b.junit.In;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesNoPrimes() {
        IntList lst = IntList.of(14, 15, 16, 18, 20);
        boolean changed = IntListExercises.squarePrimes(lst); // should be false
        assertEquals("14 -> 15 -> 16 -> 18 -> 20", lst.toString());
        assertFalse(changed);
    }

    @Test
    public void testSquarePrimesEmptyList() {
        IntList lst = IntList.of();
        boolean changed = IntListExercises.squarePrimes(lst); // should be false
        assertFalse(changed);
        assertNull(lst);
    }

    @Test
    public void testSquarePrimesManyPrimes() {
        IntList lst = IntList.of(14, 15, 16, 17, 18, 19);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18 -> 361", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesoOnlyOnePrimes() {
        IntList lst = IntList.of(19);
        boolean changed = IntListExercises.squarePrimes(lst); // should be true
        assertEquals("361", lst.toString());
        assertTrue(changed);
    }
}
