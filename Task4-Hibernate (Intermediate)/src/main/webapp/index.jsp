<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Student Management</title>
    <style>
        body { font-family: Arial; margin: 40px;
            background: #f4f4f4; }
        h2 { color: #333; }
        form { background: white; padding: 20px;
            border-radius: 8px; width: 450px; }
        input[type=text], input[type=number],
        input[type=email] { width: 100%; padding: 8px;
            margin: 8px 0; box-sizing: border-box; }
        .course-list { margin: 10px 0; }
        .course-item { margin: 5px 0; }
        button { background: #4CAF50; color: white;
            padding: 10px 20px; border: none;
            border-radius: 4px; cursor: pointer; }
        a { color: #4CAF50; display: inline-block;
            margin-top: 15px; }
    </style>
</head>
<body>

<h2>Add New Student</h2>

<form action="student" method="post">
    <input type="hidden" name="action" value="create"/>

    <label>Name:</label>
    <input type="text" name="name"
           placeholder="Enter name" required/><br/>

    <label>Age:</label>
    <input type="number" name="age"
           placeholder="Enter age" required/><br/>

    <label>Email:</label>
    <input type="email" name="email"
           placeholder="Enter email" required/><br/>

    <label>Select Courses:</label>
    <div class="course-list">
        <div class="course-item">
            <input type="checkbox" name="courses"
                   value="Java Programming"/>
            Java Programming
        </div>
        <div class="course-item">
            <input type="checkbox" name="courses"
                   value="Data Structures"/>
            Data Structures
        </div>
        <div class="course-item">
            <input type="checkbox" name="courses"
                   value="Database Systems"/>
            Database Systems
        </div>
        <div class="course-item">
            <input type="checkbox" name="courses"
                   value="Web Development"/>
            Web Development
        </div>
        <div class="course-item">
            <input type="checkbox" name="courses"
                   value="OOP in Java"/>
            OOP in Java
        </div>
    </div>

    <button type="submit">Add Student</button>
</form>

<a href="student">View All Students →</a>

</body>
</html>
