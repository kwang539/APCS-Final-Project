package levels;

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;

import gameparts.Enemy;
import gameparts.Player;

public class Level1 extends Level{
	
	private static ArrayList<Enemy> originalEnemies;
	private static ArrayList<Shape> originalObstacles;
	private static Player originalPlayer;
	
	public Level1(){
		super();
		super.enemies.add(new Enemy(300, 300));
		super.player = new Player(300, 600);
		super.obstacles.add(new Rectangle(50, 425, 700, 50));
		
		originalEnemies = new ArrayList<Enemy>(enemies);
		originalObstacles = obstacles;
		originalPlayer = player;
	}
	
	//reset method doesn't really work
	public void reset(){
		enemies = originalEnemies;
		obstacles = originalObstacles;
		player= originalPlayer;
	}
	
	
	

}
