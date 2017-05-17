package panels;

import java.awt.event.*;
import javax.swing.*;

import java.awt.*;

public class Main extends JFrame{



	JPanel cardPanel;

	public Main(String title) {
		super(title);
		setBounds(20, 20, 1200, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		cardPanel = new JPanel();
		CardLayout cl = new CardLayout();
		cardPanel.setLayout(cl);

		MenuPanel panel1 = new MenuPanel(this);
		InstructionsPanel panel2 =new InstructionsPanel(this);
		OptionsPanel panel3 = new OptionsPanel(this);
		//code for game panel, add later
		
		//Levels 1 2 and 3
		GamePanel panel4 = new GamePanel();

		addKeyListener(panel4.getKeyHandler());
		addMouseListener(panel4.getMouseHandler());
		

		cardPanel.add(panel1,"1");
		cardPanel.add(panel2,"2");
		cardPanel.add(panel3, "3");
		
		cardPanel.add(panel4, "4");
		add(cardPanel);



		setVisible(true);
	}

	public static void main(String[] args)
	{
		Main w = new Main("AP Animation Demo");
	}

	public void changePanel(String name) {
		((CardLayout)cardPanel.getLayout()).show(cardPanel,name);
		requestFocus();
	}

}

