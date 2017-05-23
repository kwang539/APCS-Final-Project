package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import gameparts.MovingImage;

public class InstructionsPanel extends JPanel implements ActionListener {
	
	Main w;
	private JButton menuButton;

	private Image InstructionsImg;
	public InstructionsPanel(Main w) {
		this.w = w;
		try {
			InstructionsImg = ImageIO.read(new File("InstructionsBackground.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menuButton = new JButton("Back To Menu");
		menuButton.addActionListener(this);
		add(menuButton);
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);  // Call JPanel's paintComponent method to paint the background

		Graphics2D g2 = (Graphics2D)g;

		int width = getWidth();
		int height = getHeight();

		double ratioX = (double)width/1200.0;
		double ratioY = (double)height/900.0;

		AffineTransform at = g2.getTransform();
		g2.scale(ratioX, ratioY);
		
		
		g2.drawImage(InstructionsImg, 0, 0, 1200, 900, 0,0,InstructionsImg.getWidth(null) , InstructionsImg.getHeight(null), null);


		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier New",Font.ROMAN_BASELINE,28));
		g2.drawString("Instructions:", 50, 100);
		g2.drawString("", 50, 150);
		g.setFont(new Font("Courier New",Font.ROMAN_BASELINE,23));

		g2.drawString("Use WASD keys to move character on screen", 50, 200);
		g2.drawString("", 50, 250);
		g2.drawString("Use the mouse to Aim", 50, 300);
		g2.drawString("Left Click to fire", 50, 350);
		g2.drawString("Ammo is displayed in the upper left hand corner", 50, 400);
		g2.drawString("", 50, 450);
		g2.drawString("Hit Spacebar to switch between a top-down game and a platformer", 50, 500);
		g2.drawString("(Essentially adds gravity)", 50, 550);
		g2.drawString("", 50, 600);
		g2.drawString("Hit R to reset to the beginning", 50, 650);
		g2.drawString("Hit ESC in game to return to the Menu Screen", 50, 700);
		//g2.drawString("But of course, you can keep playing. ", 50, 750);
		//g2.drawString("Just hit 'R' and I will glady generate a new object representing you, and play again.", 50, 800);
		g2.setTransform(at);

		// TODO Add any custom drawings here
	}
	public void actionPerformed(ActionEvent e) {
		w.changePanel("1");
	}
	
}
