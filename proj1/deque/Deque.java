package deque;

public interface Deque<T> extends Iterable<T>{
    /** 将类型为 T 的项目添加到双端队列的前面。您可以假设 item 永远不会是 null 。
     * 必须保持恒定时间*/
    public void addFirst(T item);


    /** 将类型为 T 的项目添加到双端队列的后面。您可以假设 item 永远不会是 null 。
     * 必须保持恒定时间*/
    public void addLast(T item);


    /** 如果双端队列为空，则返回 true ，否则返回 false 。*/
    default public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }


    /** 返回双端队列中的项目数量
     * 必须保持恒定时间*/
    public int size();


    /** 从第一个到最后一个打印双端队列中的项目，用空格分隔。一旦所有项目都被打印出来，打印一个新行。*/
    default public void printDeque() {
        for(int i = 0; i < size(); i++) {
            T item = get(i);
            System.out.print(item + " ");
        }
        System.out.println();
    }


    /** 删除并返回双端队列前端的项目。如果不存在这样的项目，则返回 null 。
     * 必须保持恒定时间*/
    public T removeFirst();


    /** 删除并返回双端队列后端的项目。如果不存在这样的项目，则返回 null 。
     * 必须保持恒定时间*/
    public T removeLast();


    /** 获取给定索引处的项目，如果不存在这样的项目，则返回 null 。不能修改双端队列！
     * 必须保持恒定时间*/
    public T get(int index);
}
