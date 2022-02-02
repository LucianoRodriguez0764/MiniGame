package game.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import game.Colors;

public class Dot {
	
	private Random rnd = new Random();
	public int width;
	public int height;
	public int posX=0;
	public int posY=0;
	int coordX;
	int coordY;
	static ArrayList<Integer> coordsX = new ArrayList<Integer>();
	static ArrayList<Integer> coordsY = new ArrayList<Integer>();
	public Color color = Colors.DOT;
	public int dotId;
	boolean isARepitedPosition = false;
	Image dotImage;
	public static int OBJECT_COUNT;
	public static boolean IS_LAST_ORB_ACTIVATED = false;
	public static ArrayList<Image> DOT_TEXTURES = new ArrayList<Image>();

//	{
//		coordsX.add(0);
//		coordsY.add(0);
//	}
	
	{
		DOT_TEXTURES.add(new ImageIcon(getClass().getResource("/dots/yellowDotGlowy.png")).getImage());
		DOT_TEXTURES.add(new ImageIcon(getClass().getResource("/dots/greenDotGlowy.png")).getImage());
		DOT_TEXTURES.add(new ImageIcon(getClass().getResource("/dots/lightblueDotGlowy.png")).getImage());
		DOT_TEXTURES.add(new ImageIcon(getClass().getResource("/dots/redDotGlowy.png")).getImage());
	}
	
	public Dot(){
		
		dotImage = new ImageIcon(getClass().getResource("/dots/yellowDotGlowy.png")).getImage();
//		dotImage = ImageIO.read(new File(getClass().getResource("/dots/yellowOrb.png").toURI()));

		this.dotId=OBJECT_COUNT;
		OBJECT_COUNT++;
		
		do {
		
		isARepitedPosition=false;
		
		coordX=rnd.nextInt(68);
		coordY=rnd.nextInt(36);
		this.posX= coordX*7;
		this.posY= coordY*7;	
		
		this.height=7;
		this.width=7;
		
		for(int i=0; i<coordsX.size();i++){
			
//			System.out.println(coordsX.get(i));
			
			if(!(coordsX.isEmpty())) {
				// Rangos de aparicion!, si ponés coordsX.get(i) == (coordX) && coordsY.get(i) == (coordY)
				// aparecen en distintos niveles de x e y todos.
			if((coordsX.get(i) >= (coordX-2) && coordsX.get(i) <= (coordX+2)) &&
			  (coordsY.get(i) >= (coordY-2) && coordsY.get(i) <= (coordY+2))) {
//				if(coordsX.get(i) == (coordX) && coordsY.get(i) == (coordY)) {
				isARepitedPosition = true;
				break;
			}
			}
//			System.out.println(coordsX.size());
		}
//		System.out.println(objectCount);
		}while ( (coordX<=10 && coordY<=10 ) ||
				 (coordX>=11 && coordX<=13 && coordY==36) || 
				 (coordX>=17 && coordX<=19 && coordY==36) ||
				 (coordX>=39 && coordX<=47 && coordY>=34 && coordY<=36) ||
				 (coordX>=0 && coordX<=2 && coordY>=14 && coordY<=22) ||
				 (coordX>=49 && coordX<=57 && coordY>=0 && coordY<=2)
				 || isARepitedPosition);
		
		coordsX.add(coordX);
		coordsY.add(coordY);
		
	}
	
	public Dot(int posX, int posY, boolean isGreenDot) {
		
		this.dotImage = new ImageIcon(getClass().getResource("/dots/greenDotGlowy.png")).getImage();
		this.posX = posX;
		this.posY = posY;
		this.height = 20;
		this.width  = 20;
		
	}
	
	public Dot(int posX,int posY,int width,int height, int texture, boolean especificPosition) {
		
		if(especificPosition) {
			this.posX=posX;
			this.posY=posY;
			}else{
			// 71 width positions and 37 height positions (499x259)
			this.posX=(posX*7);
			this.posY=(posY*7);
			}
		
		this.width=width;
		this.height=height;
		
		this.dotImage = DOT_TEXTURES.get(texture);
		
	}
	
	public Rectangle toRect() {
		return new Rectangle(posX, posY, width, height);
	}

	public void paint(Graphics2D g2) {
		
		if(dotImage==null) {
		g2.setColor(Colors.DOT);
		g2.fillRoundRect(posX, posY, width, height,100,100);
		}else {
		g2.drawImage(dotImage, posX-5, posY-5, width+10, height+10,null);
		}
//		if(dotImage==null) {
//		g2.fillRoundRect(posX, posY, width, height,100,100);
//		}else {
//		g2.drawImage(dotImage, posX, posY, width, height,null);
//		}
		
	}
	
	
	public static void reset() {
		
		coordsX.clear();
		coordsY.clear();
		OBJECT_COUNT=0;
		OBJECT_COUNT=0;
		IS_LAST_ORB_ACTIVATED=false;

	}
	
}