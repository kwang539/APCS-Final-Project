package panels;


import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import gameparts.Link;

import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;


public class MenuPanel extends JPanel implements ActionListener {
	
	Main w;
	private JButton instructionsButton;

	private JButton playButton;
	private Link a;
	Timer songtime=new Timer(1,this);
	
	private Image MenuImg;

	public MenuPanel(Main w) {
		this.w = w;
		
		try {
			MenuImg = ImageIO.read(new File("InstructionsBackground.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		instructionsButton = new JButton("Instructions");
		instructionsButton.addActionListener(this);
		add(instructionsButton);
		
	
		
		playButton = new JButton("Play");
		playButton.addActionListener(this);
		add(playButton);
		
		a = new Link(this);

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
		
		
		g2.drawImage(MenuImg, 0, 0, 1200, 900, 0,0,MenuImg.getWidth(null) , MenuImg.getHeight(null), null);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier New",Font.ROMAN_BASELINE,80));
		g.drawString("Programmer's Worst", 300, 400);
		g.drawString("Nightmare", 700, 500);

	
		
		g2.setTransform(at);

		// TODO Add any custom drawings here
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(instructionsButton))
		w.changePanel("2");
	
		if(e.getSource().equals(playButton)){
			
			

			songtime.start();
			//a.setBackgroundSoundto4();

			//a.playBackgroundSound();
			
			//a.sound4();
//			w.cl.removeLayoutComponent(w.panel4);
//			w.panel4 = new GamePanel(w);
//			w.cardPanel.add(w.panel4, "4");
//			w.panel4.loadLevel(w.panel4.getLevel0());
//			addKeyListener(w.panel4.getKeyHandler());
//			addMouseListener(w.panel4.getMouseHandler());
			w.changePanel("4");
			//w.panel4.setFocusable(true);
			//w.panel4.requestFocusInWindow();
			

//			System.out.println("hihi");
//			w.cl.removeLayoutComponent(w.panel4);
//			
//			w.panel4 = new GamePanel(w);
//			addKeyListener(w.panel4.getKeyHandler());
//			addMouseListener(w.panel4.getMouseHandler());
//			w.panel4.loadLevel(w.panel4.getLevel0());
//			
//			w.cardPanel.add(w.panel4, "4");
//			
//			//cardLayout.addLayoutComponent("Card 2" , card2);
//			
//			//card2 = new Tron();
//			
//			w.cl.show(w.cardPanel, "4");
//			w.cardPanel.setFocusable(true);
//			w.cardPanel.requestFocusInWindow();
			//cardLayout.next(cardPanel);
		}
		
		
		
	}
	
	
}
