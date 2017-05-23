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

/** Represents the player character
 * 
 * @author Akshay
 *
 */
public class Player extends Character {

	public static final int WIDTH = 65;
	public static final int HEIGHT = 40;
	
	
	private Line2D.Double trackingLine;
	private double mouseX, mouseY;
	
	/** Constructs a new Player object
	 * 
	 * @param x the starting x-coordinate
	 * @param y the starting y-coordinate
	 */
	public Player(int x, int y) {
		super("playerchar.png", x, y, WIDTH, HEIGHT);
		xVelocity = 0;
		yVelocity = 0;
		
		mouseX = MouseInfo.getPointerInfo().getLocation().getX();
		mouseY = MouseInfo.getPointerInfo().getLocation().getY();
	}

	// METHODS
	/** Translates this Player based off of input direction
	 * 
	 */
	public void walk(int dir) {
		super.walk(dir);
	}

	/** Makes this Player jump and fall.
	 * 
	 */
	public void jump() {
		// JUMP!
		super.jump();
	}

	/** Detects collisions between this Player and any obstacles on the map, and
	 * halts movement if they occur.
	 * 
	 * @param obstacles the array of obstacles on the map
	 * @param isPlatformer whether or not the gamemode is in platform or top-down mode
	 */
	public void act(ArrayList<Shape> obstacles, boolean isPlatformer) {
		super.act(obstacles, isPlatformer);
		generateTrackingLine(obstacles);
		
	
	}
	
	/** Kills the current Player object
	 * 
	 */
	public void death(){
		maxVelocity = 0;
		GamePanel.setplayerIsDead(true);
	}
	
	/** Generates a line between this player and the mouse, and detects collisions between the line and any obstacles.
	 * 
	 * @param obstacles the array of obstacles on the map
	 * @return a Line2D.Double
	 */
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
	
	/** Resets this Player's location to its original location
	 * 
	 * @param originalLocationX
	 * @param originalLocationY
	 */
	public void reset(int originalLocationX, int originalLocationY){
		this.moveToLocation(originalLocationX, originalLocationY);
		xVelocity = 0;
		yVelocity = 0;
		maxVelocity = 5;
		GamePanel.setplayerIsDead(false);

	}
	
	
}

