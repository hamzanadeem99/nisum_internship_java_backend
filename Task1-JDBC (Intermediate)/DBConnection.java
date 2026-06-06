import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    private static Connection connection = null;

    private DBConnection() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connected to MySQL successfully.");
            } catch (ClassNotFoundException e) {
                System.out.println("MySQL Driver not found. Add mysql-connector-j.jar to your project");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void main(String[] args) throws SQLException {
        Connection connection = getConnection();
        if (connection != null) {
            System.out.println("Connection test successful.");
        }
    }
}
