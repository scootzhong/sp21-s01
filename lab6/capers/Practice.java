package capers;

import java.io.*;

public class Practice {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File outputfile = new File("dump.txt");
        Utils.writeContents(outputfile, "hello world!");
        System.out.println(Utils.readContentsAsString(outputfile));

        Dog adog = new Dog("wangcai", "tianyuanquan", 10);
        File outputfile1 = new File("dog.txt");
//        ObjectOutputStream out =
//                new ObjectOutputStream(new FileOutputStream(outputfile1));
//        out.writeObject(adog);
//        out.close();
        Utils.writeObject(outputfile1, adog);

//        ObjectInputStream inp =
//                new ObjectInputStream(new FileInputStream(outputfile1));
//        Dog bdog = (Dog) inp.readObject();
//        inp.close();
        Dog bdog = Utils.readObject(outputfile1, Dog.class);
        System.out.println(bdog.name);

//
//        File outputdir = new File("dump");
//        outputdir.mkdir();
    }

}
