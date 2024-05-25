package gitlet;

import java.io.File;
import static gitlet.Utils.*;

// TODO: 你在这里需要的任何导入

/** 表示一个Gitlet存储库。
 *  TODO: 最好在这里描述一下这个类在高层次上还做了什么。
 *
 *  作者：待定
 */
public class Repository {
    /**
     * TODO: 在这里添加实例变量。
     *
     * 在这里列出Repository类的所有实例变量，并在它们上面添加有用的注释，描述该变量代表什么以及该变量如何使用。我们为您提供了两个示例。
     */

    /** 当前工作目录。 */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** .gitlet目录。 */
    public static final File GITLET_DIR = join(CWD, ".gitlet");

    /* TODO: 填充这个类的其余部分。 */


    /**
     * 执行 Gitlet 版本控制系统初始化操作
     */
    public static void initRepository() {
        // TODO：完成这个静态方法
        return;
    }


    /**
     * 执行add暂存文件操作
     */
    public static void stageFile(String addFileName) {
        // TODO：完成这个静态方法
        return;
    }


    /**
     * 执行commit 提交操作
     */
    public static void commit(String message) {
        // TODO：完成这个静态方法
        return;
    }


    /**
     * 执行rm 操作
     */
    public static void rmFile(String rmFileName) {
        // TODO：完成这个静态方法
        return;
    }


    /**
     * 执行log 操作
     */
    public static void checkLog() {
        // TODO：完成这个静态方法
        return;
    }


    /**
     * 执行global-log 操作
     */
    public static void checkGlobalLog() {
        // TODO：完成这个静态方法
        return;
    }


    /**
     * 执行find 操作
     */
    public static void findCommit(String commitMessage) {
        // TODO：完成这个静态方法
        return;
    }


    /**
     * status 操作
     */
    public static void checkStatus() {
        // TODO：完成这个静态方法
        return;
    }
}
