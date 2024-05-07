package bstmap;

import java.util.Iterator;
import java.util.Set;

/** 使用链表存储键值对的数据结构。
 *  字典中的任何键最多只能出现一次，但值可能多次出现。
 *  关键操作是get(key)、put(key, value)和contains(key)方法。与键关联的值是与该键的上一次put调用中的值。 */
public class ULLMap<K, V>  implements Map61B<K, V> {

    int size = 0;

    /** 返回与KEY对应的值，如果没有这样的值存在则返回null。 */
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

    /** 删除此地图中的所有映射。 */
    @Override
    public void clear() {
        size = 0;
        list = null;
    }

    /** 将KEY和VALUE的键值对插入此字典中，替换键关联的以前的值（如果有）。 */
    public void put(K key, V val) {
        if (list != null) {
            Entry lookup = list.get(key);
            if (lookup == null) {
                list = new Entry(key, val, list);
            } else {
                lookup.val = val;
            }
        } else {
            list = new Entry(key, val, list);
            size = size + 1;
        }
    }

    /** 当且仅当此字典包含KEY作为某些键值对的键时返回true。 */
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

    /** 键和值存储在链表的Entry对象中。
     *  这个变量存储了这个链表中的第一个对。 */
    private Entry list;

    /** 表示存储字典中键值对的链表中的一个节点。 */
    private class Entry {

        /** 将KEY存储为此键值对中的键，将VAL存储为值，并将NEXT存储为链表中的下一个节点。 */
        Entry(K k, V v, Entry n) {
            key = k;
            val = v;
            next = n;
        }

        /** 返回此键值对链表中键等于KEY的Entry，如果没有这样的Entry存在则返回null。 */
        Entry get(K k) {
            if (k != null && k.equals(key)) {
                return this;
            }
            if (next == null) {
                return null;
            }
            return next.get(key);
        }

        /** 将此节点列表中的键的键值对存储。 */
        K key;
        /** 将此节点列表中的键的值存储。 */
        V val;
        /** 存储链表中的下一个Entry。 */
        Entry next;

    }

    /** 迭代字典键的迭代器。 */
    private class ULLMapIter implements Iterator<K> {

        /** 通过将cur设置为存储键值对的链表中的第一个节点来创建一个新的ULLMapIter。 */
        public ULLMapIter() {
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
