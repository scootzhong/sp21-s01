package capers;

import java.io.File;
import java.util.Arrays;

import static capers.Utils.*;

/** 犬狗冒险：Gitlet 前奏。
 * @author TODO
 */
public class Main {
    /**
     * 运行三种命令之一：
     * story [文本] -- 将“文本”+换行附加到 .capers 目录中的故事文件中。另外，打印出当前故事。
     *
     * dog [名字] [品种] [年龄] -- 使用指定的参数持久地创建一只狗；还应该打印出狗的 toString()。假设狗的名字是唯一的。
     *
     * birthday [名字] -- 永久提前一只狗的年龄，并打印出庆祝消息。
     *
     * 所有持久数据应存储在当前工作目录中的“.capers”目录中。
     *
     * 推荐的结构（您不必遵循）：
     *
     * *不应手动创建这些，
     *  您的程序应该创建这些文件夹/文件*
     *
     * .capers/ -- 存放在您的 lab12 文件夹中的所有持久数据的顶级文件夹
     *    - dogs/ -- 包含所有狗的持久数据的文件夹
     *    - story -- 包含当前故事的文件
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            Utils.exitWithError("Must have at least one argument");
        }

        CapersRepository.setupPersistence();
        String text;
        switch (args[0]) {
            case "story":
                // 将“文本”+换行符（即 "\n" ）附加到 .capers 目录中的故事文件中。
                // 此外，打印出当前故事（当前故事应包括最近添加的“文本”）。
                validateNumArgs("story", args, 2);
                text = args[1];
                CapersRepository.writeStory(text);
                break;
            case "dog":
                // 持久创建指定参数的狗；
                // 还应该打印狗的 toString() 。
                // 假设狗的名字是唯一的。
                validateNumArgs("dog", args, 4);
                String name = args[1];
                String breed = args[2];
                int age = Integer.parseInt(args[3]);
                CapersRepository.makeDog(name, breed, age);
                break;
            case "birthday":
                validateNumArgs("birthday", args, 2);
                String dogName = args[1];
                CapersRepository.celebrateBirthday(dogName);
                break;
            default:
                exitWithError(String.format("Unknown command: %s", args[0]));
        }
        return;
    }

    /**
     * 检查参数数量与预期数量是否匹配，
     * 如果不匹配，则抛出 RuntimeException。
     *
     * @param cmd 您正在验证的命令的名称
     * @param args 命令行参数数组
     * @param n 预期参数的数量
     */
    public static void validateNumArgs(String cmd, String[] args, int n) {
        if (args.length != n) {
            throw new RuntimeException(
                    String.format("Invalid number of arguments for: %s.", cmd));
        }
    }
}
