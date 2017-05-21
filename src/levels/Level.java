package levels;

import java.awt.Image;
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
	
	public Level(String backgroundImg, String obstacleImg){
		enemies = new ArrayList<Enemy>();
		obstacles = new ArrayList<Shape>();
		
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

	
}
