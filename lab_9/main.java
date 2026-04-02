package lab_9;

import java.util.Scanner;

public class main {
    public static void main(String[] args){
        BinaryTreeSearch tree = new BinaryTreeSearch();
        // Пример из условия
        tree.insert(5);
        tree.insert(7);
        tree.insert(3);
        tree.insert(4);
        tree.has(3);
        tree.has(8);
        tree.insert(8);
        tree.has(8);
        tree.delete(3);
        tree.has(4);
        tree.next(7);
        tree.prev(5);

    }

}


