package levels;

import java.awt.Rectangle;

import gameparts.Enemy;
import gameparts.Player;
import gameparts.RangedEnemy;
import panels.GamePanel;


public class Level5 extends Level{


	public Level5(){
		super("backgroundtester.png", "blocktester.jpg");
		door = new Rectangle(GamePanel.DRAWING_WIDTH-50, GamePanel.DRAWING_HEIGHT - 150, 50, 50);

		super.enemies.add(new RangedEnemy("playerchar.png", 1000, 400, 3));
		super.enemies.add(new RangedEnemy("playerchar.png", 1000, 600, 3));
		super.enemies.add(new Enemy("mario.png", 700, 700, 3));
		super.enemies.add(new Enemy("mario.png", 700, 100, 3));
		super.hasRangedEnemy=true;


		super.player = new Player(50, 375);

		super.obstacles.add(new Rectangle(200, 200, 50, 500));
		super.obstacles.add(new Rectangle(0, 450, 100, 50));
		super.obstacles.add(new Rectangle(500, 800, 600, 100));
		super.obstacles.add(new Rectangle(250, 850, 200, 50));

	}
}
