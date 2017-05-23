package bosses;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/** This class represents a Boss's health bar.
 * 
 * @author Akshay
 *
 */
public class HealthBar {

	private int currentHP, maxHP;
	private static final int x = 190, y = 30, width = 810, height = 50;
	
	/** Constructs a HealthBar
	 * 
	 * @param maxHP the HP that this HealthBar, and therefore Boss, will have
	 */
	public HealthBar(int maxHP){
		this.maxHP = maxHP;
		currentHP = maxHP;
	}

	/** Reduces the current HP
	 * 
	 * @param amount The amount by which to subtract HP
	 */
	public void reduceHP(int amount){
		currentHP -= amount;
	}

	/** Returns the current HP field
	 * 
	 * @return current HP
	 */
	public int getCurrentHP(){
		return currentHP;
	}

	/** Computes the length in pixels of the currentHP
	 * 
	 * @return currentHP pixel length
	 */
	public int computeHealthFraction(){
		return (currentHP*width)/maxHP;
	}

	/** Draws this HealthBar
	 * 
	 * @param g2d Graphics2D object, assumed to not be null
	 */
	public void draw(Graphics2D g2d){
		g2d.setColor(Color.GRAY);		
		g2d.fillRect(x, y, width, height);
		g2d.setColor(new Color(220, 20, 60));
		g2d.fillRect(x+10, y+10, computeHealthFraction()-20, height-20);
		g2d.setColor(new Color(178, 34, 34));
		g2d.fillRect(x+10, y+10, computeHealthFraction()-20, (height-20)/2);
		g2d.setColor(new Color(139, 0, 0));
		g2d.fillRect(x+10, y+10, computeHealthFraction()-20, (height-20)/4);
	}

}
