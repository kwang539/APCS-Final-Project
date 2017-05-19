package levels;

import java.awt.Rectangle;
import gameparts.Enemy;
import gameparts.Player;

public class Level2 extends Level{
	
	public Level2(){
		super();
		super.enemies.add(new Enemy(50, 790,5));
		super.enemies.add(new Enemy(400, 790,5));
		
		super.player = new Player(805, 515);
		
		super.obstacles.add(new Rectangle(0, 850, 1200, 50));
		
		super.obstacles.add(new Rectangle(40, 475, 650, 50));
		super.obstacles.add(new Rectangle(200, 725, 200, 50)); 
		super.obstacles.add(new Rectangle(500, 675, 250, 50)); 
		super.obstacles.add(new Rectangle(775, 575, 250, 50)); 

		
		
		/* Window Bounds */
		//super.obstacles.add(new Rectangle(0, 900, 1200, 10));
		super.obstacles.add(new Rectangle(-10, 0, 10, 900));
		super.obstacles.add(new Rectangle(1200, 0, 10, 900));
		super.obstacles.add(new Rectangle(0, -10, 1200, 10));
	}

}
