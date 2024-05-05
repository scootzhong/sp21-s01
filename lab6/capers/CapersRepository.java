package capers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static capers.Utils.*;

/** Capers 的存储库
 * @author TODO
 * Capers 存储库的结构如下：
 *
 * .capers/ -- 您的 lab12 文件夹中所有持久数据的顶级文件夹
 *    - dogs/ -- 包含所有狗的持久数据的文件夹
 *    - story -- 包含当前故事的文件
 *
 * TODO：如果做了其他不同的事情，请更改上面的结构。
 */
public class CapersRepository {
    /** 当前工作目录。 */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** 主元数据文件夹。 */
    static final File CAPERS_FOLDER = Utils.join(CWD, ".capers"); // TODO 提示：查看 Utils 中的 `join` 函数

    /** story工作目录 */
    static final File STORY_FILE  = Utils.join(CAPERS_FOLDER, "story");

    /**
     * 执行必要的文件系统操作以实现持久性。
     * （创建任何必要的文件夹或文件）
     * 记住：推荐的结构（您不必遵循）：
     *
     * .capers/ -- 存放在您的 lab12 文件夹中的所有持久数据的顶级文件夹
     *    - dogs/ -- 包含所有狗的持久数据的文件夹
     *    - story -- 包含当前故事的文件
     */
    public static void setupPersistence() {
        // 创建文件夹.capers/
        try {
            CAPERS_FOLDER.mkdir(); // 目录存在时，则不做任何操作
        }
        catch (SecurityException e) {
            throw error("Can not create the Capers dictionary");
        }

        // 创建子文件夹- dogs/
        try {
            Dog.DOG_FOLDER.mkdir();
        }
        catch (SecurityException e) {
            throw error("Can not create the Dogs dictionary");
        }

        // 创建文件- story
        try {
            STORY_FILE.createNewFile();
        }
        catch (IOException e) {
            throw error("Can not create the story file");
        }
    }

    /**
     * 将 args 中的第一个非命令参数附加到名为 `story` 的文件中，
     * 该文件位于 .capers 目录中。
     * 打印出故事文件
     * @param text 要附加到故事中的文本字符串
     */
    public static void writeStory(String text) {
        // 创建文件系统操作以实现持久性
        CAPERS_FOLDER.mkdir();

        // 将text写入.capers目录中的story.txt文件中
        String oldContent = Utils.readContentsAsString(STORY_FILE);
        Utils.writeContents(STORY_FILE, oldContent, text, "\n");

        // 打印出新的故事内容
        System.out.println(Utils.readContentsAsString(STORY_FILE));
    }

    /**
     * 使用 args 的前三个非命令参数（名字、品种、年龄）创建并持久保存一只狗。
     * 还使用 toString() 打印出狗的信息。
     */
    public static void makeDog(String name, String breed, int age) {
        // 创建文件系统操作以实现持久性
        CAPERS_FOLDER.mkdir();

        // 创建一只狗对象
        Dog newDog = new Dog(name, breed, age);

        // 将狗对象保存到文件
        newDog.saveDog();

        // 打印出狗信息
        System.out.println(newDog.toString());
    }

    /**
     * 永久提前一只狗的年龄，并打印出庆祝消息。
     * 还使用 toString() 打印出狗的信息。
     * 根据 args 的第一个非命令参数选择要提前的狗。
     * @param name 要庆祝生日的狗的名字字符串
     */
    public static void celebrateBirthday(String name) {
        // 反序列化狗对象（假设name的狗已存在）
        Dog oldDog = Dog.fromFile(name);

        // 增加狗的年龄并庆祝
        oldDog.haveBirthday();

        // 将新的狗信息，保存到文件
        oldDog.saveDog();
    }
}
