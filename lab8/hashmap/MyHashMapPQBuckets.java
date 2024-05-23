package hashmap;

import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 使用优先队列作为桶的哈希表
 * 优先队列中的元素需要可比较，因此我们限制我们的地图只接受可比较的键
 *
 * @author Neil Kulkarni
 */
public class MyHashMapPQBuckets<K extends Comparable<K>, V> extends MyHashMap<K, V> {

    /**
     * 构造函数，创建默认初始大小和负载因子的背景区
     */
    public MyHashMapPQBuckets() {
        super();
    }

    /**
     * 构造函数，创建初始大小为initialSize的背景区和默认负载因子
     *
     * @param initialSize 背景区的初始大小
     */
    public MyHashMapPQBuckets(int initialSize) {
        super(initialSize);
    }

    /**
     * 构造函数，创建初始大小为initialSize的背景区。
     * 负载因子（# items / # buckets）应始终小于等于loadFactor
     *
     * @param initialSize 背景区的初始大小
     * @param maxLoad 最大负载因子
     */
    public MyHashMapPQBuckets(int initialSize, double maxLoad) {
        super(initialSize, maxLoad);
    }

    @Override
    protected Collection<Node> createBucket() {
        // 这是Java的高级特性，用直白的英语解释就是：
        //
        // "创建一个Node的优先队列，当比较两个Node时，
        //  使用它们的key的compareTo方法进行比较"
        //
        // 由于我们在类头定义了K extends Comparable<K>，我们知道键已经实现了compareTo方法
        return new PriorityQueue<>(Comparator.comparing(a -> a.key));
    }
}

