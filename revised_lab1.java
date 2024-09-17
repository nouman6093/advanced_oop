import javax.swing.*;
import java.awt.*;

public class myfile{
    public static void main(String[] args){
        JFrame frame = new JFrame("Nouman Hameed");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setLayout(new BorderLayout());

        JLabel label1 = new JLabel("00:00:00", SwingConstants.CENTER);
        label1.setFont(new Font("Arial", Font.BOLD, 25));

        frame.add(label1, BorderLayout.CENTER);

        JPanel panel1 = new JPanel(new GridLayout(1, 3));

        JButton button1 = new JButton("Start");
        JButton button2 = new JButton("Stop");
        JButton button3 = new JButton("Reset");

        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);

        frame.add(panel1, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
