package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    // 相比ArrayDeque，新增一个实例属性字段来存储比较器对象
    public Comparator<T> comparator;

    /** 使用给定的 Comparator 创建一个 MaxArrayDeque */
    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }


    /** 根据先前给定的 Comparator 返回deque中的最大元素。
     * 如果 MaxArrayDeque 为空，则简单返回 null
     */
    public T max() {
        if (size() == 0) {
            return null;
        }
        T maxItem = get(0);
        for (T item : this) {
            if (comparator.compare(item, maxItem) >= 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }


    /** 根据参数 Comparator c 返回受 deque 控制的最大元素。
     * 如果 MaxArrayDeque 为空，则简单返回 null 。
     */
    public T max(Comparator<T> c) {
        if (size() == 0) {
            return null;
        }
        T maxItem = get(0);
        for (T item : this) {
            if (c.compare(item, maxItem) >= 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }
}
