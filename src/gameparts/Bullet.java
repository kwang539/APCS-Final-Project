package gameparts;

import java.awt.Shape;
import java.util.ArrayList;

public class Bullet extends MovingImage{

	private int bulletVelocity;
	private double bulletAngle;

	public Bullet(String filename, int x, int y, int w, int h, double crosshairX, double crosshairY ) {
		super(filename, x, y, w, h);
		bulletVelocity = 10;
		bulletAngle = 0;
		generateAngle(crosshairX, crosshairY);
	}

	public void fire(){
		x += Math.cos(bulletAngle) * bulletVelocity;
		y += Math.sin(bulletAngle) * bulletVelocity;
	}

	private void generateAngle(double crosshairX, double crosshairY){
		if (crosshairX < x){
			bulletAngle = Math.PI + Math.atan((crosshairY-y)/(crosshairX - x));

		} else {
			bulletAngle = Math.atan((crosshairY-y)/(crosshairX - x));
		}
	}

	public boolean hitObstacle(ArrayList<Shape> obstacles){
		for (Shape s : obstacles) {
			if(this.getBounds2D().intersects(s.getBounds2D())){
				bulletVelocity = 0;
				x= 0;
				y = 0;
				return true;

			}
		}
		return false;
	}


}





