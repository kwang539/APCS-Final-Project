package levels;

import java.awt.Rectangle;

import gameparts.Enemy;
import gameparts.Player;
import panels.GamePanel;


public class Level5 extends Level{


	public Level5(){
		super("backgroundtester.png", "blocktester.jpg");
		door = new Rectangle(GamePanel.DRAWING_WIDTH-50, GamePanel.DRAWING_HEIGHT - 150, 50, 50);

		//super.enemies.add(new Enemy("mario.png",300, 700,4));

		//super.enemies.add(new Boss("playerchar.png", 150, 750, 1));
		//super.enemies.add(new Enemy("mario.png",100, 200,5));
		//super.enemies.add(new Boss("playerchar.png",1050, 275,2));
		//super.hasBoss = true;


		super.player = new Player(50, 375);

		super.obstacles.add(new Rectangle(200, 200, 50, 500));
		super.obstacles.add(new Rectangle(0, 450, 100, 50));
		super.obstacles.add(new Rectangle(500, 800, 600, 100));

	}
}
