package com.student.servlet;

import com.student.dao.StudentDAO;
import com.student.model.Course;
import com.student.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private StudentDAO studentDAO = new StudentDAO();

    @Override
    public void init() throws ServletException {
        System.out.println("StudentServlet initialized");
        try {
            com.student.util.HibernateUtil.getSessionFactory();
            System.out.println("Hibernate loaded OK");
        } catch (Exception e) {
            System.out.println("Hibernate FAILED");
            e.printStackTrace();
        }
    }

    // Get
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        System.out.println("doGet called, action: " + action);

        if (action == null) {
            List<Student> students = studentDAO.getAllStudents();
            System.out.println("Students fetched: " + (students != null ? students.size() : "null"));
            request.setAttribute("students", students);
            request.getRequestDispatcher("/WEB-INF/students.jsp").forward(request, response);
        } else if ("edit".equals(action)) {
            int rollNo = Integer.parseInt(request.getParameter("rollNo"));
            Student student = studentDAO.getStudentById(rollNo);
            request.setAttribute("student", student);
            request.getRequestDispatcher("/WEB-INF/editStudent.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            int rollNo = Integer.parseInt(request.getParameter("rollNo"));
            studentDAO.deleteStudent(rollNo);
            response.sendRedirect(request.getContextPath() + "/student");
        }
    }

    // Post
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        System.out.println("doPost called, action: " + action);

        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/student");
            return;
        }

        // Create
        if ("create".equals(action)) {
            String name = request.getParameter("name");
            String ageParam = request.getParameter("age");
            String email = request.getParameter("email");

            System.out.println("Creating: " + name + " age:" + ageParam + " email:" + email);

            int age = Integer.parseInt(ageParam);
            String[] selectedCourses = request.getParameterValues("courses");

            Student student = new Student();
            student.setName(name);
            student.setAge(age);
            student.setEmail(email);

            if (selectedCourses != null) {
                for (String courseName : selectedCourses) {
                    Course course = new Course();
                    course.setCourseName(courseName);
                    course.setCreditHours(3);
                    student.addCourse(course);
                }
            }

            studentDAO.saveStudent(student);
            response.sendRedirect(request.getContextPath() + "/student");
        }

        // Update
        else if ("update".equals(action)) {
            int rollNo = Integer.parseInt(request.getParameter("rollNo"));
            String name = request.getParameter("name");
            int age = Integer.parseInt(request.getParameter("age"));
            String email = request.getParameter("email");
            String[] selectedCourses = request.getParameterValues("courses");

            System.out.println("Updating: " + rollNo);

            Student student = studentDAO.getStudentById(rollNo);
            student.setName(name);
            student.setAge(age);
            student.setEmail(email);
            student.getCourses().clear();

            if (selectedCourses != null) {
                for (String courseName : selectedCourses) {
                    Course course = new Course();
                    course.setCourseName(courseName);
                    course.setCreditHours(3);
                    student.addCourse(course);
                }
            }

            studentDAO.updateStudent(student);
            response.sendRedirect(request.getContextPath() + "/student");
        }
    }
}
