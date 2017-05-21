package levels;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import gameparts.Enemy;
import gameparts.Player;

public abstract class Level {
	
	protected ArrayList<Enemy> enemies;
	protected ArrayList<Shape> obstacles;
	protected Player player;
	
	private Image backgroundImg;
	private Image obstacleImg;
	
	protected Rectangle door;

	
	public Level(String backgroundImg, String obstacleImg){
		enemies = new ArrayList<Enemy>();
		obstacles = new ArrayList<Shape>();
		
		//windows bounds
		obstacles.add(new Rectangle(-10, 0, 10, 900));
		obstacles.add(new Rectangle(1200, 0, 10, 900));
		obstacles.add(new Rectangle(0, -10, 1200, 10));
		try {
			this.backgroundImg = ImageIO.read(new File(backgroundImg));
			this.obstacleImg = ImageIO.read(new File(obstacleImg));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}
	
	public ArrayList<Shape> getObstacles(){
		return obstacles;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public Image getbackgroundImg(){
		return backgroundImg;
	}
	public Image getobstacleImg(){
		return obstacleImg;
	}
	
	public Rectangle getdoor(){
		return door;
	}

	
}
