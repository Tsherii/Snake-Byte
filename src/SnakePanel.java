import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import javax.swing.JPanel;

public class SnakePanel extends JPanel implements ActionListener{
	
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 75;
	
	//hold the coordinates sa snake
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	
	int bodyParts = 6, applesEaten, appleX, appleY, highscore;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;
	
	SnakePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	
	public void startGame() {
		newApple();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		draw(graphics);
		
		//Trial
        if (!running) {
            gameOver(graphics);
        }
	}
	
	public void draw(Graphics graphics) {
		
		if(running) {
			
			//GRID LINES
			/*for(int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++) {
				graphics.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				graphics.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
			}*/
			
			//ang apple
			graphics.setColor(Color.red);
			graphics.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
			
			//e draw ang ulo og ang body sa snake
			for(int i = 0; i < bodyParts; i++) {			
				if(i == 0) {
					graphics.setColor(Color.green);
					graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
				else {
					graphics.setColor(new Color(45, 180, 0));
					graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
				
			}
			
			graphics.setColor(Color.white);
	        graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 25));
	        FontMetrics metrics = getFontMetrics(graphics.getFont());

	        if (!running) {
	            // Display only the high score when the game is over
	            graphics.drawString("High Score: " + highscore, (SCREEN_WIDTH - metrics.stringWidth("High Score: " + highscore)) / 2, graphics.getFont().getSize());
	        } else {
	            // Display the current score during the game
	            graphics.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, graphics.getFont().getSize());
	        }
		}
		else {
			gameOver(graphics);
		}
		
	}
	
	public void newApple() {
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
	}
	
	public void move() {
		
		for(int i = bodyParts; i > 0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) {
		case 'U': //up
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D': //down
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L': //left
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R': //right
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
		
	}
	
	public void checkApple() {
		if((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++;
			applesEaten++;			
			newApple();
		}
	}
	
	public void checkCollisions() {
		
		//checks niya if ang head collides sa lawas
		for(int i = bodyParts; i > 0; i--) {
			if((x[0] == x[i]) && (y[0] == y[i])) running = false;
		}
		
		//checks niya if ang head touches sa left border
		if(x[0] < 0) running = false;
		
		//checks niya if ang head touches sa right border
		if(x[0] > SCREEN_WIDTH) running = false;
		
		//checks niya if ang head touches sa top border
		if(y[0] < 0) running = false;
		
		//checks niya if ang head touches sa bottom border
		if(y[0] > SCREEN_HEIGHT) running = false;
		
		if(!running) timer.stop();
		
	}
	
	public void gameOver(Graphics graphics) {	
	    // Display High Score when GameOver
	    if (applesEaten > highscore) {
	        highscore = applesEaten;
	    }

	    // Reset the score to 0 when the game is over
	    applesEaten = 0;

	    graphics.setColor(Color.white);
	    graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 25));
	    FontMetrics metrics1 = getFontMetrics(graphics.getFont());

	    // Display the current score and high score when the game is over
	    graphics.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten)) / 2, graphics.getFont().getSize());
	    graphics.drawString("High Score: " + highscore, (SCREEN_WIDTH - metrics1.stringWidth("High Score: " + highscore)) / 2, graphics.getFont().getSize() * 2);

        graphics.setColor(Color.red);
        graphics.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics2 = getFontMetrics(graphics.getFont());
        
        // Game Over Text
        String gameOverText = "Game Over";
        graphics.drawString(gameOverText, (SCREEN_WIDTH - metrics2.stringWidth(gameOverText)) / 2, SCREEN_HEIGHT / 2 - 50);

        // Better Luck Next Time Text
        String betterLuckText = "Better Luck Next Time";
        graphics.drawString(betterLuckText, (SCREEN_WIDTH - metrics2.stringWidth(betterLuckText)) / 2, SCREEN_HEIGHT / 2 + 50);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
		
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {		
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
					break;
				}			
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
					break;
				}	
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
					break;	
				}
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
					break;	
				}
			}
		}
	}	
}