package deque;

import com.sun.source.tree.AssertTree;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.LinkedList;

public class TimeLinkedListDeque {

    public static final int lowerLimitNum = 1000;
    public static final int upperLimitNum = 10000000;

    /** 打印出结果表 */
    private static void printTimingTable(LinkedList<Integer> Ns, LinkedList<Double> times, LinkedList<Integer> opCounts) {
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
        LinkedList<Integer> Ns = new LinkedList<>();
        LinkedList<Double> times = new LinkedList<>();
        LinkedList<Integer> opCounts = new LinkedList<>();
        LinkedListDeque<Integer> beWatchedList = new LinkedListDeque<>();

        // 创建起始数据结构，即数量为1000（lowerLimitNum）的列表
        for(int i = 0; i < lowerLimitNum; i++) {
            beWatchedList.addFirst(999);
        }

        // 循环多次,记录N，time，ops到对应列表
        for(int currentN = lowerLimitNum; currentN <= upperLimitNum; currentN *= 2) {
            Ns.addFirst(currentN);
            int callNum = currentN;
            opCounts.addFirst(callNum);

            Stopwatch sw = new Stopwatch();
            for(int i = 0; i < callNum; i++) {
                beWatchedList.addFirst(999);
            }
            double timeInSeconds = sw.elapsedTime();
            times.addFirst(timeInSeconds);
        }

        // 调用printTimingTable打印出结果
        System.out.println("Timing table for addFirst");
        printTimingTable(Ns, times, opCounts);

    }

    @Test
    public void testIterator() {
        LinkedListDeque<Integer> a = new LinkedListDeque<>();
        a.addFirst(1);
        a.addLast(2);
        a.addLast(3);
        for (int item : a) {
            System.out.println(item);
        }
    }

    @Test
    public void testEquals() {
        LinkedListDeque<Integer> a = new LinkedListDeque<>();
        a.addFirst(1);
        a.addLast(2);
        a.addLast(3);

        LinkedListDeque<Integer> b = new LinkedListDeque<>();
        b.addFirst(1);
        b.addLast(2);
        b.addLast(3);

        LinkedListDeque<Integer> c = new LinkedListDeque<>();
        c.addFirst(1);
        c.addLast(2);

        assertTrue(a.equals(b));
        assertFalse(a.equals(c));
    }
}
