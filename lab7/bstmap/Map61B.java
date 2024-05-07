package bstmap;

import java.util.Set;

/* 你的BSTMap实现应该实现这个接口。为此，将“implements Map61B<K,V>”附加到你的“public class...”声明的末尾，尽管你可以使用其他形式的类型参数。
 */
public interface Map61B<K, V> extends Iterable<K> {

    /** 清除此映射中的所有映射。 */
    void clear();

    /** 如果此映射包含指定键的映射，则返回true。 */
    boolean containsKey(K key);

    /** 返回指定键映射到的值，如果此映射不包含键的映射，则返回null。
     */
    V get(K key);

    /** 返回此映射中键-值映射的数量。 */
    int size();

    /** 在此映射中将指定值与指定键关联。 */
    void put(K key, V value);

    /** 返回此映射中包含的键的Set视图。对于实验7不是必需的。
     * 如果你不实现这个，抛出UnsupportedOperationException。 */
    default Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /** 如果存在，则从此映射中移除指定键的映射。
     * 对于实验7不是必需的。如果你不实现这个，抛出UnsupportedOperationException。 */
    default V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /** 仅在当前映射到指定值的情况下删除指定键的条目。对于实验7不是必需的。如果你不实现这个，抛出UnsupportedOperationException。*/
    default V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

}
