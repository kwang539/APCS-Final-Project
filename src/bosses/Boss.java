package bosses;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import gameparts.Bullet;
import gameparts.Enemy;
import gameparts.RangedEnemy1;

public class Boss extends RangedEnemy1 {
	
	private ArrayList<Bullet> bossBullets;
	private HealthBar healthBar;

	public Boss(String filename, int x, int y, double velocity) {
		super(filename, x,y, velocity);
		
		healthBar = new HealthBar(100);
		bossBullets = new ArrayList<Bullet>();
		
	}
	
	public void fire(int PlayerLocX, int PlayerLocY){
		bossBullets.add(new Bullet("fireball.png", (int) this.getCenterX(), (int)this.getCenterY(), 25, 25, PlayerLocX, PlayerLocY));
//		for(Bullet b: bossBullets){
//			b.fire();
//		}
	}
	
	public void takeDamage(){
		healthBar.reduceHP(10);
	}
	
	public int getCurrentHP(){
		return healthBar.getCurrentHP();
	}
	
	public ArrayList<Bullet> getbossBullets(){
		return bossBullets;
	}

	public void draw(Graphics g, ImageObserver io) {
		super.draw(g, io);
		healthBar.draw((Graphics2D)g);
	}

}
