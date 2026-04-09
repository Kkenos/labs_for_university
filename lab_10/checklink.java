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
            if (check == "no") {
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
    void checkConnectivity(List<List<Integer>> sample){
        List<Integer> collect = new ArrayList<>();
        collect.addAll(sample.get(0));
        for (int i = 1; i < sample.size(); i++) {
            List<Integer> currPair = sample.get(i);
            for (int j = 0; j < currPair.size(); j++) {
                if((currPair.get(j) == collect.get(0)) || (currPair.get(j) == collect.get(1))){
                    collect.add(currPair.get(j));
                    collect.add(currPair.get(j+1));
                }
            }

        }
    }

}
