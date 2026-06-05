import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Student {

    int id;
    String name;
    int age;

    // Constructor
    Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Display method
    public String toString() {
        return id + " " + name + " " + age;
    }
}
