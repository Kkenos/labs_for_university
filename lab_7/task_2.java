package lab_7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class task_2 {

    public static void main(String[] args) {
        String inputFile = "src/lab_7/lis-input.txt";
        String outputFile = "src/lab_7/lis-output.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {


            int n = Integer.parseInt(reader.readLine().trim());

            String[] tokens = reader.readLine().trim().split("\\s+");
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(tokens[i]);
            }


            List<Integer> lis = findLIS(arr);


            writer.println(lis.size());
            for (int i = 0; i < lis.size(); i++) {
                writer.print(lis.get(i));
                if (i < lis.size() - 1) {
                    writer.print(" ");
                }
            }
            writer.println();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static List<Integer> findLIS(int[] arr) {
        int n = arr.length;
        if (n == 0) return new ArrayList<>();


        int[] dp = new int[n];

        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            prev[i] = -1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
        }


        int bestIndex = 0;
        for (int i = 1; i < n; i++) {
            if (dp[i] > dp[bestIndex]) {
                bestIndex = i;
            }
        }


        List<Integer> lis = new ArrayList<>();
        int cur = bestIndex;
        while (cur != -1) {
            lis.add(0, arr[cur]);
            cur = prev[cur];
        }
        return lis;
    }
}
