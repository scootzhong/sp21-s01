package gitlet;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;

/** 杂项工具。
 *
 * 请仔细阅读此文件，因为它提供了几个有用的实用功能，可以节省您一些时间。
 *
 * 作者：P. N. Hilfinger
 */
class Utils {

    /** 完整的SHA-1 UID的长度（十六进制数字）。 */
    static final int UID_LENGTH = 40;

    /* SHA-1 HASH VALUES. */

    /** 返回VALS的串联的SHA-1哈希值，VALS可以是字节数组和字符串的任意混合。 */
    static String sha1(Object... vals) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            for (Object val : vals) {
                if (val instanceof byte[]) {
                    md.update((byte[]) val);
                } else if (val instanceof String) {
                    md.update(((String) val).getBytes(StandardCharsets.UTF_8));
                } else {
                    throw new IllegalArgumentException("improper type to sha1");
                }
            }
            Formatter result = new Formatter();
            for (byte b : md.digest()) {
                result.format("%02x", b);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException excp) {
            throw new IllegalArgumentException("System does not support SHA-1");
        }
    }

    /** 返回VALS中字符串的串联的SHA-1哈希值。 */
    static String sha1(List<Object> vals) {
        return sha1(vals.toArray(new Object[vals.size()]));
    }

    /* FILE DELETION */

    /** 如果FILE存在且不是目录，则删除FILE。如果删除了FILE，则返回true，否则返回false。
     *  除非由FILE指定的目录还包含一个名为.gitlet的目录，否则拒绝删除FILE并引发IllegalArgumentException异常。 */
    static boolean restrictedDelete(File file) {
        if (!(new File(file.getParentFile(), ".gitlet")).isDirectory()) {
            throw new IllegalArgumentException("not .gitlet working directory");
        }
        if (!file.isDirectory()) {
            return file.delete();
        } else {
            return false;
        }
    }

    /** 如果存在名为FILE的文件且不是目录，则删除该文件。如果删除了FILE，则返回true，否则返回false。
     *  除非由FILE指定的目录还包含一个名为.gitlet的目录，否则拒绝删除FILE并引发IllegalArgumentException异常。 */
    static boolean restrictedDelete(String file) {
        return restrictedDelete(new File(file));
    }

    /* READING AND WRITING FILE CONTENTS */

    /** 将FILE的整个内容作为字节数组返回。FILE必须是普通文件。在出现问题时抛出IllegalArgumentException异常。 */
    static byte[] readContents(File file) {
        if (!file.isFile()) {
            throw new IllegalArgumentException("must be a normal file");
        }
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException excp) {
            throw new IllegalArgumentException(excp.getMessage());
        }
    }

    /** 将FILE的整个内容作为字符串返回。FILE必须是普通文件。在出现问题时抛出IllegalArgumentException异常。 */
    static String readContentsAsString(File file) {
        return new String(readContents(file), StandardCharsets.UTF_8);
    }

    /** 将内容CONTENTS的字节连接写入FILE，根据需要创建或覆盖它。CONTENTS中的每个对象可以是字符串或字节数组。
     *  在出现问题时抛出IllegalArgumentException异常。 */
    static void writeContents(File file, Object... contents) {
        try {
            if (file.isDirectory()) {
                throw
                        new IllegalArgumentException("cannot overwrite directory");
            }
            BufferedOutputStream str =
                    new BufferedOutputStream(Files.newOutputStream(file.toPath()));
            for (Object obj : contents) {
                if (obj instanceof byte[]) {
                    str.write((byte[]) obj);
                } else {
                    str.write(((String) obj).getBytes(StandardCharsets.UTF_8));
                }
            }
            str.close();
        } catch (IOException | ClassCastException excp) {
            throw new IllegalArgumentException(excp.getMessage());
        }
    }

    /** 从FILE读取的对象类型为EXPECTEDCLASS，将其转换为T类型并返回。在出现问题时抛出IllegalArgumentException异常。 */
    static <T extends Serializable> T readObject(File file,
                                                 Class<T> expectedClass) {
        try {
            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(file));
            T result = expectedClass.cast(in.readObject());
            in.close();
            return result;
        } catch (IOException | ClassCastException
                 | ClassNotFoundException excp) {
            throw new IllegalArgumentException(excp.getMessage());
        }
    }

    /** 将OBJ写入FILE。 */
    static void writeObject(File file, Serializable obj) {
        writeContents(file, serialize(obj));
    }

    /* DIRECTORIES */

    /** 过滤所有非普通文件。 */
    private static final FilenameFilter PLAIN_FILES =
            new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return new File(dir, name).isFile();
                }
            };

    /** 返回目录DIR中所有普通文件的名称列表，以Java字符串的字典顺序排列。如果DIR不表示目录，则返回null。 */
    static List<String> plainFilenamesIn(File dir) {
        String[] files = dir.list(PLAIN_FILES);
        if (files == null) {
            return null;
        } else {
            Arrays.sort(files);
            return Arrays.asList(files);
        }
    }

    /** 返回目录DIR中所有普通文件的名称列表，以Java字符串的字典顺序排列。如果DIR不表示目录，则返回null。 */
    static List<String> plainFilenamesIn(String dir) {
        return plainFilenamesIn(new File(dir));
    }

    /* OTHER FILE UTILITIES */

    /** 返回FIRST和OTHERS的连接作为File指示器，类似于java.nio.file.Paths.#get(String, String[])方法。 */
    static File join(String first, String... others) {
        return Paths.get(first, others).toFile();
    }

    /** 返回FIRST和OTHERS的连接作为File指示器，类似于java.nio.file.Paths.#get(String, String[])方法。 */
    static File join(File first, String... others) {
        return Paths.get(first.getPath(), others).toFile();
    }


    /* SERIALIZATION UTILITIES */

    /** 返回包含OBJ的序列化内容的字节数组。 */
    static byte[] serialize(Serializable obj) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(stream);
            objectStream.writeObject(obj);
            objectStream.close();
            return stream.toByteArray();
        } catch (IOException excp) {
            throw error("Internal error serializing commit.");
        }
    }



    /* MESSAGES AND ERROR REPORTING */

    /** 返回一个GitletException，其消息由MSG和ARGS组成，如String.format方法。 */
    static GitletException error(String msg, Object... args) {
        return new GitletException(String.format(msg, args));
    }

    /** 打印由MSG和ARGS组成的消息，如String.format方法，后跟换行符。 */
    static void message(String msg, Object... args) {
        System.out.printf(msg, args);
        System.out.println();
    }
}
