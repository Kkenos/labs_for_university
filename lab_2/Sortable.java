package lab_2;

import java.util.List;

public interface Sortable <T extends Comparable <T>>{
    void sort(List<T> spisok);
}
