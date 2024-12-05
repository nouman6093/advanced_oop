<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Display Books</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            text-align: left;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h1>Books List</h1>

<%
    String sql = "SELECT * FROM books";
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aoop_login", "root", "");
        statement = conn.prepareStatement(sql);
        rs = statement.executeQuery();
        if (rs != null) {
%>

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Author</th>
        <th>Category</th>
    </tr>
    </thead>
    <tbody>
    <%
        while (rs.next()) {
            String name = rs.getString("title");
            String author = rs.getString("author");
            String category = rs.getString("genre");
    %>
    <tr>
        <td><%= name %></td>
        <td><%= author %></td>
        <td><%= category %></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<%
} else {
%>
<p>No books available.</p>
<%
    }
} catch (Exception e) {
    e.printStackTrace();
%>
<p>Error: <%= e.getMessage() %></p>
<%
    }
%>

</body>
</html>
