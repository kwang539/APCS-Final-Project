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
	private String message1, message2, message3, message4, message5, message6, message7, message8, message9, message10;
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
		message1 = "abcdefghijklmnopqrstuvwxyz";
		message2 = "abcdefghijklmnopqrstuvwxyz";
		message3 = "hi";
		message4 = "hi";
		message5 = "hi";
		message6 = "hi";
		message7 = "hi";
		message8 = "hi";
		message9 = "hi";
		message10 = "hi";
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
		g.setFont(new Font("Arial",Font.ROMAN_BASELINE,28));
		int strWidth = g.getFontMetrics().stringWidth(message1);
		g.drawString(message1, 50, 50);
		g.drawString(message2, 50, 100);
		g.drawString(message3, 50, 150);
		g.drawString(message4, 50, 200);
		g.drawString(message5, 50, 250);
		g.drawString(message6, 50, 300);
		g.drawString(message7, 50, 350);
		g.drawString(message8, 50, 400);
		g.drawString(message9, 50, 450);
		g.drawString(message10, 50, 500);
		
		g2.setTransform(at);

		// TODO Add any custom drawings here
	}
	public
	
	void actionPerformed(ActionEvent e) {
		w.changePanel("1");
	}
	
}
