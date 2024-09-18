import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {

    private JLabel timelabel;
    private JButton startbutton, stopButton, resetButton;
    private Timer timer;
    private long startTime;
    private boolean isRunning;

    private void start() {
        if (!isRunning) {
            startTime = System.currentTimeMillis();
            timer.start();
            isRunning = true;
            startbutton.setEnabled(false);
            stopButton.setEnabled(true);
        }
    }

    private void stop() {
        if (isRunning) {
            timer.stop();
            isRunning = false;
            startbutton.setEnabled(true);
            stopButton.setEnabled(false);
        }
    }

    private void reset() {
        stop();
        startTime = 0;
        timelabel.setText("00:00:00");
    }

    public Main() {
        timelabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timelabel.setFont(new Font("Arial", Font.BOLD, 25));
        add(timelabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        startbutton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        startbutton.addActionListener(e -> start());
        stopButton.addActionListener(e -> stop());
        resetButton.addActionListener(e -> reset());

        buttonPanel.add(startbutton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);

        add(buttonPanel, BorderLayout.SOUTH);

        timer = new Timer(1000, e -> {
            long elapsed = System.currentTimeMillis() - startTime;
            int seconds = (int) (elapsed / 1000) % 60;
            int minutes = (int) ((elapsed / (1000 * 60)) % 60);
            int hours = (int) ((elapsed / (1000 * 60 * 60)) % 24);
            timelabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        });

        startTime = 0;
        isRunning = false;

        setTitle("Stopwatch");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Not needed since we use lambda expressions
    }
}
