package lab_7;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;


public class task_1 {
    public static void main(String[] args) {
        String file = "src/lab_7/roguelike-input.csv";
        String line;
        String hzkaknazvat = ";";

        List<List<Integer>> matrix = new ArrayList<>();

        try (BufferedReader buff = new BufferedReader(new FileReader(file))) {
            while ((line = buff.readLine()) != null) {
                String[] val = line.split(hzkaknazvat);
                List<Integer> row = new ArrayList<>();
                for (String value : val) {
                    row.add(Integer.parseInt(value));
                }
                matrix.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        prtintGround(matrix);
        matrixOfSum(matrix);

    }

    static void prtintGround(List<List<Integer>> mass) {
        for (List<Integer> row : mass) {
            for (Integer num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    static void matrixOfSum(List<List<Integer>> mass) {
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

        while (i > 0 || j > 0) {
            if (i == 0) {
                System.out.print("R");
                j--;
            } else if (j == 0) {
                System.out.print("D");
                i--;
            } else {
                if (mtrxsm[i - 1][j] >= mtrxsm[i][j - 1]) {
                    System.out.print("D");
                    i--;
                } else {
                    System.out.print("R");
                    j--;
                }
            }
        }

    }
}

