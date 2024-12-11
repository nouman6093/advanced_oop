package com.code;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;

@WebServlet("/updateProfile")
public class UpdateProfileServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get user data from request
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        Part profileImagePart = request.getPart("image");

        // Get user details from session
        HttpSession session = request.getSession();
        String currentUsername = (String) session.getAttribute("username"); // Retrieve username from session
        Integer userId = (Integer) session.getAttribute("userId"); // Retrieve userId from session

        // Handle image upload (if provided)
        String profileImagePath = null;
        if (profileImagePart != null && profileImagePart.getSize() > 0) {
            String imageName = Paths.get(profileImagePart.getSubmittedFileName()).getFileName().toString();
            String uploadDir = getServletContext().getRealPath("/uploads");
            File uploadFile = new File(uploadDir, imageName);
            profileImagePart.write(uploadFile.getAbsolutePath());
            profileImagePath = "/uploads/" + imageName;
        }

        // Update or insert the user profile in the database using userId
        boolean profileUpdated = saveOrUpdateUserProfile(userId, name, email, profileImagePath);

        if (profileUpdated) {
            request.setAttribute("message", "Profile updated successfully.");
        } else {
            request.setAttribute("message", "Failed to update profile.");
        }

        // Retrieve updated profile data (name, email, and image) to show in the settings page
        String userProfileImage = getUserProfileImage(userId);

        // Forward to settings page with updated data
        request.setAttribute("userName", name);
        request.setAttribute("userEmail", email);
        request.setAttribute("profileImage", userProfileImage);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    // Method to check if profile exists and update or insert
    private boolean saveOrUpdateUserProfile(int userId, String name, String email, String profileImagePath) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Establish database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aoop_project", "root", "");

            // First, check if a profile already exists for the user
            String checkSql = "SELECT COUNT(*) FROM profile WHERE id = ?";
            stmt = conn.prepareStatement(checkSql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            boolean recordExists = false;
            if (rs.next() && rs.getInt(1) > 0) {
                recordExists = true;
            }
            rs.close();

            if (recordExists) {
                // If profile exists, update it
                String updateSql = "UPDATE profile SET name = ?, email = ?, image = ? WHERE id = ?";
                stmt = conn.prepareStatement(updateSql);
                stmt.setString(1, name);
                stmt.setString(2, email);
                if (profileImagePath != null) {
                    stmt.setString(3, profileImagePath);
                } else {
                    stmt.setNull(3, Types.VARCHAR); // Set the column as NULL if no image is provided
                }
                stmt.setInt(4, userId);
                int rowsUpdated = stmt.executeUpdate();
                return rowsUpdated > 0;
            } else {
                // If no profile exists, insert a new record
                String insertSql = "INSERT INTO profile (id, name, email, image) VALUES (?, ?, ?, ?)";
                stmt = conn.prepareStatement(insertSql);
                stmt.setInt(1, userId);
                stmt.setString(2, name);
                stmt.setString(3, email);
                if (profileImagePath != null) {
                    stmt.setString(4, profileImagePath);
                } else {
                    stmt.setNull(4, Types.VARCHAR); // Set the column as NULL if no image is provided
                }
                int rowsInserted = stmt.executeUpdate();
                return rowsInserted > 0;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null && !conn.isClosed()) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to retrieve the user's profile image
    private String getUserProfileImage(int userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aoop_project", "root", "");

            // SQL query to get profile image path using userId
            String sql = "SELECT image FROM profile WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String imagePath = rs.getString("image");
                if (imagePath != null) {
                    return imagePath; // Return the stored image URL
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null && !conn.isClosed()) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Return a default image if no profile image is found
        return "https://cdn-icons-png.freepik.com/512/17740/17740782.png"; // Default image if not found
    }
}
