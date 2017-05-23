package levels;

import java.awt.Rectangle;

import gameparts.Enemy;
import gameparts.Player;
import gameparts.RangedEnemy1;
import panels.GamePanel;


public class Level4 extends Level{

	
	public Level4(){
	super("pixelart4.png", "portal4.png");
	door = new Rectangle(GamePanel.DRAWING_WIDTH-50, GamePanel.DRAWING_HEIGHT - 150, 50, 50);

	super.enemies.add(new Enemy("minion.png", 100, 200, 3, 46, 60));
	super.enemies.add(new Enemy("minion.png", 1000, 200, 3, 46, 60));
	
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

		super.enemies.add(new Enemy("minion.png", 100, 200, 3, 46, 60));
		super.enemies.add(new Enemy("minion.png", 1000, 200, 3, 46, 60));

		player.reset(590, 440);
	}

}