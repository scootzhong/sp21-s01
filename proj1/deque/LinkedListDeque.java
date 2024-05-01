package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListDeque<T> implements Deque<T>, Comparable<LinkedListDeque>  {
    private int size;
    private Node sent;


    /** 嵌套类Node，用于存放items */
    public class Node {
        public T item;
        public Node pre;
        public Node back;

        // Node构造函数，创建一个Node对象
        public Node(T i, Node p, Node b) {
            item = i;
            pre = p;
            back = b;
        }

        // Node构造函数
        public Node(T i) {
            item = i;
        }
    }


    /** LinkedListDeque构造函数，创建一个空的链表双端队列 */
    public LinkedListDeque() {
        size = 0;

        // 空列表的哨兵节点，pre和back都指向自身
        sent = new Node(null);
        sent.pre = sent;
        sent.back = sent;
    }


    /** 将类型为 T 的项目添加到双端队列的前面。您可以假设 item 永远不会是 null 。
     * 操作不能涉及任何循环或递归
     * 单个此类操作必须花费“常数时间”*/
    @Override
    public void addFirst(T item) {
        Node temp = new Node(item);
        temp.pre = sent;
        temp.back = sent.back;
        sent.back.pre = temp;
        sent.back = temp;

        size++;
    }


    /** 将类型为 T 的项目添加到双端队列的后面。您可以假设 item 永远不会是 null 。
     * 操作不能涉及任何循环或递归
     * 单个此类操作必须花费“常数时间*/
    @Override
    public void addLast(T item) {
        Node temp = new Node(item);
        temp.back = sent;
        temp.pre = sent.pre;
        sent.pre.back = temp;
        sent.pre = temp;

        size++;
    }


    /** 返回双端队列中的项目数量
     * 必须在常数时间内完成*/
    @Override
    public int size() {
        return size;
    }


    /** 从第一个到最后一个打印双端队列中的项目，用空格分隔。一旦所有项目都被打印出来，打印一个新行。*/
    @Override
    public void printDeque() {
        for (Node p = sent.back; p != sent; p = p.back) {
            System.out.print(p.item + " ");
        }
        System.out.println();
    }


    /** 删除并返回双端队列前端的项目。如果不存在这样的项目，则返回 null 。
     * 操作不能涉及任何循环或递归
     * 单个此类操作必须花费“常数时间*/
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        Node temp = sent.back;
        temp.back.pre = sent;
        sent.back = temp.back;
        size--;
        return temp.item;
    }


    /** 删除并返回双端队列后端的项目。如果不存在这样的项目，则返回 null 。
     * 操作不能涉及任何循环或递归
     * 单个此类操作必须花费“常数时间*/
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        Node temp = sent.pre;
        temp.pre.back = sent;
        sent.pre = temp.pre;
        size--;
        return temp.item;
    }


    /** 获取给定索引处的项目，如果不存在这样的项目，则返回 null 。不能修改双端队列！
     * 必须使用迭代，而不是递归*/
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node temp = sent.back;
        for(int i = 0; i < index; i++) {
            //循环，直到将temp指向index item
            temp = temp.back;
        }
        return temp.item;
    }


    /** 与 get 相同，但使用递归 */
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return helpGet(index, sent);
    }


    /** 方便使用递归的函数 */
    private T helpGet(int index, Node list) {
        if (list.back == list) {
            return null;
        } else if (index == 0) {
            return list.back.item;
        }
        return helpGet(index - 1, list.back);
    }


    /**
     * Returns an iterator over elements of type {@code T}.
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator(this);
    }


    /** LinkedListDequeIterator */
    public class LinkedListDequeIterator implements Iterator<T> {
        public Node link;
        public Node sent;

        public LinkedListDequeIterator(LinkedListDeque<T> d) {
            link = d.sent;
            sent = d.sent;
        }


        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            if (link.back == sent) {
                return false;
            }
            return true;
        }


        /**
         * Returns the next element in the iteration.
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("the iteration has no more elements");
            }
            T temp = link.back.item;
            link = link.back;
            return temp;
        }
    }


    /** 返回参数 o 是否等于 Deque。
     * 如果它是一个 Deque 并且包含相同的内容（由泛型 T 的 equals 方法控制）且顺序相同，则被视为相等。*/
    @Override
    public boolean equals(Object o) {
        // 如果对象相同，则直接返回ture
        if (this == o) {
            return true;
        }

        // 检查对象o是否是LinkedListDeque类的实例，若不是则返回false
        if ( !(o instanceof LinkedListDeque)) {
            return false;
        }

        // 检查对象o和this是否具有相同的size，若不是则返回false
        if (this.size != ((LinkedListDeque) o).size) {
            return false;
        }

        // 将对象o和this分别创建迭代器，若有一次的内容不相同，则返回false
        Iterator<T> oIterator = ((LinkedListDeque) o).iterator();
        Iterator<T> thisIterator = iterator();
        while (thisIterator.hasNext()) {
            if (!thisIterator.next().equals(oIterator.next())) {
                return false;
            }
        }

        // 检查完毕，返回true
        return true;
    }


    /** 返回Deque的字符串表达形式，用[]包含Deque中的item
     * 比如[1, 2, 3]
     * @return
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("[");
        for (T item: this) {
            output.append(item.toString());
            output.append(", ");
        }
        int length = output.length();
        output.delete(length - 2, length);
        output.append("]");
        return output.toString();
    }


    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(LinkedListDeque o) {
        return this.size - o.size;
    }
}
