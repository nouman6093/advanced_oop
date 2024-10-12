import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Database GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(9, 7));

        JButton insertButton = new JButton("Insert");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton displayButton = new JButton("Display");

        JTextField nameText = new JTextField();
        JTextField marksText = new JTextField();
        JTextField subjectText = new JTextField();
        JTextField rollNoText = new JTextField();

        JTable dataTable = new JTable(new DefaultTableModel(new Object[]{"Name", "Marks", "Subject", "RollNo"}, 0));

        insertButton.addActionListener(evt -> {
            insertData(nameText, marksText, subjectText, rollNoText);
            display(dataTable);
        });
        updateButton.addActionListener(evt -> {
            updateData(dataTable);
            display(dataTable);
        });
        deleteButton.addActionListener(evt -> {
            deleteData(rollNoText);
            display(dataTable);
        });
        displayButton.addActionListener(evt -> display(dataTable));

        frame.add(new JLabel("Name"));
        frame.add(nameText);
        frame.add(new JLabel("Marks"));
        frame.add(marksText);
        frame.add(new JLabel("Subject"));
        frame.add(subjectText);
        frame.add(new JLabel("RollNo"));
        frame.add(rollNoText);

        frame.add(insertButton);
        frame.add(updateButton);
        frame.add(deleteButton);
        frame.add(displayButton);

        frame.add(new JScrollPane(dataTable));

        frame.setSize(800, 700);
        frame.setVisible(true);

        display(dataTable);
    }

    private static void insertData(JTextField nameText, JTextField marksText, JTextField subjectText, JTextField rollNoText) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/aoop", "root", "");
            String name = nameText.getText();
            int marks = Integer.parseInt(marksText.getText());
            String subject = subjectText.getText();
            int rollNo = Integer.parseInt(rollNoText.getText());

            String sql = "INSERT INTO assignment(RollNo, Name, Marks, Subject)VALUES(?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, rollNo);
            ps.setString(2, name);
            ps.setInt(3, marks);
            ps.setString(4, subject);

            int insert = ps.executeUpdate();

            if (insert > 0) {
                System.out.println("Inserted");
            } else {
                System.out.println("Check Your Table, Database and Columns");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private static void updateData(JTable dataTable) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/aoop", "root", "");
            String sql = "SELECT * FROM assignment";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = (DefaultTableModel) dataTable.getModel();

            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = {
                        rs.getString("Name"),
                        rs.getInt("Marks"),
                        rs.getString("Subject"),
                        rs.getInt("RollNo")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private static void deleteData(JTextField rollNoText) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/aoop", "root", "");
            int rollNo = Integer.parseInt(rollNoText.getText());

            String sql = "DELETE FROM assignment WHERE RollNo=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, rollNo);

            int delete = ps.executeUpdate();

            if (delete > 0) {
                System.out.println("Deleted");
            } else {
                System.out.println("No record found with RollNo: " + rollNo);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private static void display(JTable dataTable) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/aoop", "root", "");
            String sql = "SELECT * FROM assignment";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
            model.setRowCount(0); // Clear existing data

            while (rs.next()) {
                Object[] row = {
                        rs.getString("Name"),
                        rs.getInt("Marks"),
                        rs.getString("Subject"),
                        rs.getInt("RollNo")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
