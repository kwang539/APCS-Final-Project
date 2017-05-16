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

	public Enemy(int x, int y) {
		super("survivor-idle_rifle_0.png", x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
		dX = 0;
		dY = 0;
	}

	// METHODS
	public void walk(int dir) {
		// WALK!
		//THIS MAKES IT NOT MERGE!!!
		super.walk(dir);
	}


	public void jump() {
		// JUMP!
	}

	public void act(ArrayList<Shape> obstacles, boolean isPlatformer) {
		//dY += 0.5;
		super.act(obstacles, isPlatformer);
		walk(2);


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
		System.out.println("remove");
	}

	public void hitByBullet(ArrayList<Bullet> bullets){
		for (Bullet b : bullets) {
			if(this.getBounds2D().intersects(b.getBounds2D())){
				isHit = true;
			}

		}


	}

}
