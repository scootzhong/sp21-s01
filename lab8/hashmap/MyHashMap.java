package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;


/**
 * 一个基于哈希表的Map实现。在最佳情况下，提供常量时间访问元素的能力，
 * 通过get()、remove()和put()方法实现平均摊还成本。
 * 假设不会插入null键，并且在移除时不会缩小容量。
 * @author 强子
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * 清除此地图中所有的映射。
     */
    @Override
    public void clear() {

    }

    /**
     * 如果此地图包含指定键的映射，则返回true。
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        return false;
    }

    /**
     * 返回指定键所映射的值，如果此地图不包含该键的映射，则返回null。
     *
     * @param key
     */
    @Override
    public V get(K key) {
        return null;
    }

    /**
     * 返回此地图中的键值映射数量。
     */
    @Override
    public int size() {
        return 0;
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

    }

    /**
     * 返回此地图中包含的键的Set视图。
     */
    @Override
    public Set<K> keySet() {
        return null;
    }

    /**
     * 如果存在，则从此地图中删除指定键的映射。这不是Lab 8的要求。
     * 如果你没有实现此功能，抛出UnsupportedOperationException。
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        return null;
    }

    /**
     * 如果当前映射到指定值，则仅删除指定键的条目。这不是Lab 8的要求。
     * 如果你没有实现此功能，抛出UnsupportedOperationException。
     *
     * @param key
     * @param value
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("woo!");
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return null;
    }

    /**
     * 受保护的辅助类，用于存储键值对
     * 使用受保护的限定符允许子类访问
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }


    /* 实例变量 */
    private Collection<Node>[] buckets; // 哈希表的桶的数组,Node 是我们提供的一个私有帮助器类，用于存储单个键值映射
    private int size; // 键值对数量
    private int tableSize; // 数组的大小


    /** 构造器 */
    public MyHashMap() {
        size = 0;
        buckets = createTable(16);
        tableSize = 16;
    }

    public MyHashMap(int initialSize) {}

    /**
     * MyHashMap构造函数，创建初始大小为initialSize的背景区。
     * 负载因子（# items / # buckets）应始终小于等于maxLoad
     *
     * @param initialSize 背景数组的初始大小
     * @param maxLoad 最大负载因子
     */
    public MyHashMap(int initialSize, double maxLoad) {}

    /**
     * 返回一个新的节点，放置在哈希表桶中
     */
    private Node createNode(K key, V value) {
        return null;
    }


    /**
     * 返回一个数据结构作为哈希表桶
     *
     * 哈希表桶的唯一要求是：
     * 1. 插入元素（`add` 方法）
     * 2. 移除元素（`remove` 方法）
     * 3. 遍历元素（`iterator` 方法）
     *
     * 这些方法都由java.util.Collection支持，
     * 大多数Java数据结构都继承自Collection，所以我们几乎可以使用任何数据结构作为桶。
     *
     * 重写此方法以使用不同的数据结构作为底层桶类型
     *
     * 确保在创建自己的桶数据结构时不直接使用NEW运算符，而是调用此工厂方法！
     */
    protected Collection<Node> createBucket() {
        return null;
    }


    /**
     * 返回一个用于我们哈希表的表。如上面的注释所述，
     * 这个表可以是一个Collection对象的数组
     * 在创建表时确保调用此工厂方法，以便所有桶类型都是JAVA.UTIL.COLLECTION
     * @param tableSize 要创建的表的大小
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new LinkedList[tableSize];
    }

}
