package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import game.entities.*;

class MenuWindow extends JPanel{
	
	public GameStart gameStart;
	public LevelButton level1;
	public LevelButton level2;
	public LevelButton level3;
	public static int buttonCount;
	private Player animatedPlayer = new Player();
	private int randomXDir;
    private int randomYDir;
    private static int iteratorCount=30;
    private Random rnd = new Random();
    private Triangle triangle1 = new Triangle(200, 300-32, 30,30,Triangle.UP);
    private Triangle triangle2 = new Triangle(230, 300-32, 30,30,Triangle.UP);
    private Triangle triangle3 = new Triangle(260, 300-32, 30,30,Triangle.UP);
    private Triangle triangle4 = new Triangle(500-32, 130-30, 30,30,Triangle.LEFT);
    private Triangle triangle5 = new Triangle(500-32, 160-30, 30,30,Triangle.LEFT);
    private Triangle triangle6 = new Triangle(500-32, 190-30, 30,30,Triangle.LEFT);
    private Dot yellowDot1 = new Dot();
    private Dot yellowDot2 = new Dot();
    public Image backgroundImage;

	
	public MenuWindow(GameStart gameStart) {
			
		this.gameStart = gameStart;
		
		setLayout(null);
		
		setSize(500, 300);
				
		level1 = new LevelButton("Nivel 1", true);
		level2 = new LevelButton("Nivel 2", false);
		level3 = new LevelButton("Nivel 3", false);

		add(level1);
		add(level2);
		add(level3);
						
		setBackground(Color.BLACK);
	
		animatedPlayer.playerWidth=30;
		animatedPlayer.playerHeight=30;
		animatedPlayer.xPosition=250;
		animatedPlayer.yPosition=120;
		
		yellowDot1.posX =420;
		yellowDot1.posY =230;
//		yellowDot1.posX =320;
//		yellowDot1.posY =130;
		yellowDot1.width=25;
		yellowDot1.height=25;
		
		yellowDot2.posX =110;
		yellowDot2.posY =250;
		yellowDot2.width=25;
		yellowDot2.height=25;
		
		Dot.OBJECT_COUNT-=2;
		
		backgroundImage = new ImageIcon(getClass().getResource("/backgrounds/backgroundHDTech500x300.jpg")).getImage();
		
		InputMap inputMap = this.getInputMap(WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke("ESCAPE"),"doSomething");
		this.getActionMap().put("doSomething", new escapeAction());
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.drawImage(backgroundImage, 0, 0, 500, 300, null);

		
		if(backgroundImage==null) {
		g2.setFont(new Font("Magneto Negrita", Font.PLAIN, 50));
		g2.setColor(new Color(255,255,255));
		g2.drawString("GameFunk", 110 , 50);
		}
		drawCubeAnimation(g2);
		drawTriangles(g2);
		
		yellowDot1.paint(g2);
		yellowDot2.paint(g2);
		

		
	}

	private void drawTriangles(Graphics2D g2) {
		
		triangle1.paint(g2);
		triangle2.paint(g2);
		triangle3.paint(g2);
		
		triangle4.paint(g2);
		triangle5.paint(g2);
		triangle6.paint(g2);
	
	}

	private void drawCubeAnimation(Graphics2D g2) {
		
		if(iteratorCount != 0) {
//
		if(animatedPlayer.xPosition + randomXDir > 420 || animatedPlayer.xPosition+randomXDir < 150) {
			randomXDir = -randomXDir;
		}
		if(animatedPlayer.yPosition+randomYDir > 220 || animatedPlayer.yPosition+randomYDir < 90) {
			randomYDir = -randomYDir;
		}
		animatedPlayer.xPosition+=randomXDir;
		animatedPlayer.yPosition+=randomYDir;
		g2.drawImage(animatedPlayer.playerImage,animatedPlayer.xPosition,animatedPlayer.yPosition, animatedPlayer.playerWidth,animatedPlayer.playerHeight, null);
//		g2.drawRect(150, 90, 300, 160);
		
//		System.out.println(animatedPlayer.xPosition+","+animatedPlayer.yPosition+","+animatedPlayer.playerHeight+","+animatedPlayer.playerWidth);
		
		
		
//		System.out.println(iteratorCount +","+animatedPlayer.playerWidth+","+animatedPlayer.playerHeight);
		iteratorCount--;

		} else {
		randomXDir=(rnd.nextInt(3)-1);
		randomYDir=(rnd.nextInt(3)-1);
		g2.drawImage(animatedPlayer.playerImage,animatedPlayer.xPosition, animatedPlayer.yPosition, animatedPlayer.playerWidth,animatedPlayer.playerHeight,null);
//		g2.drawRect(150, 90, 300, 160);
		iteratorCount=30;
//		System.out.println(iteratorCount +","+animatedPlayer.playerWidth+","+animatedPlayer.playerHeight);
		iteratorCount--;
		}
		
		if(animatedPlayer.toRect().intersects(yellowDot1.toRect())) {
			yellowDot1.posX=501;
			yellowDot1.posY=301;
		}
		
	}

	class escapeAction extends AbstractAction{
		
		public escapeAction() {
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
		
	}
	

	 class LevelButton extends JButton implements ActionListener{
		
		public boolean isEnabled = false;
		public Object buttonSource;
		
		public LevelButton(String text, boolean isEnabled) {
			
			this.setBackground(Color.BLUE.brighter());
			this.setForeground(Color.WHITE);
			this.setBounds(10, 105+40*buttonCount, 100, 30);
			this.setText(text);
			this.isEnabled=isEnabled;
			this.setFocusPainted(false);
			
			if(!isEnabled) {
				this.setEnabled(false);
				this.setBackground(Color.GRAY);
			}
			
			addActionListener(this); 
			
			buttonCount++;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			buttonSource = e.getSource();
			
			if(buttonSource==level1) {
			gameStart.gameWindow.level.getLevel((short)1);

			}
			if(buttonSource==level2) {
			
			gameStart.gameWindow.level = new Level(gameStart.gameWindow);
			gameStart.gameWindow.level.getLevel((short)2);

			}
			if(buttonSource==level3) {
				
				gameStart.gameWindow.level.getLevel((short)3);

			}
			
			gameStart.changeState(1);

		}
		
		
	}

	
	
}

