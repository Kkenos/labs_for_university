package lab_4.lab_4_1;

public interface MinStack<T> {

    void push(T value);

    T pop();

    T top();

    T min();
}