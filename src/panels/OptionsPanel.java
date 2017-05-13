package panels;

import java.awt.*;
import javax.swing.*;



import java.awt.event.*;


public class OptionsPanel extends JPanel implements ActionListener {
	
	Main w;
	private JButton menuButton;
	
	public OptionsPanel(Main w) {
		this.w = w;
		menuButton = new JButton("Back to Menu");
		menuButton.addActionListener(this);
		add(menuButton);
	}
	
	public void actionPerformed(ActionEvent e) {
		w.changePanel("1");
	}
	
}

