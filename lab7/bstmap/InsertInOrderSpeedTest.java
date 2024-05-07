package bstmap;

import java.util.HashMap;
import java.util.TreeMap;
import java.io.IOException;
import java.util.Scanner;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * 对三种不同的集合实现进行时间测试。
 * 对于BSTMap，假设<K,V>是<String, Integer>对。
 *
 * 作者：Josh Hug
 * 作者：Brendan Hu
 */
public class InsertInOrderSpeedTest {
    /**
     * 请求用户输入并对三种不同的集合实现执行测试。ARGS未使用。
     */
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        // 从InsertRandomSpeedTest中借用waitForPositiveInt(Scanner input)方法
        InsertRandomSpeedTest i = new InsertRandomSpeedTest();
        System.out.println("This program inserts lexicographically "
                + "increasing Strings into Maps as <String, Integer> pairs.");

        String repeat = "y";
        do {
            System.out.print("\nEnter # strings to insert into the maps: ");
            int N = i.waitForPositiveInt(input);
            timeInOrderMap61B(new ULLMap<>(), N);
            timeInOrderMap61B(new BSTMap<>(), N);
            timeInOrderTreeMap(new TreeMap<>(), N);
            timeInOrderHashMap(new HashMap<>(), N);

            System.out.print("Would you like to try more timed-tests? (y/n): ");
            repeat = input.nextLine();
        } while (!repeat.equalsIgnoreCase("n") && !repeat.equalsIgnoreCase("no"));
        input.close();
    }

    /**
     * 返回将N个字符串按顺序插入到Map61B中所需的时间。
     * 使用StringUtils.nextString(String s)方法
     */
    public static double insertInOrder(Map61B<String, Integer> map61B, int N) {
        Stopwatch sw = new Stopwatch();
        String s = "cat";
        for (int i = 0; i < N; i++) {
            s = StringUtils.nextString(s);
            map61B.put(s, new Integer(i));
        }
        return sw.elapsedTime();
    }

    /**
     * 返回将N个字符串按顺序插入到TreeMap中所需的时间。
     */
    public static double insertInOrder(TreeMap<String, Integer> ts, int N) {
        Stopwatch sw = new Stopwatch();
        String s = "cat";
        for (int i = 0; i < N; i++) {
            s = StringUtils.nextString(s);
            ts.put(s, new Integer(i));
        }
        return sw.elapsedTime();
    }

    public static double insertInOrder(HashMap<String, Integer> ts, int N) {
        Stopwatch sw = new Stopwatch();
        String s = "cat";
        for (int i = 0; i < N; i++) {
            s = StringUtils.nextString(s);
            ts.put(s, new Integer(i));
        }
        return sw.elapsedTime();
    }

    /**
     * 尝试将N个顺序字符串插入到map中，
     * 打印N次插入调用的时间，否则
     * 打印有关错误的详细信息
     */
    public static void timeInOrderMap61B(Map61B<String, Integer> map, int N) {
        try {
            double mapTime = insertInOrder(map, N);
            System.out.printf(map.getClass() + ": %.2f sec\n", mapTime);
        } catch (StackOverflowError e) {
            printInfoOnStackOverflow(N);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * 尝试将N个顺序字符串插入到TreeMap中，
     * 打印N次插入调用的时间，否则
     * 打印有关错误的详细信息
     */
    public static void timeInOrderTreeMap(TreeMap<String, Integer> treeMap, int N) {
        try {
            double javaTime = insertInOrder(treeMap, N);
            System.out.printf("Java's Built-in TreeMap: %.2f sec\n", javaTime);
        } catch (StackOverflowError e) {
            printInfoOnStackOverflow(N);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * 尝试将N个顺序字符串插入到HashMap中，
     * 打印N次插入调用的时间，否则
     * 打印有关错误的详细信息
     */
    public static void timeInOrderHashMap(HashMap<String, Integer> hashMap, int N) {
        try {
            double javaTime = insertInOrder(hashMap, N);
            System.out.printf("Java's Built-in HashMap: %.2f sec\n", javaTime);
        } catch (StackOverflowError e) {
            printInfoOnStackOverflow(N);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /* ---------------------- Private methods ---------------------- */

    /**
     * 在捕获StackOverflowError后调用
     * 打印错误以及相应的N和L
     */
    private static void printInfoOnStackOverflow(int N) {
        System.out.println("--Stack Overflow -- couldn't add "
                + N + " strings.");
    }

}
