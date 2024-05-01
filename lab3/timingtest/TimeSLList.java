package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    public static final int lowerLimitNum = 1000;
    public static final int upperLimitNum = 64000;
    public static final int op = 10000;

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
        // 创建3个列表，分别用来存储数据结构数量N、观测所得时间time和调用次数ops
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        // 循环多次,记录N，time，ops到对应列表
        for(int currentN = lowerLimitNum; currentN <= upperLimitNum; currentN *= 2) {
            Ns.addLast(currentN); // 记录N
            opCounts.addLast(op); // 记录op

            // 创建数量为N的列表
            SLList<Integer> beWatchedList = new SLList<>();
            for(int i = 0; i < currentN; i++) {
                beWatchedList.addLast(999);
            }

            // 计时并记录
            Stopwatch sw = new Stopwatch();
            for(int i = 0; i < op; i++) {
                beWatchedList.addLast(999);
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
        }

        // 调用printTimingTable打印出结果
        System.out.println("Timing table for addLast");
        printTimingTable(Ns, times, opCounts);
    }

}
