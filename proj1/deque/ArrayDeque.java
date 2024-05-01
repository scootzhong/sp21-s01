package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;

public class ArrayDeque<T> implements Deque<T> {
    public static final int MINARRAYSIZE  = 8;
    public static final int FIRSTINDEX = 3;
    public static final int LASTINDEX = 4;
    public static final int EXPANDFACTOR = 2;

    private int size; // Deque项目数量
    private T[] items; // 记录item的数组
    private int addFirstIndex; // addFirst时，存入item所在数组中的索引
    private int addLastIndex; // addLast时，存入item所在数组中的索引
    public int arrayLength; // 用于记录所用数组的长度

    // 构造函数，创建一个空Deque
    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[MINARRAYSIZE];
        arrayLength = MINARRAYSIZE;
        addFirstIndex = FIRSTINDEX;
        addLastIndex = LASTINDEX; // 空Deque，addLastIndex - addFirstIndex == 1
    }

   /** 调整数组长度的方法 */
   public void raise(int newArrayLength) {
       // 调整数组长度，是将原数组复制到新数组，新数组的第一个是addFirstIndex
       T[] newArray = (T[]) new Object[newArrayLength];

       // 如果前 < 后，即数据在中间
       if (addFirstIndex < addLastIndex) {
           System.arraycopy(items, addFirstIndex + 1, newArray, 1, size);
       }
       
       // 如果后 > 前 且 后=0，即数据在右边
       else if (addFirstIndex > addLastIndex && addLastIndex == 0) {
           System.arraycopy(items, addFirstIndex + 1, newArray, 1, size);
       }

       // 如果后 > 前 且 前=length - 1，即数据在左边
       else if (addFirstIndex > addLastIndex && addFirstIndex == arrayLength - 1) {
           System.arraycopy(items, 0, newArray, 1, size);
       }

       // 其他情况，即后 > 前，数据在两边
       else {
           int rightNum = arrayLength - addFirstIndex - 1; // 右侧数据量
           System.arraycopy(items, addFirstIndex + 1, newArray, 1, rightNum);
           System.arraycopy(items, 0, newArray, rightNum + 1, size - rightNum);
       }

       items = newArray;
       addFirstIndex = 0;
       addLastIndex = size + 1;
       arrayLength = newArrayLength;
   }


    /** 将类型为 T 的项目添加到双端队列的前面。您可以假设 item 永远不会是 null 。
     * 必须保持恒定时间*/
    @Override
    public void addFirst(T item) {
        // 判断数组是否满了，若满了，则需先扩大数组
        // 数组没有满，添加item到addFirstIndex，并将addFirstIndex减1
        // 需判断新的addFirstIndex是否为-1，若是则调整addFirstIndex到数组末尾

        if (arrayLength - size == 2) {
            raise(arrayLength * EXPANDFACTOR);
        }

        items[addFirstIndex] = item;
        size++;
        addFirstIndex--;

        if (addFirstIndex == - 1) {
            addFirstIndex = arrayLength - 1;
        }
    }


    /** 将类型为 T 的项目添加到双端队列的后面。您可以假设 item 永远不会是 null 。
     * 必须保持恒定时间*/
    @Override
    public void addLast(T item) {
        // 判断数组是否满了，若满了，则需先扩大数组
        // 数组没有满，添加item到addLastIndex，并将addFirstIndex加1
        // 需判断新的addLastIndex是否为arrayLength，若是则调整addFirstIndex到数组前列

        if (arrayLength - size == 2) {
            raise(arrayLength * EXPANDFACTOR);
        }

        items[addLastIndex] = item;
        size++;
        addLastIndex++;

        if (addLastIndex == arrayLength) {
            addLastIndex = 0;
        }
    }


    /** 返回双端队列中的项目数量
     * 必须保持恒定时间*/
    @Override
    public int size() {
        return size;
    }


//    /** 从第一个到最后一个打印双端队列中的项目，用空格分隔。一旦所有项目都被打印出来，打印一个新行。*/
//    public void printDeque() {
//        for(int i = 0; i < size; i++) {
//            T item = get(i);
//            System.out.print(item + " ");
//        }
//        System.out.println();
//    }


    /** 删除并返回双端队列前端的项目。如果不存在这样的项目，则返回 null 。
     * 必须保持恒定时间*/
    @Override
    public T removeFirst() {
        // 如果队列为空，则返回null
        // 否则，暂存addFirstIndex + 1中的项目
        // 将addFirstIndex + 1中的指引变为null，并addFirstIndex+1
        // 需判断新的addLastIndex是否为arrayLength，若是则调整addFirstIndex到数组前列
        // 判断数组需要调整，则需则调整数组

        if (size == 0) {
            return null;
        }

        T temp;

        // addFirstIndex可能正好在数组末尾，需做特殊判断处理
        try {
            temp = items[addFirstIndex + 1];
            items[addFirstIndex + 1] = null;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            temp = items[0];
            items[0] = null;
        }

        addFirstIndex++;
        size--;

        if (addFirstIndex == arrayLength) {
            addFirstIndex = 0;
        }

        if (size <= arrayLength / 4 && arrayLength > 8) {
            raise(arrayLength / EXPANDFACTOR);
        }

        return temp;
    }


    /** 删除并返回双端队列后端的项目。如果不存在这样的项目，则返回 null 。
     * 必须保持恒定时间*/
    @Override
    public T removeLast() {
        // 如果队列为空，则返回null
        // 否则，暂存addLastIndex - 1中的项目
        // 将addLastIndex - 1中的指引变为null，并addLastIndex - 1
        // 需判断新的addLastIndex是否为-1，若是则调整到数组末尾

        if (size == 0) {
            return null;
        }

        T temp;

        // addLastIndex可能正好在数组前列，需做特殊判断处理
        try {
            temp = items[addLastIndex - 1];
            items[addLastIndex - 1] = null;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            temp = items[arrayLength - 1];
            items[arrayLength - 1] = null;
        }

        addLastIndex--;
        size--;

        if (addLastIndex == -1) {
            addLastIndex = arrayLength - 1;
        }

        if (size <= arrayLength / 4 && arrayLength > 8) {
            raise(arrayLength / EXPANDFACTOR);
        }

        return temp;
    }


    /** 获取给定索引处的项目，如果不存在这样的项目，则返回 null 。不能修改双端队列！
     * 必须保持恒定时间*/
    @Override
    public T get(int index) {
        if (size == 0) {
            return null;
        }

        int newIndex;

        // 如果前 < 后，即数据在中间
        if (addFirstIndex < addLastIndex) {
            newIndex = addFirstIndex + 1 + index;
        }

        // 如果后 > 前 且 后=0，即数据在右边
        else if (addFirstIndex > addLastIndex && addLastIndex == 0) {
            newIndex = addFirstIndex + 1 + index;
        }

        // 如果后 > 前 且 前=length - 1，即数据在左边
        else if (addFirstIndex > addLastIndex && addFirstIndex == arrayLength - 1) {
            newIndex = index;
        }

        // 其他情况，即后 > 前，数据在两边
        else {
            if (addFirstIndex + 1 + index <= arrayLength - 1) {
                newIndex = addFirstIndex + 1 + index;
            }
            else {
                int rightNum = arrayLength - addFirstIndex - 1; // 右侧数据量
                newIndex = index - rightNum;
            }
        }

        return items[newIndex];
    }


    /**
     * Returns an iterator over elements of type {@code T}.
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator(this);
    }


    /** 嵌套私有类ArrayDequeIterator */
    private class ArrayDequeIterator implements Iterator<T> {
        public int iterIndex;
        public ArrayDeque<T> array;

        // 构造函数
        public ArrayDequeIterator(ArrayDeque a) {
            iterIndex = 0;
            array = a;
        }

        @Override
        public boolean hasNext() {
            if (iterIndex < array.size()) {
                return true;
            }
            return false;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("the iteration has no more elements");
            }
            T temp = array.get(iterIndex);
            iterIndex++;
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

        // 检查对象o是否是ArrayDeque类的实例，若不是则返回false
        if ( !(o instanceof ArrayDeque)) {
            return false;
        }

        // 检查对象o和this是否具有相同的size，若不是则返回false
        if (this.size != ((ArrayDeque) o).size) {
            return false;
        }

        // 将对象o和this分别创建迭代器，若有一次的内容不相同，则返回false
        Iterator<T> oIterator = ((ArrayDeque) o).iterator();
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
}
