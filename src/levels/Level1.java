package levels;

import java.awt.Rectangle;
import gameparts.Enemy;
import gameparts.Player;

public class Level1 extends Level{
	
	public Level1(){
		super();
		super.enemies.add(new Enemy(300, 300));
		super.player = new Player(300, 600);
		super.obstacles.add(new Rectangle(50, 425, 700, 50));
	}
	
	
	
	

}
