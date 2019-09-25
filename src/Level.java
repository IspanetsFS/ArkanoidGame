import java.io.*;

public class Level {
	
	int[][] levelArray = new int[8][10];
	String[] values;
	
	public void levelCreater(int level) {
		switch (level) {
		
		case 0:
			for (int i = 0, Y = Bricks.defualtY; i < Bricks.bricks.length; i++, Y += Bricks.HEIGHT) {
				for (int j = 0, X = Bricks.defualtX; j < Bricks.bricks[0].length; j++, X += Bricks.WIDTH) {
					Bricks.bricks[i][j] = new Bricks(0);
					Bricks.bricks[i][j].x = X;
					Bricks.bricks[i][j].y = Y;
				}
			}
		
		case 1:
			try {
				FileInputStream fstream = new FileInputStream("resources\\levels\\level_1.level");
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				String str = null;
				int j = 0;
				while((str = br.readLine()) != null) {
					values = str.split(" ");
					for(int i = 0; i < values.length; i++)
						levelArray[j][i] = Integer.parseInt(values[i]);
					j++;
				}
				Bricks.initLevel(levelArray);
				fstream.close();
				br.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		break;
		
		case 2:
			try {
				FileInputStream fstream = new FileInputStream("resources\\levels\\level_2.level");
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				String str = null;
				int j = 0;
				while((str = br.readLine()) != null) {
					values = str.split(" ");
					for(int i = 0; i < values.length; i++)
						levelArray[j][i] = Integer.parseInt(values[i]);
					j++;
				}
				Bricks.initLevel(levelArray);
				fstream.close();
				br.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		break;
		
		case 3:
			try {
				FileInputStream fstream = new FileInputStream("resources\\levels\\level_3.level");
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				String str = null;
				int j = 0;
				while((str = br.readLine()) != null) {
					values = str.split(" ");
					for(int i = 0; i < values.length; i++)
						levelArray[j][i] = Integer.parseInt(values[i]);
					j++;
				}
				Bricks.initLevel(levelArray);
				fstream.close();
				br.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		break;
		
		case 4:
			try {
				FileInputStream fstream = new FileInputStream("resources\\levels\\level_4.level");
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				String str = null;
				int j = 0;
				while((str = br.readLine()) != null) {
					values = str.split(" ");
					for(int i = 0; i < values.length; i++)
						levelArray[j][i] = Integer.parseInt(values[i]);
					j++;
				}
				Bricks.initLevel(levelArray);
				fstream.close();
				br.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		break;
		
		case 5:
			try {
				FileInputStream fstream = new FileInputStream("resources\\levels\\level_5.level");
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				String str = null;
				int j = 0;
				while((str = br.readLine()) != null) {
					values = str.split(" ");
					for(int i = 0; i < values.length; i++)
						levelArray[j][i] = Integer.parseInt(values[i]);
					j++;
				}
				Bricks.initLevel(levelArray);
				fstream.close();
				br.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		break;
		
		case 6:
			try {
				FileInputStream fstream = new FileInputStream("resources\\levels\\level_6.level");
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				String str = null;
				int j = 0;
				while((str = br.readLine()) != null) {
					values = str.split(" ");
					for(int i = 0; i < values.length; i++)
						levelArray[j][i] = Integer.parseInt(values[i]);
					j++;
				}
				Bricks.initLevel(levelArray);
				fstream.close();
				br.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		break;
		
		case 7:
			try {
				FileInputStream fstream = new FileInputStream("resources\\levels\\level_7.level");
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				String str = null;
				int j = 0;
				while((str = br.readLine()) != null) {
					values = str.split(" ");
					for(int i = 0; i < values.length; i++)
						levelArray[j][i] = Integer.parseInt(values[i]);
					j++;
				}
				Bricks.initLevel(levelArray);
				fstream.close();
				br.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		break;
		
		case 8:
			try {
				FileInputStream fstream = new FileInputStream("resources\\levels\\level_8.level");
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				String str = null;
				int j = 0;
				while((str = br.readLine()) != null) {
					values = str.split(" ");
					for(int i = 0; i < values.length; i++)
						levelArray[j][i] = Integer.parseInt(values[i]);
					j++;
				}
				Bricks.initLevel(levelArray);
				fstream.close();
				br.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		break;
		
		case 9:
			try {
				FileInputStream fstream = new FileInputStream("resources\\levels\\level_9.level");
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				String str = null;
				int j = 0;
				while((str = br.readLine()) != null) {
					values = str.split(" ");
					for(int i = 0; i < values.length; i++)
						levelArray[j][i] = Integer.parseInt(values[i]);
					j++;
				}
				Bricks.initLevel(levelArray);
				fstream.close();
				br.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		break;
		
		case 10:
			try {
				FileInputStream fstream = new FileInputStream("resources\\levels\\level_10.level");
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				String str = null;
				int j = 0;
				while((str = br.readLine()) != null) {
					values = str.split(" ");
					for(int i = 0; i < values.length; i++)
						levelArray[j][i] = Integer.parseInt(values[i]);
					j++;
				}
				Bricks.initLevel(levelArray);
				fstream.close();
				br.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		break;
		
		default:
			Bricks.initRandom();
			break;
		}
	}
	
}
