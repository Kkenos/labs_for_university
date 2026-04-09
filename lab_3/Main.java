package lab_3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<BigInteger> list = new ArrayList<>(Arrays.asList(
                new BigInteger("19446744073709551657"),
                new BigInteger("18446744073709551616"),
                new BigInteger("18446744073709551629"),
                new BigInteger("18446744073709551633")
        ));
        System.out.println("Изначальный список: \n" + list);


        Sorting<BigInteger> sorter = new RadixSort();
        sorter.sort(list);
        System.out.println("Отсортированный список: \n" + list);
    }
}