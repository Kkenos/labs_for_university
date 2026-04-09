package lab_3;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;



public class RadixSort implements Sorting<BigInteger> {
    @Override
    public void sort(List<BigInteger> nums) {
        for (int i = 0; i < 20; i++) {
            countingsort(nums, i);
        }
    }

    private int getdigit(BigInteger x, int razryad) {
        BigInteger ten = BigInteger.TEN;
        return x.divide(ten.pow(razryad)).mod(ten).intValue();
    }

    private void countingsort(List<BigInteger> array, int razryad) {
        List<List<BigInteger>> countArray = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            countArray.add(new LinkedList<>());
        }

        for (BigInteger i : array) {
            countArray.get(getdigit(i, razryad)).add(i);
        }

        int listIndex = 0;
        for (List<BigInteger> i : countArray) {
            for (BigInteger j : i) {
                array.set(listIndex++, j);
            }
        }
    }
}