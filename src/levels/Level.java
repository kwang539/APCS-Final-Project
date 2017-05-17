package levels;

import java.awt.Shape;
import java.util.ArrayList;
import gameparts.Enemy;
import gameparts.Player;

public abstract class Level {
	
	protected ArrayList<Enemy> enemies;
	protected ArrayList<Shape> obstacles;
	protected Player player;
	
	public Level(){
		enemies = new ArrayList<Enemy>();
		obstacles = new ArrayList<Shape>();
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

	
}
