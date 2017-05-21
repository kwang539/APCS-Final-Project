package levels;

import java.awt.Rectangle;

import bosses.Boss;
import gameparts.Enemy;
import gameparts.Player;
import panels.GamePanel;


public class Level3 extends Level{

	
	public Level3(){
	super("backgroundtester.png", "blocktester.jpg");
	door = new Rectangle(GamePanel.DRAWING_WIDTH-50, GamePanel.DRAWING_HEIGHT - 150, 50, 50);

	//super.enemies.add(new Enemy("mario.png",300, 700,4));
	
	//super.enemies.add(new Boss("playerchar.png", 150, 750, 1));
	super.enemies.add(new Enemy("mario.png",100, 200,5));
	super.enemies.add(new Boss("mario.png",1050, 275,2));

	
	super.player = new Player(500, 100);
	
	super.obstacles.add(new Rectangle(100, 300, 200, 50));
	super.obstacles.add(new Rectangle(250, 350, 50, 100));
	super.obstacles.add(new Rectangle(300, 400, 150, 50));
	super.obstacles.add(new Rectangle(400, 300, 50, 100));
	
	
	super.obstacles.add(new Rectangle(750, 220, 50, 130));
	super.obstacles.add(new Rectangle(800, 300, 200, 50));
	super.obstacles.add(new Rectangle(950, 350, 50, 100));
	super.obstacles.add(new Rectangle(1000, 400, 150, 50));
	super.obstacles.add(new Rectangle(1100, 300, 50, 100));

	
	}
}
