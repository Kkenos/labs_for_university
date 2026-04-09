package lab_6;

import java.util.Scanner;

public class task_2 {

    public static boolean fit(int side,int n,int w,int h){
        int cols = side/h;
        int rows = side/w;

        if(cols > n/rows){
            return true;
        }

        return cols*rows >= n;
    }
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите значение n(количество): ");
            int n = scanner.nextInt();
            System.out.println("Введите значение w(ширина): ");
            int w = scanner.nextInt();
            System.out.println("Введите значение h(высота): ");
            int h = scanner.nextInt();

            int LeftValueForSearch = Math.max(h,w);
            int RightValueForSearch = Math.max(h,w)*n;



            int checkWeight = n/w;
            int checkheight = n/h;
            while(LeftValueForSearch < RightValueForSearch){
                int mid = (LeftValueForSearch + RightValueForSearch) / 2;
                if(fit(mid,n,w,h)){
                    RightValueForSearch = mid;
                }
                else {
                    LeftValueForSearch = mid + 1;
                }
            }


            System.out.println("Минимальное значение для дипломов " + LeftValueForSearch);
            scanner.close();
        }

}
