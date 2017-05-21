package bosses;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import gameparts.Bullet;
import gameparts.Enemy;
import gameparts.Player;

public class RangedBoss extends Boss {
	
	private ArrayList<Bullet> bossBullets;

	public RangedBoss(String filename, int x, int y, double velocity) {
		super(filename, x,y, velocity);
		
		bossBullets = new ArrayList<Bullet>();
		
	}
	
	public void walk(){
		
	}
	
	public void act(ArrayList<Shape> obstacles, boolean isPlatformer, Player player1){
		//dY += 0.5;
				//super.act(obstacles, isPlatformer);
				
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

					//onASurface = false;

					//Problem is that is is testing to see if the mario is on the surface,
					//if mario is not on a surface, he keeps going.







					if (yVelocity > 0) {
						Shape standingSurface = null;
						for (Shape s : obstacles) {
							if (s.intersects(strechY)) {
								//onASurface = true;
								standingSurface = s;
								yVelocity = 0;

							}
						}
						if (standingSurface != null) {
							Rectangle r = standingSurface.getBounds();
							yCoord2 = r.getY()-height;
							//if coming from the top
							//makes it go around the obstacle to the right						
								
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
							//if coming from the bottom of the obstacle, will go around to the right also

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
			
				
			
				
				walk(player1.getCenterX(), player1.getCenterY());

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
