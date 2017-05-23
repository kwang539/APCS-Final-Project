package gameparts;



import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;

/** Represents a character affected by friction and gravity
 * 
 * @author Akshay
 *
 */
public class Character extends MovingImage {

	public static final int MARIO_WIDTH = 40;
	public static final int MARIO_HEIGHT = 60;

	protected double xVelocity, yVelocity;
	protected boolean onASurface;
	protected double friction;
	protected double gravity;
	protected double jumpStrength;
	protected double maxVelocity;

	/** Constructs a new Character.
	 * 
	 * @param name the filename of the Image to be drawn
	 * @param x the x-coordinate of the Image
	 * @param y the y-coordinate of the Image
	 * @param width the width of the Image
	 * @param height the height of the Image
	 */
	public Character(String name, int x, int y, int width, int height) {
		super(name, x, y, width, height);
		xVelocity = 0;
		yVelocity = 0;
		onASurface = false;
		gravity = 0.23;
		friction = 0.85;
		jumpStrength = 9.5;
		maxVelocity = 5;
	}

	// METHODS
	/** Moves this Character based off of input direction
	 * 
	 * @param dir -2 and 2 correspond to W and S keys. -1 and 1 correspond to A and D keys.
	 */
	public void walk(int dir) {



		if(dir == -2 || dir == 2){

			int newdir = dir/2;


			if (yVelocity <= maxVelocity && yVelocity >= -maxVelocity){
				yVelocity += newdir;
			}
		}


		if(dir == -1 || dir == 1){
			if (xVelocity <= maxVelocity && xVelocity >= -maxVelocity)
				xVelocity += dir;
		}

	}

	/** Makes this Character jump and land.
	 * 
	 */
	public void jump() {
		if (onASurface)
			yVelocity -= jumpStrength;
	}

	/** Detects collisions between this Character and obstacles, and halts movement if they occur.
	 * 
	 * @param obstacles the array of obstacles on the map
	 * @param isPlatformer whether the gamemode is in platform or top-down mode
	 */
	public void act(ArrayList<Shape> obstacles, boolean isPlatformer) {
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
	}



}




