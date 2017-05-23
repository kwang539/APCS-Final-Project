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

	private int currentHP, maxHP;
	private static final int x = 190, y = 30, width = 810, height = 50;
	
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
		g2d.setColor(new Color(220, 20, 60));
		g2d.fillRect(x+10, y+10, computeHealthFraction()-20, height-20);
		g2d.setColor(new Color(178, 34, 34));
		g2d.fillRect(x+10, y+10, computeHealthFraction()-20, (height-20)/2);
		g2d.setColor(new Color(139, 0, 0));
		g2d.fillRect(x+10, y+10, computeHealthFraction()-20, (height-20)/4);
	}

}
