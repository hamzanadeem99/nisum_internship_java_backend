<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.student.model.Student" %>
<%@ page import="com.student.model.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Set" %>
<html>
<head>
    <title>Edit Student</title>
    <style>
        body { font-family: Arial; margin: 40px;
            background: #f4f4f4; }
        form { background: white; padding: 20px;
            border-radius: 8px; width: 450px; }
        input[type=text], input[type=number],
        input[type=email] {
            width: 100%; padding: 8px;
            margin: 8px 0; box-sizing: border-box; }
        .course-list { margin: 10px 0; }
        .course-item { margin: 5px 0; }
        button { background: #2196F3; color: white;
            padding: 10px 20px; border: none;
            border-radius: 4px; cursor: pointer; }
        a { color: #4CAF50; display: inline-block;
            margin-top: 10px; }
    </style>
</head>
<body>

<%
    Student student =
            (Student) request.getAttribute("student");
    List<Course> studentCourses = student.getCourses();

    Set<String> selectedCourseNames = new HashSet<>();
    for (Course c : studentCourses) {
        selectedCourseNames.add(c.getCourseName());
    }
%>

<h2>Edit Student</h2>

<form action="student" method="post">
    <input type="hidden" name="action" value="update"/>
    <input type="hidden" name="rollNo"
           value="<%= student.getRollNo() %>"/>

    <label>Name:</label>
    <input type="text" name="name"
           value="<%= student.getName() %>" required/><br/>

    <label>Age:</label>
    <input type="number" name="age"
           value="<%= student.getAge() %>" required/><br/>

    <label>Email:</label>
    <input type="email" name="email"
           value="<%= student.getEmail() %>" required/><br/>

    <label>Select Courses:</label>
    <div class="course-list">

        <div class="course-item">
            <input type="checkbox" name="courses"
                   value="Java Programming"
                    <%= selectedCourseNames.contains(
                            "Java Programming") ? "checked" : "" %>/>
            Java Programming
        </div>

        <div class="course-item">
            <input type="checkbox" name="courses"
                   value="Data Structures"
                    <%= selectedCourseNames.contains(
                            "Data Structures") ? "checked" : "" %>/>
            Data Structures
        </div>

        <div class="course-item">
            <input type="checkbox" name="courses"
                   value="Database Systems"
                    <%= selectedCourseNames.contains(
                            "Database Systems") ? "checked" : "" %>/>
            Database Systems
        </div>

        <div class="course-item">
            <input type="checkbox" name="courses"
                   value="Web Development"
                    <%= selectedCourseNames.contains(
                            "Web Development") ? "checked" : "" %>/>
            Web Development
        </div>

        <div class="course-item">
            <input type="checkbox" name="courses"
                   value="OOP in Java"
                    <%= selectedCourseNames.contains(
                            "OOP in Java") ? "checked" : "" %>/>
            OOP in Java
        </div>

    </div>

    <button type="submit">Update Student</button>
</form>

<a href="student">← Back to Students</a>

</body>
</html>
