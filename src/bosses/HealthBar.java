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


public class HealthBar {

	private Rectangle2D.Double currentBar, maxBar;
	private int currentWidth;
	private int currentHP, maxHP;
	private static final int x = 190, y = 100, width = 810, height = 50;

	private BufferedImage barTexture, healthTexture;

	public HealthBar(int maxHP){
		this.maxHP = maxHP;
		currentHP = maxHP;
	}

	public void reduceHP(int amount){
		currentHP -= amount;
	}

	public int getCurrentHP(){
		return currentHP;
	}

	public int getMaxHP(){
		return maxHP;
	}

	public int computeHealthFraction(){
		return (currentHP*width)/maxHP;
	}

	public void draw(Graphics2D g2d){
		g2d.setColor(Color.GRAY);		
		g2d.fillRect(x, y, width, height);
		g2d.setColor(Color.RED);
		g2d.fillRect(x+10, y+10, computeHealthFraction()-20, height-20);
	}

	public void loadImage(){
		try {
			barTexture = ImageIO.read(new File("cobblestone.png"));
		} catch(IOException e){

		}
	}
}
