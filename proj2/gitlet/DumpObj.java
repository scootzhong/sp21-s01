package gitlet;

import java.io.File;

/** 一个调试类，其主程序可以按以下方式调用：
 *      java gitlet.DumpObj FILE...
 *  其中每个FILE都是由Utils.writeObject（或包含序列化对象的任何文件）产生的文件。 这将简单地读取FILE，对其进行反序列化，并在结果对象上调用dump方法。
 *  对象必须实现gitlet.Dumpable接口才能使此功能正常工作。 例如，您可以像这样定义您的类：
 *
 *        import java.io.Serializable;
 *        import java.util.TreeMap;
 *        class MyClass implements Serializeable, Dumpable {
 *            ...
 *            @Override
 *            public void dump() {
 *               System.out.printf("size: %d%nmapping: %s%n", _size, _mapping);
 *            }
 *            ...
 *            int _size;
 *            TreeMap<String, String> _mapping = new TreeMap<>();
 *        }
 *
 *  如图所示，您的dump方法应该从您的类的对象中打印有用的信息。
 *  作者：P. N. Hilfinger
 */
public class DumpObj {

    /** 反序列化并对FILES中的每个文件的内容应用dump。 */
    public static void main(String... files) {
        for (String fileName : files) {
            Dumpable obj = Utils.readObject(new File(fileName),
                    Dumpable.class);
            obj.dump();
            System.out.println("---");
        }
    }
}
