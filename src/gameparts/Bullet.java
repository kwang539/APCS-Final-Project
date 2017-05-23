package gameparts;

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;

/** Represents an onscreen Bullet to be fired by characters
 * 
 * @author Akshay
 *
 */
public class Bullet extends MovingImage{

	private int bulletVelocity;
	private double bulletAngle;

	/** Constructs a new Bullet
	 * 
	 * @param filename the Image to be drawn representing this Bullet
	 * @param x the x-coordinate of this Bullet image
	 * @param y the y-coordinate of this Bullet image
	 * @param w the width of this Bullet image
	 * @param h the height of this Bullet image
	 * @param crosshairX the current position of the mouse
	 * @param crosshairY the current position of the mouse
	 */
	public Bullet(String filename, int x, int y, int w, int h, double crosshairX, double crosshairY ) {
		super(filename, x, y, w, h);
		bulletVelocity = 10;
		bulletAngle = 0;
		generateAngle(crosshairX, crosshairY);
	}

	/** Moves this bullet across the screen
	 * 
	 */
	public void fire(){

		x += Math.cos(bulletAngle) * bulletVelocity;
		y += Math.sin(bulletAngle) * bulletVelocity;
	}
	
	/** Calculates the angle at which this Bullet will travel
	 * 
	 * @param crosshairX the x-position of the mouse
	 * @param crosshairY the y-position of the mouse
	 */
	private void generateAngle(double crosshairX, double crosshairY){
		if (crosshairX < x){
			bulletAngle = Math.PI + Math.atan((crosshairY-y)/(crosshairX - x));

		} else {
			bulletAngle = Math.atan((crosshairY-y)/(crosshairX - x));
		}
	}
	
	/** Determines whether this Bullet collided with an obstacle or not
	 * 
	 * @param obstacles the array of obstacles on the map
	 * @return true if collision occured, false otherwise
	 */
	public boolean hitObstacle(ArrayList<Shape> obstacles){
		
		for (Shape s : obstacles) {
		if(this.getBounds2D().intersects(s.getBounds2D())){
			bulletVelocity = 0;
			x= 0;
			y = 0;
			return true;
		
		}
			
		}
		return false;
	}
	

}





