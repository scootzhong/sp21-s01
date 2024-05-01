package gh2;

/* 从 edu.princeton.cs.introcs 包导入所需的音频库。 */

import edu.princeton.cs.introcs.StdAudio;

import org.junit.Test;
import static org.junit.Assert.*;

/** 测试 GuitarString 类。作者：Josh Hug */
public class TestGuitarString  {

    @Test
    public void testPluckTheAString() {
        GuitarString aString = new GuitarString(GuitarHeroLite.CONCERT_A);
        aString.pluck();
        for (int i = 0; i < 50000; i += 1) {
            StdAudio.play(aString.sample());
            aString.tic();
        }
    }

    @Test
    public void testSample() {
        GuitarString s = new GuitarString(100);
        assertEquals(0.0, s.sample(), 0.0);
        assertEquals(0.0, s.sample(), 0.0);
        assertEquals(0.0, s.sample(), 0.0);
        s.pluck();
        double sample = s.sample();
        assertNotEquals("弹奏后，样本不应为 0。", 0.0, sample);

        assertEquals("样本不应改变弦的状态。", sample, s.sample(), 0.0);
        assertEquals("样本不应改变弦的状态。", sample, s.sample(), 0.0);
    }


    @Test
    public void testTic() {
        GuitarString s = new GuitarString(100);
        assertEquals(0.0, s.sample(), 0.0);
        assertEquals(0.0, s.sample(), 0.0);
        assertEquals(0.0, s.sample(), 0.0);
        s.pluck();
        double sample1 = s.sample();
        assertNotEquals("弹奏后，样本不应为 0。", 0.0, sample1);

        s.tic();
        assertNotEquals("调用tic() 后，样本不应保持不变。", sample1, s.sample());
    }


    @Test
    public void testTicCalculations() {
        // 创建频率为 11025 的 GuitarString，长度为 4 的双端队列。
        GuitarString s = new GuitarString(11025);
        s.pluck();

        // 记录前四个值，同时执行 tic 操作。
        double s1 = s.sample();
        s.tic();
        double s2 = s.sample();
        s.tic();
        double s3 = s.sample();
        s.tic();
        double s4 = s.sample();

        // 如果我们再执行一次 tic，结果应该等于 0.996*0.5*(s1 + s2)
        s.tic();

        double s5 = s.sample();
        double expected = 0.996 * 0.5 * (s1 + s2);

        // 使用容差为 0.001 检查新样本是否正确。
        // 请参阅 JUnit 文档，了解 assertEquals(double, double) 如何使用容差。
        assertEquals("错误的 tic 值。请尝试运行 testTic 方法。", expected, s5, 0.001);
    }
}





//package gh2;
//
///* Imports the required audio library from the
// * edu.princeton.cs.introcs package. */
//import edu.princeton.cs.introcs.StdAudio;
//
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///** Tests the GuitarString class.
// *  @author Josh Hug
// */
//public class TestGuitarString  {
//
//    @Test
//    public void testPluckTheAString() {
//        GuitarString aString = new GuitarString(GuitarHeroLite.CONCERT_A);
//        aString.pluck();
//        for (int i = 0; i < 50000; i += 1) {
//            StdAudio.play(aString.sample());
//            aString.tic();
//        }
//    }
//
//    @Test
//    public void testSample() {
//        GuitarString s = new GuitarString(100);
//        assertEquals(0.0, s.sample(), 0.0);
//        assertEquals(0.0, s.sample(), 0.0);
//        assertEquals(0.0, s.sample(), 0.0);
//        s.pluck();
//        double sample = s.sample();
//        assertNotEquals("After plucking, your samples should not be 0.", 0.0, sample);
//
//        assertEquals("Sample should not change the state of your string.", sample, s.sample(), 0.0);
//        assertEquals("Sample should not change the state of your string.", sample, s.sample(), 0.0);
//    }
//
//
//    @Test
//    public void testTic() {
//        GuitarString s = new GuitarString(100);
//        assertEquals(0.0, s.sample(), 0.0);
//        assertEquals(0.0, s.sample(), 0.0);
//        assertEquals(0.0, s.sample(), 0.0);
//        s.pluck();
//        double sample1 = s.sample();
//        assertNotEquals("After plucking, your samples should not be 0.", 0.0, sample1);
//
//        s.tic();
//        assertNotEquals("After tic(), your samples should not stay the same.", sample1, s.sample());
//    }
//
//
//    @Test
//    public void testTicCalculations() {
//        // Create a GuitarString of frequency 11025, which
//        // is a Deque of length 4.
//        GuitarString s = new GuitarString(11025);
//        s.pluck();
//
//        // Record the front four values, ticcing as we go.
//        double s1 = s.sample();
//        s.tic();
//        double s2 = s.sample();
//        s.tic();
//        double s3 = s.sample();
//        s.tic();
//        double s4 = s.sample();
//
//        // If we tic once more, it should be equal to 0.996*0.5*(s1 + s2)
//        s.tic();
//
//        double s5 = s.sample();
//        double expected = 0.996 * 0.5 * (s1 + s2);
//
//        // Check that new sample is correct, using tolerance of 0.001.
//        // See JUnit documentation for a description of how tolerances work
//        // for assertEquals(double, double)
//        assertEquals("Wrong tic value. Try running the testTic method.", expected, s5, 0.001);
//    }
//}
//
