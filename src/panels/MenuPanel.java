package panels;


import java.awt.*;
import javax.swing.*;



import java.awt.event.*;


public class MenuPanel extends JPanel implements ActionListener {
	
	Main w;
	private JButton instructionsButton;
	private JButton OptionsButton;
	private JButton playButton;
	private JButton playButton1;
	private JButton playButton2;
	private JButton playButton3;
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
		
		playButton1 = new JButton("Play1");
		playButton1.addActionListener(this);
		add(playButton1);
		playButton2 = new JButton("Play2");
		playButton2.addActionListener(this);
		add(playButton2);
		playButton3 = new JButton("Play3");
		playButton3.addActionListener(this);
		add(playButton3);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(instructionsButton))
		w.changePanel("2");
		if(e.getSource().equals(OptionsButton))
			w.changePanel("3");
		if(e.getSource().equals(playButton)){
			w.changePanel("4");
		}
		
		if(e.getSource().equals(playButton1)){
			w.changePanel("L1");
			
		}
		if(e.getSource().equals(playButton2))
			w.changePanel("L2");
		if(e.getSource().equals(playButton3))
			w.changePanel("L3");
	}
	
	
}
