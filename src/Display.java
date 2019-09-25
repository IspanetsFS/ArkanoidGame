import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class Display {

	private static boolean key = false;
	private static String chooseMode = "Кампания";
	private static int mode = 1;
	private static String chooseDifficulty = "Легкий";
	private static int difficulty = 1;
	private static int level_counter = 1;
	static Clip clipSound;
	private static boolean chooser = true;

	public static int getDifficulty() {
		return difficulty;
	}

	public static void main(String[] args)
			throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		// create window
		JFrame frame = new JFrame("Arkanoid");
		frame.setSize(806, 630);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

		Menu menu = new Menu();
		Game game = new Game(frame);
		Ball ball = new Ball();
		CardLayout cards = new CardLayout();
		JPanel parent = new JPanel();
		Level level = new Level();
		HighScores highScores = new HighScores();

		JPanel panel = new JPanel();

		// add all panels into cardLayout
		frame.add(parent);
		parent.setLayout(cards);
		parent.add(menu, "menu");
		parent.add(game, "game");
		parent.add(highScores, "highScores");
		parent.add(panel, "panel");
		cards.show(parent, "menu"); // this main panel
		// create default level
		loadSound("mainMenu.wav");
		level.levelCreater(level_counter);

		// actions if play button isClicked
		menu.play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do {
					Player.name = JOptionPane.showInputDialog(menu, "Введите имя игрока", "Имя игрока",
							JOptionPane.PLAIN_MESSAGE);
					if (Player.name == null)
						break;
					if (Player.name.compareTo("") == 0) {
						JOptionPane.showMessageDialog(menu, "Вы не ввели имя игрока!", "Предупреждение",
								JOptionPane.WARNING_MESSAGE);
					}
				} while (Player.name.compareTo("") == 0);
				if (Player.name != null) {
					cards.show(parent, "game");
					game.requestFocus();
					key = true;
					Player.date = LocalDate.now();
					if (chooser) {
						if (difficulty == 1) {
							clipSound.stop();
							clipSound.close();
							try {
								loadSound("easyDifficulty.wav");
							} catch (UnsupportedAudioFileException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							} catch (LineUnavailableException e1) {
								e1.printStackTrace();
							}
						} else if (difficulty == 2) {
							clipSound.stop();
							clipSound.close();
							try {
								loadSound("mediumDifficulty.wav");
							} catch (UnsupportedAudioFileException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							} catch (LineUnavailableException e1) {
								e1.printStackTrace();
							}
						} else if (difficulty == 3) {
							clipSound.stop();
							clipSound.close();
							try {
								loadSound("hardDifficulty.wav");
							} catch (UnsupportedAudioFileException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							} catch (LineUnavailableException e1) {
								e1.printStackTrace();
							}
						}
					}
				}

			}
		});

		// actions if modes button isClicked
		menu.modes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] posibilities = { "Кампания", "Бесконечный" };
				chooseMode = (String) JOptionPane.showInputDialog(menu, "Выберите режим игры", "Режим игры",
						JOptionPane.PLAIN_MESSAGE, null, posibilities, posibilities[0]);
				if (chooseMode != null) {
					mode = game.gameMode.get(chooseMode);
					if (mode == 1) {
						level_counter = 1;
						Game.mode = true;
						level.levelCreater(level_counter);
					} else {
						level_counter = 11;
						Game.mode = false;
						level.levelCreater(level_counter);
					}
				}
			}
		});

		// actions if difficulty button isClicked
		menu.difficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] posibilities = { "Легкий", "Средний", "Сложный" };
				chooseDifficulty = (String) JOptionPane.showInputDialog(menu, "Выберите сложность игры",
						"Сложность игры", JOptionPane.PLAIN_MESSAGE, null, posibilities, posibilities[0]);
				if (chooseDifficulty != null) {
					difficulty = game.gameDifficulty.get(chooseDifficulty);
					game.setDifficult(difficulty);
				}
			}
		});

		// actions if rules button isClicked
		menu.rules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame rulesFrame = new JFrame("Правила");
				JPanel rulesPanel = new JPanel();
				JLabel rulesTitle = new JLabel();
				JLabel rulesText = new JLabel();

				rulesFrame.setSize(400, 400);
				rulesFrame.setBounds(0, 0, 500, 550);
				rulesFrame.setResizable(false);
				rulesFrame.setVisible(true);

				rulesPanel.setLayout(null);
				rulesFrame.add(rulesPanel);

				rulesTitle.setBounds(0, 20, 500, 20);
				rulesTitle.setHorizontalAlignment(SwingConstants.CENTER);
				rulesTitle.setText("Добро пожаловать в Arkanoid!");
				rulesTitle.setFont(new Font("TimesRoman", Font.BOLD, 18));
				rulesPanel.add(rulesTitle);

				rulesText.setBounds(20, 0, 450, 500);
				rulesText.setHorizontalAlignment(SwingConstants.LEFT);

				rulesText.setText("<html>" + "Arkanoid - аркадная игра, целью которой является очистка поля от блоков "
						+ "с помощью мячика и платформы.<br>"
						+ "Вам доступно два режима игры и три уровня сложности. <br>"
						+ "В режиме \"Кампания\" Вам предстоит пройти 10 различных уровней, не потеряв "
						+ "все свои жизни. <br>"
						+ "В начале каждой игры у вас будет 3 жизни. При каждом прохождении очередного "
						+ "уровня, Вы будете получать дополнительную жизнь, но жизней не может быть "
						+ "больше пяти штук. Если мячик попадает в нижнюю границу игрового поля, Вы теряете"
						+ "одну жизнь.<br>" + "Каждый блок имеет определенное количество жизней и при разрушении "
						+ "прибавляет игроку определенное количество очков, в зависимости от сложности игры. <br>"
						+ "В режиме \"Бесконечный\" генерируются случайные поля и игра ведется на рекорд "
						+ "по правилам режима \"Кампания\"."
						+ "<br>Чтобы начать игру, нажмите клавишу SPACE на клавиатуре.<br><br>" + "Желаю Вам удачи!"
						+ "</html>");
				rulesText.setFont(new Font("TimesRoman", Font.BOLD, 14));
				rulesPanel.add(rulesText);
			}
		});

		// actions if highscore button isClicked
		menu.highscore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(parent, "highScores");
			}
		});

		// return menu panel from highscore panel
		highScores.exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(parent, "menu");
			}
		});

		// close program, if exit button clicked
		menu.exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
				System.exit(0);
			}
		});

		menu.sound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chooser) {
					try {
						ImageIcon icon = new ImageIcon("resources\\sound_off.png");
						menu.sound.setIcon(icon);
						chooser = !chooser;
						clipSound.stop();
						clipSound.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				} else {
					try {
						ImageIcon icon = new ImageIcon("resources\\sound_on.png");
						menu.sound.setIcon(icon);
						loadSound("mainMenu.wav");
						chooser = !chooser;
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});

		// game loop
		while (true) {
			game.requestFocus(); 
			if (key) { 
				if (game.start()) { 
					ball.move(); 
				}

				if (Player.lives <= 0) { 
					game.over(); 
					game.setOverText("ИГРА ОКОНЧЕНА"); 
					Thread.sleep(2000); 
					cards.show(parent, "menu"); 
					if (chooser) { 
						clipSound.stop(); 
						clipSound.close(); 
						loadSound("mainMenu.wav"); 
					}
					key = false; 
					game.setOverText(""); 
					Player.outputData(); 
					Player.update();
					if (mode == 2) { 
						level.levelCreater(11); 
					} else {
						level.levelCreater(1); 
					}
				}
				// check on gamefield clear
				if (Bricks.checkForClean()) { 
					// if player pass all 10 levels -> game end
					if (level_counter == 10 && mode == 1) { 
						game.over(); 
						game.setOverText("ИГРА ОКОНЧЕНА");
						Thread.sleep(2000);
						cards.show(parent, "menu"); 
						if (chooser) {
							clipSound.stop();
							clipSound.close();
							loadSound("mainMenu.wav");
						}
						key = false; 
						game.setOverText(""); 
						Player.outputData(); 
						Player.update(); 
					}
					Game.setGo(false); 
					Player.level_pass += 1; 
					level_counter += 1; 
					Ball.setX(Player.x + Player.WIDTH / 2 - ball.getRadius() / 2); 
					Ball.setY(Player.y - ball.getRadius() - 4); 
					if (Player.lives < 5) { 
						Player.lives += 1; 
					}
					game.repaint(); //428
					level.levelCreater(level_counter); 
					game.repaint(); 
					game.showLevel(Player.level_pass + 1); 
					Thread.sleep(2000); 
					game.refreshLevel(); 
				}
			}

			Thread.sleep(game.getThreadTime());
		}

	}

	private static void loadSound(String file)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		File f = new File("resources\\music\\" + file);
		AudioFileFormat aff = AudioSystem.getAudioFileFormat(f);
		AudioFormat af = aff.getFormat();
		DataLine.Info info = new DataLine.Info(Clip.class, af);
		if (AudioSystem.isLineSupported(info)) {
			clipSound = (Clip) AudioSystem.getLine(info);
			AudioInputStream ais = AudioSystem.getAudioInputStream(f);
			clipSound.open(ais);
			clipSound.start();
			clipSound.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}

}
