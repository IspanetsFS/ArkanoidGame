import java.awt.*;
import java.io.*;
import java.time.LocalDate;

public class Player {

	public static int lives = 3;
	public static int score = 0;
    public static int WIDTH = 120;
    public static int SIDE_WIDTH = 20;
    public static int realSideWidth = 15;
    public static int realWidth = 150;
    public static int HEIGHT = 20;
    public static int default_x = 350;
    public static int x = 350;
    public static int y = 550;
    public static String name;
    public static LocalDate date = LocalDate.now();
    public static int level_pass = 0;
    public int speed = 4;

    private Direction playerDirection = Direction.NONE;

    public int getScore() {
    	return score;
    }
    
    public int getLives() {
    	return lives;
    }
    
    public static int getX(){
        return x;
    }

    public static int getY(){
        return y;
    }

    public int getSpeed(){
        return speed;
    }
    
    public void setPlayerDirection(Direction dir) {
    	playerDirection = dir;
    }
    
    // converter date to string for file output
    public static String dateToString() {
    	String day = Integer.toString(date.getDayOfMonth());
    	String month = Integer.toString(date.getMonthValue());
    	String year = Integer.toString(date.getYear());
    	if(date.getMonthValue()<10) {
    		month = "0".concat(month);
    	}
    	if(date.getDayOfMonth() < 10) {
    		day = "0".concat(day);
    	}
    	String result = day + "." + month + "." + year;
    	return result;
    }
    
    // filling all player data in file
    private static void fillInfo(String file) {
    	
    	try(
    			OutputStream out = new FileOutputStream("resources\\scores\\" + file, true);
    			BufferedOutputStream buf = new BufferedOutputStream(out);
    			DataOutputStream data = new DataOutputStream(buf);
    			){
    		
    		data.writeUTF(name);
    		data.writeUTF(dateToString());
    		data.writeInt(level_pass);
    		data.writeInt(score);
    		
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    }
    
    // output player data in file, when game is ended
    public static void outputData() {
    	if (Game.mode) {
    		switch (Display.getDifficulty()) {
    		case 1:
    			fillInfo("campaignEasy.dat");
    			break;
    		case 2:
    			fillInfo("campaignMedium.dat");
    			break;
    		case 3:
    			fillInfo("campaignHard.dat");
    			break;
    		default:
    			break;
    		}
    	}else {
    		switch (Display.getDifficulty()) {
    		case 1:
    			fillInfo("endlessEasy.dat");
    			break;
    		case 2:
    			fillInfo("endlessMedium.dat");
    			break;
    		case 3:
    			fillInfo("endlessHard.dat");
    			break;
    		default:
    			break;
    		}
    	}
    }
    
    // updating player's data
    public static void update() {
    	name = "";
    	score = 0;
    	lives = 3;
    	level_pass = 0;
    }
    
    // speed setter for game difficulty
    public void setSpeed(int level) {
    	switch (level) {
    	case 1:
    		speed = 4;
    		break;
    	case 2:
    		speed = 8;
    		break;
    	case 3:
    		speed = 12;
    		break;
    	default:
    		break;
    	}
    }
    
    public void loseLives() {
    	lives -= 1;
    }
    
    public void move(){
    	if(playerDirection == Direction.LEFT) {
    		if(x - (SIDE_WIDTH) > 0) {
    			x -= speed;
    		}
    	}
    	if(playerDirection == Direction.RIGHT) {
    		if(x + WIDTH + SIDE_WIDTH < Game.WIDTH - 10) {
    			x += speed;
    		}
    	}
    }

    //paint player platform
    public void paint(Graphics g){
    	g.setColor(Color.CYAN);
    	g.fillRoundRect(x - (SIDE_WIDTH - 5), y, SIDE_WIDTH, HEIGHT, 20, 20);
    	
    	g.setColor(Color.CYAN);
    	g.fillRoundRect(x + (WIDTH - 5), y, SIDE_WIDTH, HEIGHT, 20, 20);
    	
        g.setColor(Color.BLUE);
        g.fillRect(x, y, WIDTH, HEIGHT);
       
    }



}
