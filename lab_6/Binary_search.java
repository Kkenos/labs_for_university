package lab_6;


import java.util.List;


public class Binary_search{
    public static void print(List<Integer> num,Integer target) {
        int left = 0;
        int right = num.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (num.get(mid).equals(target)) {
                System.out.println(mid);
                break;
            }
             else if (target < num.get(mid)) {
                right = mid - 1;
            } else{
                left = mid + 1;
            }

        }
        if(right < 0){
            System.out.println(num.get(0));
        } else if (left>num.size()-1) {
            System.out.println(num.size()-1);
        }
        else{
            int differentRight = target - num.get(right);
            int differentLeft = num.get(left) - target;
            if(differentRight<=differentLeft){
                System.out.println(num.get(right));
            }
            else{
                System.out.println(num.get(left));
            }
        }


    }
}



