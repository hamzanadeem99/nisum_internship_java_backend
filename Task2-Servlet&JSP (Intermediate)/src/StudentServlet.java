import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    // Show all students (GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String[]> students = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Student");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                students.add(new String[]{
                        String.valueOf(rs.getInt("rollNo")),
                        rs.getString("name"),
                        String.valueOf(rs.getInt("age")),
                        rs.getString("email")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("students", students);
        request.getRequestDispatcher("students.jsp").forward(request, response);
    }

    // Handle form submission (POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // null check for action
        if (action == null) {
            response.sendRedirect("student");
            return;
        }

        try {
            Connection conn = DBConnection.getConnection();

            // CREATE
            if (action.equals("create")) {
                int rollNo   = Integer.parseInt(request.getParameter("rollNo"));
                String name  = request.getParameter("name");
                int age      = Integer.parseInt(request.getParameter("age"));
                String email = request.getParameter("email");

                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO Student (rollNo, name, age, email) VALUES (?, ?, ?, ?)");
                ps.setInt(1, rollNo);
                ps.setString(2, name);
                ps.setInt(3, age);
                ps.setString(4, email);
                ps.executeUpdate();
            }

            // UPDATE
            else if (action.equals("update")) {
                int rollNo   = Integer.parseInt(request.getParameter("rollNo"));
                String name  = request.getParameter("name");
                int age      = Integer.parseInt(request.getParameter("age"));
                String email = request.getParameter("email");

                PreparedStatement ps = conn.prepareStatement(
                        "UPDATE Student SET name=?, age=?, email=? WHERE rollNo=?");
                ps.setString(1, name);
                ps.setInt(2, age);
                ps.setString(3, email);
                ps.setInt(4, rollNo);
                ps.executeUpdate();
            }

            // DELETE
            else if (action.equals("delete")) {

                String rollNoParam = request.getParameter("rollNo");
                System.out.println("DELETE requested");
                System.out.println("action received: " + action);
                System.out.println("rollNo received: " + rollNoParam);

                // null check for rollNo
                if (rollNoParam == null || rollNoParam.trim().isEmpty()) {
                    System.out.println("ERROR: rollNo is null or empty!");
                    response.sendRedirect("student");
                    return;
                }

                int rollNo = Integer.parseInt(rollNoParam);

                // First delete courses of this student
                PreparedStatement deleteCourses = conn.prepareStatement(
                        "DELETE FROM Course WHERE rollNo=?");
                deleteCourses.setInt(1, rollNo);
                int coursesDeleted = deleteCourses.executeUpdate();
                System.out.println("Courses deleted: " + coursesDeleted);

                // Then delete the student
                PreparedStatement deleteStudent = conn.prepareStatement(
                        "DELETE FROM Student WHERE rollNo=?");
                deleteStudent.setInt(1, rollNo);
                int rowsAffected = deleteStudent.executeUpdate();
                System.out.println("Student deleted: " + rowsAffected);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: " + e.getMessage());
            e.printStackTrace();
        }

        response.sendRedirect("student");
    }
}
