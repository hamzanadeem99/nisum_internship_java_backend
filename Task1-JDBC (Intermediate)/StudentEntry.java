import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentEntry {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        Connection connection = DBConnection.getConnection();

        // Insert Student
        System.out.println("Enter Student Details");
        System.out.print("Roll No: "); int rollNo = scanner.nextInt(); scanner.nextLine();
        System.out.print("Name: "); String name = scanner.nextLine();
        System.out.print("Age: ");  int age = scanner.nextInt(); scanner.nextLine();
        System.out.print("Email: "); String email = scanner.nextLine();

        String insertSQL = "INSERT INTO Student (rollNo, name, age, email) VALUES (?, ?, ?, ?)";
        PreparedStatement insertStatement = connection.prepareStatement(insertSQL);
        insertStatement.setInt(1, rollNo);
        insertStatement.setString(2, name);
        insertStatement.setInt(3, age);
        insertStatement.setString(4, email);
        insertStatement.executeUpdate();
        System.out.println("\nStudent inserted successfully.");

        // Retrieve and Print
        System.out.println("\nStudent Record");
        String selectSQL = "SELECT * FROM Student WHERE rollNo = ?";
        PreparedStatement selectStatement = connection.prepareStatement(selectSQL);
        selectStatement.setInt(1, rollNo);
        ResultSet studentResult = selectStatement.executeQuery();

        if (studentResult.next()) {
            System.out.println("Roll No: " + studentResult.getInt("rollNo"));
            System.out.println("Name: " + studentResult.getString("name"));
            System.out.println("Age: " + studentResult.getInt("age"));
            System.out.println("Email: " + studentResult.getString("email"));
        }

        studentResult.close();
        insertStatement.close();
        selectStatement.close();
    }
}
