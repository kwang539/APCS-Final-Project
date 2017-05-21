package levels;

import java.awt.Rectangle;

import bosses.Boss;
import gameparts.Enemy;
import gameparts.Player;
import gameparts.RangedEnemy;
import panels.GamePanel;

public class Level2 extends Level{
	
	public Level2(){
		super("backgroundtester2.gif", "blocktester.jpg");
		door = new Rectangle(GamePanel.DRAWING_WIDTH-50, GamePanel.DRAWING_HEIGHT - 500, 50, 50);

		
		super.enemies.add(new Enemy("mario.png",50, 790,5));
		super.enemies.add(new Enemy("mario.png",400, 790,5));
		super.enemies.add(new Enemy("mario.png",300, 790,5));

		super.enemies.add(new RangedEnemy("playerchar.png",400, 600,3));
		super.enemies.add(new RangedEnemy("playerchar.png",300, 600,3));

		
		hasRangedEnemy = true;
		super.player = new Player(805, 515);
		
		super.obstacles.add(new Rectangle(0, 850, 1200, 50));
		
		super.obstacles.add(new Rectangle(40, 475, 650, 50));
		super.obstacles.add(new Rectangle(200, 700, 200, 50)); 
		super.obstacles.add(new Rectangle(500, 675, 250, 50)); 
		super.obstacles.add(new Rectangle(775, 575, 250, 50)); 

		
		
	
	
	}

}
