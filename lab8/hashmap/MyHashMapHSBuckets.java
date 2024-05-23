package hashmap;

import java.util.HashSet;
import java.util.Collection;

/**
 * 使用HashSet作为桶的哈希表（很神奇！）
 * @author Neil Kulkarni
 */
public class MyHashMapHSBuckets<K, V> extends MyHashMap<K, V> {

    /**
     * 构造函数，创建默认初始大小和负载因子的背景区
     */
    public MyHashMapHSBuckets() {
        super();
    }

    /**
     * 构造函数，创建初始大小为initialSize的背景区和默认负载因子
     *
     * @param initialSize 背景区的初始大小
     */
    public MyHashMapHSBuckets(int initialSize) {
        super(initialSize);
    }

    /**
     * 构造函数，创建初始大小为initialSize的背景区。
     * 负载因子（# items / # buckets）应始终小于等于loadFactor
     *
     * @param initialSize 背景区的初始大小
     * @param maxLoad 最大负载因子
     */
    public MyHashMapHSBuckets(int initialSize, double maxLoad) {
        super(initialSize, maxLoad);
    }

    @Override
    protected Collection<Node> createBucket() {
        return new HashSet<>();
    }
}

