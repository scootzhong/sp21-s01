package capers;

import java.io.File;
import java.io.Serializable;
import static capers.Utils.*;

/** 表示可以序列化的狗。
 * @author TODO
 */
public class Dog implements Serializable {

    /** 狗所居住的文件夹。 */
    static final File DOG_FOLDER = Utils.join(CapersRepository.CAPERS_FOLDER, "dogs"); ; // TODO (提示：查看 Utils 中的 `join` 函数)

    /** 狗的年龄。 */
    private int age;
    /** 狗的品种。 */
    private String breed;
    /** 狗的名字。 */
    public String name;

    /**
     * 使用指定的参数创建一个狗对象。
     * @param name 狗的名字
     * @param breed 狗的品种
     * @param age 狗的年龄
     */
    public Dog(String name, String breed, int age) {
        this.age = age;
        this.breed = breed;
        this.name = name;
    }

    /**
     * 从 DOG_FOLDER 中名为 NAME 的文件中读取并反序列化一只狗。
     *
     * @param name 要加载的狗的名字
     * @return 从文件中读取的狗
     */
    public static Dog fromFile(String name) {
        // TODO (提示：查看 Utils 文件)
        File oldDogFile = Utils.join(DOG_FOLDER, name);
        return Utils.readObject(oldDogFile, Dog.class);
    }

    /**
     * 增加一只狗的年龄并庆祝！
     */
    public void haveBirthday() {
        age += 1;
        System.out.println(toString());
        System.out.println("Happy birthday! Woof! Woof!");
    }

    /**
     * 将狗保存到文件以备将来使用。
     */
    public void saveDog() {
        // TODO (提示：不要忘记狗的名字是唯一的)
        // 保存狗对象到文件中，文件名为狗名字
        File dogfile = Utils.join(DOG_FOLDER, name);
        Utils.writeObject(dogfile, this);
    }

    @Override
    public String toString() {
        return String.format(
                "Woof! My name is %s and I am a %s! I am %d years old! Woof!",
                name, breed, age);
    }

}
