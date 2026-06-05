<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>All Students</title>
    <style>
        body  { font-family: Arial; margin: 40px; background: #f4f4f4; }
        h2    { color: #333; }
        table { width: 100%; border-collapse: collapse; background: white; }
        th    { background: #4CAF50; color: white; padding: 10px; }
        td    { padding: 10px; border-bottom: 1px solid #ddd; text-align: center; }
        tr:hover { background: #f1f1f1; }
        .btn-update { background: #2196F3; color: white; border: none; padding: 6px 12px; border-radius: 4px; cursor: pointer; }
        .btn-delete { background: #f44336; color: white; border: none; padding: 6px 12px; border-radius: 4px; cursor: pointer; }
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
        <th>Update</th>
        <th>Delete</th>
    </tr>

    <%
        List<String[]> students = (List<String[]>) request.getAttribute("students");
        if (students != null) {
            for (String[] s : students) {
    %>
    <tr>
        <td><%= s[0] %></td>
        <td><%= s[1] %></td>
        <td><%= s[2] %></td>
        <td><%= s[3] %></td>

        <%-- UPDATE FORM --%>
        <td>
            <form action="student" method="post">
                <input type="hidden" name="action"  value="update"/>
                <input type="hidden" name="rollNo"  value="<%= s[0] %>"/>
                <input type="text"   name="name"    value="<%= s[1] %>" style="width:80px"/>
                <input type="number" name="age"     value="<%= s[2] %>" style="width:50px"/>
                <input type="email"  name="email"   value="<%= s[3] %>" style="width:120px"/>
                <button class="btn-update" type="submit">Update</button>
            </form>
        </td>

        <%-- DELETE FORM --%>
        <td>
            <form action="student" method="post">
                <input type="hidden" name="action"  value="delete"/>
                <input type="hidden" name="rollNo"  value="<%= s[0] %>"/>
                <button class="btn-delete" type="submit">Delete</button>
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>

</body>
</html>
