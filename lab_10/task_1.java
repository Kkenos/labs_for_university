package lab_10;

import java.util.List;
import java.util.Scanner;
import java.util.Collections;

public class task_1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        if (input.hasNextInt()) {
            int n = input.nextInt();

            checklink checker = new checklink();
            checker.addSublist(n);


            checker.addEdge(input);


            List<List<Integer>> result = checker.findComponents(n);


            for (List<Integer> component : result) {
                Collections.sort(component);
                for (int vertex : component) {
                    System.out.print(vertex + " ");
                }
                System.out.println();
            }
        }
    }
}
