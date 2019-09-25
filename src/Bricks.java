import java.awt.*;
import java.util.*;

public class Bricks {

	//Array of bricks
	public static Bricks[][] bricks = new Bricks[8][10];

	public static final int WIDTH = 75;
	public static final int HEIGHT = 22;
	public static int indexRows = 0;
	public static int indexColumns = 0;
	public static int point = 0; // score for destroyed brick
	public int x = 25;
	public int y = 100;
	public static int defualtX = 25;
	public static int defualtY = 100;
	public int hits = 1;
	public Color colorBorder = Color.WHITE;
	public Color colorBody = Color.GRAY;
	// Color of different bricks
	private static Color blue = new Color(0, 0, 255);
	private static Color lightBlue = new Color(0, 128, 255);
	private static Color green = new Color(0, 136, 0);
	private static Color lightGreen = new Color(0, 191, 0);
	private static Color red = new Color(210, 0, 0);
	private static Color lightRed = new Color(255, 6, 6);
	private static Color yellow = new Color(191, 191, 0);
	private static Color lightYellow = new Color(242, 242, 0);
	private static Color gray = new Color(117, 117, 117);
	private static Color lightGray = new Color(153, 153, 153);
	private static Color violet = new Color(140, 0, 140);
	private static Color lightViolet = new Color(202, 0, 202);

	public Bricks() {
		this.hits = 1;
		this.colorBody = lightBlue;
		this.colorBorder = blue;
	}

	public Bricks(int hits) {
		this.hits = hits;
		switch (hits) {
		case 1:
			this.colorBody = lightBlue;
			this.colorBorder = blue;
			break;
		case 2:
			this.colorBody = lightGreen;
			this.colorBorder = green;
			break;
		case 3:
			this.colorBody = lightYellow;
			this.colorBorder = yellow;
			break;
		case 4:
			this.colorBody = lightRed;
			this.colorBorder = red;
			break;
		case 5:
			this.colorBody = lightGray;
			this.colorBorder = gray;
			break;
		case 6:
			this.colorBody = lightViolet;
			this.colorBorder = violet;
			break;
		default:
			break;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Color getColorBorder() {
		return colorBorder;
	}

	public Color getColorBody() {
		return colorBody;
	}

	public int getHits() {
		return hits;
	}
	
	/*
	 * public boolean collision() {
	 * 
	 * if (ball.getYa() < 0) { if (ball.getY() <= 122 && ball.getY() > 100)
	 * indexRows = 0; else if (ball.getY() <= 144 && ball.getY() > 122) indexRows =
	 * 1; else if (ball.getY() <= 166 && ball.getY() > 144) indexRows = 2; else if
	 * (ball.getY() <= 188 && ball.getY() > 166) indexRows = 3; else if (ball.getY()
	 * <= 210 && ball.getY() > 188) indexRows = 4; else if (ball.getY() <= 232 &&
	 * ball.getY() > 210) indexRows = 5; else if (ball.getY() <= 254 && ball.getY()
	 * > 232) indexRows = 6; else if (ball.getY() <= 276 && ball.getY() > 254)
	 * indexRows = 7; else indexRows = -1; } else { if (ball.getY() +
	 * ball.getRadius() <= 122 && ball.getY() + ball.getRadius() >= 100) indexRows =
	 * 0; else if (ball.getY() + ball.getRadius() <= 144 && ball.getY() +
	 * ball.getRadius() >= 122) indexRows = 1; else if (ball.getY() +
	 * ball.getRadius() <= 166 && ball.getY() + ball.getRadius() >= 144) indexRows =
	 * 2; else if (ball.getY() + ball.getRadius() <= 188 && ball.getY() +
	 * ball.getRadius() >= 166) indexRows = 3; else if (ball.getY() +
	 * ball.getRadius() <= 210 && ball.getY() + ball.getRadius() >= 188) indexRows =
	 * 4; else if (ball.getY() + ball.getRadius() <= 232 && ball.getY() +
	 * ball.getRadius() >= 210) indexRows = 5; else if (ball.getY() +
	 * ball.getRadius() <= 254 && ball.getY() + ball.getRadius() >= 232) indexRows =
	 * 6; else if (ball.getY() + ball.getRadius() <= 276 && ball.getY() +
	 * ball.getRadius() >= 254) indexRows = 7; else indexRows = -1; }
	 * 
	 * if (ball.getXa() < 0) { if (ball.getX() <= 100 && ball.getX() > 25)
	 * indexColumns = 0; else if (ball.getX() <= 175 && ball.getX() > 100)
	 * indexColumns = 1; else if (ball.getX() <= 250 && ball.getX() > 175)
	 * indexColumns = 2; else if (ball.getX() <= 325 && ball.getX() > 250)
	 * indexColumns = 3; else if (ball.getX() <= 400 && ball.getX() > 325)
	 * indexColumns = 4; else if (ball.getX() <= 475 && ball.getX() > 400)
	 * indexColumns = 5; else if (ball.getX() <= 550 && ball.getX() > 475)
	 * indexColumns = 6; else if (ball.getX() <= 625 && ball.getX() > 550)
	 * indexColumns = 7; else if (ball.getX() <= 700 && ball.getX() > 625)
	 * indexColumns = 8; else if (ball.getX() <= 775 && ball.getX() > 700)
	 * indexColumns = 9; else indexColumns = -1; } else { if (ball.getX() +
	 * ball.getRadius() < 100 && ball.getX() + ball.getRadius() >= 25) indexColumns
	 * = 0; else if (ball.getX() + ball.getRadius() < 175 && ball.getX() +
	 * ball.getRadius() >= 100) indexColumns = 1; else if (ball.getX() +
	 * ball.getRadius() < 250 && ball.getX() + ball.getRadius() >= 175) indexColumns
	 * = 2; else if (ball.getX() + ball.getRadius() < 325 && ball.getX() +
	 * ball.getRadius() >= 250) indexColumns = 3; else if (ball.getX() +
	 * ball.getRadius() < 400 && ball.getX() + ball.getRadius() >= 325) indexColumns
	 * = 4; else if (ball.getX() + ball.getRadius() < 475 && ball.getX() +
	 * ball.getRadius() >= 400) indexColumns = 5; else if (ball.getX() +
	 * ball.getRadius() < 550 && ball.getX() + ball.getRadius() >= 475) indexColumns
	 * = 6; else if (ball.getX() + ball.getRadius() < 625 && ball.getX() +
	 * ball.getRadius() >= 550) indexColumns = 7; else if (ball.getX() +
	 * ball.getRadius() < 700 && ball.getX() + ball.getRadius() >= 625) indexColumns
	 * = 8; else if (ball.getX() + ball.getRadius() < 775 && ball.getX() +
	 * ball.getRadius() >= 700) indexColumns = 9; else indexColumns = -1; }
	 * 
	 * if (indexColumns >= 0 && indexRows >= 0) { if
	 * (bricks[indexRows][indexColumns].hits != 0) { return true; } }
	 * 
	 * return false; }
	 */

	// updating brick "lives"
	public static void updateHits(int index) {
		Color check = bricks[indexRows][indexColumns].getColorBorder();
		if (check == blue)
			point = index;
		else if (check == green)
			point = index*2;
		else if (check == red)
			point = index*3;
		else if (check == yellow)
			point = index*4;
		else if (check == gray)
			point = index*5;
		else if (check == violet)
			point = index*6;
		else
			point = 0;

		bricks[indexRows][indexColumns].hits -= 1;
		if (bricks[indexRows][indexColumns].hits == 0)
			Player.score += point;
	}

	static Random rnd = new Random();

	// Initialization custom levels for campaign mode
	public static void initLevel(int[][] level) {
		for (int i = 0, Y = defualtY; i < bricks.length; i++, Y += HEIGHT) {
			for (int j = 0, X = defualtX; j < bricks[0].length; j++, X += WIDTH) {
				bricks[i][j] = new Bricks(level[i][j]);
				bricks[i][j].x = X;
				bricks[i][j].y = Y;
			}
		}
	}

	// Initialization random levels foê endless mode
	public static void initRandom() {
		for (int i = 0, Y = defualtY; i < bricks.length; i++, Y += HEIGHT) {
			for (int j = 0, X = defualtX; j < bricks[0].length; j++, X += WIDTH) {
					bricks[i][j] = new Bricks(rnd.nextInt(Integer.MAX_VALUE) % 7); 
					bricks[i][j].x = X;
					bricks[i][j].y = Y;
			}
		}

	}

	public static boolean checkForClean() {
		for (int i = 0; i < bricks.length; i++) {
			for (int j = 0; j < bricks[0].length; j++) {
				if (bricks[i][j].hits != 0) {
					return false;
				}
			}
		}
		return true;
	}

	public void paint(Graphics g) {
		for (int i = 0; i < bricks.length; i++) {
			for (int j = 0; j < bricks[0].length; j++) {
				if (bricks[i][j].hits != 0) {
					g.setColor(bricks[i][j].colorBorder);
					g.fillRoundRect(bricks[i][j].x, bricks[i][j].y, WIDTH, HEIGHT, 15, 10);
					g.setColor(bricks[i][j].colorBody);
					g.fillRoundRect(bricks[i][j].x + 2, bricks[i][j].y + 4, WIDTH - 4, HEIGHT - 4, 15, 10);
				}
			}
		}
	}

}
