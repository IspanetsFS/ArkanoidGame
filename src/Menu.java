import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton play = new JButton();
	public JButton rules = new JButton();
	public JButton highscore = new JButton();
	public JButton modes = new JButton();
	public JButton difficulty = new JButton();
	public JButton exit = new JButton();
	public JButton sound = new JButton();

	public Menu() {

		this.setLayout(null);
		play.setLocation(308, 300);
		play.setSize(200, 50);
		play.setOpaque(false);
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);
		play.setText("»√–¿“‹");
		play.setFont(new Font("TimesRoman", Font.BOLD, 30));
		play.setForeground(Color.WHITE);
		this.add(play);
		
		modes.setLocation(308, 351);
		modes.setSize(200, 50);
		modes.setOpaque(false);
		modes.setContentAreaFilled(false);
		modes.setBorderPainted(false);
		modes.setText("–≈∆»Ã€");
		modes.setFont(new Font("TimesRoman", Font.BOLD, 30));
		modes.setForeground(Color.WHITE);
		this.add(modes);

		difficulty.setLocation(283, 402);
		difficulty.setSize(250, 50);
		difficulty.setOpaque(false);
		difficulty.setContentAreaFilled(false);
		difficulty.setBorderPainted(false);
		difficulty.setText("—ÀŒ∆ÕŒ—“‹");
		difficulty.setFont(new Font("TimesRoman", Font.BOLD, 30));
		difficulty.setForeground(Color.WHITE);
		this.add(difficulty);
		
		highscore.setLocation(283, 453);
		highscore.setSize(250, 50);
		highscore.setOpaque(false);
		highscore.setContentAreaFilled(false);
		highscore.setBorderPainted(false);
		highscore.setText("–≈ Œ–ƒ€");
		highscore.setFont(new Font("TimesRoman", Font.BOLD, 30));
		highscore.setForeground(Color.WHITE);
		this.add(highscore);
		
		rules.setLocation(0, 535);
		rules.setSize(60, 60);
		rules.setOpaque(false);
		rules.setContentAreaFilled(false);
		rules.setBorderPainted(false);
		try {
			ImageIcon icon = new ImageIcon("resources\\Question.png");
			rules.setIcon(icon);
		}catch(Exception e) {
			e.printStackTrace();
		}
		this.add(rules);
		
		sound.setLocation(60, 535);
		sound.setSize(60, 60);
		sound.setOpaque(false);
		sound.setContentAreaFilled(false);
		sound.setBorderPainted(false);
		try {
			ImageIcon icon = new ImageIcon("resources\\sound_on.png");
			sound.setIcon(icon);
		}catch(Exception e) {
			e.printStackTrace();
		}
		this.add(sound);
		
		exit.setLocation(743, 545);
		exit.setSize(40, 40);
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		try {
			ImageIcon icon = new ImageIcon("resources\\Exit.png");
			exit.setIcon(icon);
		}catch(Exception e) {
			e.printStackTrace();
		}
		this.add(exit);
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		try {
			File background = new File("resources\\Background.jpg");
			File name = new File("resources\\Arkanoid.png");
			BufferedImage backgroundImage = ImageIO.read(background);
			BufferedImage nameImage = ImageIO.read(name);
			g.drawImage(backgroundImage, 0, 0, this);
			g.drawImage(nameImage, 95, 20, this); // 218, 100
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
