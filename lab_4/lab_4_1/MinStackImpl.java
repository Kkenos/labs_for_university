package lab_4.lab_4_1;

public class MinStackImpl implements MinStack<Integer> {
    private static class Node{
        Integer value;
        Integer min;
        Node next;

        Node(Integer min, Integer value){
            this.value = value;
            this.min = min;
            this.next = null;
        }
    }

    private Node head;

    @Override
    public void push(Integer value) {

        Integer newmin;
        if(head == null){
            newmin = value;
        } else {
            newmin=Math.min(value,head.min);
        }
        Node newnode = new Node(newmin,value);
        newnode.next = head;
        head = newnode;
    }

    @Override
    public Integer pop() {
        if (head == null){
            return null;
        }
        Integer result = head.value;
        head = head.next;
        return result;

    }

    @Override
    public Integer top() {
        if(head == null) {
            return null;
        }
        Integer topElement = head.value;
        return topElement;
    }

    @Override
    public Integer min() {
        if (head == null) {
            return null;
        }
        Integer minElement = head.min;
        return minElement;
    }
}