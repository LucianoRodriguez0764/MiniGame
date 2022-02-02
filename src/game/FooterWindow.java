package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FooterWindow extends JPanel{
	
	GameStart gameStart;
	private static int textPosition;
	private Image footerImage;
	private Image deathIcon;
	int timeInSeconds;
	int timeMinutes;
	int timeSeconds;
	String ceroMinuteCompleter;
	String ceroSecondCompleter;
	public static boolean timeFinished = false;
	private String timeInString;
	public static boolean BLINK_START = false;
	private static int blinkCounter = 0;
	public static int DANGER_TIME = 19;
	public static int TOO_DANGER_TIME = 9;
	public static int BLINK_TIME = 5;

	
	
	public FooterWindow(GameStart gameStart, int footerLevel) {
		
		this.gameStart=gameStart;
		
		setLayout(null);
				
		setSize(500, 40);
		
		this.setBackground(new Color(0,20,0));
		
		getFooterImage(footerLevel);
		
		this.deathIcon = new ImageIcon(getClass().getResource("/footers/deaths.png")).getImage();

		
	}
	
	private void getFooterImage(int footerLevel) {

		switch(footerLevel) {
		case 1 ->
		this.footerImage = new ImageIcon(getClass().getResource("/footers/level1 footer.jpg")).getImage();
		default-> { }
		}
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.drawImage(footerImage,0,0,500,40,null);
		
		drawDotQuantity(g2);
		
		drawClock(g2);
		
		drawDeaths(g2);
		
		if(GameWindow.pauseScreen) {
			g2.setColor(new Color(0,0,0,180));
			g2.fillRect(0, 0, this.getWidth() , this.getHeight());
		}
	}
	
	private void drawDotQuantity(Graphics2D g2) {
		
		g2.setColor(new Color(130,255,200,160));
		g2.fillRoundRect(435-430, 5, 60, 30,10,10);
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(2));
		g2.drawRoundRect(435-430, 5, 60, 30, 10,10);
		g2.setFont(new Font("Arial", Font.BOLD, 18));
		
		if(Level.TOTAL_DOTS-Level.DOTS_COLLECTED <10) {
			FooterWindow.textPosition = 461-430;
		}else if(Level.TOTAL_DOTS-Level.DOTS_COLLECTED <100) {
			FooterWindow.textPosition = 456-430;
		}else if(Level.TOTAL_DOTS-Level.DOTS_COLLECTED >=100) {
			FooterWindow.textPosition = 450-430;
		}
		if(Level.TOTAL_DOTS != -1) {
		g2.setColor(Color.BLACK);
		g2.drawString(String.valueOf(Level.TOTAL_DOTS - Level.DOTS_COLLECTED),FooterWindow.textPosition-2, 26);
		g2.setColor(Color.WHITE);
		g2.drawString(String.valueOf(Level.TOTAL_DOTS - Level.DOTS_COLLECTED),FooterWindow.textPosition, 27);
		}else{
			
		g2.setColor(Color.BLACK);
		g2.drawString("0",FooterWindow.textPosition-2, 26);
		g2.setColor(Color.GREEN);
		g2.drawString("0",FooterWindow.textPosition, 27);
		g2.setColor(Color.WHITE);
			
		}
		
	}

	private void drawClock(Graphics2D g2) {
		
		timeInString = clock();
		
		if(timeInSeconds <= BLINK_TIME) {
			BLINK_START = true;
		}
		
		if(timeInSeconds <= TOO_DANGER_TIME) {
			g2.setColor(Color.RED);
		}else if(timeInSeconds <= DANGER_TIME) {
			g2.setColor(Color.YELLOW);
		}
		
		if(BLINK_START) {
			
			blinkCounter++;
			
			if(blinkCounter>20) {
				g2.setColor(Color.RED.darker().darker());
			}else {
				g2.setColor(Color.RED);
			}
			
			if(blinkCounter==30) {
				blinkCounter=0;
			}
			
			if(timeInSeconds<=0) {
				BLINK_START=false;
			}
			
		}
		
		g2.drawString( timeInString ,90,27);
		
	}
	
	private String clock() {
		
		timeInSeconds =(int) (Level.TOTAL_TIME - (System.currentTimeMillis()/1000 - Level.INITIAL_TIME/1000) + Level.ELLAPSED_PAUSED_TIME);
		
//		System.out.println(timeInSeconds);
		
		timeMinutes = (timeInSeconds / 60);
		timeSeconds = (timeInSeconds % 60);
		
		if(timeMinutes<10) {
		ceroMinuteCompleter="0";
		} else {
			ceroMinuteCompleter="";
		}
		if(timeSeconds<10) {
			ceroSecondCompleter="0";
		} else {
			ceroSecondCompleter="";
		}
		
		if(timeInSeconds < 0) {
			timeFinished=true;
			timeMinutes=0;
			timeSeconds=0;
		}
		
//		System.out.println(timeInSeconds+"-"+timeMinutes+"-"+timeSeconds);
		
		return ceroMinuteCompleter+String.valueOf(timeMinutes) +":"+ceroSecondCompleter+String.valueOf(timeSeconds);
	}
	
	private void drawDeaths(Graphics2D g2) {
		
		g2.setColor(Color.BLACK);
		g2.drawString( String.valueOf(Level.PLAYER_DEATHS), 390-2, 26);
		g2.setColor(Color.WHITE);
		g2.drawString( String.valueOf(Level.PLAYER_DEATHS), 390, 27);

		g2.drawImage(deathIcon, 430, 0, 40, 40, null);
		
	}

}
