package gameparts;



import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**This class represents a moving melee Enemy.
 * 
 * @author Akshay
 *
 */
public class Enemy extends Character {

;

	private double dX, dY;
	private boolean isHit;

	private Rectangle2D.Double hitbox;
	
	private double enemyAcceleration;
	private double enemyAngle;
	
	private double enemyVelocity;
	private int width, height;

	/** Constructs a new Enemy.
	 * 
	 * @param filename The name of the image file to draw
	 * @param x Starting x coordinate
	 * @param y Starting y coordinate
	 * @param velocity Speed at which this enemy moves
	 * @param width Width at which to draw image
	 * @param height Height at which to draw image
	 */
	public Enemy(String filename, int x, int y, double enemyVelocity, int width, int height) {
		super(filename, x, y, width, height);
		dX = 0;
		dY = 0;
		enemyAcceleration = .6;
		this.enemyVelocity = enemyVelocity;
		this.width = width;
		this.height = height;
	}

	/** Tracks the current coordinates, assuming the gamemode is in platform mode.
	 * 
	 * @param locX the location to track, x-coordinate
	 * @param locY the location to track, y-coordinate
	 */
	public void walk(double locX, double locY) {
		generateAngle(locX,locY);
		if (yVelocity <= enemyVelocity && yVelocity >= -enemyVelocity){
			
			yVelocity += Math.sin(enemyAngle) * enemyAcceleration;
		}
		
		if (xVelocity <= enemyVelocity && xVelocity >= -enemyVelocity){
			xVelocity += Math.cos(enemyAngle) * enemyAcceleration;
		}

	}
	
	/** Tracks the current coordinates, assuming the gamemode is in top-down mode.
	 * 
	 * @param locX the location to track, x-coordinate
	 * @param locY the location to track, y-coordinate
	 */
	public void walk(double locX, int locY) {

		generateAngle(locX,locY);
		
		if (xVelocity <= enemyVelocity && xVelocity >= -enemyVelocity){
			xVelocity += Math.cos(enemyAngle) * enemyAcceleration;
		}
	}

	/** Makes this Enemy jump and fall.
	 * 
	 */
	public void jump() {
		super.jump();
	}
	
	/** Serves as Enemy AI. Tracks the current Player object and moves towards it.
	 * Detects collisions between objects and the Enemy.
	 * 
	 * @param obstacles the array of obstacles on the map
	 * @param isPlatformer whether or not the gamemode is in platform or top-down mode
	 * @param player1 the current Player object
	 */
	public void act(ArrayList<Shape> obstacles, boolean isPlatformer, Player player1) {
	
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
					xVelocity += enemyAcceleration;
				
						
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
					xVelocity += enemyAcceleration;

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
	
		
		if(isPlatformer){
			walk(player1.getCenterX(), (int)player1.getCenterY());
			if(player1.getCenterY()+ this.height*1.7 < y){
			jump();
			}

		}
		else{
		walk(player1.getCenterX(), (double)player1.getCenterY());
		}

	}

	/** Generates this Enemy's hitbox
	 * 
	 * @return a Rectangle2D.Double representing the hitbox
	 */
	public Rectangle2D.Double makeHitBox(){
		return  new Rectangle2D.Double(x, y, this.width, this.height);
	}

	/** Generates the angle between this Enemy and the target location
	 * 
	 * @param crosshairX the x-coordinate of the location to track
	 * @param crosshairY the y-coordinate of the location to track
	 */
	private void generateAngle(double crosshairX, double crosshairY){
		if (crosshairX < x){
			enemyAngle = Math.PI + Math.atan((crosshairY-y)/(crosshairX - x));

		} else {
			enemyAngle = Math.atan((crosshairY-y)/(crosshairX - x));
		}
	}
	

}
