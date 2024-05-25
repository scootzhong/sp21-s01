package hashmap;

import java.util.*;

/**
 * 一个基于哈希表实现的Map。在最佳情况下，通过get()、remove()和put()方法访问元素的时间复杂度为摊销常数时间。
 * 假设永远不会插入null键，并且在执行remove()操作时不缩小哈希表大小。
 * @author 你的名字
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    public final int INITIAL_SIZE = 16;
    public final double DEFAULT_MAX_LOAD_FACTOR = 0.75;

    /**
     * 返回指定键的哈希表索引。
     * @param key
     */
    public int getIndexHelper(K key) {
        int hash = key.hashCode();
        return Math.floorMod(hash, bucketSize);
    }

    /**
     * 清除此地图中所有的映射。
     */
    @Override
    public void clear() {
        itemSize = 0;
        buckets = createTable(bucketSize);
    }

    /**
     * 如果此地图包含指定键的映射，则返回true。
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        // 计算key的索引
        int index = getIndexHelper(key);

        // 遍历bucket，找到key对应的node
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回指定键所映射的值，如果此地图不包含该键的映射，则返回null。
     * @param key
     */
    @Override
    public V get(K key) {
        // 计算key的索引
        int index = getIndexHelper(key);

        // 遍历bucket，找到key对应的node
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    /**
     * 返回此地图中的键值映射数量。
     */
    @Override
    public int size() {
        return itemSize;
    }

    /**
     * 将指定的值与指定的键关联到此地图中。如果地图之前包含该键的映射，
     * 则替换旧值。
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        // 计算key的索引
        int index = getIndexHelper(key);

        // 迭代遍历bucket，判断key是否已经存在，存在则替换value，否则添加
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        buckets[index].add(createNode(key, value));
        itemSize += 1;

        // 若添加后，负载因子大于阈值，则扩容
        if ((double) itemSize / bucketSize > maxLoadFactor) {
            resize(bucketSize * 2);
        }
    }

    /**
     * 扩容
     */
    private void resize(int newSize) {
        // 创建一个新表
        Collection<Node>[] newBuckets = createTable(newSize);

        // 遍历原表的每个桶，将每个节点添加到新表中
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                int newIndex = Math.floorMod(node.key.hashCode(), newSize);
                newBuckets[newIndex].add(node);
            }
        }
        buckets = newBuckets;
        // 桶数量变成新表大小
        bucketSize = newSize;
    }

    /**
     * 返回此地图中包含的键的Set视图。
     */
    @Override
    public Set<K> keySet() {
        // 创建一个set
        Set<K> keySet = new HashSet<>();

        // 遍历buckets，将key添加到set中
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                keySet.add(node.key);
            }
        }
        return keySet;
    }

    /**
     * 如果存在，则从此地图中删除指定键的映射。这不是Lab 8的要求。
     * 如果你没有实现此功能，抛出UnsupportedOperationException。
     * @param key
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * 如果当前映射到指定值，则仅删除指定键的条目。这不是Lab 8的要求。
     * 如果你没有实现此功能，抛出UnsupportedOperationException。
     * @param key
     * @param value
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    /**
     * 返回类型为{@code T}的元素迭代器。
     * @return 一个迭代器对象。
     */
    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIterator();
    }

    private class MyHashMapIterator implements Iterator<K> {
        private int bucketIndex; // 当前所在的桶的索引
        private Iterator<Node> bucketIterator; // 当前桶的节点迭代器

        public MyHashMapIterator() {
            bucketIndex = 0;
            bucketIterator = getBucketIterator(bucketIndex);
        }

        private Iterator<Node> getBucketIterator(int index) {
            while (index < buckets.length && (buckets[index] == null || buckets[index].isEmpty())) {
                index++;
            }
            if (index < buckets.length) {
                return buckets[index].iterator();
            }
            return null;
        }

        @Override
        public boolean hasNext() {
            if (bucketIterator == null) {
                return false;
            }
            if (bucketIterator.hasNext()) {
                return true;
            }
            bucketIterator = getBucketIterator(++bucketIndex);
            return bucketIterator != null && bucketIterator.hasNext();
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return bucketIterator.next().key;
        }
    }


    /* 实例变量 */
    private Collection<Node>[] buckets;
    private int bucketSize; // 存储键值对的桶的数量（哈希表数组长度）
    private double maxLoadFactor; // 最大负载因子(负载因子达到此值时，哈希表会自动扩容)
    private int itemSize; // 存储键值对的数量

    /** 构造函数 */
    public MyHashMap() {
        bucketSize = INITIAL_SIZE;
        maxLoadFactor = DEFAULT_MAX_LOAD_FACTOR;
        itemSize = 0; // 初始化itemSize
        buckets = createTable(bucketSize);
    }


    public MyHashMap(int initialSize) {
        bucketSize = initialSize;
        maxLoadFactor = DEFAULT_MAX_LOAD_FACTOR;
        itemSize = 0; // 初始化itemSize
        buckets = createTable(bucketSize);
    }


    /**
     * MyHashMap构造函数，创建大小为initialSize的底层数组。
     * 项目数量与桶数量的负载因子应始终小于等于maxLoad。
     * @param initialSize 初始数组大小
     * @param maxLoad 最大负载因子
     */
    public MyHashMap(int initialSize, double maxLoad) {
        bucketSize = initialSize;
        maxLoadFactor = maxLoad;
        itemSize = 0; // 初始化itemSize
        buckets = createTable(bucketSize);
    }


    /**
     * 受保护的辅助类用于存储键/值对
     * 受保护的限定符允许子类访问
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /**
     * 为放置在哈希表桶中创建一个新的节点
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }


    /**
     * 返回一个作为哈希表桶使用的数据结构
     *
     * 哈希表桶的唯一要求是我们能够：
     *  1. 插入项目（`add`方法）
     *  2. 移除项目（`remove`方法）
     *  3. 遍历项目（`iterator`方法）
     *
     * 这些方法都受到java.util.Collection的支持，
     * Java中的大多数数据结构都继承自Collection，因此我们可以几乎使用任何数据结构作为我们的桶。
     * 覆盖这个方法以使用不同的数据结构作为底层桶类型
     * 确保在创建桶数据结构时调用此工厂方法，而不是使用new操作符自行创建！
     */
    protected Collection<Node> createBucket() {
        return new ArrayList<>();
    }


    /**
     * 返回支持我们哈希表的表。如上文注释所述，这个表可以是Collection对象的数组
     * 确保在创建表时调用此工厂方法，以便所有桶类型都是java.util.Collection
     *
     * @param tableSize 要创建的表的大小
     */
    private Collection<Node>[] createTable(int tableSize) {
        // 创建一个数组，并将每个元素设置为一个空的桶
        Collection<Node>[] table = new Collection[tableSize];

        // 遍历数组，将每个元素设置为一个空的桶
        for (int i = 0; i < tableSize; i++) {
            table[i] = createBucket();
        }
        return table;
    }
}

