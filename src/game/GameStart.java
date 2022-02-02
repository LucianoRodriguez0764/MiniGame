package game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import game.entities.*;


public class GameStart extends JFrame {

	public GameStart() {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); 
				
		setBounds(dimension.width/2 - gameWidth/2, dimension.height/2 -gameHeight/2,gameWidth,gameHeight );
		
		setTitle("Game Start 1");
		
		setUndecorated(true);
		
		add(gameWindow);

		add(menuWindow);
		
//		add(pauseWindow);
		
		changeState(0);
		
		setResizable(false);
		
		this.addKeyListener(keys);
						
		setVisible(true);
		
		thread.start();
		
	}
	
	public void updateGame() {
		
		if(isOnMenu) {
			
			this.menuWindow.repaint();
			
		}else{
			if(!isOnPause) {
		this.gameWindow.repaint();
		this.gameWindow.footerWindow.repaint();
		this.gameWindow.checkWin();
		}
		}
	}
	
	public void changeState(int state) {
		// 0 menu, 1 level, 2 pause
		if(state == 1) {
			
		this.gameState=1;
		this.isOnMenu = false;
		
		if(this.gameState!=2) {
		this.gameWindow.resetLevel();
		}
		
		this.menuWindow.setVisible(false);
		this.gameWindow.setVisible(true);
		
		}else if(state==0) {
			
			this.gameState=0;
			this.isOnMenu = true;
			this.gameWindow.setVisible(false);
			this.menuWindow.setVisible(true);
			
		}else if(state==2) { 
			
			this.gameState=2;
			
			if(this.isOnPause) {
			this.isOnPause=false;
			Level.ELLAPSED_PAUSED_TIME += System.currentTimeMillis()/1000 - Level.INITIAL_PAUSED_TIME/1000;
//			System.out.println(Level.ellapsedPausedTime);

			} else {
			Level.INITIAL_PAUSED_TIME = System.currentTimeMillis();
			this.isOnPause=true;
			
			}
		}
		
		
		this.requestFocus();
	}
	
	public static void main(String args[]) {
				
		GameStart game = new GameStart();

	}
	
	GameWindow gameWindow = new GameWindow(this);
	MenuWindow menuWindow = new MenuWindow(this);
	public Thread thread = new Thread(new Rendering(this));
	public Keys keys = new Keys(this, this.gameWindow);
	final short gameWidth = 500;
	final short gameHeight = 300;
	private boolean isOnMenu = false;
	public int gameState;
	boolean isOnPause;


}

class GameWindow extends JPanel{
	


	public GameWindow(GameStart gameStart) {
		
		
		this.gameStart = gameStart;
		
		setSize(gameStart.gameWidth, gameStart.gameHeight); //500x260
		
//		setBackground(Colors.FONDO);
		
		setLayout(null);
		
		level = new Level(this);
		
		add(footerWindow);
		
		footerWindow.setLocation(0, 260);
				
	}
	
	

	public void checkWin() {
		
		if(Level.DOTS_COLLECTED == Level.TOTAL_DOTS) {
			
			level.activeGreenOrb();
			
		}
		
		if(Level.GREEN_TAKED) {
		
			Level.GREEN_TAKED=false;
			
			if(this.level.levelNumber==1) {
			gameStart.menuWindow.getComponentAt(10,145 ).setEnabled(true);//Mejorar con id de boton or something
			gameStart.menuWindow.getComponentAt(10,145 ).setBackground(Color.BLUE.brighter());
			gameStart.changeState(0);
			}else
			if(this.level.levelNumber==2) {
				gameStart.menuWindow.getComponentAt(10,185 ).setEnabled(true);//Mejorar con id de boton or something
				gameStart.menuWindow.getComponentAt(10,185 ).setBackground(Color.BLUE.brighter());
				gameStart.changeState(0);	
			}else
			if(this.level.levelNumber==3) {
				gameStart.menuWindow.getComponentAt(10,225 ).setEnabled(true);//Mejorar con id de boton or something
				gameStart.menuWindow.getComponentAt(10,225  ).setBackground(Color.BLUE.brighter());
				gameStart.changeState(0);	
				System.out.println("Has completado el juego!");
			}
		
		}
		
	}

	public void resetLevel() {

		Dot.reset();
		level.createDots();	
		player.reset();
		
		if(gameStart.gameState==1) {
			Player.recentDeath=false;
		}
		
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		paintBackground(g2);
		
		// MAP OF ALL POSSIBLE DOT UBICATIONS
//		g2.setColor(Color.WHITE);
//		for(int i=0; i<71;i++) {
//			for(int j=0; j<37;j++) {
//				g2.fillRoundRect(i*7,j*7,7,7,100,100);
//			}
//		}
		
//		Color auxiliar = g2.getColor();
//		g2.setColor(Colors.DOT);
//		for(yellowDot y: level.dots) {
//			y.paint(g2);
//		}
//		g2.setColor(auxiliar);
		
		Color auxiliar = g2.getColor();
		g2.setColor(Colors.DOT);
		for(Dot y: level.dots) {
			y.paint(g2);
		}
		g2.setColor(auxiliar);
		
		g2.setColor(player.playerColor);
		player.move(g2);
		g2.setColor(Colors.ENEMIGO);

		for(Enemy e: level.enemies) {
			e.paint(g2);
		}
		
		for(Triangle t: level.triangles) {
			t.paint(g2);
		}
		
		paintMiscelanea(g2);
		
		checkCollision(g2);
		
		if(isOnAnimation) {
			player.drawAnimation(g2);
		}
		
		if(pauseScreen) {
			g2.setColor(new Color(0,0,0,180));
			g2.fillRect(0, 0, this.gameStart.getWidth(), this.gameStart.getHeight());
			g2.setColor(new Color(255,255,255));
			g2.fillRect(220,120 , 20, 60);
			g2.fillRect(260,120 , 20, 60);
			g2.setFont(new Font("Magneto Negrita", Font.PLAIN, 32));
			g2.drawString("Paused Game", 135 , 90);
		}
					
	}

	private void paintMiscelanea(Graphics2D g2){
		
		
		g2.setColor(Colors.ENEMIGO.brighter());
		g2.setStroke(new BasicStroke(6));
		g2.drawRect(0, 0, 500,260);
		
		
//		g2.setColor(new Color(230,255,230,160));
//		//String.valueOf(level.dotsCollecteds)
//		
//		g2.fillRoundRect(435, 5, 60, 30, 150,190);
//		g2.setColor(Color.WHITE);
//		g2.setStroke(new BasicStroke(2));
//		g2.drawRoundRect(435, 5, 60, 30, 150,190);
//		g2.setFont(new Font("Arial", Font.BOLD, 18));
//		
//		if(Level.totalDots-Level.dotsCollecteds <10) {
//			GameWindow.textPosition = 461;
//		}else if(Level.totalDots-Level.dotsCollecteds <100) {
//			GameWindow.textPosition = 456;
//		}else if(Level.totalDots-Level.dotsCollecteds >=100) {
//			GameWindow.textPosition = 450;
//		}
//		g2.setColor(Color.BLACK);
//		g2.drawString(String.valueOf(Level.totalDots - Level.dotsCollecteds),GameWindow.textPosition-2, 26);
//		g2.setColor(Color.WHITE);
//		g2.drawString(String.valueOf(Level.totalDots - Level.dotsCollecteds),GameWindow.textPosition, 27);

//		System.out.println(level.dots.get(50).dotId);

		
	}

	private void paintBackground(Graphics2D g2) {

//		int k=1;
//		
//		for(int i=0; i<20; i++) {
//			for(int j=0; j<9; j++) {
//				
//				if(k%2==1) {
//					g2.setColor(Colors.FONDO);
//					k++;
//				}else {
//					g2.setColor((Colors.FONDO2));
//					k--;
//				}
//				
//				g2.fillRect(30*i-1-15, 30*j, 30, 30);
//				
//			}
//			
//		}
		
		g2.drawImage(level.backgroundImage, 0,0, this.getWidth(),this.getHeight(),null);
		
		
	}




	private void checkCollision(Graphics2D g2) {
		
		if(FooterWindow.timeFinished) {
			
			FooterWindow.timeFinished=false;
			
			FooterWindow.BLINK_START=false;
						
			Dot.reset();
			
			level.createDots();	
				
			player.reset();
			
			isOnAnimation=true;
			
			Level.PLAYER_DEATHS++;
			
//			System.out.println(Level.playerDeaths);
			
			Level.INITIAL_TIME = System.currentTimeMillis();
			
			Level.ELLAPSED_PAUSED_TIME = 0;
			
			return;
		}

		for(Enemy e: level.enemies) {
			
			if(player.toRect().intersects(e.toRect())) {
			
			FooterWindow.BLINK_START=false;
				
			Dot.reset();
			
			level.createDots();	
				
			player.reset();
			
			isOnAnimation=true;
			
			Level.PLAYER_DEATHS++;
			
//			System.out.println(Level.PLAYER_DEATHS);
			
			Level.INITIAL_TIME = System.currentTimeMillis();
			
			Level.ELLAPSED_PAUSED_TIME = 0;
			
			return;
			}
			
		}
		
		for(Triangle t: level.triangles) {
			
			if(t.toPol().intersects(player.toRect())) {
				
			FooterWindow.BLINK_START=false;
				
			Dot.reset();
			
			level.createDots();	
				
			player.reset();
			
			isOnAnimation=true;
			
			Level.PLAYER_DEATHS++;
			
//			System.out.println(Level.PLAYER_DEATHS);
			
			Level.INITIAL_TIME = System.currentTimeMillis();
			
			Level.ELLAPSED_PAUSED_TIME = 0;

			return;
			}
			
		}
		
		for(Dot y: level.dots) {
			
			if(!Dot.IS_LAST_ORB_ACTIVATED) {
			if(player.toRect().intersects(y.toRect())) {

//			level.DOTS_COLLECTED.add(y);
			level.dots.remove(y);
			Level.DOTS_COLLECTED++;
							
			break;
			}
			}else {
				if(player.toRect().intersects(y.toRect())) {
					Level.GREEN_TAKED = true;
					FooterWindow.BLINK_START=false;
					checkWin();
				}
			}
			
		}
		
		if(player.xPosition >= this.getBounds().width - player.playerWidth
				|| player.xPosition <= 0
				|| player.yPosition >= this.getBounds().height -40 - player.playerHeight
				|| player.yPosition <= 0) {
//			player.velocityX=0;
//			player.xPosition=this.getBounds().width- player.playerWidth;
						
			FooterWindow.BLINK_START=false;

			Dot.reset();
			
			player.reset();
			
			isOnAnimation=true;

			level.createDots();
			
			Level.PLAYER_DEATHS++;
			
//			System.out.println(Level.playerDeaths);
			
			Level.INITIAL_TIME = System.currentTimeMillis();
			
			Level.ELLAPSED_PAUSED_TIME = 0;
			
			
			return;
		}
		
		
	}

	Player player = new Player();
	public Level level;
	private GameStart gameStart;
	FooterWindow footerWindow = new FooterWindow(gameStart,1);
	public static boolean isOnAnimation = false;
	public static boolean pauseScreen = false;

}


class Keys implements KeyListener{

	public Keys() {
		
	}
	
	public Keys(GameStart gameStart, GameWindow gameWindow) {
		this.gameStart=gameStart;
		this.gameWindow=gameWindow;
		this.player = gameWindow.player;
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
						
			if(!Player.recentDeath || keysReleased[1]) {
			
			if(e.isControlDown()) {
				player.velocityX=3;
			} else {
			player.velocityX=2;
			}
			
			keysReleased[1]=false;
			
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
						
			if(!Player.recentDeath || keysReleased[3]) {
			
			if(e.isControlDown()) {
			player.velocityX=-3;
			} else {
			player.velocityX=-2;
			}
			
			keysReleased[3]=false;
			
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_UP) {
						
			if(!Player.recentDeath || keysReleased[0]) {
			
			if(e.isControlDown()) {
			player.velocityY=-3;
			} else {
			player.velocityY=-2;
			}
			
			keysReleased[0]=false;
			
			}
			
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
						
			if(!Player.recentDeath || keysReleased[2]) {
			
			if(e.isControlDown()) {
			player.velocityY=3;
			} else {
			player.velocityY=2;
			}
			
			keysReleased[2]=false;
			
			}
			
		}
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
//			this.gameStart.dispose();
			
			System.exit(0);
		} else {
			
			if(this.gameStart.gameState != 0) {
			
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			
			if(this.gameStart.gameState==2) {
				this.gameWindow.resetLevel();
				this.gameStart.isOnPause=false;
				GameWindow.pauseScreen= false;

			}
			
			Level.PLAYER_DEATHS++;
			
			this.gameStart.changeState(0);
			
		}else
		if(e.getKeyCode()==KeyEvent.VK_P) {
			
			if(!this.gameStart.isOnPause) {
            GameWindow.pauseScreen=true;
			}else{
			GameWindow.pauseScreen=false;
			}
			this.gameWindow.repaint();
			this.gameStart.changeState(2);
			}
		
			}
		}
		
//		System.out.println(e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
		
		keyReleased = e.getKeyCode();

		
		if(keyReleased==KeyEvent.VK_RIGHT) {
			
			keysReleased[1]=true;
						
			// Only reset the stop if release the key
			if(Player.recentDeath) {
				Player.recentDeath=false;
			}
			
			player.velocityX=0;
		}
		if(keyReleased==KeyEvent.VK_LEFT) {
			
			keysReleased[3]=true;
			
			if(Player.recentDeath) {
				Player.recentDeath=false;
			}
			
			player.velocityX=0;
		}
		if(keyReleased==KeyEvent.VK_UP) {
			
			keysReleased[0]=true;
						
			if(Player.recentDeath) {
				Player.recentDeath=false;
			}
			
			player.velocityY=0;
		}
		if(keyReleased==KeyEvent.VK_DOWN) {
			
			keysReleased[2]=true;
			
			if(Player.recentDeath) {
				Player.recentDeath=false;
			}
			player.velocityY=0;
		}
	}
	
	public void getSource() {
		
	}
	
	Player player;
	GameStart gameStart;
	GameWindow gameWindow;
	public static int keyReleased;
	public static boolean[] keysReleased = {false,false,false,false}; //U,R,D,L
	public static boolean RIGHT_PRESSED = false;
	public static boolean LEFT_PRESSED = false;
	public static boolean UP_PRESSED = false;
	public static boolean DOWN_PRESSED = false;

}

class Rendering extends Thread{
	
	public Rendering(GameStart game) {
		this.game = game;
		gameWindow = game.gameWindow;
	}
	
	public void run(){
		
		while(playing) {
			
			game.updateGame();
			
			start = System.currentTimeMillis();
			//System.out.println(start);
			while(start >= System.currentTimeMillis()-delayTime);
			
		}
		
	}
	
	
	GameStart game;
	GameWindow gameWindow;
	boolean playing = true;
	final short FPS = 60;
	final long delayTime = 1000/FPS;
	long start;
}



class Player{
	
	Image playerImage;
	Color playerColor = Colors.JUGADOR;
	ArrayList<Image> blastImages = new ArrayList<Image>();
	static int animationX;
	static int animationY;
	int animationCount=0;
	int iterationCount=1;
	int k=8; //Discriminator, just because animation must start at image8
	public static boolean recentDeath = false;


	
	public Player(){
		
			playerImage = new ImageIcon(getClass().getResource("/PlayerImage 62x62.png")).getImage();
		
			
			for(int i=0; i<17; i++) {
				if(i+k > 16) {k=-9; }
				blastImages.add(new ImageIcon(getClass().getResource("/blast2/blast2-"+(i+k)+".png")).getImage());
			}

	}
	
	public void drawAnimation(Graphics2D g2) {

//		g2.setColor(new Color(255,255,255,(16-animationCount)*15));
//		g2.fillRect(50, 50, 100, 100);
		

		
		g2.drawImage(blastImages.get(animationCount), animationX-15,  animationY-15, playerWidth+30, playerHeight+30, null);
		
		if(iterationCount % 4 == 0) {
		animationCount++;
		}
		iterationCount++;
		if(iterationCount==64) {
			GameWindow.isOnAnimation = false;
			animationCount=0;
			iterationCount=0;
		}
		
	}

	public Rectangle toRect() {
		return new Rectangle(xPosition, yPosition, playerWidth, playerHeight);
	}
	
	public void move(Graphics2D g2) {
		if(playerImage!=null) {
		g2.drawImage(playerImage, xPosition+=velocityX,  yPosition+=velocityY, playerWidth, playerHeight, null);
		}else {
		g2.fillRect(xPosition+=velocityX, yPosition+=velocityY, playerWidth, playerHeight);
		}
	}
	
	public void reset() {
		
		if(!GameWindow.isOnAnimation) {
		animationX=this.xPosition; // Guarda la locacion para la animat.
		animationY=this.yPosition;
		}
		
		this.xPosition=10;
		this.yPosition=10;
		this.velocityX=0;
		this.velocityY=0;
		
		recentDeath = true;
		
	}
	
	int velocityX = 0;
	int velocityY = 0;
	int xPosition=10;
	int yPosition=10;
	int playerWidth=16;
	int playerHeight=16;

	
}

