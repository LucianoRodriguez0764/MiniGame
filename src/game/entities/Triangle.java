package game.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;

import javax.swing.ImageIcon;

import game.Colors;

public class Triangle{
	 
	int width;
	int height;
	int posX;
	int posY;
	int rotation=0;
	public Color color = Colors.DOT;
	Image triangleImage;
    int[] xPoints = new int[4];
	int[] yPoints = new int[4];
	public static int LEFT=3;
	public static int DOWN=2;
	public static int RIGHT=1;
	public static int UP=0;
	
	public Triangle(int x, int y, int width, int height, int rotation){
		
		this.posX= x;
		this.posY= y;	
		this.width=width;
		this.height=height;
		this.rotation=rotation;
		
		triangleImage= new ImageIcon(getClass().getResource("/spikes/SpikeUp.png")).getImage();

		createPolygon(rotation);
		
	}
	 
	 
	private void createPolygon(int rotation) {

		switch(rotation) {
		
		case 0:
			xPoints[0]=posX;
			xPoints[1]=posX+width/2;
			xPoints[2]=posX+width;
			xPoints[3]=posX;

			yPoints[0]=posY+height;
			yPoints[1]=posY;
			yPoints[2]=posY+height;
			yPoints[3]=posY+height;

			break;
			
		case 1:
			xPoints[0]=posX;
			xPoints[1]=posX+width;
			xPoints[2]=posX;
			xPoints[3]=posX;

			yPoints[0]=posY;
			yPoints[1]=posY+height/2;
			yPoints[2]=posY+height;
			yPoints[3]=posY;

			break;
			
		case 2:
			xPoints[0]=posX+width;
			xPoints[1]=posX+width/2;
			xPoints[2]=posX;
			xPoints[3]=posX+width;

			yPoints[0]=posY;
			yPoints[1]=posY+height;
			yPoints[2]=posY;
			yPoints[3]=posY;

			break;
			
		case 3:
			xPoints[0]=posX+width;
			xPoints[1]=posX;
			xPoints[2]=posX+width;
			xPoints[3]=posX+width;

			yPoints[0]=posY+height;
			yPoints[1]=posY+height/2;
			yPoints[2]=posY;
			yPoints[3]=posY+height;

			break;
		
		}
		
	}





	public void paint(Graphics2D g2) {
		
		// 0: up, 1: 90 clock, 2: 180 (DOWN), 3: left;

		if(triangleImage==null) {
			
			g2.fillPolygon(xPoints, yPoints, 4);

		
		}else{
			
			switch(rotation){
			case 0 -> {break;}
			case 1 -> triangleImage= new ImageIcon(getClass().getResource("/spikes/SpikeRight.png")).getImage();
			case 2 -> triangleImage= new ImageIcon(getClass().getResource("/spikes/SpikeDown.png")).getImage();
			case 3 -> triangleImage= new ImageIcon(getClass().getResource("/spikes/SpikeLeft.png")).getImage();
			default -> {break;}
			}
			
			g2.drawImage(triangleImage, posX, posY, width, height, null);
//			g2.fillPolygon(xPoints, yPoints, 4);

		}
	}
	
	 
	 public Polygon toPol() {
		return new Polygon(xPoints, yPoints, 4);
	 }
	 
	 
	 
 }