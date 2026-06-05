import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentCourses {

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        Connection connection = DBConnection.getConnection();

        System.out.print("Enter Student Roll No: ");
        int rollNo = scanner.nextInt();

        // Get Student Info
        PreparedStatement studentStatement = connection.prepareStatement(
                "SELECT * FROM Student WHERE rollNo = ?");
        studentStatement.setInt(1, rollNo);
        ResultSet studentResult = studentStatement.executeQuery();

        if (studentResult.next()) {
            System.out.println("\nStudent: " + studentResult.getString("name"));
            System.out.println("Email: " + studentResult.getString("email"));
        } else {
            System.out.println("No student found with Roll No: " + rollNo);
            return;
        }

        // Get Courses
        PreparedStatement courseStatement = connection.prepareStatement(
                "SELECT * FROM Course WHERE rollNo = ?");
        courseStatement.setInt(1, rollNo);
        ResultSet courseResult = courseStatement.executeQuery();

        System.out.println("Enrolled Courses");
        System.out.printf("%-10s %-25s %-12s%n", "Course ID", "Course Name", "Credit Hours");

        boolean found = false;
        while (courseResult.next()) {
            found = true;
            System.out.printf("%-10d %-25s %-12d%n",
                    courseResult.getInt("courseId"),
                    courseResult.getString("courseName"),
                    courseResult.getInt("creditHours"));
        }

        if (!found) System.out.println("No courses found for this student.");

        studentResult.close();
        courseResult.close();
        studentStatement.close();
        courseStatement.close();
    }
}
