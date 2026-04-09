package lab_2;

import java.util.ArrayList;
import java.util.List;

public class lab2_3 {
    static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Vitalya", 19));
        students.add(new Student("Andrew", 19));
        students.add(new Student("Igor", 18));
        students.add(new Student("Egor",20));
        students.add(new Student("Egor", 19));

        Sortable<Student>SorterStudents = new Insetion_sort<Student>();
        SorterStudents.sort(students);

        for(Student i : students){
            System.out.println(i.getName() +" "+i.getAge());
        }
    }
}