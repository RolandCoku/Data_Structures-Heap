import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(5);
        queue.add(3);
        queue.add(7);
        queue.add(1);
        queue.add(9);
        queue.add(2);
        queue.add(4);
        queue.add(6);
        queue.add(8);

        queue.print();
        System.out.println("Min: " + queue.remove());
        queue.print();
        queue.remove(4);
        queue.print();


        Comparator<Integer> comparator = Comparator.reverseOrder();
        PriorityQueue<Integer> queue2 = new PriorityQueue<>(comparator);

        queue2.add(5);
        queue2.add(3);
        queue2.add(7);
        queue2.add(1);
        queue2.add(9);
        queue2.add(2);
        queue2.add(4);
        queue2.add(6);
        queue2.add(8);

        queue2.print();
        System.out.println("Max: " + queue2.remove());
        queue2.print();
        System.out.println("Max: " + queue2.getMin());

        System.out.println("Max: " + queue2.remove());
        queue2.print();

    }
}