package lab_7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class task_1 {
    public static void main(String[] args) {
        String file = "src/lab_7/roguelike-input.csv";
        String outputFile = "src/lab_7/roguelike-output.txt";
        String line;
        String delimiter = ";";

        List<List<Integer>> matrix = new ArrayList<>();

        try (BufferedReader buff = new BufferedReader(new FileReader(file))) {
            while ((line = buff.readLine()) != null) {
                String[] values = line.split(delimiter);
                List<Integer> row = new ArrayList<>();
                for (String value : values) {
                    row.add(Integer.parseInt(value));
                }
                matrix.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        matrixOfSum(matrix, outputFile);
    }

    static void matrixOfSum(List<List<Integer>> mass, String outputFile) {
        int rowsAndCols = mass.size();
        int[][] mtrxsm = new int[rowsAndCols][rowsAndCols];
        mtrxsm[0][0] = mass.get(0).get(0);


        for (int i = 1; i < rowsAndCols; i++) {
            mtrxsm[0][i] = mtrxsm[0][i - 1] + mass.get(0).get(i);
        }
        for (int j = 1; j < rowsAndCols; j++) {
            mtrxsm[j][0] = mtrxsm[j - 1][0] + mass.get(j).get(0);
        }


        for (int i = 1; i < rowsAndCols; i++) {
            for (int j = 1; j < rowsAndCols; j++) {
                mtrxsm[i][j] = mass.get(i).get(j) + Math.max(mtrxsm[i - 1][j], mtrxsm[i][j - 1]);
            }
        }


        int i = rowsAndCols - 1;
        int j = rowsAndCols - 1;
        StringBuilder pathBuilder = new StringBuilder();

        while (i > 0 || j > 0) {
            if (i == 0) {
                pathBuilder.append("R");
                j--;
            } else if (j == 0) {
                pathBuilder.append("D");
                i--;
            } else {
                if (mtrxsm[i - 1][j] >= mtrxsm[i][j - 1]) {
                    pathBuilder.append("D");
                    i--;
                } else {
                    pathBuilder.append("R");
                    j--;
                }
            }
        }


        String path = pathBuilder.reverse().toString();


        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            writer.print(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





















