package bosses;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import gameparts.Bullet;
import gameparts.Enemy;

public class RangedEnemy extends Enemy{

	private ArrayList<Bullet> bossBullets;

	public RangedEnemy(String filename, int x, int y, double velocity, int width, int height) {
		super(filename, x,y, velocity, width, height);
		
		bossBullets = new ArrayList<Bullet>();
		
	}
	
	public void fire(int PlayerLocX, int PlayerLocY){
		bossBullets.add(new Bullet("fireball.png", (int) this.getCenterX(), (int)this.getCenterY(), 25, 25, PlayerLocX, PlayerLocY));
//		for(Bullet b: bossBullets){
//			b.fire();
//		}
	}

	

	
	public ArrayList<Bullet> getbossBullets(){
		return bossBullets;
	}

	

}
