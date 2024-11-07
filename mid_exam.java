import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Meal: ");
        String meal = sc.nextLine();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "");

            System.out.println("Enter 1 for add 2 for update 3 for delete and 4 for display: ");
            int n = sc.nextInt();

            if(n == 1){
                String addSql = "INSERT INTO hotel_tb (meal) VALUES (?)";
                PreparedStatement ps = conn.prepareStatement(addSql);
                ps.setString(1, meal);
                int inserted = ps.executeUpdate();
                if(inserted > 0){
                    System.out.println("Added Successfully");
                } else {
                    System.out.println("failed to add");
                }
            } else if(n == 2){
                String update = "UPDATE hotel_tb SET meal = ? WHERE id = 1";
                PreparedStatement ps = conn.prepareStatement(update);
                ps.setString(1, meal);
                int updated = ps.executeUpdate();
                if(updated > 0){
                    System.out.println("Updated Successfully");
                } else {
                    System.out.println("failed to update");
                }
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
