package timingtest;
<<<<<<< HEAD

=======
>>>>>>> 160747451c147c59d8e3cbf70a7afee2b73bebdb
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
<<<<<<< HEAD
        AList<Integer> Ns = new AList<>();
        for (int x = 1000; x <= 128000; x = x * 2) {
            Ns.addLast(x);
        }
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        AList<Integer> example = new AList<>();
        for (int i = 0; i < Ns.size(); i += 1) {
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < Ns.get(i); j += 1) {
                example.addLast(1);
            }
            times.addLast(sw.elapsedTime());
            opCounts.addLast(Ns.get(i));
            example = new AList<>();
        }
        printTimingTable(Ns,times,opCounts);
=======
>>>>>>> 160747451c147c59d8e3cbf70a7afee2b73bebdb
    }
}
