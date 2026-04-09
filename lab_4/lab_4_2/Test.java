package lab_4.lab_4_2;

public class Test {
    public static void main(String[] args){
        PriorityQueue<Integer> Queue = new PriorityQueueImpl();
        Queue.enqueue(10);
        Queue.enqueue(42);
        Queue.enqueue(9);

        System.out.println(Queue.dequeueMax());
        Queue.increment(3,10);
        System.out.println(Queue.dequeueMax());
        System.out.println(Queue.dequeueMax());
        System.out.println(Queue.dequeueMax());

    }
}
