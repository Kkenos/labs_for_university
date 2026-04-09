package lab_8;

import java.util.ArrayList;


public class MinElemTree {
    private ArrayList<Integer> tree;
    private int size;

    public MinElemTree(ArrayList<Integer> arr) {
        size = 1;
        while (size < arr.size()) {
            size *= 2;
        }


        tree = new ArrayList<>(size * 2);


        for (int i = 0; i < size * 2; i++) {
            tree.add(0);
        }


        for (int i = 0; i < arr.size(); i++) {
            tree.set(size + i, arr.get(i));
        }


        for (int i = arr.size(); i < size; i++) {
            tree.set(size + i, Integer.MAX_VALUE);
        }


        for (int i = size - 1; i >= 0; i--) {
            int minValue = Math.min(tree.get(i * 2), tree.get(i * 2 + 1));
            tree.set(i, minValue);
        }
    }

    public int query(int left, int right) {
        left += size;
        right += size;
        int result = Integer.MAX_VALUE;

        while (left <= right) {
            if ((left % 2) != 0) {
                result = Math.min(result, tree.get(left++));
            }
            if ((right % 2) == 0) {
                result = Math.min(result, tree.get(right--));
            }
            left /= 2;
            right /= 2;
        }
        return result;
    }
}



