package bosses;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import gameparts.Bullet;
import gameparts.Enemy;
import gameparts.RangedEnemy;

public class Boss extends RangedEnemy {
	
	private ArrayList<Bullet> bossBullets;
	private HealthBar healthBar;

	public Boss(String filename, int x, int y, double velocity, int width, int height) {
		super(filename, x,y, velocity, width, height);
		healthBar = new HealthBar(100);
		bossBullets = new ArrayList<Bullet>();
	}
	
	public void fire(int PlayerLocX, int PlayerLocY){
		bossBullets.add(new Bullet("fireball.png", (int) this.getCenterX(), (int)this.getCenterY(), 25, 25, PlayerLocX, PlayerLocY));
	}
	
	public void takeDamage(){
		healthBar.reduceHP(17);
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
