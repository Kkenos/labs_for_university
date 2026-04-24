package lab_7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static lab_7.task_3.calculateLCS;

public class main {
    public static void main(String[] args) {
        String fileName = "lcs-input.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String s1 = br.readLine();
            String s2 = br.readLine();

            if (s1 == null || s2 == null) {
                System.out.println(0);
                return;
            }

            int result = calculateLCS(s1, s2);
            System.out.println(result);

        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
