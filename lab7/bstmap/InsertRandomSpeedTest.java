package bstmap;

import java.util.HashMap;
import java.util.TreeMap;
import java.io.IOException;
import java.util.Scanner;
import edu.princeton.cs.algs4.Stopwatch;

/** 对三种不同的集合实现进行时间测试。
 *  作者：Josh Hug
 *  作者：Brendan Hu
 */
public class InsertRandomSpeedTest {
    /**
     请求用户输入并对三种不同的集合实现执行测试。ARGS未使用。
     */
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("This program inserts random "
                + "Strings of length L "
                + "into different types of maps "
                + "as <String, Integer> pairs.");
        System.out.print("Please enter desired length of each string: ");
        int L = waitForPositiveInt(input);

        String repeat = "y";
        do {
            System.out.print("\nEnter # strings to insert into the maps: ");
            int N = waitForPositiveInt(input);
            timeRandomMap61B(new ULLMap<>(), N, L);
            timeRandomMap61B(new BSTMap<>(), N, L);
            timeRandomTreeMap(new TreeMap<>(), N, L);
            timeRandomHashMap(new HashMap<>(), N, L);

            System.out.print("Would you like to try more timed-tests? (y/n)");
            repeat = input.nextLine();
        } while (!repeat.equalsIgnoreCase("n") && !repeat.equalsIgnoreCase("no"));
        input.close();
    }

    /** 返回将N个长度为L的随机字符串插入到Map61B中所需的时间。 */
    public static double insertRandom(Map61B<String, Integer> map61B, int N, int L) {
        Stopwatch sw = new Stopwatch();
        String s = "cat";
        for (int i = 0; i < N; i++) {
            s = StringUtils.randomString(L);
            map61B.put(s, new Integer(i));
        }
        return sw.elapsedTime();
    }

    /** 返回将N个长度为L的随机字符串插入到TreeMap中所需的时间。 */
    public static double insertRandom(TreeMap<String, Integer> treeMap, int N, int L) {
        Stopwatch sw = new Stopwatch();
        String s = "cat";
        for (int i = 0; i < N; i++) {
            s = StringUtils.randomString(L);
            treeMap.put(s, new Integer(i));
        }
        return sw.elapsedTime();
    }

    /** 返回将N个长度为L的随机字符串插入到HashMap中所需的时间。 */
    public static double insertRandom(HashMap<String, Integer> treeMap, int N, int L) {
        Stopwatch sw = new Stopwatch();
        String s = "cat";
        for (int i = 0; i < N; i++) {
            s = StringUtils.randomString(L);
            treeMap.put(s, new Integer(i));
        }
        return sw.elapsedTime();
    }

    /**
     尝试将N个长度为L的随机字符串插入到map中，
     打印N次插入调用的时间，否则
     打印有关错误的详细信息
     */
    public static void timeRandomMap61B(Map61B<String, Integer> map, int N, int L) {
        try {
            double mapTime = insertRandom(map, N, L);
            System.out.printf(map.getClass() + ": %.2f sec\n", mapTime);
        } catch (StackOverflowError e) {
            printInfoOnStackOverflow(N, L);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     尝试将N个长度为L的随机字符串插入到TreeMap中，
     打印N次插入调用的时间，否则
     打印有关错误的详细信息
     */
    public static void timeRandomTreeMap(TreeMap<String, Integer> treeMap, int N, int L) {
        try {
            double javaTime = insertRandom(treeMap, N, L);
            System.out.printf("Java's Built-in TreeMap: %.2f sec\n", javaTime);
        } catch (StackOverflowError e) {
            printInfoOnStackOverflow(N, L);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     尝试将N个长度为L的随机字符串插入到HashMap中，
     打印N次插入调用的时间，否则
     打印有关错误的详细信息
     */
    public static void timeRandomHashMap(HashMap<String, Integer> hashMap, int N, int L) {
        try {
            double javaTime = insertRandom(hashMap, N, L);
            System.out.printf("Java's Built-in HashMap: %.2f sec\n", javaTime);
        } catch (StackOverflowError e) {
            printInfoOnStackOverflow(N, L);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     等待Scanner的另一侧的用户输入一个正整数，
     并输出该整数
     */
    public static int waitForPositiveInt(Scanner input) {
        int ret = 0;
        do {
            while (!input.hasNextInt()) {
                errorBadIntegerInput();
                input.next();
            }
            ret = input.nextInt();
            input.nextLine(); //consume not taken by nextInt()
        } while (ret <= 0);
        return ret;
    }
    /* ------------------------------- Private methods ------------------------------- */
    /**
     在捕获StackOverflowError后调用
     打印错误以及相应的N和L
     */
    private static void printInfoOnStackOverflow(int N, int L) {
        System.out.println("--Stack Overflow -- couldn't add " + N
                + " strings of length " + L + ".");
    }

    /** 为用户提供有关错误输入的友好消息 */
    private static void errorBadIntegerInput() {
        System.out.print("Please enter a positive integer: ");
    }

}
