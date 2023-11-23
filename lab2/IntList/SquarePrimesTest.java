package IntList;

import static org.junit.Assert.*;
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
<<<<<<< HEAD
    @Test
    public void testSquarePrimesSimple2() {
        IntList lst = IntList.of(1,2,5,9,24,63,67);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("1 -> 4 -> 25 -> 9 -> 24 -> 63 -> 4489", lst.toString());
        assertTrue(changed);
    }
=======
>>>>>>> 160747451c147c59d8e3cbf70a7afee2b73bebdb
}
