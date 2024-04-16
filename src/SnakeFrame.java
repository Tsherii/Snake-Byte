import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class SnakeFrame extends JFrame {
	
	SnakeFrame(){
		
		this.add(new SnakePanel());
		this.setTitle("Snake Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		ImageIcon image = new ImageIcon("snake_game_icon.png");
		this.setIconImage(image.getImage());
		
	}
}