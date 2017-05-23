package gameparts;



import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.imageio.ImageIO;
import javax.swing.*;

import panels.GamePanel;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Player extends Character {

	public static final int WIDTH = 65;
	public static final int HEIGHT = 40;
	
	
	private Line2D.Double trackingLine;
	private double mouseX, mouseY;
	
	

	
	public Player(int x, int y) {
		super("playerchar.png", x, y, WIDTH, HEIGHT);
		xVelocity = 0;
		yVelocity = 0;
		
		mouseX = MouseInfo.getPointerInfo().getLocation().getX();
		mouseY = MouseInfo.getPointerInfo().getLocation().getY();
	}

	// METHODS
	public void walk(int dir) {
		super.walk(dir);
	}


	public void jump() {
		// JUMP!
		super.jump();
	}

	public void act(ArrayList<Shape> obstacles, boolean isPlatformer) {
		super.act(obstacles, isPlatformer);
		generateTrackingLine(obstacles);
		
	
	}
	
	public void death(){
		maxVelocity = 0;
		GamePanel.setplayerIsDead(true);
		
	}
	
	public Line2D.Double generateTrackingLine(ArrayList<Shape> obstacles){
		
		try{
			mouseX = MouseInfo.getPointerInfo().getLocation().getX();
			mouseY = MouseInfo.getPointerInfo().getLocation().getY();
		}
		catch(NullPointerException e){
			System.out.println("Mouse is out of bounds");
		}
		trackingLine = new Line2D.Double(mouseX, mouseY, this.getCenterX(), this.getCenterY());
		
		for(Shape s: obstacles){
			
			if( trackingLine.intersects((Rectangle2D)s)){
				trackingLine = new Line2D.Double(mouseX, mouseY, this.getCenterX(), this.getCenterY());
				
			}
		}
		
		return trackingLine;
		
	}
	
	public void reset(int originalLocationX, int originalLocationY){
		this.moveToLocation(originalLocationX, originalLocationY);
		xVelocity = 0;
		yVelocity = 0;
		maxVelocity = 5;
		GamePanel.setplayerIsDead(false);

	}
	
	
}

