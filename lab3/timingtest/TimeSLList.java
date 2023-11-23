package timingtest;
<<<<<<< HEAD

=======
>>>>>>> 160747451c147c59d8e3cbf70a7afee2b73bebdb
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
<<<<<<< HEAD
        int M = 10000;
        AList<Integer> Ns = new AList<>();
        for (int x = 1000; x <= 128000; x = x * 2) {
            Ns.addLast(x);
        }
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        SLList<Integer> example = new SLList<>();
        for (int i = 0; i < Ns.size(); i += 1) {
            for (int j = 0; j < Ns.get(i); j += 1) {
                example.addLast(1);
            }
            Stopwatch sw = new Stopwatch();
            for (int k = 0; k < M; k += 1) {
                example.getLast();
            }
            times.addLast(sw.elapsedTime());
            opCounts.addLast(M);
            example = new SLList<>();
        }
        printTimingTable(Ns, times, opCounts);
=======
>>>>>>> 160747451c147c59d8e3cbf70a7afee2b73bebdb
    }

}
