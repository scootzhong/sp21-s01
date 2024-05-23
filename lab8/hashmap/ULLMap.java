package hashmap;

import java.util.Iterator;
import java.util.Set;

/**
 * 一个使用链表存储键值对的数据结构。
 * 任何键最多在字典中出现一次，但值可以多次出现。
 * 键操作包括get(key)，put(key, value)和contains(key)方法。
 * 关联到一个键的值是最后一次调用put时传入的那个值。
 */
public class ULLMap<K, V> implements Map61B<K, V> {
    int size = 0;

    /**
     * 返回与KEY关联的值，如果不存在则返回null。
     */
    public V get(K key) {
        if (list == null) {
            return null;
        }
        Entry lookup = list.get(key);
        if (lookup == null) {
            return null;
        }
        return lookup.val;
    }

    @Override
    public int size() {
        return size;
    }

    /** 删除此映射中的所有键值对。 */
    @Override
    public void clear() {
        size = 0;
        list = null;
    }

    /**
     * 将键值对KEY和VALUE插入到字典中，
     * 替换与KEY关联的先前值，如果有的话。
     */
    public void put(K key, V val) {
        if (list != null) {
            Entry lookup = list.get(key);
            if (lookup == null) {
                list = new Entry(key, val, list);
                size = size + 1;
            } else {
                lookup.val = val;
            }
        } else {
            list = new Entry(key, val, list);
            size = size + 1;
        }
    }

    /**
     * 如果此字典包含某个键值对的KEY，则返回true。
     */
    public boolean containsKey(K key) {
        if (list == null) {
            return false;
        }
        return list.get(key) != null;
    }

    @Override
    public Iterator<K> iterator() {
        return new ULLMapIter();
    }

    /**
     * 键值对存储在Entry对象的链表中。
     * 此变量存储链表中的第一个键值对。
     */
    private Entry list;

    /**
     * 表示链表中存储字典中键值对的一个节点。
     */
    private class Entry {

        /**
         * 使用给定的KEY作为键，VAL作为值，NEXT作为链表中的下一个节点来存储键值对。
         */
        Entry(K k, V v, Entry n) {
            key = k;
            val = v;
            next = n;
        }

        /**
         * 返回链表中键为KEY的Entry，如果不存在则返回null。
         */
        Entry get(K k) {
            if (k != null && k.equals(key)) {
                return this;
            }
            if (next == null) {
                return null;
            }
            return next.get(k);
        }

        /** 存储链表中当前节点的键。 */
        K key;
        /** 存储链表中当前节点的值。 */
        V val;
        /** 存储链表中的下一个Entry。 */
        Entry next;

    }

    /** 迭代器遍历字典的键。 */
    private class ULLMapIter implements Iterator<K> {

        /**
         * 创建一个新的ULLMapIter，将cur设置为存储键值对的链表的第一个节点。
         */
        ULLMapIter() {
            cur = list;
        }

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public K next() {
            K ret = cur.key;
            cur = cur.next;
            return ret;
        }

        /** 存储当前键值对。 */
        private Entry cur;

    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

}


