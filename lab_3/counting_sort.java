package lab_3;


import java.util.ArrayList;
import java.util.List;

public class counting_sort implements Sorting<Integer> {
    @Override
    public void sort(List<Integer> nums) {
        if(nums.size() <= 1){
            return;
        }
        int max = nums.get(0);
        for(int num : nums){
            if(num > max){
                max=num;
            }
        }

        List<Integer> count = new ArrayList<>();

        for(int i = 0;i<=max;i++){
            count.add(0);
        }

        for(int num : nums){
            count.set(num, count.get(num)+1);
        }

        int index = 0;
        for(int i = 0;i<count.size();i++){
            for(int j = 0;j<count.get(i);j++){
                nums.set(index,i);
                index++;
            }
        }
    }
}