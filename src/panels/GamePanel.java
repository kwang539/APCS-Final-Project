package panels;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import gameparts.Bullet;
import gameparts.Enemy;
import gameparts.Link;
import gameparts.Player;

import java.util.*;


public class GamePanel extends JPanel implements Runnable
{
	public static final int DRAWING_WIDTH = 1200;
	public static final int DRAWING_HEIGHT = 900;

	private long timeOfLastProjectile = 0;
	private long timeNow = 0;
	private long time = 0;

	private boolean isPlatformer;

	private Rectangle screenRect;

	private Player player;
	//private Character cmario;


	private double mX, mY, mouseAngle;


	private KeyHandler keyControl;
	private MouseHandler mouseControl;

	private Image cursorImage;

	private Link sound;



	private ArrayList<Bullet> bullets;
	private ArrayList<Enemy> enemies;


	private ArrayList<Shape> obstacles;






	public GamePanel () {
		super();

		isPlatformer = false;

		mX = MouseInfo.getPointerInfo().getLocation().getX();
		mY = MouseInfo.getPointerInfo().getLocation().getY();

		keyControl = new KeyHandler();
		mouseControl = new MouseHandler();
		setBackground(Color.GRAY);
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
		obstacles = new ArrayList<Shape>();
		enemies = new ArrayList<Enemy>();
		bullets = new ArrayList<Bullet>();


		/*obstacles.add(new Rectangle(200,400,400,50));
		obstacles.add(new Rectangle(0,250,100,50));
		obstacles.add(new Rectangle(700,250,100,50));
		obstacles.add(new Rectangle(375,300,50,100));
		obstacles.add(new Rectangle(300,250,200,50));
		 */
		spawnNewPlayer(0,0);
		//spawnNewEnemy(90,100);
		//enemy2 = new Enemy(200,30);
		obstacles.add(new Rectangle(0,250,400,50));

		enemies.add(new Enemy(100,0));
		enemies.add(new Enemy(50,0));

		enemies.add(new Enemy(250,0));

		//spawnNewCharacter(100,200);

		try {
			cursorImage = ImageIO.read(new File("crosshair.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Cursor c = toolkit.createCustomCursor(cursorImage , new Point(16, 16), "img");
		setCursor (c);


		sound = new Link(this);

		new Thread(this).start();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);  // Call JPanel's paintComponent method to paint the background

		Graphics2D g2 = (Graphics2D)g;

		int width = getWidth();
		int height = getHeight();

		double ratioX = (double)width/DRAWING_WIDTH;
		double ratioY = (double)height/DRAWING_HEIGHT;

		AffineTransform at = g2.getTransform();
		g2.scale(ratioX, ratioY);

		g.setColor(new Color(205,102,29));
		for (Shape s : obstacles) {
			g2.fill(s);
		}


		Point mousePoint = this.getMousePosition();

		try{
			mX = mousePoint.getX();
			mY = mousePoint.getY();
		}
		catch(NullPointerException e){
			System.out.println("Mouse is out of bounds");
		}

		double dY = mY - player.getCenterY();
		double dX = mX - player.getCenterX();


		//if you generate the tracking Line in gamepanel, it doesn't get screwed up when  you move the screen around
		/*
		Line2D.Double trackingLine = new Line2D.Double(mX, mY, mario.getCenterX(), mario.getCenterY());

		for(Shape s: obstacles){

			if( trackingLine.intersects((Rectangle2D)s)){
				//basically make a new line that goes from the center of the character to the intersection point of the rectangle and trackiong line
				trackingLine = new Line2D.Double(s.getBounds2D().getX(), s.getBounds2D().getY(), mario.getCenterX(), mario.getCenterY());
			}
		}
		 */


		//idk if this belongs here
		//basically its a hit scan detection, if the tracking line is in contact with the enemy, you can hit the enemey

		//this way you can make your own hitbox
		//if(trackingLine.intersects(enemy1.makeHitBox())){

		for(Enemy e: enemies){

			for(Bullet b: bullets){
				//b.hitObstacle(obstacles);
				
				if(b != null && b.getBounds2D().intersects(e.getBounds2D())){
					e.setIsHit(true);
					//System.out.println("why");
					//e.removeEnemy();
					enemies.remove(e);
				}else{
					e.setIsHit(false);

				}
			}
		}
		for(Bullet b: bullets){
			if(b.hitObstacle(obstacles)){
				bullets.remove(b);
			}
		}
		
		/*
		if(player.generateTrackingLine(obstacles).intersects(enemy1.getBounds2D())){
			//System.out.println("hit!");
			//enemy1.setIsHit(true);
			//draws a box where the eney can be hit
			//g2.draw( new Rectangle2D.Double(mX,mY,6,6));

		}
		else{
			enemy1.setIsHit(false);
		}
		 */
		//g2.draw(trackingLine);

		//draws trackingline, but we are no longer using a tracking line, just projectiles
		//g2.draw(player.generateTrackingLine(obstacles));


		double scalar = dY/dX;


		if (mX < player.getCenterX()){
			mouseAngle = Math.PI + Math.atan(scalar);
		} else {
			mouseAngle = Math.atan(scalar);
		}

		for(Enemy e: enemies){
			e.draw(g2, null);

		}
		//cmario.draw(g2, null);
		if(bullets.size() > 0){

			//sometimes has a concurrentModificationException
			for(Bullet b: bullets){
				b.draw(g2, null);
			}
		}
		g2.rotate(mouseAngle, player.getCenterX(), player.getCenterY());

		player.draw(g2, null);


		// TODO Add any custom drawings here
	}


	public void spawnNewPlayer(int locX, int locY) {
		//player = new Player(DRAWING_WIDTH/2-player.MARIO_WIDTH/2,50);
		player = new Player(locX,locY);

	}

	public void spawnNewEnemy(int locX, int locY) {
		//enemy1 = new Enemy(locX,locY);
		enemies.add(new Enemy(locX,locY));

	}
	
	public void spawnNewObstacle(int locX, int locY, int height, int width){
		
		obstacles.add(new Rectangle(locX, locY, height, width));
	}
	
	
	
//	public void removeBullet(Bullet b){
//		bullets.remove(b);
//	}
	
	public KeyHandler getKeyHandler() {
		return keyControl;
	}

	public MouseHandler getMouseHandler(){
		return mouseControl;
	}

	public void run() {
		while (true) { // Modify this to allow quitting
			long startTime = System.currentTimeMillis();



			if(keyControl.isPressed(KeyEvent.VK_1)){
				sound.sound1();
			}
			if(keyControl.isPressed(KeyEvent.VK_2)){
				sound.sound2();
			}


			if(!isPlatformer){


				if (keyControl.isPressed(KeyEvent.VK_A))
					player.walk(-1);
				if (keyControl.isPressed(KeyEvent.VK_D))
					player.walk(1);

				if (keyControl.isPressed(KeyEvent.VK_W))
					player.walk(-2);

				if (keyControl.isPressed(KeyEvent.VK_S))
					player.walk(2);
			}
			else{
				if (keyControl.isPressed(KeyEvent.VK_A))
					player.walk(-1);
				if (keyControl.isPressed(KeyEvent.VK_D))
					player.walk(1);

				if (keyControl.isPressed(KeyEvent.VK_W))
					player.jump();

			}

			//if (keyControl.isPressed(KeyEvent.VK_SPACE)){
			//togglePerspective();
			//}

			if(mouseControl.isClicked(MouseEvent.BUTTON1)){

				if (time == 0 || time >= 300) {
					timeOfLastProjectile = System.currentTimeMillis();
					//System.out.println("Last" + timeOfLastProjectile);
					bullets.add(new Bullet("fireball.png", (int) player.getCenterX(), (int)player.getCenterY(), 25, 25, mX, mY));
					sound.sound1();
				}

				timeNow = System.currentTimeMillis()+1;
				//System.out.println("Now" + timeNow);
				time = timeNow - timeOfLastProjectile;
				//System.out.println("time" + time);
			}

			for(Bullet b: bullets){
				b.fire(mX, mY, screenRect);

				if(screenRect.intersects(b)){
					b = null;
				}
				for(Enemy e: enemies){
				if(b != null && e != null && b.getBounds2D().intersects(e.getBounds2D())){
					e.setIsHit(true);
				}else{
					e.setIsHit(false);
				}
				}
			}
			
			player.act(obstacles, isPlatformer);

			for(Enemy e: enemies){
			if(e.getIsHit()){
				//e.removeEnemy();
				enemies.remove(e);
			}

			e.act(obstacles, isPlatformer);
			e.hitByBullet(bullets);
			//cmario.act(obstacles);
			}

			if (!screenRect.intersects(player))
				spawnNewPlayer(0,0);


			repaint();

			long waitTime = 10 - (System.currentTimeMillis()-startTime);
			try {
				if (waitTime > 0)
					Thread.sleep(waitTime);
				else
					Thread.yield();
			} catch (InterruptedException e) {}
		}
	}

	public void togglePerspective(){
		isPlatformer = !isPlatformer;
	}

	public class KeyHandler implements KeyListener {

		private ArrayList<Integer> keys;

		public KeyHandler() {
			keys = new ArrayList<Integer>();
		}

		public void keyPressed(KeyEvent e) {
			keys.add(e.getKeyCode());

			if(e.getKeyCode() == KeyEvent.VK_SPACE){
				togglePerspective();
			}
		}

		public void keyReleased(KeyEvent e) {
			Integer code = e.getKeyCode();
			while(keys.contains(code))
				keys.remove(code);
		}

		public void keyTyped(KeyEvent e) {
		}

		public boolean isPressed(int code) {
			return keys.contains(code);
		}
	}

	public class MouseHandler implements MouseListener {
		private int mX;
		private int mY;
		private ArrayList<Integer> mouses;

		public MouseHandler() {
			mouses = new ArrayList<Integer>();
		}



		public boolean isClicked(int code) {
			return mouses.contains(code);
		}
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			mouses.add(e.getButton());
			//System.out.println("hi");
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			Integer code = e.getButton();
			while(mouses.contains(code))
				mouses.remove(code);
		}

		/*		private ArrayList<Integer> keys;

		public KeyHandler() {
			keys = new ArrayList<Integer>();
		 */

		/*public void keyPressed(KeyEvent e) {
			keys.add(e.getKeyCode());
		}*/

		//		public void keyReleased(KeyEvent e) {
		//			Integer code = e.getKeyCode();
		//			while(keys.contains(code))
		//				keys.remove(code);
		//		}
		//
		//		public void keyTyped(KeyEvent e) {
		//
		//		}
		//
		//		public boolean isPressed(int code) {
		//			return keys.contains(code);
		//		}



	}

}
