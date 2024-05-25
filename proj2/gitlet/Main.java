package gitlet;

/** Gitlet的驱动程序类，是Git版本控制系统的子集。
 *  作者：待定
 */
public class Main {

    /** 用法：java gitlet.Main ARGS，其中ARGS包含
     *  <COMMAND> <OPERAND1> <OPERAND2> ...
     */
    public static void main(String[] args) {
        /*
        不适用于特定命令的故障情况:

        2、如果用户输入不存在的命令，则打印消息 No command with that name exists. 并退出
        3、如果用户输入的命令的操作数数量或格式错误，则打印消息 Incorrect operands. 并退出
        4、如果用户输入的命令需要位于已初始化的 Gitlet 工作目录（即包含 .gitlet 子目录的目录）中
        但不在这样的目录中，则打印消息 Not in an initialized Gitlet directory.

        要立即退出程序，可以调用 System.exit(0) 。例如，如果出现错误时
         */

        // TODO: 如果args为空怎么办？
        // 如果用户未输入任何参数，则打印消息 Please enter a command. 并退出
        if (args.length == 0) {
            System.out.println("Please provide a command.");
            return;
        }
        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                // TODO: 处理 `init` 命令
                validateNumArgs("init", args, 1);
                Repository.initRepository();
                break;
            case "add":
                // TODO: 处理 `add [filename]` 命令
                // 在gitlet中，一次只能添加一个文件
                validateNumArgs("add", args, 2);
                String addFileName = args[1];
                Repository.stageFile(addFileName);
                break;
            case "commit":
                // TODO: 处理 `commit [message]` 命令
                validateNumArgs("commit", args, 2);
                String message = args[1];
                Repository.commit(message);
                break;
            case "rm":
                // TODO: 处理 `rm [filename]` 命令
                validateNumArgs("rm", args, 2);
                String rmFileName = args[1];
                Repository.rmFile(rmFileName);
                break;
            case "log":
                // TODO: 处理 `log` 命令
                validateNumArgs("log", args, 1);
                Repository.checkLog();
                break;
            case "global-log":
                // TODO: 处理 `global-log` 命令
                validateNumArgs("global-log", args, 1);
                Repository.checkGlobalLog();
                break;
            case "find":
                // TODO: 处理 `find [commit message]` 命令
                validateNumArgs("find", args, 2);
                String commitMessage = args[1];
                Repository.findCommit(commitMessage);
                break;
            case "status":
                // TODO: 处理 `status` 命令
                validateNumArgs("status", args, 1);
                Repository.checkStatus();
                break;
            case "checkout":
                // TODO: 处理 `checkout` 命令，包括三种用法
                // checkout -- [file name]
                // checkout [commit id] -- [file name]
                // checkout [branch name]
                break;

        }
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
