package com.code;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet("/todo")
public class ToDoServlet extends HttpServlet {

    private Connection connection;

    public void init() throws ServletException {
        try {
            // Setup database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aoop_project", "root", "");
        } catch (Exception e) {
            throw new ServletException("Database connection problem", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if user is logged in (userId should be in the session)
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            // Redirect to login if userId is not found in session (user is not logged in)
            response.sendRedirect("login.jsp");
            return;
        }

        // Redirect to home.jsp without passing tasks (removed task fetching functionality)
        response.sendRedirect("home.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if user is logged in (userId should be in the session)
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            // Redirect to login if userId is not found in session (user is not logged in)
            response.sendRedirect("login.jsp");
            return;
        }

        // Continue with processing using userId if the user is logged in
        String action = request.getParameter("action");

        // Handling different actions based on the form submission
        if ("add".equals(action)) {
            String toDo = request.getParameter("to_do");
            String description = request.getParameter("description");
            String dueDate = request.getParameter("due_date");
            String dueTime = request.getParameter("due_time");
            String important = request.getParameter("important");
            boolean sendMail = request.getParameter("send_mail") != null;

            try {
                String query = "INSERT INTO to_do_table (to_do, description, due_date, due_time, send_mail, important) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, toDo);
                ps.setString(2, description);
                ps.setString(3, dueDate);
                ps.setString(4, dueTime);
                ps.setInt(5, sendMail ? 1 : 0);
                ps.setBoolean(6, Boolean.parseBoolean(important));
                ps.executeUpdate();

                if (sendMail) {
                    sendEmailReminder(request);
                }

                // Pass success message to JSP
                request.setAttribute("message", "Task added successfully!");
                request.getRequestDispatcher("home.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new ServletException("Error adding task", e);
            }
        }
    }

    private void sendEmailReminder(HttpServletRequest request) {
        // Sending email code here...
    }

    public void destroy() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
