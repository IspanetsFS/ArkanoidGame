import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Game extends JPanel implements ActionListener {

	/**
	 * @author Rustam
	 */
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 806;
	public static final int HEIGHT = 600;
	private int threadTime = 6; // time for thread sleep
	private static boolean go = false; // start/stop game loop
	public static int index = 1; // coefficient of difficulty
	public static boolean mode = true; // game mode, true - campaign, false - endless
	public HashMap<String, Integer> gameMode = new HashMap<String, Integer>(2); 
	public HashMap<String, Integer> gameDifficulty = new HashMap<String, Integer>(3);
	

	JLabel textField;
	JLabel OVER;
	JLabel nextLevel;
	JFrame frame;
	Player player = new Player();
	Ball ball = new Ball();
	Bricks bricks = new Bricks();
	Level level = new Level();
	Timer timer = new Timer(6, this);

	public Game(JFrame frame) {
		this.frame = frame;
		timer.start();
		this.setBackground(Color.BLACK);
		setFocusable(true);
		this.setLayout(null);

		gameMode.put("Кампания", 1);
		gameMode.put("Бесконечный", 2);
		gameDifficulty.put("Легкий", 1);
		gameDifficulty.put("Средний", 2);
		gameDifficulty.put("Сложный", 3);
		
		textField = new JLabel();
		textField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		textField.setBounds(0, 0, WIDTH, 30);
		textField.setFont(new Font("Courier", Font.ITALIC, 15));
		textField.setForeground(Color.WHITE);
		this.add(textField);
		
		nextLevel = new JLabel("", SwingConstants.CENTER);
		nextLevel.setBounds(0, 0, WIDTH, HEIGHT);
		nextLevel.setFont(new Font("Courier", Font.PLAIN, 20));
		nextLevel.setForeground(Color.WHITE);
		this.add(nextLevel);
		
		OVER = new JLabel("", SwingConstants.CENTER);
		OVER.setBounds(0, 0, WIDTH, HEIGHT);
		OVER.setFont(new Font("Courier", Font.PLAIN, 50));
		OVER.setForeground(Color.WHITE);
		this.add(OVER);

		// player's controller
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
		        if(key == KeyEvent.VK_LEFT) {
		        	player.setPlayerDirection(Direction.LEFT);
		        }
		        if(key == KeyEvent.VK_RIGHT) {
		        	player.setPlayerDirection(Direction.RIGHT);
		        }
		        if(key == KeyEvent.VK_SPACE) {
		        	go = true;
		        }
			}

			public void keyReleased(KeyEvent e) {
				player.setPlayerDirection(Direction.NONE);
			}

		});
	}

	public int getThreadTime() {
		return threadTime;
	}

	public static boolean getGo() {
		return go;
	}
	
	public static void setGo(boolean key) {
		go = key;
	}
	
	public void setOverText(String str) {
		OVER.setText(str);
	}
	
	// game difficulty setter
	public void setDifficult(int difficulty) {
		switch (difficulty) {
		case 1:
			player.speed = 6;
			Ball.setSpeed(2);
			Ball.setXa(2);
			Ball.setYa(2);
			index = 1;
			break;
		case 2:
			player.speed = 10;
			Ball.setSpeed(3);
			Ball.setXa(3);
			Ball.setYa(3);
			index = 2;
			break;
		case 3:
			player.speed = 12;
			Ball.setSpeed(4);
			Ball.setXa(4);
			Ball.setYa(4);
			index = 3;
			break;
		default:
			break;
		}
	}

	public void showScore() {
		textField.setText("ОЧКИ:   " + Player.score);
		textField.revalidate();
	}

	public void showLevel(int levelCount) {
		nextLevel.setText("УРОВЕНЬ " + levelCount);
	}
	
	public void refreshLevel() {
		nextLevel.setText("");
	}
	
	public void over() {
			go = false;
	}
	
	// if player press "SPACE" on keyboard, game start
	public boolean start() {
		
		return go;

	}

	// action which doing, while timer is started
	public void actionPerformed(ActionEvent e) {
		repaint();
		player.move();
		showScore();
	}

	// graphics painter on gamefield
	public void paint(Graphics g) {
		super.paint(g);
		player.paint(g);
		ball.paint(g);
		bricks.paint(g);
		try {
			File heart = new File("resources\\Heart.png");
			BufferedImage heartImage = ImageIO.read(heart);
			for (int i = 0, x = WIDTH - 40; i < Player.lives; i++, x -= 20)
				g.drawImage(heartImage, x, 5, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
