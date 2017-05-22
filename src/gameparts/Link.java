package gameparts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.Timer;


public class Link implements ActionListener {
private final int ACTION_TIMEOUT = 500;

	// Load the sounds from the disk. 
	private final EasySound2 sound1 = new EasySound2("Sound1.wav");
	private final EasySound2 sound2 = new EasySound2("Sound2.wav");
	private final EasySound2 sound3 = new EasySound2("Sound3.wav");
	private final EasySound2 sound4 = new EasySound2("Sound4.wav");


	private JComponent surface; // for repainting when we make changes to his image

	private Timer actionTimer;

	public Link(JComponent surface) {
		this.surface = surface;
		actionTimer = new Timer(ACTION_TIMEOUT,this);
		actionTimer.setRepeats(false);
	}

	public void sound1() {
		sound1.play();
		actionTimer.restart();
	}

	public void sound2() {
		sound2.play();
		actionTimer.restart();
	}

	public void sound3(){
		sound3.play();
	}
	
	public void sound4(){
		sound4.play();
		
	}
	
	public void stopSound4(){
		actionTimer.stop();
	}
	
	public void actionPerformed(ActionEvent e) {
		surface.repaint();
		
	}



}
