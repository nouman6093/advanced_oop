package com.code;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("add")) {
            String name = request.getParameter("name");
            Connection conn = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localost:3306/practise", "root", "");
                String query = "INSERT INTO table1 (name) VALUES (?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, name);
                ps.executeUpdate();
            } catch (ClassNotFoundException | SQLException e) {
                request.setAttribute("message", "error occured while deleting from database");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    request.setAttribute("message", "error occured while closing connection");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }
        } else if (action.equals("delete")) {
            String name = request.getParameter("name");
            Connection conn = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localost:3306/practise", "root", "");
                String query = "DELETE FROM table1 WHERE name = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, name);
                ps.executeUpdate();
            } catch (ClassNotFoundException | SQLException e) {
                request.setAttribute("message", "error occured while deleting from database");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    request.setAttribute("message", "error occured while closing connection");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }
        } else if (action.equals("update")) {
            String oldName = request.getParameter("oldName");
            String newName = request.getParameter("newName");
            Connection conn = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/practise", "root", "");
                String query = "UPDATE table1 SET name = ? WHERE name = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, newName);
                ps.setString(2, oldName);
                ps.executeUpdate();
            } catch (ClassNotFoundException | SQLException e) {
                request.setAttribute("message", "Error occurred while updating in the database");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    request.setAttribute("message", "Error occurred while closing connection");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }
        }
    }
}
