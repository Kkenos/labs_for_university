package lab_10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class checklink {
    List<List<Integer>> storage = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);
    int count = scanner.nextInt();

    void add(){
        while(true) {
            System.out.println("Добавляем пару?(yes/no)");
            String check = scanner.nextLine();
            if (check.equals("no")) {
                break;
            }
            System.out.println("напиши пару чтобы добавить:");
            List<Integer> link = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                int num = scanner.nextInt();
                if (num > count) {
                    System.out.println("Не-а");
                    return;
                } else {
                    link.add(num);
                }
            }
            storage.add(link);
        }
    }
    void DFS (List<List<Integer>> storage){
        boolean[] visited = new boolean[count];
        for(int i = 0; i < count ; i++){
            if(!visited[i]){

            }
        }
    }

}
