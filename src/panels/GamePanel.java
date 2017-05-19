package panels;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import levels.Level;
import gameparts.Bullet;
import gameparts.Enemy;
import gameparts.Link;
import gameparts.Player;
import levels.Level1;
import levels.Level2;

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



	private Main m;
	//private boolean isRunning;

	private double mX, mY, mouseAngle;
	private KeyHandler keyControl;
	private MouseHandler mouseControl;
	private Image cursorImage;
	private Link sound;

	private ArrayList<Bullet> bullets;
	private ArrayList<Enemy> enemies;
	private ArrayList<Shape> obstacles;
	private Player player;


	private ArrayList<Level> levels;
	private Level1 level1;
	private Level2 level2;


	private final int lastLevel = 2;
	private Level currentLevel;

	private boolean levelFinished;

	public GamePanel (Main m) {
		super();
		this.m = m;
		//isRunning = true;
		//Where all the levels will be added
		levels = new ArrayList<Level>();
		level1 = new Level1();
		level2 = new Level2();
		levels.add(level1);
		levels.add(level2);


		levelFinished = false;
		isPlatformer = false;

		mX = MouseInfo.getPointerInfo().getLocation().getX();
		mY = MouseInfo.getPointerInfo().getLocation().getY();

		keyControl = new KeyHandler();
		mouseControl = new MouseHandler();
		setBackground(Color.GRAY);
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);

		//		obstacles = level1.getObstacles();
		//		enemies = level1.getEnemies();
		//		player = level1.getPlayer();

		bullets = new ArrayList<Bullet>();


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

		for(Enemy e: enemies){

			for(Bullet b: bullets){
				//b.hitObstacle(obstacles);

				if(b != null && b.getBounds2D().intersects(e.getBounds2D())){
					e.setIsHit(true);
					//System.out.println("why");
					//e.removeEnemy();
					enemies.remove(e);
					bullets.remove(b);
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

	//Spawn methods not really needed?
	public void spawnNewPlayer(int locX, int locY) {
		//player = new Player(DRAWING_WIDTH/2-player.MARIO_WIDTH/2,50);
		player = new Player(locX,locY);

	}

	public void spawnNewEnemy(int locX, int locY, int velocity) {
		//enemy1 = new Enemy(locX,locY);
		enemies.add(new Enemy(locX,locY, velocity));

	}

	public void spawnNewObstacle(int locX, int locY, int height, int width){

		obstacles.add(new Rectangle(locX, locY, height, width));
	}

	public void clearLevel(){
		enemies.clear();
		obstacles.clear();
		player = null;
	}

	public void loadLevel(Level level){

		this.obstacles = level.getObstacles();
		this.enemies = level.getEnemies();
		this.player = level.getPlayer();

		currentLevel = level;
	}


	public Level1 getLevel1(){
		return level1;
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


			if(keyControl.isPressed(KeyEvent.VK_L)){

				//make sure you cap it at the last level too
				for(int i = 1; i < lastLevel; i ++){
					if(levels.get(i) == currentLevel)
						loadLevel(levels.get(i+1));
				}
			}


			if(keyControl.isPressed(KeyEvent.VK_1)){
				//sound.sound1();
				loadLevel(level1);
				isPlatformer = false;



			}



			if(keyControl.isPressed(KeyEvent.VK_2)){
				loadLevel(level2);
				isPlatformer = false;

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

				for(Enemy e1: enemies){
					if(e1 != e)
						obstacles.add(e1);
				}

				e.act(obstacles, isPlatformer, player);
				for(Enemy e1: enemies){
					if(e1 != e)
						obstacles.remove(e1);
				}
				e.hitByBullet(bullets);


				//if enemy touches player
				if(e.intersects(player)){
					player.death();
				}
			}


			if (!screenRect.intersects(player))
				spawnNewPlayer(0,0);


			if(enemies.size() == 0){
				levelFinished = true;
			}

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

			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				m.changePanel("1");
				//isRunning = !isRunning;
			}
			if(e.getKeyCode() == KeyEvent.VK_4){
				//clearLevel();
				//loadLevel(level2);

			}
			//if level is finished, hit enter to move onto the next one
			//for some reason, if you have hit 'r', the enter key will not work anymore
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				//clearLevel();
				//loadLevel(level1);

				if(levelFinished){
					for(int i = 0; i < lastLevel-1; i ++){
						if(levels.get(i) == currentLevel)
							loadLevel(levels.get(i+1));
					}
				}
				isPlatformer= false;
			}
			if(e.getKeyCode() == KeyEvent.VK_R){
				//clearLevel();
				isPlatformer = false;
				if(currentLevel == level1){
					level1 = null;
					level1 = new Level1();
					clearLevel();
					loadLevel(level1);

				}
				else if(currentLevel == level2){
					level2 = null;
					level2 = new Level2();
					loadLevel(level2);
				}

				levelFinished = false;
				

				//level1.reset();
				//System.out.println("wut");
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
