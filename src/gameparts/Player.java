package gameparts;



import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Player extends Character {

	public static final int MARIO_WIDTH = 40;
	public static final int MARIO_HEIGHT = 60;
	

	private double xVelocity, yVelocity;
	private boolean onASurface;
	private double friction;
	private double gravity;
	private double jumpStrength;
	
	private Line2D.Double trackingLine;
	private double mouseX, mouseY;
	
	

	
	public Player(int x, int y) {
		super("playerchar.png", x, y, MARIO_WIDTH, MARIO_HEIGHT);
		xVelocity = 0;
		yVelocity = 0;
		
		mouseX = MouseInfo.getPointerInfo().getLocation().getX();
		mouseY = MouseInfo.getPointerInfo().getLocation().getY();
	}

	// METHODS
	public void walk(int dir) {
		super.walk(dir);
		
		//old walk code, more snappy
		/*if(dir == -2 || dir == 2){
			
			int newdir = dir/2;
			
			//y = y + newdir*yVelocity;
			y += newdir*4;
		}
		
	
		if(dir == -1 || dir == 1){
			
			if (xVelocity <= 4 && xVelocity >= -4){
				xVelocity += dir;
			}
		x+= dir*xVelocity;
		
			x += dir*4;
		}
		*/
		
		// WALK!
	}


	public void jump() {
		// JUMP!
	}

	public void act(ArrayList<Shape> obstacles) {
		//dY += 0.5;
		super.act(obstacles);
		generateTrackingLine(obstacles);
		
	
	}
	
	public Line2D.Double generateTrackingLine(ArrayList<Shape> obstacles){
		
		try{
			mouseX = MouseInfo.getPointerInfo().getLocation().getX();
			mouseY = MouseInfo.getPointerInfo().getLocation().getY();
		}
		catch(NullPointerException e){
			System.out.println("Mouse is out of bounds");
		}
		
		//double dY = mouseY - this.getCenterY();
		//double dX = mouseX - this.getCenterX();
		
		trackingLine = new Line2D.Double(mouseX, mouseY, this.getCenterX(), this.getCenterY());
		
		for(Shape s: obstacles){
			
			if( trackingLine.intersects((Rectangle2D)s)){
				//basically make a new line that goes from the center of the character to the intersection point of the rectangle and trackiong line
				trackingLine = new Line2D.Double(mouseX, mouseY, this.getCenterX(), this.getCenterY());
				
			}
		}
		
		return trackingLine;
		
	}

	
	
	
}

