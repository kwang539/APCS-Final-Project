package levels;

import java.awt.Rectangle;
import gameparts.Enemy;
import gameparts.Player;
import panels.GamePanel;

public class Level1 extends Level{
	

	public Level1(){
		super("grass.png", "blocktester.jpg");
		door = new Rectangle(GamePanel.DRAWING_WIDTH-50, GamePanel.DRAWING_HEIGHT - 100, 50, 50);

		
		super.enemies.add(new Enemy("mario.png",300, 200,4));
		
		super.player = new Player(300, 600);
		
		super.obstacles.add(new Rectangle(50, 425, 500, 50));
		super.obstacles.add(new Rectangle(0, 850, 500, 50));
		super.obstacles.add(new Rectangle(700, 850, 500, 50));
		super.obstacles.add(new Rectangle(1000, 200, 50, 500));
		
		/* Window Bounds */
		//super.obstacles.add(new Rectangle(0, 900, 1200, 10));
		
	}
	
	
	
}
