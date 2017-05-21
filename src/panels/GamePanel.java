package panels;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import bosses.Boss;
import levels.Level;
import levels.Level0;
import gameparts.Bullet;
import gameparts.Enemy;
import gameparts.Link;
import gameparts.Player;
import levels.Level1;
import levels.Level2;
import levels.Level3;

import java.util.*;


public class GamePanel extends JPanel implements Runnable
{
	public static final int DRAWING_WIDTH = 1200;
	public static final int DRAWING_HEIGHT = 900;

	private long timeOfLastProjectile = 0;
	private long timeNow = 0;
	private long time = 0;

	private long timeOfLastProjectileB = 0;
	private long timeNowB = 0;
	private long timeB = 0;

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

	//private Rectangle door;

	private ArrayList<Level> levels;
	private Level0 level0;
	private Level1 level1;
	private Level2 level2;
	private Level3 level3;

	private BufferedImage backgroundImg, wallImg;

	private final int lastLevel = 3;
	private Level currentLevel;

	private boolean levelFinished;
	private static boolean playerIsDead;
	private int ammo;

	public GamePanel (Main m) {
		super();
		this.m = m;
		levels = new ArrayList<Level>();
		level0 = new Level0();
		level1 = new Level1();
		level2 = new Level2();
		level3 = new Level3();
		levels.add(level0);
		levels.add(level1);
		levels.add(level2);
		levels.add(level3);

		ammo = 5;

		try {
			backgroundImg = ImageIO.read(new File("grass.png"));
			wallImg = ImageIO.read(new File("stonewall.png"));
		} catch (IOException e){

		}



		levelFinished = false;
		isPlatformer = false;
		playerIsDead = false;

		mX = MouseInfo.getPointerInfo().getLocation().getX();
		mY = MouseInfo.getPointerInfo().getLocation().getY();

		keyControl = new KeyHandler();
		mouseControl = new MouseHandler();
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



		//draws an image scaled perfectlyto the size of the screen
		//<<<<<<< HEAD
		//g2.drawImage(currentLevel.getbackgroundImg(), 0, 0, DRAWING_WIDTH, DRAWING_HEIGHT, 0,0,currentLevel.getbackgroundImg().getWidth(null) , currentLevel.getbackgroundImg().getHeight(null), null);



		//=======
		g2.drawImage(currentLevel.getbackgroundImg(), 0, 0, DRAWING_WIDTH, DRAWING_HEIGHT, 0,0,currentLevel.getbackgroundImg().getWidth(null) , currentLevel.getbackgroundImg().getHeight(null), null);

		//g2.setPaint(new TexturePaint(backgroundImg, new Rectangle(0, 0, 16, 16)));
		//g2.fillRect(0, 0, 1200, 900);



		//<<<<<<< HEAD

		//=======

		g2.setPaint(null);

		//>>>>>>> branch 'DankAI' of https://github.com/kwang539/APCS-Final-Project.git
		int width = getWidth();
		int height = getHeight();

		double ratioX = (double)width/DRAWING_WIDTH;
		double ratioY = (double)height/DRAWING_HEIGHT;

		AffineTransform at = g2.getTransform();
		g2.scale(ratioX, ratioY);


		//puts textures onto the obstacles, makes it a bit laggier
		Rectangle door1 = currentLevel.getdoor();
		g2.drawImage(currentLevel.getobstacleImg(),(int) door1.getX(), (int)door1.getY(), (int)(door1.getX() +door1.getWidth()), (int)(door1.getY() + door1.getHeight()), 0,0,currentLevel.getobstacleImg().getWidth(null) , currentLevel.getobstacleImg().getHeight(null), null);

		g2.setPaint(new TexturePaint(wallImg, new Rectangle(0,0,64,64)));
		for(Shape o: obstacles){

			g2.fill(o);
		}

		g2.setPaint(null);

		//draws the ammo
		g2.setColor(Color.BLUE);
		g2.setFont(new Font("Arial",Font.PLAIN,28));
		String ammoDisplay="";
		for(int i = 0; i < ammo; i++){
			ammoDisplay = ammoDisplay + "I";
		}
		//int strWidth = g.getFontMetrics().stringWidth(""+ammo);
		g2.drawString(ammoDisplay, 20, 40);

		//draws game over screen when dead
		if(playerIsDead){
			g2.drawString("GAME OVER", 500, 500);
		}


		Point mousePoint = this.getMousePosition();

		try{
			mX = mousePoint.getX();
			mY = mousePoint.getY();
		}
		catch(NullPointerException e){
			//System.out.println("Mouse is out of bounds");
		}

		double dY = mY - player.getCenterY();
		double dX = mX - player.getCenterX();


		for(Enemy e: enemies){

			for(Bullet b: bullets){
				//b.hitObstacle(obstacles);

				if(b != null && b.getBounds2D().intersects(e.getBounds2D())){

					if (e instanceof Boss){
						((Boss) e).takeDamage();
						System.out.println(enemies.size() + " is the size of the enemy array");
						if (((Boss) e).getCurrentHP() <= 0){
							enemies.remove(e);
						}
						bullets.remove(b);
					} else {
						e.setIsHit(true);
						enemies.remove(e);
						bullets.remove(b);
					}
				} else {
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
			//boss shoots back at you
			if(currentLevel == level3){
				if(e instanceof Boss){
					if (timeB == 0 || timeB >= 1000) {
						timeOfLastProjectileB = System.currentTimeMillis();
						((Boss) e).fire((int)player.getCenterX(), (int)player.getCenterY());
					}
					timeNowB = System.currentTimeMillis()+1;
					//System.out.println("Now" + timeNow);
					timeB = timeNowB - timeOfLastProjectileB;


					for(Bullet b1: ((Boss) e).getbossBullets()){
						b1.fire();
						if(b1 != null && b1.getBounds2D().intersects(player.getBounds2D())){
							player.death();
							System.out.println("lolwut");

						}
						if(b1.hitObstacle(obstacles)){
							((Boss) e).getbossBullets().remove(b1);
						}
						b1.draw(g2, null);
					}
				}
			}

			e.draw(g2, null);

		}

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
		enemies.add(new Enemy("mario.png",locX,locY, velocity));

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


	public Level0 getLevel0(){
		return level0;
	}

	public static void setplayerIsDead(boolean isDead){
		playerIsDead = isDead;
	}

	public KeyHandler getKeyHandler() {
		return keyControl;
	}

	public MouseHandler getMouseHandler(){
		return mouseControl;
	}

	public void run() {
		while (true) { 
			long startTime = System.currentTimeMillis();

			if(keyControl.isPressed(KeyEvent.VK_0)){
				//sound.sound1();
				loadLevel(level0);
				isPlatformer = false;



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

			if(keyControl.isPressed(KeyEvent.VK_3)){
				loadLevel(level3);
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

			if(mouseControl.isClicked(MouseEvent.BUTTON1)){

				if ((time == 0 || time >= 1000)&& ammo >0 && currentLevel != level0) {
					timeOfLastProjectile = System.currentTimeMillis();
					bullets.add(new Bullet("fireball.png", (int) player.getCenterX(), (int)player.getCenterY(), 25, 25, mX, mY));
					sound.sound1();

					ammo--;
				}

				timeNow = System.currentTimeMillis()+1;
				time = timeNow - timeOfLastProjectile;
			}

			for(Bullet b: bullets){
				b.fire();

				if(screenRect.intersects(b)){
					b = null;
				}
			}

			player.act(obstacles, isPlatformer);


			for(Enemy e: enemies){

				for(Enemy e1: enemies){
					if(e1 != e)
						obstacles.add(e1);
				}

				e.act(obstacles, isPlatformer, player);
				for(Enemy e1: enemies){
					if(e1 != e)
						obstacles.remove(e1);
				}
				//e.hitByBullet(bullets);


				//if enemy touches player
				if(e.intersects(player)){
					player.death();
				}
				//if(e.intersects(screenRect)){

				//}
			}

			for(int i = 0; i< enemies.size(); i++){
				if(!screenRect.intersects(enemies.get(i))){
					enemies.remove(i);

				}
			}


			if (!screenRect.intersects(player)){
				player.death();
			}

			if(currentLevel.getdoor().intersects(player)){
				int nextLevelIndex = levels.indexOf(currentLevel)+1;

				if(levelFinished == true && nextLevelIndex < lastLevel+1){
					loadLevel(levels.get(nextLevelIndex));
					isPlatformer= false;
				}

				levelFinished = false;
			}


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
			if(e.getKeyCode() == KeyEvent.VK_R){
				//clearLevel();
				isPlatformer = false;
				if(currentLevel == level1 && playerIsDead){
					level1 = null;
					level1 = new Level1();
					levels.add(1,level1);
					//clearLevel();
					loadLevel(level1);
					//levels.add(0,level1);
					currentLevel = level1;
					//playerIsDead = false;

				}
				else if(currentLevel == level2 && playerIsDead){
					level2 = null;
					level2 = new Level2();

					loadLevel(level2);
					levels.add(2,level2);
					currentLevel = level2;
					//playerIsDead = false;


				}

				playerIsDead = false;


				levelFinished = false;


				//level1.reset();
				//System.out.println("wut");
			}
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				//clearLevel();
				//loadLevel(level1);
				//				if(levelFinished){
				//					for(int i = 0; i < lastLevel-1; i ++){
				//						if(levels.get(i) == currentLevel)
				//							loadLevel(levels.get(i+1));
				//						//loadLevel(level);
				//
				//					}
				//				}
				//hitting enter to switch levels now works!!

				int nextLevelIndex = levels.indexOf(currentLevel)+1;
				if(levelFinished && nextLevelIndex < lastLevel){
					loadLevel(levels.get(nextLevelIndex));
					isPlatformer= false;

				}
				levelFinished = false;
			}

		}

		public void keyReleased(KeyEvent e) {
			Integer code = e.getKeyCode();
			while(keys.contains(code))
				keys.remove(code);
		}

		public void keyTyped(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				//clearLevel();
				//loadLevel(level1);
				//okay well this is screwed
				if(levelFinished){
					for(int i = 0; i < lastLevel; i ++){
						if(levels.get(i) == currentLevel)
							loadLevel(levels.get(i+1));
						//loadLevel(level);

					}
				}
				isPlatformer= false;
				levelFinished = false;
			}
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
