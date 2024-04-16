import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SnakeStartup extends JFrame {

    private JButton startButton;
    private JButton exitButton;

    public SnakeStartup() {
        // Set the title of the window
        super("Snake Byte");
        setSize(600, 600);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.BLACK);

        // game title
        JLabel lblTheSnakeGame = new JLabel("The Snake Byte", SwingConstants.CENTER);
        lblTheSnakeGame.setFont(new Font("Ink Free", Font.BOLD, 60));
        lblTheSnakeGame.setForeground(Color.RED);
        lblTheSnakeGame.setBounds(41, 85, 500, 89);

        //label
        getContentPane().add(lblTheSnakeGame);

        // adding a pic
        JLabel picLabel = new JLabel(new ImageIcon("C:\\Users\\hp\\Desktop\\Eclipse - OOP\\snake game\\images\\snakee.png"));
        picLabel.setFont(new Font("Tahoma", Font.PLAIN, 41));
        picLabel.setForeground(Color.WHITE);
        picLabel.setBounds(262, 203, 266, 333);
        getContentPane().add(picLabel);
        
        //adding a gif
        JLabel gifLabel = new JLabel(new ImageIcon("C:\\Users\\hp\\Desktop\\Eclipse - OOP\\snake game\\images\\giphy (2) (1).gif"));
        gifLabel.setFont(new Font("Tahoma", Font.PLAIN, 41));
        gifLabel.setForeground(Color.WHITE);
        gifLabel.setBounds(385, 152, 150, 143);
        getContentPane().add(gifLabel);

        // Create start button
        startButton = new JButton("Play");
        startButton.setFont(new Font("Ink Free", Font.BOLD, 34));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(new Color(0, 0, 0));
        startButton.setBounds(102, 288, 150, 50);
        startButton.setBorderPainted(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // diri ibutang ang code para mag start ang game
            }
        });
        MouseAdapter startmouseAdapter = new MouseAdapter() { //change font color when hovering or clicked
            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                startButton.setForeground(Color.WHITE);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                startButton.setForeground(Color.RED);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                startButton.setForeground(Color.WHITE);
            }
        };
        startButton.addMouseListener(startmouseAdapter);
        getContentPane().add(startButton);

        
        // Create exit button
        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Ink Free", Font.BOLD, 34));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(0, 0, 0));
        exitButton.setBounds(102, 366, 150, 50);
        exitButton.setBorderPainted(false);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        MouseAdapter exitmouseAdapter = new MouseAdapter() { //change font color when hovering or clicked
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setForeground(Color.WHITE);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                exitButton.setForeground(Color.RED);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                exitButton.setForeground(Color.WHITE);
            }
        };
        exitButton.addMouseListener(exitmouseAdapter); 
        getContentPane().add(exitButton);

        
        // Set the window to be visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Create a new SnakeStartup object
        new SnakeStartup();
    }
}

