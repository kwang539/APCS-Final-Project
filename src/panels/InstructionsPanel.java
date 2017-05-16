package panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * The InstructionPanel class creates the instruction screen
 * @author Jacob, Keving, Akshay
 * Date: 5/15/17
 *
 */
public class InstructionsPanel extends JPanel implements ActionListener {
	
	Main w;
	private JButton menuButton;
	
	public InstructionsPanel(Main w) {
		this.w = w;
		menuButton = new JButton("Back To Menu");
		menuButton.addActionListener(this);
		add(menuButton);
	}
	
	public void actionPerformed(ActionEvent e) {
		w.changePanel("1");
	}
	
}
