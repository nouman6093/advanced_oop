import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // Main Frame
        JFrame mainFrame = new JFrame("User Management");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(350, 400);
        mainFrame.setLayout(new BorderLayout());

        // Menu Bar with "Sign Up" and "Login" buttons
        JMenuBar menuBar = new JMenuBar();
        JButton signUpButton = new JButton("Sign Up");
        JButton loginButton = new JButton("Login");
        menuBar.add(signUpButton);
        menuBar.add(loginButton);
        mainFrame.setJMenuBar(menuBar);

        // Sign-Up Form Components
        JPanel signUpPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        signUpPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel label1 = new JLabel("Username");
        JTextField textField1 = new JTextField();
        JLabel label2 = new JLabel("Password");
        JPasswordField textField2 = new JPasswordField();
        JLabel label3 = new JLabel("Role");
        String[] roles = {"Student", "Teacher"};
        JComboBox<String> comboBox = new JComboBox<>(roles);
        JButton signUpSubmitButton = new JButton("Sign Up");

        // Adding components to the Sign-Up panel
        signUpPanel.add(label1);
        signUpPanel.add(textField1);
        signUpPanel.add(label2);
        signUpPanel.add(textField2);
        signUpPanel.add(label3);
        signUpPanel.add(comboBox);
        signUpPanel.add(new JLabel()); // Filler
        signUpPanel.add(signUpSubmitButton);

        mainFrame.add(signUpPanel, BorderLayout.CENTER);

        // Action Listener for "Sign Up" button to show sign-up form
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.setTitle("Sign Up");
                signUpPanel.setVisible(true);
            }
        });

        // Login Frame and Components
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(350, 250);
        loginFrame.setLayout(new BorderLayout());
        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel loginLabel1 = new JLabel("Username");
        JTextField loginTextField1 = new JTextField();
        JLabel loginLabel2 = new JLabel("Password");
        JPasswordField loginTextField2 = new JPasswordField();
        JButton loginSubmitButton = new JButton("Login");

        // Adding components to the Login panel
        loginPanel.add(loginLabel1);
        loginPanel.add(loginTextField1);
        loginPanel.add(loginLabel2);
        loginPanel.add(loginTextField2);
        loginPanel.add(new JLabel()); // Filler
        loginPanel.add(loginSubmitButton);
        loginFrame.add(loginPanel, BorderLayout.CENTER);

        // Action Listener for "Login" button to show login form
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginFrame.setVisible(true);
            }
        });

        // Sign-Up Submit Action
        signUpSubmitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password = new String(textField2.getPassword());
                String role = (String) comboBox.getSelectedItem();

                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(mainFrame, "Username field is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(mainFrame, "Password field is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aoop_login", "root", "")) {
                        String checkQuery = "SELECT * FROM signup_info WHERE username = ?";
                        PreparedStatement checkStatement = con.prepareStatement(checkQuery);
                        checkStatement.setString(1, username);
                        ResultSet rs = checkStatement.executeQuery();

                        if (rs.next()) {
                            JOptionPane.showMessageDialog(mainFrame, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            String query = "INSERT INTO signup_info (username, password, role) VALUES (?, ?, ?)";
                            PreparedStatement statement = con.prepareStatement(query);
                            statement.setString(1, username);
                            statement.setString(2, password);
                            statement.setString(3, role);
                            statement.executeUpdate();
                            JOptionPane.showMessageDialog(mainFrame, "Sign Up Successful");
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(mainFrame, "Failed to connect: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Login Submit Action
        loginSubmitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = loginTextField1.getText();
                String password = new String(loginTextField2.getPassword());

                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(loginFrame, "Username field is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(loginFrame, "Password field is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aoop_login", "root", "")) {
                        String query = "SELECT * FROM signup_info WHERE username = ? AND password = ?";
                        PreparedStatement statement = con.prepareStatement(query);
                        statement.setString(1, username);
                        statement.setString(2, password);
                        ResultSet rs = statement.executeQuery();

                        if (rs.next()) {
                            String role = rs.getString("role");
                            JOptionPane.showMessageDialog(loginFrame, "Sign In Successful");
                            loginFrame.dispose();
                            JFrame dashboardFrame = new JFrame(role + " Dashboard");
                            dashboardFrame.setSize(350, 350);
                            dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            dashboardFrame.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(loginFrame, "Sign In Failed", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(loginFrame, "Failed to connect: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        mainFrame.setVisible(true);
    }
}
