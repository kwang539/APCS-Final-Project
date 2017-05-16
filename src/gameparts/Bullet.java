package gameparts;

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;

/**
 * Bullet class that contains the info for how the bullet moves and its hit detection
 * @author Jacob, Keving, Akshay
 * Date: 5/15/17
 *
 */
public class Bullet extends MovingImage{

	private int bulletVelocity;
	private double bulletAngle;

	/**
	 * 
	 * @param filename Filename of bullet image
	 * @param x xCord of bullet
	 * @param y yCord of bullet
	 * @param w width of bullet
	 * @param h height of bullet
	 * @param crosshairX xCord of crosshair
	 * @param crosshairY yCord of crosshair
	 */
	public Bullet(String filename, int x, int y, int w, int h, double crosshairX, double crosshairY ) {
		super(filename, x, y, w, h);
		bulletVelocity = 10;
		bulletAngle = 0;
		generateAngle(crosshairX, crosshairY);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param crosshairX Xcord for crosshair
	 * @param crosshairY Ycord for crosshair
	 * @param border The rectangle that the corss hair contains
	 */
	public void fire(double crosshairX, double crosshairY, Rectangle border){
		
//
//		if (crosshairX < x){
//			//mouseAngle = Math.PI + Math.atan(scalar);
//			bulletAngle = Math.PI + Math.atan((crosshairY-y)/(crosshairX - x));
//
//		} else {
//			bulletAngle = Math.atan((crosshairY-y)/(crosshairX - x));
//		}

		//while(this.intersects(border) == false){


		x += Math.cos(bulletAngle) * bulletVelocity;
		y += Math.sin(bulletAngle) * bulletVelocity;
	}
	
	/**
	 * Generates the bulletangle the the bullet will take from the player when shot
	 * @param crosshairX xCord for crosshair
	 * @param crosshairY yCord for crosshair
	 */
	private void generateAngle(double crosshairX, double crosshairY){
		if (crosshairX < x){
			//mouseAngle = Math.PI + Math.atan(scalar);
			bulletAngle = Math.PI + Math.atan((crosshairY-y)/(crosshairX - x));

		} else {
			bulletAngle = Math.atan((crosshairY-y)/(crosshairX - x));
		}
	}
	
	/**
	 * Determines if bullet hits an obstacle, and if does stops the bullet
	 * @param obstacles Passes in array of obstacles
	 */
	public void hitObstacle(ArrayList<Shape> obstacles){
		for (Shape s : obstacles) {
		if(this.getBounds2D().intersects(s.getBounds2D())){
			bulletVelocity = 0;
			x= 0;
			y = 0;
		}
			
		}
	}
	

}





