package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
<<<<<<< HEAD

=======
>>>>>>> 160747451c147c59d8e3cbf70a7afee2b73bebdb
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
<<<<<<< HEAD
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();
        for (int i = 4; i <= 6; i += 1) {
            correct.addLast(i);
            buggy.addLast(i);
        }
        assertEquals(correct.size(), buggy.size());
        assertEquals(correct.removeLast(), buggy.removeLast());
        assertEquals(correct.removeLast(), buggy.removeLast());
        assertEquals(correct.removeLast(), buggy.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();
        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int sizeL = L.size();
                int sizeB = B.size();
                assertEquals(sizeL,sizeB);
            } else if (L.size() > 0 && operationNumber == 2) {
                int lastL = L.getLast();
                int lastB = B.getLast();
                L.removeLast();
                B.removeLast();
            }
        }
    }

    public void main(String[] args) {
        testThreeAddThreeRemove();
        randomizedTest();
    }
=======
  // YOUR TESTS HERE
>>>>>>> 160747451c147c59d8e3cbf70a7afee2b73bebdb
}
