package gameparts;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

 
/**
 * MovingImage class that has the basics for moving an image
 * @author Jacob, Keving, Akshay
 * Date: 5/15/17
 *
 */
public class MovingImage extends Rectangle2D.Double {
	
	// FIELDS
	private Image image;
	
	
	// CONSTRUCTORS
	/**
	 * 
	 * @param filename Filename of image
	 * @param x xCord of image
	 * @param y yCord of image
	 * @param w width of image
	 * @param h height of image
	 */
	public MovingImage(String filename, int x, int y, int w, int h) {
		this((new ImageIcon(filename)).getImage(),x,y,w,h);
		
	}
	
	/**
	 * 
	 * @param img Image of image
	 * @param x xCord of image
	 * @param y yCord of image
	 * @param w width of image
	 * @param h height of image
	 */
	public MovingImage(Image img, int x, int y, int w, int h) {
		super(x,y,w,h);
		image = img;
	}
	
	
	// METHODS	
	/**
	 * Moves image to passed in coordinates
	 * @param x Moves to xCord
	 * @param y Moves to yCord
	 */
	public void moveToLocation(double x, double y) {
		super.x = x;
		super.y = y;
	}
	
	/**
	 * Moves image by x and y amount
	 * @param x xCord amount of change
	 * @param y yCord amount of change
	 */
	public void moveByAmount(double x, double y) {
		super.x += x;
		super.y += y;
	}
	
	/**
	 * Sets limits to window
	 * @param windowWidth Limit of width
	 * @param windowHeight Limit of height
	 */
	public void applyWindowLimits(int windowWidth, int windowHeight) {
		x = Math.min(x,windowWidth-width);
		y = Math.min(y,windowHeight-height);
		x = Math.max(0,x);
		y = Math.max(0,y);
	}
	
	/**
	 * Draws the image
	 * @param g Graphics passed in
	 * @param io Passed in ImageObserver
	 */
	public void draw(Graphics g, ImageObserver io) {
		g.drawImage(image,(int)x,(int)y,(int)width,(int)height,io);
		AffineTransform at = AffineTransform.getTranslateInstance(100,100);
	}
	
	/**
	 * 
	 * @param filename Name of image being loaded
	 * @return Returns image
	 */
	private BufferedImage loadImage(String filename){
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(filename));
		} catch (IOException e){
			
		}
		return img;
	}
	
	
}










