package gameparts;



import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Enemy extends Character {

	public static final int ENEMY_WIDTH = 40;
	public static final int ENEMY_HEIGHT = 60;

	private double dX, dY;
	private boolean isHit;
	//the hitbox field(rect) should move with the image, so that way you won't have to create new hitboxes every second
	//not implemented yet
	private Rectangle2D.Double hitbox;
	
	private double enemyAcceleration;
	private double enemyAngle;
	

	public Enemy(int x, int y) {
		super("playerchar.png", x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
		dX = 0;
		dY = 0;
		enemyAcceleration = .6;
	}

	// METHODS
	public void walk(double locX, double locY) {
		// WALK!
		//THIS MAKES IT NOT MERGE!!!
		
		generateAngle(locX,locY);
		if (yVelocity <= 4 && yVelocity >= -4){
			
			yVelocity += Math.sin(enemyAngle) * enemyAcceleration;
			//y += newdir;
		}
		
		if (xVelocity <= 4 && xVelocity >= -4){
			xVelocity += Math.cos(enemyAngle) * enemyAcceleration;
		}
		//x += Math.cos(enemyAngle) * enemyVelocity;
		//y += Math.sin(enemyAngle) * enemyVelocity;
	}


	public void jump() {
		// JUMP!
	}

	public void act(ArrayList<Shape> obstacles, boolean isPlatformer, Player player1) {
		//dY += 0.5;
		super.act(obstacles, isPlatformer);
		
		
		walk(player1.getCenterX(), player1.getCenterY());


	}

	//should only be called once within this class for most efficiency
	public Rectangle2D.Double makeHitBox(){
		return  new Rectangle2D.Double(x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
	}

	public void setIsHit(boolean hit){
		isHit = hit;
	}
	public boolean getIsHit(){
		return isHit;
	}

	//doesn't reallly work
	public void removeEnemy(){
		hitbox = null;
		dX = 0;
		dY = 0;


		x= 90;
		y = 30;
		//System.out.println("remove");
	}
	
	private void generateAngle(double crosshairX, double crosshairY){
		if (crosshairX < x){
			//mouseAngle = Math.PI + Math.atan(scalar);
			enemyAngle = Math.PI + Math.atan((crosshairY-y)/(crosshairX - x));

		} else {
			enemyAngle = Math.atan((crosshairY-y)/(crosshairX - x));
		}
	}
	

	public void hitByBullet(ArrayList<Bullet> bullets){
		for (Bullet b : bullets) {
			if(this.getBounds2D().intersects(b.getBounds2D())){
				isHit = true;
			}

		}


	}

}
