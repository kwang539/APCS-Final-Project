package bosses;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import gameparts.Bullet;
import gameparts.Enemy;
import gameparts.RangedEnemy;


/** This class represents a melee Boss that tracks the Player.
 * 
 * @author Akshay
 *
 */
public class Boss extends RangedEnemy {
	
	private ArrayList<Bullet> bossBullets;
	private HealthBar healthBar;

	/** Constructs a new Boss.
	 * 
	 * @param filename The name of the image file to draw
	 * @param x Starting x coordinate
	 * @param y Starting y coordinate
	 * @param velocity Speed at which this boss moves
	 * @param width Width at which to draw image
	 * @param height Height at which to draw image
	 */
	public Boss(String filename, int x, int y, double velocity, int width, int height) {
		super(filename, x,y, velocity, width, height);
		healthBar = new HealthBar(100);
		bossBullets = new ArrayList<Bullet>();
	}
	
	/** Fires a bullet onscreen.
	 * 
	 * @param PlayerLocX the x-coordinate of the Player this Bullet will track
	 * @param PlayerLocY the y-coordinate of the Player this Bullet will track
	 */
	public void fire(int PlayerLocX, int PlayerLocY){
		bossBullets.add(new Bullet("fireball.png", (int) this.getCenterX(), (int)this.getCenterY(), 25, 25, PlayerLocX, PlayerLocY));
	}
	
	/** Reduces this Boss's HP
	 * 
	 */
	public void takeDamage(){
		healthBar.reduceHP(17);
	}
	
	/** Returns this Boss's HP
	 * 
	 * @return current HP
	 */
	public int getCurrentHP(){
		return healthBar.getCurrentHP();
	}
	
	/** Returns the array of Bullets that this Boss fired
	 * 
	 * @return bossBullets the Bullet array
	 */
	public ArrayList<Bullet> getbossBullets(){
		return bossBullets;
	}

	/** Draws this Boss, along with its HealthBar
	 * 
	 * @param g the Graphics object used in Swing
	 * @param io ImageObserver 
	 */
	public void draw(Graphics g, ImageObserver io) {
		super.draw(g, io);
		healthBar.draw((Graphics2D)g);
	}

}
