import javax.xml.crypto.Data;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void display(){
        Connection con = null;  //It will be used to establish a connection to the database.
        PreparedStatement ps = null;    //used to execute SQL queries with parameters, which helps prevent SQL injection attacks.
        ResultSet rs = null;    //It's used to store the result of a SQL query

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/aoop", "root", "");

            String sql = "SELECT * FROM assignment";
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()){
                int rollNo = rs.getInt("RollNo");
                String name = rs.getString("Name");
                int marks = rs.getInt("Marks");
                String subject = rs.getString("Subject");
                System.out.println("Result: RollNo : " + rollNo + "Name: " + name + "Marks: " + marks + "Subject: " + subject);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void insertData(){
        Connection con = null;  //It will be used to establish a connection to the database.
        PreparedStatement ps = null;    //used to execute SQL queries with parameters, which helps prevent SQL injection attacks.
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/aoop", "root", "");
            String sql = "INSERT INTO assignment(RollNo, Name, Marks, Subject)VALUES(?, ?, ?, ?)";
            ps = con.prepareStatement(sql);

            ps.setInt(1, 10);
            ps.setString(2, "abc");
            ps.setInt(3, 12);
            ps.setString(4, "Aoop");
            int rows = ps.executeUpdate();

            int insert = ps.executeUpdate();

            if (insert > 0){
                System.out.println("Inserted");
            } else {
                System.out.println("Check Your Table, Database and Columns");
            }
        } catch ( SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args){
        insertData();
        display();
    }
}
