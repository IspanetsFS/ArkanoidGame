import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {

	private static int radius = 22;
	private static int default_x = Player.x + Player.WIDTH / 2 - 11;
	private static int default_y = Player.y - 26;
	private static int x = default_x;
	private static int y = default_y;
	public static int speed = 2;
	private static int xa = speed; // 3
	private static int ya = -speed; // 3

	Random rnd = new Random();
	Player player = new Player();

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeed() {
		return speed;
	}

	public int getRadius() {
		return radius;
	}

	public int getXa() {
		return xa;
	}

	public int getYa() {
		return ya;
	}

	public static void setX(int sX) {
		x = sX;
	}
	
	public static void setY(int sY) {
		y = sY;
	}
	
	public static void setSpeed(int spd) {
		speed = spd;
	}
	
	public static void setXa(int XA) {
		xa = XA;
	}
	
	public static void setYa(int YA) {
		ya = YA;
	}
	
	public void setStartDirection(int speed) {
		xa = speed;
		ya = -speed;
	}

	public boolean collisionWithPlayer() {

		if (y + radius + ya > Player.getY()) {
			if (x >= Player.getX() - Player.realSideWidth - radius
					&& x <= Player.getX() + Player.realWidth - Player.realSideWidth) {
				return true;
			}
		}
		return false;
	}

	public boolean collisionWithBrick() {

		if (ya < 0) {
			if (y <= 122 && y >= 78)
				Bricks.indexRows = 0;
			else if (y <= 144 && y > 122)
				Bricks.indexRows = 1;
			else if (y <= 166 && y > 144)
				Bricks.indexRows = 2;
			else if (y <= 188 && y > 166)
				Bricks.indexRows = 3;
			else if (y <= 210 && y > 188)
				Bricks.indexRows = 4;
			else if (y <= 232 && y > 210)
				Bricks.indexRows = 5;
			else if (y <= 254 && y > 232)
				Bricks.indexRows = 6;
			else if (y <= 276 && y > 254)
				Bricks.indexRows = 7;
			else
				Bricks.indexRows = -1;
		} else {
			if (y + radius < 122 && y + radius >= 100)
				Bricks.indexRows = 0;
			else if (y + radius < 144 && y + radius >= 122)
				Bricks.indexRows = 1;
			else if (y + radius < 166 && y + radius >= 144)
				Bricks.indexRows = 2;
			else if (y + radius < 188 && y + radius >= 166)
				Bricks.indexRows = 3;
			else if (y + radius < 210 && y + radius >= 188)
				Bricks.indexRows = 4;
			else if (y + radius < 232 && y + radius >= 210)
				Bricks.indexRows = 5;
			else if (y + radius < 254 && y + radius >= 232)
				Bricks.indexRows = 6;
			else if (y + radius <= 298 && y + radius >= 254)
				Bricks.indexRows = 7;
			else
				Bricks.indexRows = -1;
		}

		if (xa < 0) {
			if (x <= 100 && x >= 3)
				Bricks.indexColumns = 0;
			else if (x <= 175 && x > 100)
				Bricks.indexColumns = 1;
			else if (x <= 250 && x > 175)
				Bricks.indexColumns = 2;
			else if (x <= 325 && x > 250)
				Bricks.indexColumns = 3;
			else if (x <= 400 && x > 325)
				Bricks.indexColumns = 4;
			else if (x <= 475 && x > 400)
				Bricks.indexColumns = 5;
			else if (x <= 550 && x > 475)
				Bricks.indexColumns = 6;
			else if (x <= 625 && x > 550)
				Bricks.indexColumns = 7;
			else if (x <= 700 && x > 625)
				Bricks.indexColumns = 8;
			else if (x <= 775 && x > 700)
				Bricks.indexColumns = 9;
			else
				Bricks.indexColumns = -1;
		} else {
			if (x + radius <= 100 && x + radius >= 25)
				Bricks.indexColumns = 0;
			else if (x + radius < 175 && x + radius >= 100)
				Bricks.indexColumns = 1;
			else if (x + radius < 250 && x + radius >= 175)
				Bricks.indexColumns = 2;
			else if (x + radius < 325 && x + radius >= 250)
				Bricks.indexColumns = 3;
			else if (x + radius < 400 && x + radius >= 325)
				Bricks.indexColumns = 4;
			else if (x + radius < 475 && x + radius >= 400)
				Bricks.indexColumns = 5;
			else if (x + radius < 550 && x + radius >= 475)
				Bricks.indexColumns = 6;
			else if (x + radius < 625 && x + radius >= 550)
				Bricks.indexColumns = 7;
			else if (x + radius < 700 && x + radius >= 625)
				Bricks.indexColumns = 8;
			else if (x + radius <= 797 && x + radius >= 700)
				Bricks.indexColumns = 9;
			else
				Bricks.indexColumns = -1;
		}

		if (Bricks.indexColumns >= 0 && Bricks.indexRows >= 0) {
			if (Bricks.bricks[Bricks.indexRows][Bricks.indexColumns].hits != 0) {
				return true;
			}
		}

		return false;
	}

	public void move() {
		// left border
		if (x + xa < 0 + xa) {
			xa = -xa;
		}
		// right border
		else if (x + radius + xa > Game.WIDTH - 8 - xa) {
			xa = -xa;
		}
		// upper border
		else if (y + ya <= 30 + ya) {
			ya = -ya;
		}
		// down border
		else if (y + radius + ya > Game.HEIGHT + ya) {
			ya = -ya;
		}

		if (y + radius >= Player.y + Player.HEIGHT) {
			player.loseLives();
			Game.setGo(false);
			x = Player.x + Player.WIDTH / 2 - radius / 2;
			y = Player.y - radius - 4;
		}

		if (collisionWithPlayer()) {
			// left border of player
			if (y >= Player.y - 30) {
				if (x >= (Player.x - Player.realSideWidth) - radius && x + radius <= Player.x) {
					int gradeY = rnd.nextInt(2) + 1;
					int gradeX = rnd.nextInt(2) + 1;
					if (xa > 0) {
						xa = -1 * (xa + gradeX);
						ya = -1 * (ya + gradeY);
						xa = (xa % (speed + 1)) - speed;
						ya = (ya % speed) - speed;

					} else {
						ya = -1 * (ya + gradeY);
						ya %= speed;
						if (ya % speed == 0) {
							ya -= (speed + 1);
						}
					}
				}
				// right border of player
				else if (x >= Player.getX() + Player.WIDTH
						&& x <= Player.getX() + Player.realWidth - Player.realSideWidth) {
					int gradeY = rnd.nextInt(2) + 1;
					int gradeX = rnd.nextInt(2) + 1;
					if (xa < 0) {
						xa = -1 * (xa - gradeX);
						ya = -1 * (ya + gradeY);
						xa = (xa % 3) + speed;
						ya = (ya % 2) - speed;
					} else {
						ya = -1 * (ya + gradeY);
						ya %= 2;
						if (ya % 2 == 0) {
							ya -= speed;
						}
					}
				} else {
					ya = -ya;
				}
			}
		}

		if (collisionWithBrick()) {
			int xBrick = Bricks.bricks[Bricks.indexRows][Bricks.indexColumns].x;
			int yBrick = Bricks.bricks[Bricks.indexRows][Bricks.indexColumns].y;
			if (xa < 0) {
				if (ya > 0) {
					if (x >= xBrick + Bricks.WIDTH - radius / 2 && y + radius <= yBrick + radius / 2) {
						xa = -xa;
						ya = -ya;
					} else if (x <= xBrick + Bricks.WIDTH - radius / 2) {
						ya = -ya;
					} else {
						xa = -xa;
					}
				} else {
					if (x >= xBrick + Bricks.WIDTH - radius / 2 && y <= yBrick + radius / 2) {
						xa = -xa;
						ya = -ya;
					} else if (x <= xBrick + Bricks.WIDTH - radius / 2) {
						ya = -ya;
					} else {
						xa = -xa;
					}
				}
			} else {
				if (ya > 0) {
					if (x + radius <= xBrick + radius / 2 && y + radius <= yBrick + radius / 2) {
						xa = -xa;
						ya = -ya;
					} else if (x + radius >= xBrick + radius / 2) {
						ya = -ya;
					} else if (y + radius >= yBrick + radius / 2) {
						xa = -xa;
					}
				} else {
					if (x + radius <= xBrick + radius / 2 && y - radius / 2 >= yBrick + radius / 2) {
						xa = -xa;
						ya = -ya;
					} else if (x + radius >= xBrick + radius / 2) {
						ya = -ya;
					} else {
						xa = -xa;
					}
				}
			}

			Bricks.updateHits(Game.index);

		}

		x += xa;
		y += ya;

	}

	public void paint(Graphics g) {
		g.setColor(Color.RED);
		if (!Game.getGo()) {
			x = Player.x + Player.WIDTH / 2 - radius / 2;
			y = Player.y - radius - 4;
			g.fillRoundRect(Player.x + Player.WIDTH / 2 - 11, Player.y - 26, radius, radius, radius, radius);
		} else {
			g.fillRoundRect(x, y, radius, radius, radius, radius);
		}
	}

}
