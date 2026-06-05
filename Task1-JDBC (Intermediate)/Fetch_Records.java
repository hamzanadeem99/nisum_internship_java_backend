import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Fetch_Records {

    public static void main(String[] args) throws Exception {

        Connection connection = DBConnection.getConnection();

        if (connection != null) {

            Statement statement = connection.createStatement();

            // Fetch Student Table
            System.out.println("Student Table");

            ResultSet studentResultSet = statement.executeQuery("SELECT * FROM Student");

            while (studentResultSet.next()) {
                int rollNo    = studentResultSet.getInt("rollNo");
                String name   = studentResultSet.getString("name");
                int age       = studentResultSet.getInt("age");
                String email  = studentResultSet.getString("email");

                System.out.printf("%-10d %-20s %-5d %-25s%n", rollNo, name, age, email);
            }

            studentResultSet.close();

            // Fetch Course Table
            System.out.println("\nCourse Table");

            ResultSet courseResultSet = statement.executeQuery("SELECT * FROM Course");

            while (courseResultSet.next()) {
                int courseId       = courseResultSet.getInt("courseId");
                int rollNo         = courseResultSet.getInt("rollNo");
                String courseName  = courseResultSet.getString("courseName");
                int creditHours    = courseResultSet.getInt("creditHours");

                System.out.printf("%-10d %-10d %-20s %-5d%n", courseId, rollNo, courseName, creditHours);
            }

            courseResultSet.close();
            statement.close();
        }
    }
}
