package deque;

public class Main {
    public static void main(String[] args) {
        Deque<Integer> adeque = new ArrayDeque<>();
        adeque.addFirst(1);
        adeque.addFirst(2);
        System.out.println(adeque);
    }
}
