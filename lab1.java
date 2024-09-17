import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.event.ActionEvent;
public class Main extends JFrame {

    private JLabel timelabel;
    private JButton startbutton, stopButton, resetButton;
    private Timer timer;
    private long startTime;
    private boolean isRunning;

    public Main(){
        timelabel = new JLabel("00:00:00", SwingConstants.LEFT);
        timelabel.setFont(new Font("Arial", Font.BOLD, 25));
        add(timelabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        startbutton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");


        buttonPanel.add(startbutton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);

        add(buttonPanel,BorderLayout.SOUTH);

        setTitle("Nouman Hameed");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
            new Main().setVisible(true);
    }
}
