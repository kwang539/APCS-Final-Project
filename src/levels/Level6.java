package levels;

import java.awt.Rectangle;

import bosses.Boss;
import gameparts.RangedEnemy;
import gameparts.Enemy;
import gameparts.Player;
import gameparts.RangedEnemy;
import panels.GamePanel;


public class Level6 extends Level{


	public Level6(){
		super("pixelart6.png", "portal4.png");
		door = new Rectangle(GamePanel.DRAWING_WIDTH-50, GamePanel.DRAWING_HEIGHT - 150, 50, 50);
		
		super.obstacles.add(new Rectangle(400, 300, 50, 300));
		super.obstacles.add(new Rectangle(450, 300, 250, 50));
		super.obstacles.add(new Rectangle(700, 300, 50, 300));
		super.obstacles.add(new Rectangle(400, 600, 100, 50));
		super.obstacles.add(new Rectangle(650, 600, 100, 50));

		super.obstacles.add(new Rectangle(0, 850, 750, 50));
		//super.obstacles.add(new Rectangle(400, 850, 400, 50));
		super.obstacles.add(new Rectangle(930, 850, 270, 50));

		super.obstacles.add(new Rectangle (50, 400, 100, 50));
		super.obstacles.add(new Rectangle (1100, 400, 100, 50));

		super.hasBoss = true;
		super.hasRangedEnemy = true;
		super.enemies.add(new Boss("bossmelee.png", 50, 50, 3, 60, 80));
		super.enemies.add(new RangedEnemy("andromalius.png", 1000, 100, 3, 53, 86));
		super.enemies.add(new Enemy("minion.png",300, 750,7, 45, 56));
		super.enemies.add(new Enemy("minion.png",800, 750,7, 45, 56));


//		super.enemies.add(new Boss("mario.png", 50, 50, 3));
//		super.enemies.add(new RangedEnemy("playerchar.png", 1150, 50, 3));
//		super.enemies.add(new Enemy("mario.png", 300, 750, 7));
//		super.enemies.add(new Enemy("mario.png", 800, 750, 7));
//		

		super.player = new Player(560, 400);
	}
	
	public void reset(){
		enemies.clear();
		
//		super.enemies.add(new Boss("mario.png", 50, 50, 3));
//		super.enemies.add(new RangedEnemy("playerchar.png", 1150, 50, 3));
//		super.enemies.add(new Enemy("mario.png", 300, 750, 7));
//		super.enemies.add(new Enemy("mario.png", 800, 750, 7));
		

		super.enemies.add(new Boss("bossmelee.png", 50, 50, 3, 60, 80));
		super.enemies.add(new RangedEnemy("andromalius.png", 1000, 100, 3, 53, 86));
		super.enemies.add(new Enemy("minion.png",300, 750,7, 45, 56));
		super.enemies.add(new Enemy("minion.png",800, 750,7, 45, 56));

		
		player.reset(560, 400);

	}
	
	
}