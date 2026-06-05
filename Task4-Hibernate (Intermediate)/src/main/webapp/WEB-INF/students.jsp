<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.student.model.Student" %>
<%@ page import="com.student.model.Course" %>
<html>
<head>
    <title>All Students</title>
    <style>
        body  { font-family: Arial; margin: 40px;
            background: #f4f4f4; }
        table { width: 100%; border-collapse: collapse;
            background: white; }
        th    { background: #4CAF50; color: white;
            padding: 10px; }
        td    { padding: 10px; border-bottom: 1px solid #ddd;
            text-align: center; }
        tr:hover { background: #f1f1f1; }
        .btn-edit { background: #2196F3; color: white;
            border: none; padding: 6px 12px;
            border-radius: 4px; cursor: pointer;
            text-decoration: none; }
        .btn-delete { background: #f44336; color: white;
            border: none; padding: 6px 12px;
            border-radius: 4px; cursor: pointer; }
        .course-tag { background: #e3f2fd; padding: 2px 8px;
            border-radius: 10px; margin: 2px;
            display: inline-block; font-size: 12px; }
        a { color: #4CAF50; }
    </style>
</head>
<body>

<h2>All Students</h2>
<a href="index.jsp">+ Add New Student</a>
<br/><br/>

<table>
    <tr>
        <th>Roll No</th>
        <th>Name</th>
        <th>Age</th>
        <th>Email</th>
        <th>Courses</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>

    <%
        List<Student> students =
                (List<Student>) request.getAttribute("students");
        if (students != null && !students.isEmpty()) {
            for (Student s : students) {
    %>
    <tr>
        <td><%= s.getRollNo() %></td>
        <td><%= s.getName()   %></td>
        <td><%= s.getAge()    %></td>
        <td><%= s.getEmail()  %></td>
        <td>
            <%
                List<Course> courses = s.getCourses();
                if (courses != null && !courses.isEmpty()) {
                    for (Course c : courses) {
            %>
            <span class="course-tag">
                    <%= c.getCourseName() %>
                </span>
            <%
                }
            } else {
            %>
            <span>No courses</span>
            <%  } %>
        </td>
        <td>
            <a class="btn-edit"
               href="student?action=edit&rollNo=<%= s.getRollNo() %>">
                Edit
            </a>
        </td>
        <td>
            <form action="student" method="get">
                <input type="hidden" name="action" value="delete"/>
                <input type="hidden" name="rollNo"
                       value="<%= s.getRollNo() %>"/>
                <button class="btn-delete" type="submit"
                        onclick="return confirm(
                                'Delete <%= s.getName() %>?')">
                    Delete
                </button>
            </form>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="7">No students found</td>
    </tr>
    <%  } %>
</table>

</body>
</html>
