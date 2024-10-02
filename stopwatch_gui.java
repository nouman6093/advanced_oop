import javax.imageio.ImageIO;
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
        setTitle("Stopwatch");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        getContentPane().setBackground(Color.LIGHT_GRAY);

        timelabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timelabel.setFont(new Font("Arial", Font.BOLD, 40));
        add(timelabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        ImageIcon startIcon = new ImageIcon(new ImageIcon("C:\\Users\\student\\IdeaProjects\\untitled\\src\\play.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        ImageIcon stopIcon = new ImageIcon(new ImageIcon("C:\\Users\\student\\IdeaProjects\\untitled\\src\\stop.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        ImageIcon resetIcon = new ImageIcon(new ImageIcon("C:\\Users\\student\\IdeaProjects\\untitled\\src\\reset.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

        startbutton = new JButton(startIcon);
        stopButton = new JButton(stopIcon);
        resetButton = new JButton(resetIcon);

        startbutton.addActionListener(e -> start());
        stopButton.addActionListener(e -> stop());
        resetButton.addActionListener(e -> reset());

        customizeButton(startbutton);
        customizeButton(stopButton);
        customizeButton(resetButton);

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
    }

    private void customizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        button.setFocusPainted(false);
        button.setMargin(new Insets(10, 20, 10, 20));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.LIGHT_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
