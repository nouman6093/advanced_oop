<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.DriverManager" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Signup</title>
</head>
<body>
<%
    String message = (String) request.getAttribute("message");
    if (message == null) {
        message = "";
    }
%>
<p><%= message %></p>

<form action="Servlet" method="post">
    <input type="hidden" name="action" value="add">
    <input type="text" name="name" placeholder="Enter name" required>
    <input type="submit" value="Add">
</form>

<form action="Servlet" method="post">
    <input type="hidden" name="action" value="delete">
    <input type="text" name="name" placeholder="Enter name to delete" required>
    <input type="submit" value="Delete">
</form>

<form action="Servlet" method="post">
    <input type="hidden" name="action" value="update">
    <input type="text" name="oldName" placeholder="Enter old name" required>
    <input type="text" name="newName" placeholder="Enter new name" required>
    <input type="submit" value="Update">
</form>

<div>
    <form action="Servlet" method="post">
        <input type="hidden" name="action" value="display">
        <input type="submit" value="Display Records">
    </form>

    <%
        String query = "SELECT * FROM table1";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/practise", "root", "");
            statement = conn.prepareStatement(query);
            rs = statement.executeQuery();

            if (rs != null) {
    %>

    <table border="1" cellpadding="5" cellspacing="0" style="margin: 20px auto;">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
        </tr>
        </thead>
        <tbody>
        <%
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
        %>
        <tr>
            <td><%= id %></td>
            <td><%= name %></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <%
    } else {
    %>
    <p style="text-align: center;">No records found.</p>
    <%
        }
    } catch (Exception e) {
    %>
    <p style="text-align: center; color: red;">Error: <%= e.getMessage() %></p>
    <%
        } finally {
            if (rs != null) try { rs.close(); } catch (Exception e) {}
            if (statement != null) try { statement.close(); } catch (Exception e) {}
            if (conn != null) try { conn.close(); } catch (Exception e) {}
        }
    %>
</div>

<div>
    <a href="LogoutServlet">Logout</a>
</div>

</body>
</html>
