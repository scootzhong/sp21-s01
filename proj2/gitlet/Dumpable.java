package gitlet;

import java.io.Serializable;

/** 一个描述可转储对象的接口。
 * 作者：P. N. Hilfinger
 */
interface Dumpable extends Serializable {
    /** 在System.out上打印有关此对象的有用信息。 */
    void dump();
}
