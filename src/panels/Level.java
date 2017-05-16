package panels;


import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import gameparts.MovingImage;


public class Level extends Rectangle2D.Double {

	private ArrayList<MovingImage> scene;
	
	public Level() {
		scene = new ArrayList<MovingImage>();
		scene.add(new MovingImage("mario.png",150,150,175,300));
		scene.add(new MovingImage("mario.png",525,250,200,200));
		scene.add(new MovingImage("mario.png",575,50,150,150));
		this.width = 800;
		this.height = 600;
	}
	
	public void draw(Graphics2D g2, ImageObserver io) {
		for (MovingImage mi : scene)
			mi.draw(g2, io);
	}
	
}
