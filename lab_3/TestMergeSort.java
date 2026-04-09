package lab_3;

import java.util.Arrays;
import java.util.List;

public class TestMergeSort {
    public static void main(String[] args){
        List<Integer> numbers = Arrays.asList(124,68,57,0,93,60,24,71,125,70,87,83,105,34,30,15);
        Sorting<Integer> sort = new MergeSort();

        sort.sort(numbers);
        System.out.println(numbers);
    }
}
