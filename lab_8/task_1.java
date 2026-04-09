package lab_8;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Scanner;

public class task_1 {
    public static void main(String[] args){
        ArrayList<Integer> EnternalMass = new ArrayList<>(Arrays.asList(1,4,3,-6,2,6,-8,2,3,5));
        ArrayList<Integer> FibonachiMass = new ArrayList<>();
        for (int i = 0;i<EnternalMass.size();i++){

            if (i>0){
                FibonachiMass.add(EnternalMass.get(i) + FibonachiMass.get(i-1));
            }
            else{
                FibonachiMass.add(EnternalMass.get(i));
            }

        }
        System.out.println(EnternalMass);
        Scanner scan = new Scanner(System.in);

        System.out.println("Введите левую границу: ");
        int DemandLeft = scan.nextInt();

        System.out.println("Введите правую границу: ");
        int DemandRight = scan.nextInt();

        System.out.println("Сумма в данном диапазоне: ");
        if(DemandLeft > 0 ) {
            System.out.println(FibonachiMass.get(DemandRight) - FibonachiMass.get(DemandLeft - 1));
        }
        else{
            System.out.println(FibonachiMass.get(DemandRight));
        }
        scan.close();
    }
    
}
