package bstmap;

import java.util.regex.Pattern;
import java.util.Random;

/** 字符串的实用函数。
 *  作者：Josh Hug
 */
public class StringUtils {
    /** 使风格检查器安静。 */
    private static final int ALPHABET_SIZE = 26;

    /** 此类的随机数生成器。 */
    private static Random r = new Random();

    /** 将随机种子设置为L，以便randomString的结果是可预测的。*/
    public static void setSeed(long l) {
        r = new Random(l);
    }

    /** 返回长度为LENGTH的下一个随机字符串。 */
    public static String randomString(int length) {
        char[] someChars = new char[length];
        for (int i = 0; i < length; i++) {
            someChars[i] = (char) (r.nextInt(ALPHABET_SIZE) + 'a');
        }
        return new String(someChars);
    }

    /** 如果字符串S仅由字符'a'到'z'组成，则返回true。不允许空格、数字、大写字母或任何其他字符。 */
    public static boolean isLowerCase(String s) {
        return Pattern.matches("[a-z]*", s);
    }

    /** 返回字母表顺序中紧接在S之后的字符串。
     * 例如，如果s是'potato'，则此方法将返回'potatp'。如果
     * 最后一个字符是z，则我们加到下一个位置，依此类推。
     */
    public static String nextString(String s) {
        /* 将所有的z作为特殊情况处理，以保持辅助方法的简单性。 */
        if (isAllzs(s)) {
            return allAs(s.length() + 1);
        }
        char[] charVersion = s.toCharArray();
        incrementCharArray(charVersion, charVersion.length - 1);
        return new String(charVersion);
    }

    /** nextString的辅助函数。将X的P位置增加一，如果P == 'z'，则循环到'a'。如果发生循环，我们需要进行进位，然后递增位置P - 1。
     * 对于只包含zs的字符数组将失败。
     */
    private static void incrementCharArray(char [] x, int p) {
        if (x[p] != 'z') {
            x[p] += 1;
        } else {
            x[p] = 'a';
            incrementCharArray(x, p - 1);
        }
    }

    /** 返回长度为LEN的所有'a'字符串。 */
    private static String allAs(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append('a');
        }
        return sb.toString();
    }

    /** 如果S全是'z'，则返回true。对于空字符串返回false */
    public static boolean isAllzs(String s) {
        return Pattern.matches("[z]+", s);
    }

}
