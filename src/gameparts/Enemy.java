package gameparts;



import java.awt.*;
import java.awt.geom.Rectangle2D;

import java.util.*;

public class Enemy extends Character {

	public static final int ENEMY_WIDTH = 40;
	public static final int ENEMY_HEIGHT = 60;


	private double enemyAcceleration;
	private double enemyAngle;

	private double enemyVelocity;

	public Enemy(String filename, int x, int y, double enemyVelocity) {
		super(filename, x, y, ENEMY_WIDTH, ENEMY_HEIGHT);

		enemyAcceleration = .6;
		this.enemyVelocity = enemyVelocity;
	}

	// METHODS
	public void walk(double locX, double locY, boolean isPlatformer) {

		if (isPlatformer){
			generateAngle(locX,locY);

			if (xVelocity <= enemyVelocity && xVelocity >= -enemyVelocity){
				xVelocity += Math.cos(enemyAngle) * enemyAcceleration;
			}
		} else {
			generateAngle(locX,locY);
			if (yVelocity <= enemyVelocity && yVelocity >= -enemyVelocity){

				yVelocity += Math.sin(enemyAngle) * enemyAcceleration;
			}

			if (xVelocity <= enemyVelocity && xVelocity >= -enemyVelocity){
				xVelocity += Math.cos(enemyAngle) * enemyAcceleration;
			}
		}

	}

	public void jump() {
		super.jump();
	}



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
			walk(player1.getCenterX(), (int)player1.getCenterY(), true);
			if(player1.getCenterY()+ ENEMY_HEIGHT*1.7 < y){
				jump();
			}

		}
		else{
			walk(player1.getCenterX(), player1.getCenterY(), false);
		}

	}

	public Rectangle2D.Double makeHitBox(){
		return  new Rectangle2D.Double(x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
	}

	private void generateAngle(double crosshairX, double crosshairY){
		if (crosshairX < x){
			enemyAngle = Math.PI + Math.atan((crosshairY-y)/(crosshairX - x));
		} else {
			enemyAngle = Math.atan((crosshairY-y)/(crosshairX - x));
		}
	}

}
