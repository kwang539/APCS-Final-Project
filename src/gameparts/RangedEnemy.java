package gameparts;

import java.util.ArrayList;

/** This class represents an Enemy that can fire at the Player.
 * 
 * @author Akshay
 *
 */
public class RangedEnemy extends Enemy {
	
	private ArrayList<Bullet> bossBullets;

	/** Constructs a new RangedEnemy.
	 * 
	 * @param filename The name of the image file to draw
	 * @param x Starting x coordinate
	 * @param y Starting y coordinate
	 * @param velocity Speed at which this ranged enemy moves
	 * @param width Width at which to draw image
	 * @param height Height at which to draw image
	 */
	public RangedEnemy(String filename, int x, int y, double velocity, int width, int height) {
		super(filename, x,y, velocity, width, height);
		
		bossBullets = new ArrayList<Bullet>();
		
	}
	
	/** Fires a bullet onscreen belonging to this RangedEnemy.
	 * 
	 * @param PlayerLocX the location of the Player object to track, x-coordinate
	 * @param PlayerLocY the location of the Player object to track, y-coordinate
	 */
	public void fire(int PlayerLocX, int PlayerLocY){
		bossBullets.add(new Bullet("fireball.png", (int) this.getCenterX(), (int)this.getCenterY(), 25, 25, PlayerLocX, PlayerLocY));
	}
	
	/** Returns Bullet array
	 * 
	 * @return Bullet array
	 */
	public ArrayList<Bullet> getbossBullets(){
		return bossBullets;
	}
	

}
