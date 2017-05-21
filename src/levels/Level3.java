package levels;

import java.awt.Rectangle;

import gameparts.Boss;
import gameparts.Enemy;
import gameparts.Player;


public class Level3 extends Level{

	
	public Level3(){
	super("backgroundtester.png", "blocktester.jpg");
	//super.enemies.add(new Enemy("mario.png",300, 700,4));
	
	super.enemies.add(new Boss("playerchar.png", 150, 750, 1));
	
	
	super.player = new Player(800, 600);
	
	super.obstacles.add(new Rectangle(50, 425, 500, 50));
	super.obstacles.add(new Rectangle(0, 850, 500, 50));
	super.obstacles.add(new Rectangle(700, 850, 500, 50));
	super.obstacles.add(new Rectangle(1000, 200, 50, 500));
	
	/* Window Bounds */
	//super.obstacles.add(new Rectangle(0, 900, 1200, 10));
	super.obstacles.add(new Rectangle(-10, 0, 10, 900));
	super.obstacles.add(new Rectangle(1200, 0, 10, 900));
	super.obstacles.add(new Rectangle(0, -10, 1200, 10));
	}
}
