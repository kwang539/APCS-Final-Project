package levels;

import java.awt.Rectangle;

import bosses.Boss;
import gameparts.Enemy;
import gameparts.Player;
import gameparts.RangedEnemy1;
import panels.GamePanel;


public class Level3 extends Level{

	
	public Level3(){
	super("pixelart3.png", "portal4.png");
	door = new Rectangle(GamePanel.DRAWING_WIDTH-50, GamePanel.DRAWING_HEIGHT - 150, 50, 50);

	super.enemies.add(new Enemy("minion.png",100, 200, 3, 46, 60));
	super.enemies.add(new Enemy("minion.png",800, 500, 3, 46, 60));

	super.enemies.add(new Boss("bossmelee.png",1050, 275,2, 60, 80));

	hasBoss = true;
	
	super.player = new Player(100, 600);
	
	super.obstacles.add(new Rectangle(100, 300, 200, 50));
	super.obstacles.add(new Rectangle(250, 350, 50, 100));
	super.obstacles.add(new Rectangle(300, 400, 150, 50));
	super.obstacles.add(new Rectangle(400, 300, 50, 100));
	
	
	
	super.obstacles.add(new Rectangle(750, 240, 50, 130));
	super.obstacles.add(new Rectangle(800, 300, 200, 50));
	super.obstacles.add(new Rectangle(950, 350, 50, 100));
	super.obstacles.add(new Rectangle(1000, 400, 150, 50));
	super.obstacles.add(new Rectangle(0, 0, 1200, 130));


	
	super.obstacles.add(new Rectangle(700, 600, 50, 150));
	super.obstacles.add(new Rectangle(700, 750, 250, 50));

	
	}
	
	public void reset(){
		enemies.clear();
		super.enemies.add(new Enemy("minion.png",100, 200, 3, 46, 60));
		super.enemies.add(new Enemy("minion.png",800, 500, 3, 46, 60));

		super.enemies.add(new Boss("bossmelee.png",1050, 275,2, 60, 80));

		player.reset(100, 600);
	}
}
