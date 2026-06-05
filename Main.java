public class Main {
    public static void main(String[] args) {
        // Create a new student object
        Student student1 = new Student();

        // Using setters to assign data
        student1.setName("Hamza Nadeem");
        student1.setRollNumber("bc240220196");
        student1.setDepartment("Computer Science");
        student1.setCgpa(3.5);
        student1.setSemester(8);

        // Accessing data via getters
        System.out.println("Student Profile");
        System.out.println("Name: " + student1.getName());
        System.out.println("Roll No: " + student1.getRollNumber());
        System.out.println("Department: " + student1.getDepartment());
        System.out.println("CGPA: " + student1.getCgpa());
        System.out.println("Semester: " + student1.getSemester());
    }
}
