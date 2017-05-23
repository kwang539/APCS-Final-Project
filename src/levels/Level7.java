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
public class Level7 extends Level{
	public Level7(){
		//the story will we made onto the image
		super("black-solid-color-background.jpg", "portal4.png");
		super.player = new Player(0, 420);

		door = new Rectangle(GamePanel.DRAWING_WIDTH-50, GamePanel.DRAWING_HEIGHT/2, 50, 50);
		
		//window bottom bound
		obstacles.add(new Rectangle(0, 900, 1200, 10));
		
		
	}
	
	public void reset(){
		
		
		//enemies = Oenemies;
		
		
		//obstacles = Oobstacles;
		player.reset(0, 420);
	}
}
