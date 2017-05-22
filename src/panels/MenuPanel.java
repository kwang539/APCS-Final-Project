package panels;


import java.awt.*;
import javax.swing.*;



import java.awt.event.*;


public class MenuPanel extends JPanel implements ActionListener {
	
	Main w;
	private JButton instructionsButton;
	private JButton OptionsButton;
	private JButton playButton;
	
	public MenuPanel(Main w) {
		this.w = w;
		instructionsButton = new JButton("Instructions");
		instructionsButton.addActionListener(this);
		add(instructionsButton);
		
		OptionsButton = new JButton("Options");
		OptionsButton.addActionListener(this);
		add(OptionsButton);
		
		playButton = new JButton("Play");
		playButton.addActionListener(this);
		add(playButton);
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(instructionsButton))
		w.changePanel("2");
		if(e.getSource().equals(OptionsButton))
			w.changePanel("3");
		if(e.getSource().equals(playButton)){
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
