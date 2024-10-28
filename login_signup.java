import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame1 = new JFrame("Sign Up");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(300, 300);
        frame1.setLayout(new BorderLayout());

        JLabel label1 = new JLabel("Username");
        JTextField textField1 = new JTextField();
        JLabel label2 = new JLabel("Password");
        JTextField textField2 = new JTextField();

        JButton button1 = new JButton("Sign Up");
        JButton button2 = new JButton("Login");

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3, 2));
        panel1.add(label1);
        panel1.add(textField1);
        panel1.add(label2);
        panel1.add(textField2);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 1));
        panel2.add(button1);
        panel2.add(button2);

        frame1.add(panel1, BorderLayout.CENTER);
        frame1.add(panel2, BorderLayout.SOUTH);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password = textField2.getText();
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aoop_login", "root", "")) {
                    String query = "INSERT INTO signup_info (username, password) VALUES (?, ?)";
                    PreparedStatement statement = con.prepareStatement(query);
                    statement.setString(1, username);
                    statement.setString(2, password);
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(frame1, "Sign Up Successful");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame1, "Failed to connect: " + ex.getMessage());
                }
            }
        });

        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String username = textField1.getText();
                String password = textField2.getText();
                try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aoop_login","root","")){
                    String query = "SELECT * FROM signup_info WHERE username = ? AND password = ?";
                    PreparedStatement statement = con.prepareStatement(query);
                    statement.setString(1, username);
                    statement.setString(2, password);
                    ResultSet rs = statement.executeQuery();
                    if(rs.next()){
                        JOptionPane.showMessageDialog(frame1, "Sign In Successful");
                    } else {
                        JOptionPane.showMessageDialog(frame1, "Sign In Failed");
                    }
                } catch (SQLException ex){
                    JOptionPane.showMessageDialog(frame1, "Failed to connect");
                }
            }
        });

        frame1.setVisible(true);
    }
}
