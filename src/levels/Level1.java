package levels;

import java.awt.Rectangle;
import gameparts.Enemy;
import gameparts.Player;

public class Level1 extends Level{
	
	public Level1(){
		super();
		super.enemies.add(new Enemy(300, 200));
		
		super.player = new Player(300, 600);
		
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
