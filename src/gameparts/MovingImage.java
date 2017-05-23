package gameparts;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

 /** Represents an image translated across the screen.
  * 
  * @author Akshay
  *
  */
public class MovingImage extends Rectangle2D.Double {
	
	// FIELDS
	private Image image;
	
	
	/** Constructs a new MovingImage.
	 * 
	 * @param filename The name of the image file to draw
	 * @param x Starting x coordinate
	 * @param y Starting y coordinate
	 * @param w Width at which to draw image
	 * @param h Height at which to draw image
	 */
	public MovingImage(String filename, int x, int y, int w, int h) {
		this((new ImageIcon(filename)).getImage(),x,y,w,h);
		
	}
	
	/** Constructs a new MovingImage.
	 * 
	 * @param Image the image object to draw
	 * @param x Starting x coordinate
	 * @param y Starting y coordinate
	 * @param w Width at which to draw image
	 * @param h Height at which to draw image
	 */
	public MovingImage(Image img, int x, int y, int w, int h) {
		super(x,y,w,h);
		image = img;
	}
	
	// METHODS	
	/** Instantly translates this MovingImage to the desired coordinates
	 * 
	 * @param x the desired x-coordinate
	 * @param y the desired y-coordinate
	 */
	public void moveToLocation(double x, double y) {
		super.x = x;
		super.y = y;
	}
	
	/** Draws this MovingImage.
	 * 
	 * @param g the Graphics image to draw, assumed to be null
	 * @param io ImageObserver
	 */
	public void draw(Graphics g, ImageObserver io) {
		g.drawImage(image,(int)x,(int)y,(int)width,(int)height,io);
		AffineTransform at = AffineTransform.getTranslateInstance(100,100);
	}

}










