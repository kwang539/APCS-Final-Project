package panels;

import java.awt.event.*;
import javax.swing.*;

import levels.Level;
import levels.Level1;

import java.awt.*;

public class Main extends JFrame implements ActionListener{



	public JPanel cardPanel;
	public GamePanel panel4;
	public CardLayout cl;
	
	private JButton playButton;
	public Main(String title) {
		super(title);
		
		
		setBounds(0, 0, 1200, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		cardPanel = new JPanel();
		cl = new CardLayout();
		cardPanel.setLayout(cl);

		
		playButton = new JButton("Play");
		playButton.addActionListener(this);
		add(playButton);
		
		MenuPanel panel1 = new MenuPanel(this);
		InstructionsPanel panel2 =new InstructionsPanel(this);
		//code for game panel, add later
		
		//Levels 1 2 and 3
		panel4 = new GamePanel(this);
		//panel4.loadLevel(panel4.getLevel1());
		//panel4.clearLevel();
		panel4.loadLevel(panel4.getLevel0());


		addKeyListener(panel4.getKeyHandler());
		addMouseListener(panel4.getMouseHandler());
		

		cardPanel.add(panel1,"1");
		cardPanel.add(panel2,"2");
		
		cardPanel.add(panel4, "4");
		add(cardPanel);



		setVisible(true);
	}

	public static void main(String[] args)
	{
		Main w = new Main("Programmer's Worst Nightmare");
	}

	public void changePanel(String name) {
		((CardLayout)cardPanel.getLayout()).show(cardPanel,name);
		requestFocus();
	}
	
	public void loadGamePanel(Level level){
		panel4.loadLevel(level);
	}
	public GamePanel getGamePanel(){
		return panel4;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(playButton))
		{
			System.out.println("hihi");
			cl.removeLayoutComponent(panel4);
			
			panel4 = new GamePanel(this);
			cardPanel.add(panel4, "4");
			
			//cardLayout.addLayoutComponent("Card 2" , card2);
			
			//card2 = new Tron();
			cl.show(cardPanel, "4");
			cardPanel.setFocusable(true);
			cardPanel.requestFocusInWindow();
			//cardLayout.next(cardPanel);
		}
		
	}

	
}

