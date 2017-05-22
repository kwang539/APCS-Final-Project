package levels;

import java.awt.Rectangle;

import gameparts.Enemy;
import gameparts.Player;
import gameparts.RangedEnemy1;
import panels.GamePanel;


public class Level4 extends Level{

	
	public Level4(){
	super("backgroundtester.png", "blocktester.jpg");
	door = new Rectangle(GamePanel.DRAWING_WIDTH-50, GamePanel.DRAWING_HEIGHT - 150, 50, 50);

	super.enemies.add(new Enemy("mario.png", 100, 200, 5));
	super.enemies.add(new Enemy("mario.png", 1000, 200, 5));
	
	super.hasBoss = true;

	
	super.player = new Player(590, 440);
	
	super.obstacles.add(new Rectangle(0, 250, 25, 400));
	super.obstacles.add(new Rectangle(1175, 250, 25, 400));
	super.obstacles.add(new Rectangle(200, 875, 200, 25));
	super.obstacles.add(new Rectangle(800, 875, 200, 25));
	super.obstacles.add(new Rectangle(588, 600, 30, 100));
	
	super.obstacles.add(new Rectangle(200, 450, 250, 50));
	super.obstacles.add(new Rectangle(800, 450, 250, 50));


	
	}
	
	public void reset(){
		enemies.clear();

		super.enemies.add(new Enemy("mario.png", 100, 200, 5));
		super.enemies.add(new Enemy("mario.png", 1000, 200, 5));
		

	
		//enemies = Oenemies;
		
		
		//obstacles = Oobstacles;
		player.reset(590, 440);
	}

}