package levels;

import java.awt.Rectangle;

import gameparts.Enemy;
import gameparts.Player;

public class Level2 extends Level{

	public Level2(){
		super();
		super.enemies.add(new Enemy(400, 300));
		super.player = new Player(300, 600);
		super.obstacles.add(new Rectangle(200, 425, 700, 50));
	}
}
