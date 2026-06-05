public class Main {

    public static void main(String[] args) {

        ArrayList<Student> list = new ArrayList<>();

        // Unsorted data
        list.add(new Student(1, "Ayub", 22));
        list.add(new Student(2, "Khalid", 20));
        list.add(new Student(3, "Ayub", 19));
        list.add(new Student(4, "Nouman", 23));
        list.add(new Student(5, "Khalid", 21));

        // Sorting using comparator
        Collections.sort(list, new Comparator<Student>() {
            public int compare(Student student1, Student student2) {

                // First compare by name
                int nameCompare = student1.name.compareTo(student2.name);

                // If names are same, compare by age
                if (nameCompare == 0) {
                    return student1.age - student2.age;
                } else {
                    return nameCompare;
                }
            }
        });

        // Print sorted list
        System.out.println("Sorted Students:");
        for (Student student : list) {
            System.out.println(student);
        }
    }
}
