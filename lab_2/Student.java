package lab_2;

class Student implements Comparable<Student>{
    private final String name;
    private final Integer age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Student x) {
        if (!(name.compareTo(x.getName()) == 0)){
            return name.compareTo(x.getName()) < 0 ? -1 : 1;
        }
        if (!(age.compareTo(x.getAge()) == 0)){
            return age.compareTo(x.getAge()) < 0 ? -1 : 1;
        }
        else {
            return 0;
        }
    }

}