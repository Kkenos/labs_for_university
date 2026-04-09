package lab_2;

import java.util.*;

public class Insetion_sort<T extends Comparable <T>> implements Sortable<T> {
    @Override
    public void sort(List<T> nums) {
        if (nums.size() <= 1){
            return;
        }
        for (int i = 1; i < nums.size(); i++) {
            T curr = nums.get(i);
            while (i > 0 && nums.get(i-1).compareTo(curr) > 0) { //.compareTo(curr) > 0
                nums.set(i, nums.get(i - 1));
                i -= 1;
            }
            nums.set(i, curr);
        }
    }
}