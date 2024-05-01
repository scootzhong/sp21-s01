package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    public static final int lowerLimitNum = 1000;
    public static final int upperLimitNum = 100000000;

    /** 打印出结果表 */
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
        // 创建新的AList对象，分别用于存储的N、记录的time和ops
        // 从1000到12800（不要用魔法数字），调用stopwatch函数计算时间和结果
        // 将对应的N、记录的time和ops，存到AList对应的索引内
        // 调用printTimingTable打印出结果

        // 创建4个列表，分别用来存储数据结构数量N、观测所得时间time和调用次数ops，以及用于被观察时间的列表
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        AList<Integer> beWatchedList = new AList<>();

        // 创建起始数据结构，即数量为1000（lowerLimitNum）的列表
        for(int i = 0; i < lowerLimitNum; i++) {
            beWatchedList.addLast(999);
        }

        // 循环多次,记录N，time，ops到对应列表
        for(int currentN = lowerLimitNum; currentN <= upperLimitNum; currentN *= 2) {
            Ns.addLast(currentN);
            int callNum = currentN;
            opCounts.addLast(callNum);

            Stopwatch sw = new Stopwatch();
            for(int i = 0; i < callNum; i++) {
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
