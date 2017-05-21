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
			w.changePanel("4");
		}
		
		
	}
	
	
}
