package lab_4.lab_4_2;

public interface PriorityQueue<T extends Number> {
    void enqueue(T value);

    T dequeueMax();

    void increment(long operation, T addition);
}