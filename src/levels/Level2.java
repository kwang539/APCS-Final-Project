package levels;

import java.awt.Rectangle;

import bosses.RangedEnemy;
import bosses.Boss;
import gameparts.Enemy;
import gameparts.Player;
import gameparts.RangedEnemy1;
import panels.GamePanel;

public class Level2 extends Level{
	
	public Level2(){
		super("pixelart2.png", "portal4.png");
		door = new Rectangle(GamePanel.DRAWING_WIDTH-50, GamePanel.DRAWING_HEIGHT - 500, 50, 50);

		
		super.enemies.add(new Enemy("minion.png",50, 790,3, 46, 60));
		super.enemies.add(new Enemy("minion.png",400, 790,3, 46, 60));
		

	
		super.enemies.add(new RangedEnemy1("andromalius.png",300, 700,3, 53, 68));

		
		hasRangedEnemy = true;
		super.player = new Player(805, 515);
		
		super.obstacles.add(new Rectangle(0, 850, 1200, 50));
		
		super.obstacles.add(new Rectangle(40, 475, 650, 50));
		super.obstacles.add(new Rectangle(200, 700, 200, 50)); 
		super.obstacles.add(new Rectangle(500, 675, 250, 50)); 
		super.obstacles.add(new Rectangle(775, 575, 250, 50)); 

		
		
	
	
	}
	
	public void reset(){
		enemies.clear();
		
		super.enemies.add(new Enemy("minion.png",50, 790,3, 46, 60));
		super.enemies.add(new Enemy("minion.png",400, 790,3, 46, 60));
		

	
		super.enemies.add(new RangedEnemy1("andromalius.png",300, 700,3, 53, 68));

		player.reset(805, 515);
	}

}
