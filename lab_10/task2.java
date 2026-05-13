package lab_10;

import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Введи количество вершин");
        if(input.hasNextInt()){
            int n = input.nextInt();

            TopologySort ts = new TopologySort();
            ts.init(n);

            System.out.println("Введите пары вершин (u v) для ребер u -> v. на ctrlD перестать заполнять");
            ts.addEdge(input);

            System.out.println("Результат топологической сортировки:");
            ts.sort(n);
        }
    }
}
