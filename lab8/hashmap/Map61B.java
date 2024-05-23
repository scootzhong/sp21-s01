package hashmap;

import java.util.Set;
/**
 * 你的实现应该扩展hashmap.MyHashMap并实现这个接口。为此，在你的"public class..."声明的末尾添加"implements hashmap.Map61B<K, V>"，
 * 尽管你可以使用其他形式的类型参数，如果你愿意的话。
 */
public interface Map61B<K, V> extends Iterable<K> {
    /** 清除此地图中所有的映射。 */
    void clear();

    /** 如果此地图包含指定键的映射，则返回true。 */
    boolean containsKey(K key);

    /**
     * 返回指定键所映射的值，如果此地图不包含该键的映射，则返回null。
     */
    V get(K key);

    /** 返回此地图中的键值映射数量。 */
    int size();

    /**
     * 将指定的值与指定的键关联到此地图中。如果地图之前包含该键的映射，
     * 则替换旧值。
     */
    void put(K key, V value);

    /** 返回此地图中包含的键的Set视图。 */
    Set<K> keySet();

    /**
     * 如果存在，则从此地图中删除指定键的映射。这不是Lab 8的要求。
     * 如果你没有实现此功能，抛出UnsupportedOperationException。
     */
    V remove(K key);

    /**
     * 如果当前映射到指定值，则仅删除指定键的条目。这不是Lab 8的要求。
     * 如果你没有实现此功能，抛出UnsupportedOperationException。
     */
    V remove(K key, V value);
}


