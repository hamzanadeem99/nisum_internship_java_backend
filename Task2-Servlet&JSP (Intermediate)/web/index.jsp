<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Student Management</title>
    <style>
        body { font-family: Arial; margin: 40px; background: #f4f4f4; }
        h2   { color: #333; }
        form { background: white; padding: 20px; border-radius: 8px; width: 400px; }
        input { width: 100%; padding: 8px; margin: 8px 0; box-sizing: border-box; }
        button { background: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; }
        a { display: inline-block; margin-top: 20px; color: #4CAF50; }
    </style>
</head>
<body>

<h2>Add New Student</h2>
<form action="student" method="post">
    <input type="hidden" name="action" value="create"/>
    <input type="number" name="rollNo"  placeholder="Roll No"  required/><br/>
    <input type="text"   name="name"    placeholder="Name"     required/><br/>
    <input type="number" name="age"     placeholder="Age"      required/><br/>
    <input type="email"  name="email"   placeholder="Email"    required/><br/>
    <button type="submit">Add Student</button>
</form>

<a href="student">View All Students →</a>

</body>
</html>
