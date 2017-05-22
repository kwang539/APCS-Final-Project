package levels;

import java.awt.Rectangle;

import bosses.Boss;
import gameparts.Player;
import gameparts.RangedEnemy1;
import panels.GamePanel;

/**
 * 
 * @author Kevin
 *this level is where the story goes
 */
public class Level0 extends Level{
	public Level0(){
		//the story will we made onto the image
		super("backgroundtester2.gif", "blocktester.jpg");
		super.player = new Player(0, 450);

		door = new Rectangle(GamePanel.DRAWING_WIDTH-50, GamePanel.DRAWING_HEIGHT/2, 50, 50);
		
		//window bottom bound
		obstacles.add(new Rectangle(0, 900, 1200, 10));
		
		
	}
	
	public void reset(){
		
		
		//enemies = Oenemies;
		
		
		//obstacles = Oobstacles;
		player.reset(0, 450);
	}
}
