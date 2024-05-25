package gitlet;

/** 表示Gitlet错误的通用异常。对于致命错误，getMessage()的结果是要打印的错误消息。
 *  作者：P. N. Hilfinger
 */
class GitletException extends RuntimeException {


    /** 没有消息的GitletException。 */
    GitletException() {
        super();
    }

    /** 以MSG作为其消息的GitletException。 */
    GitletException(String msg) {
        super(msg);
    }

}
