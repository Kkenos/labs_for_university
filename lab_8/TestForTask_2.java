package lab_8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class TestForTask_2 {
    public static void main(String[] args){
        ArrayList<Integer> EnternalMass = new ArrayList<>(Arrays.asList(1,4,3,-6,2,6,-8,2,3,5));
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите правую границу: ");
        int left = scanner.nextInt();
        System.out.println("Введите левую границу: ");
        int right = scanner.nextInt();

        MinElemTree tree = new MinElemTree(EnternalMass);


        System.out.println("Минимальное число на опрезке: " + left + " " + right);
        System.out.println(tree.query(left,right));

    }
}
