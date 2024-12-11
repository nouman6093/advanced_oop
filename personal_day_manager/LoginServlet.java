package com.code;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aoop_project", "root", "");

            // Validate username and password in the signup_info table
            String query = "SELECT * FROM signup_info WHERE username = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                // User authenticated, retrieve userId and set session attributes
                int userId = rs.getInt("id"); // Assuming 'signup_info' has an 'id' column
                HttpSession session = request.getSession();
                session.setAttribute("userId", userId); // Set userId in session
                session.setAttribute("username", username); // Set username in session

                // Retrieve all tasks for the logged-in user
                String sql = "SELECT * FROM to_do_table WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, userId);
                ResultSet taskResultSet = stmt.executeQuery();
                List<Task> allTasks = new ArrayList<>();
                while (taskResultSet.next()) {
                    Task task = new Task(
                            taskResultSet.getInt("id"),
                            taskResultSet.getString("to_do"),
                            taskResultSet.getString("description"),
                            taskResultSet.getDate("due_date"),
                            taskResultSet.getTime("due_time"),
                            taskResultSet.getBoolean("send_mail"),
                            taskResultSet.getBoolean("important")
                    );
                    allTasks.add(task);
                }
                request.setAttribute("allTasks", allTasks); // Set all tasks to request attribute

                // Retrieve only important tasks (important = true)
                String sqlImportant = "SELECT * FROM to_do_table WHERE id = ? AND important = TRUE";
                PreparedStatement stmtImportant = conn.prepareStatement(sqlImportant);
                stmtImportant.setInt(1, userId);
                ResultSet importantTasksResultSet = stmtImportant.executeQuery();
                List<Task> completedTasks = new ArrayList<>();
                while (importantTasksResultSet.next()) {
                    Task task = new Task(
                            importantTasksResultSet.getInt("id"),
                            importantTasksResultSet.getString("to_do"),
                            importantTasksResultSet.getString("description"),
                            importantTasksResultSet.getDate("due_date"),
                            importantTasksResultSet.getTime("due_time"),
                            importantTasksResultSet.getBoolean("send_mail"),
                            importantTasksResultSet.getBoolean("important")
                    );
                    completedTasks.add(task);
                }
                request.setAttribute("completedTasks", completedTasks); // Set completed tasks to request attribute

                // Forward to home.jsp
                request.getRequestDispatcher("/home.jsp").forward(request, response);
            } else {
                // Invalid login credentials
                request.setAttribute("message", "Invalid username or password. Please try again.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred during login. Please try again." + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
