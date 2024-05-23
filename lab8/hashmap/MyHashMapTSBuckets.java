package hashmap;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * 使用TreeSet作为桶的哈希表
 * 树集合中的元素需要可比较，因此我们限制我们的地图只接受可比较的键
 *
 * @author Neil Kulkarni
 */
public class MyHashMapTSBuckets<K extends Comparable<K>, V> extends MyHashMap<K, V> {

    /**
     * 构造函数，创建默认初始大小和负载因子的背景区
     */
    public MyHashMapTSBuckets() {
        super();
    }

    /**
     * 构造函数，创建初始大小为initialSize的背景区和默认负载因子
     *
     * @param initialSize 背景区的初始大小
     */
    public MyHashMapTSBuckets(int initialSize) {
        super(initialSize);
    }

    /**
     * 构造函数，创建初始大小为initialSize的背景区。
     * 负载因子（# items / # buckets）应始终小于等于loadFactor
     *
     * @param initialSize 背景区的初始大小
     * @param maxLoad 最大负载因子
     */
    public MyHashMapTSBuckets(int initialSize, double maxLoad) {
        super(initialSize, maxLoad);
    }

    @Override
    protected Collection<Node> createBucket() {
        // 这是Java的高级特性，用直白的英语解释就是：
        //
        // "创建一个Node的TreeSet，当比较两个Node时，
        //  使用它们的key的compareTo方法进行比较"
        //
        // 因为我们已经在类头定义了K extends Comparable<K>，我们知道键已经实现了compareTo方法
        return new TreeSet<>(Comparator.comparing(a -> a.key));
    }
}

