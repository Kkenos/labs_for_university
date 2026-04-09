package lab_4.lab_4_1;

public class Test {
    public static void main(String[] args){
        MinStack<Integer> stack = new MinStackImpl();
        stack.push(42);
        System.out.println("Верхнее значение стека " + stack.top());
        System.out.println("Минимальное значение стека " + stack.min());
        stack.push(12);
        System.out.println("Минимальное значение стека " + stack.min());
        stack.push(13);
        System.out.println("Верхнее значение стека " + stack.top());
        System.out.println("Минимальное значение стека " + stack.min());
        stack.pop();
        stack.pop();
        System.out.println("Минимальное значение стека " + stack.min());
    }
}
