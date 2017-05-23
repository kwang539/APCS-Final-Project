package levels;

import java.awt.Rectangle;

import gameparts.Enemy;
import gameparts.Player;
import gameparts.RangedEnemy;
import panels.GamePanel;


public class Level5 extends Level{


	public Level5(){
		super("pixelart5.gif", "portal4.png");
		door = new Rectangle(GamePanel.DRAWING_WIDTH-50, GamePanel.DRAWING_HEIGHT - 150, 50, 50);

		super.enemies.add(new RangedEnemy("andromalius.png", 1000, 400, 3, 53, 86));
		super.enemies.add(new RangedEnemy("andromalius.png", 1000, 600, 3, 53, 86));
		super.enemies.add(new Enemy("minion.png", 100, 800, 2, 46, 60));
		super.enemies.add(new Enemy("minion.png", 100, 50, 2, 46, 60));
		super.hasRangedEnemy=true;


		super.player = new Player(50, 375);

		super.obstacles.add(new Rectangle(200, 200, 50, 500));
		super.obstacles.add(new Rectangle(0, 450, 100, 50));
		super.obstacles.add(new Rectangle(500, 800, 600, 100));
		super.obstacles.add(new Rectangle(250, 850, 200, 50));

	}
	
	public void reset(){
		enemies.clear();

		super.enemies.add(new RangedEnemy("andromalius.png", 1000, 400, 3, 53, 86));
		super.enemies.add(new RangedEnemy("andromalius.png", 1000, 600, 3, 53, 86));
		super.enemies.add(new Enemy("minion.png", 100, 800, 2, 46, 60));
		super.enemies.add(new Enemy("minion.png", 100, 50, 2, 46, 60));

		player.reset(50, 375);
	}
}