package lab_3;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
public class test {
    public static void main(String[]arg){
        List<Integer>numbers = Arrays.asList(124,68,57,0,93,60,24,71,125,70,87,83,105,34,30,15);
        counting_sort sorter = new counting_sort();
        sorter.sort(numbers);
        System.out.println(numbers);

    }


}
