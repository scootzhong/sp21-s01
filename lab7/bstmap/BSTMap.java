package bstmap;

import java.util.Iterator;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private BSTNode<K, V> sent;
    private int size;


    /**
     * 构造函数，创建空BSTMap
     */
    public BSTMap() {
        sent = null;
        size = 0;
    }


    /**
     * 私有嵌套类BSTNode，用于存储K-V
     */
    private static class BSTNode<K, V> {
        public K key;
        public V value;
        public BSTNode<K, V> left;
        public BSTNode<K, V> right;

        public BSTNode(K k, V v) {
            key = k;
            value = v;
            left = null;
            right = null;
        }
    }


    /**
     * 清除此映射中的所有映射。
     */
    @Override
    public void clear() {
        sent = null;
        size = 0;
    }


    /**
     * 如果此映射包含指定键的映射，则返回true。
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(sent, key);
    }


    /**
     * containsKeyHepler用于帮助containsKey
     */
    private boolean containsKeyHelper(BSTNode<K, V> bstNode, K key) {
        // 如果是null，则返回false
        if (bstNode == null) {
            return false;
        }

        // 如果bstNode.key equal key，则返回true
        if (key.compareTo(bstNode.key) == 0) {
            return true;
        } else if (key.compareTo(bstNode.key) > 0) { // 如果bstNode.key > key，则递归右边。否则递归左边
            return containsKeyHelper(bstNode.right, key);
        }
        else {
            return containsKeyHelper(bstNode.left, key);
        }
    }


    /**
     * 返回指定键映射到的值，如果此映射不包含键的映射，则返回null。
     * @param key
     */
    @Override
    public V get(K key) {
        return getHelper(sent, key);
    }


    /**
     * getHelper用于帮助get方法进行递归
     */
    private V getHelper(BSTNode<K, V> bstNode, K key) {
        // 如果是null，则返回null
        if (bstNode == null) {
            return null;
        }

        // 如果bstNode.key equal key，则返回value
        if (key.compareTo(bstNode.key) == 0) {
            return bstNode.value;
        } else if (key.compareTo(bstNode.key) > 0) { // 如果bstNode.key > key，则递归右边。否则递归左边
            return getHelper(bstNode.right, key);
        }
        else {
            return getHelper(bstNode.left, key);
        }
    }


    /**
     * 返回此映射中键-值映射的数量。
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * 在此映射中将指定值与指定键关联。若Map包含key，则只更新value，而不修改size
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        // 如果this.sent为null(空BSTMap)，则直接把key生成新的bstNode,由sent指向，并将size+1
        if (sent == null) {
            sent = new BSTNode<>(key, value);
            size++;
        }

        // 否则调用helper函数put到BSTMap，并且只有在BSTMap新增加key时返回true
        else {
            if (putHelper(sent, key, value)) {
                size++;
            }
        }
    }

    /**
     * putHelper用于帮助put方法进行递归
     * 将指定值与指定键关联
     * 若BSTMap新增加key，则返回true
     */
    private boolean putHelper(BSTNode<K, V> bstNode, K key, V value) {
        // 如果bstNode.key == key，则更新bstNode.value = value，并且返回false
        if (key.compareTo(bstNode.key) == 0) {
            bstNode.value = value;
            return false;
        }
        else if (key.compareTo(bstNode.key) > 0) { // 如果key > bstNode.key
            // 如果bstNode.right为null，则创建新BSTNode，并且返回true
            if (bstNode.right == null) {
                bstNode.right = new BSTNode<>(key, value);
                return true;
            }

            // 否则，递归调用putHelper
            else {
                return putHelper(bstNode.right, key, value);
            }
        }
        else { // 如果key < bstNode.key ，同理
            if (bstNode.left == null) {
                bstNode.left = new BSTNode<>(key, value);
                return true;
            }
            else {
                return putHelper(bstNode.left, key, value);
            }
        }
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
