package bosses;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import gameparts.Bullet;
import gameparts.Enemy;
import gameparts.Player;

/** This class represents a Boss with ranged capabilities
 * 
 * @author Akshay
 *
 */
public class RangedBoss extends Boss {

	private ArrayList<Bullet> bossBullets;

	/** Constructs a new RangedBoss.
	 * 
	 * @param filename The name of the image file to draw
	 * @param x Starting x coordinate
	 * @param y Starting y coordinate
	 * @param velocity Speed at which this boss moves
	 * @param width Width at which to draw image
	 * @param height Height at which to draw image
	 */
	public RangedBoss(String filename, int x, int y, double velocity, int width, int height) {
		super(filename, x,y, velocity, width, height);
		bossBullets = new ArrayList<Bullet>();
	}

	/** Defines enemy AI behavior. Tracks player, and if this RangedBoss collides with an
	 * obstacle, strafes around the obstacle to the bottom-right.
	 * 
	 * @param obstacles the array of obstacles in the game
	 * @param isPlatformer whether the game is in platform mode or not
	 * @param player1 the current Player object
	 */
	public void act(ArrayList<Shape> obstacles, boolean isPlatformer, Player player1){

		double xCoord = getX();
		double yCoord = getY();
		double width = getWidth();
		double height = getHeight();

		// ***********Y AXIS***********

		if (isPlatformer){

			yVelocity += gravity; // GRAVITY

			double yCoord2 = yCoord + yVelocity;

			Rectangle2D.Double stretchY = new Rectangle2D.Double(xCoord,Math.min(yCoord,yCoord2),width,height+Math.abs(yVelocity));

			onASurface = false;

			if (yVelocity > 0) {
				Shape standingSurface = null;
				for (Shape s : obstacles) {
					if (s.intersects(stretchY)) {
						onASurface = true;
						standingSurface = s;
						yVelocity = 0;
					}
				}
				if (standingSurface != null) {
					Rectangle r = standingSurface.getBounds();
					yCoord2 = r.getY()-height;
				}
			} else if (yVelocity < 0) {
				Shape headSurface = null;
				for (Shape s : obstacles) {
					if (s.intersects(stretchY)) {
						headSurface = s;
						yVelocity = 0;
					}
				}
				if (headSurface != null) {
					Rectangle r = headSurface.getBounds();
					yCoord2 = r.getY()+r.getHeight();
				}
			}

			if (Math.abs(yVelocity) < .2)
				yVelocity = 0;

			if (yVelocity > 0) {
				Shape standingSurface = null;
				for (Shape s : obstacles) {
					if (s.intersects(stretchY)) {
						standingSurface = s;
						yVelocity = 0;
					}
				}
				if (standingSurface != null) {
					Rectangle r = standingSurface.getBounds();
					yCoord2 = r.getX()-width;
				}
			} else if (yVelocity < 0) {
				Shape headSurface = null;
				for (Shape s : obstacles) {
					if (s.intersects(stretchY)) {
						headSurface = s;
						yVelocity = 0;
					}
				}
				if (headSurface != null) {
					Rectangle r = headSurface.getBounds();
					yCoord2 = r.getX()+r.getWidth();
				}
			}

			if (Math.abs(yVelocity) < .2)
				yVelocity = 0;

			// ***********X AXIS***********


			xVelocity *= friction;

			double xCoord2 = xCoord + xVelocity;

			Rectangle2D.Double strechX = new Rectangle2D.Double(Math.min(xCoord,xCoord2),yCoord2,width+Math.abs(xVelocity),height);

			if (xVelocity > 0) {
				Shape rightSurface = null;
				for (Shape s : obstacles) {
					if (s.intersects(strechX)) {
						rightSurface = s;
						xVelocity = 0;
					}
				}
				if (rightSurface != null) {
					Rectangle r = rightSurface.getBounds();
					xCoord2 = r.getX()-width;
				}
			} else if (xVelocity < 0) {
				Shape leftSurface = null;
				for (Shape s : obstacles) {
					if (s.intersects(strechX)) {
						leftSurface = s;
						xVelocity = 0;
					}
				}
				if (leftSurface != null) {
					Rectangle r = leftSurface.getBounds();
					xCoord2 = r.getX()+r.getWidth();
				}
			}


			if (Math.abs(xVelocity) < .2)
				xVelocity = 0;

			moveToLocation(xCoord2,yCoord2);	
		}


		else {

			yVelocity *= friction;
			double yCoord2 = yCoord + yVelocity;

			Rectangle2D.Double strechY = new Rectangle2D.Double(xCoord,Math.min(yCoord,yCoord2),width,height+Math.abs(yVelocity));
			
			if (yVelocity > 0) {
				Shape standingSurface = null;
				for (Shape s : obstacles) {
					if (s.intersects(strechY)) {
						standingSurface = s;
						yVelocity = 0;

					}
				}
				if (standingSurface != null) {
					Rectangle r = standingSurface.getBounds();
					yCoord2 = r.getY()-height;
				}
			} else if (yVelocity < 0) {
				Shape headSurface = null;
				for (Shape s : obstacles) {
					if (s.intersects(strechY)) {
						headSurface = s;
						yVelocity = 0;
					}
				}
				if (headSurface != null) {
					Rectangle r = headSurface.getBounds();
					yCoord2 = r.getY()+r.getHeight();
				}
			}

			if (Math.abs(yVelocity) < .2)
				yVelocity = 0;

			// ***********X AXIS***********

			xVelocity *= friction;

			double xCoord2 = xCoord + xVelocity;

			Rectangle2D.Double strechX = new Rectangle2D.Double(Math.min(xCoord,xCoord2),yCoord2,width+Math.abs(xVelocity),height);

			if (xVelocity > 0) {
				Shape rightSurface = null;
				for (Shape s : obstacles) {
					if (s.intersects(strechX)) {
						rightSurface = s;
						xVelocity = 0;
					}
				}
				if (rightSurface != null) {
					Rectangle r = rightSurface.getBounds();
					xCoord2 = r.getX()-width;
				}
			} else if (xVelocity < 0) {
				Shape leftSurface = null;
				for (Shape s : obstacles) {
					if (s.intersects(strechX)) {
						leftSurface = s;
						xVelocity = 0;
					}
				}
				if (leftSurface != null) {
					Rectangle r = leftSurface.getBounds();
					xCoord2 = r.getX()+r.getWidth();
				}
			}


			if (Math.abs(xVelocity) < .2)
				xVelocity = 0;

			moveToLocation(xCoord2,yCoord2);

		}

		walk(player1.getCenterX(), player1.getCenterY());
	}

	/** Fires a bullet onscreen.
	 * 
	 * @param PlayerLocX the x-coordinate of the Player this Bullet will track
	 * @param PlayerLocY the y-coordinate of the Player this Bullet will track
	 */
	public void fire(int PlayerLocX, int PlayerLocY){
		bossBullets.add(new Bullet("fireball.png", (int) this.getCenterX(), (int)this.getCenterY(), 25, 25, PlayerLocX, PlayerLocY));
	}

	/** Returns the array of Bullets that this Boss fired
	 * 
	 * @return bossBullets the Bullet array
	 */
	public ArrayList<Bullet> getbossBullets(){
		return bossBullets;
	}


}
