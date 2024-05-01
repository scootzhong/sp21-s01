package gh2;

// TODO: 在准备好开始这部分后取消下面的导入注释
import deque.ArrayDeque;
import deque.Deque;
import deque.LinkedListDeque;

import java.util.Map;
// TODO: 也许需要更多的导入

//注意：在完成Deque实现之前，此文件将无法编译
public class GuitarString {
    /** 常量。请勿更改。如果你感兴趣，关键字final
     * 表示这些值在运行时不能被更改。我们将在周五的讲座中讨论这个和
     * 其他话题。 */
    private static final int SR = 44100;      // 采样率
    private static final double DECAY = .996; // 衰减因子

    /* 用于存储声音数据的缓冲区。 */
    private Deque<Double> buffer;

    /* 创建给定频率的吉他弦。  */
    public GuitarString(double frequency) {
        // TODO: 创建一个容量为SR / frequency的缓冲区。你需要
        //       将此除法操作的结果转换为int。为了
        //       更好的精度，在转换之前使用Math.round()函数。
        //       你应该最初用零填充你的缓冲区数组。

        int size = (int) Math.round(SR / frequency);
        buffer = new LinkedListDeque<>();
        for (int i = 0; i < size; i++) {
            buffer.addLast(0.0);
        }
    }


    /* 通过用白噪声替换缓冲区来弹奏吉他弦。 */
    public void pluck() {
        // TODO: 出队缓冲区中的所有内容，并用随机数替换
        //       介于-0.5和0.5之间。你可以通过以下方式获得这样的数值：
        //       double r = Math.random() - 0.5;
        //
        //       确保你的随机数彼此不同。这并不意味着你需要检查这些数字
        //       是否彼此不同。它意味着你应该重复调用
        //       Math.random() - 0.5来为每个数组索引生成新的随机数。
        int size = buffer.size();
        for (int i = 0; i < size; i++) {
            buffer.removeFirst();
            double randomNumber = (Math.random() * 2 - 1) * 0.5;
            buffer.addLast(randomNumber);
        }

    }

    /* 通过执行Karplus-Strong算法的一次迭代来推进模拟的一个时间步。 */
    public void tic() {
        // TODO: 出队前端样本，并入队一个新样本，
        //       其值是这两个样本的平均值乘以DECAY因子。
        //       **不要调用StdAudio.play()。**

        double first = buffer.removeFirst();
        double second = buffer.get(0);
        double newDouble = (first + second) / 2 * DECAY;
        buffer.addLast(newDouble);
    }

    /* 返回缓冲区前面的双精度数值。 */
    public double sample() {
        // TODO: 返回正确的东西。
        return buffer.get(0);
    }
}
// TODO: 在完成时删除所有的TODO注释。







//package gh2;
//
//// TODO: uncomment the following import once you're ready to start this portion
//// import deque.Deque;
//// TODO: maybe more imports
//
////Note: This file will not compile until you complete the Deque implementations
//public class GuitarString {
//    /** Constants. Do not change. In case you're curious, the keyword final
//     * means the values cannot be changed at runtime. We'll discuss this and
//     * other topics in lecture on Friday. */
//    private static final int SR = 44100;      // Sampling Rate
//    private static final double DECAY = .996; // energy decay factor
//
//    /* Buffer for storing sound data. */
//    // TODO: uncomment the following line once you're ready to start this portion
//    // private Deque<Double> buffer;
//
//    /* Create a guitar string of the given frequency.  */
//    public GuitarString(double frequency) {
//        // TODO: Create a buffer with capacity = SR / frequency. You'll need to
//        //       cast the result of this division operation into an int. For
//        //       better accuracy, use the Math.round() function before casting.
//        //       Your should initially fill your buffer array with zeros.
//    }
//
//
//    /* Pluck the guitar string by replacing the buffer with white noise. */
//    public void pluck() {
//        // TODO: Dequeue everything in buffer, and replace with random numbers
//        //       between -0.5 and 0.5. You can get such a number by using:
//        //       double r = Math.random() - 0.5;
//        //
//        //       Make sure that your random numbers are different from each
//        //       other. This does not mean that you need to check that the numbers
//        //       are different from each other. It means you should repeatedly call
//        //       Math.random() - 0.5 to generate new random numbers for each array index.
//    }
//
//    /* Advance the simulation one time step by performing one iteration of
//     * the Karplus-Strong algorithm.
//     */
//    public void tic() {
//        // TODO: Dequeue the front sample and enqueue a new sample that is
//        //       the average of the two multiplied by the DECAY factor.
//        //       **Do not call StdAudio.play().**
//    }
//
//    /* Return the double at the front of the buffer. */
//    public double sample() {
//        // TODO: Return the correct thing.
//        return 0;
//    }
//}
//    // TODO: Remove all comments that say TODO when you're done.
