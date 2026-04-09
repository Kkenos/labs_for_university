package lab_6;

import java.util.Scanner;

public class task_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();

        if (a == 1.0) {
            System.out.printf("%.6f%n", 1.0);
            return;
        }


        double left = 0.0;
        double right = a + 1.0;
        double epsilon = 0.0000001;


        while (right - left > epsilon) {
            double mid = (left + right) / 2.0;
            double value = mid * mid - mid + Math.sqrt(mid);

            if (value < a) {
                left = mid;
            } else {
                right = mid;
            }
        }


        System.out.printf("%.6f%n", (left + right) / 2.0);
    }
}