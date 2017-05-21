package gameparts;

import java.util.ArrayList;

public class RangedEnemy extends Enemy {
	
	private ArrayList<Bullet> bossBullets;

	public RangedEnemy(String filename, int x, int y, double velocity) {
		super(filename, x,y, velocity);
		
		bossBullets = new ArrayList<Bullet>();
		
	}
	
	public void fire(int PlayerLocX, int PlayerLocY){
		bossBullets.add(new Bullet("fireball.png", (int) this.getCenterX(), (int)this.getCenterY(), 25, 25, PlayerLocX, PlayerLocY));

	}
	
	public ArrayList<Bullet> getbossBullets(){
		return bossBullets;
	}
	

}
