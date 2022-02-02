package game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import game.Colors;

//import game.Colors;

	public class Enemy{
		 
		int velocityX = 0;
		int velocityY = 0;
		int xBullet;
		int yBullet;
		public int xPosition;
		public int yPosition;
		public int initialXPosition;
		public int initialYPosition;
		int playerWidth;
		int playerHeight;
		int iterations=0;
		int topIt = 0;
		int texture = 0;
		boolean isStatic;
		boolean isBullet = false;
		
		int[][] movementRoute = new int[20][2];
		int[][] speedRoute = new int[20][2];
		int it = 0;
		boolean isLoop;
		int movements;

		
		Random rnd = new Random();
		Image image1;
		Image image2;
		Image image3;
		Image image4;
		Image image5;
		Image image6;
		Image blockDefinedImage;
		int randomImageIndex = rnd.nextInt(6);
		ArrayList<Image> blockImages = new ArrayList<Image>();
	
	 
	 {
		image1 = new ImageIcon(getClass().getResource("/enemies/BLOCK 64X64.png")).getImage();
		image2 = new ImageIcon(getClass().getResource("/enemies/BLOCK 2 64X64.png")).getImage();
		image3 = new ImageIcon(getClass().getResource("/enemies/BLOCK 3 64X64.png")).getImage();
		image4 = new ImageIcon(getClass().getResource("/enemies/BLOCK 4 64X64.png")).getImage();
		image5 = new ImageIcon(getClass().getResource("/enemies/BLOCK 5 64X64.png")).getImage();
		image6 = new ImageIcon(getClass().getResource("/enemies/BLOCK 6 64X64.png")).getImage();
		blockImages.add(image1);
		blockImages.add(image2);
		blockImages.add(image3);
		blockImages.add(image4);
		blockImages.add(image5);
		blockImages.add(image6);
		blockDefinedImage=blockImages.get(randomImageIndex);
	 }
	 
	
	
	public Enemy(int xPosition,int yPosition,int playerWidth,int playerHeight, int velX, int velY, int topIt){
		this.xPosition=xPosition;
		this.yPosition=yPosition;
		this.playerWidth=playerWidth;
		this.playerHeight=playerHeight;
		this.velocityX=velX;
		this.velocityY=velY;
		this.topIt = topIt;
	}
	
	public Enemy(int xBullet,int yBullet,int playerWidth,int playerHeight, int velX, int velY, int topIt, boolean isBullet){
		this.xBullet=xBullet;
		this.yBullet=yBullet;
		this.xPosition=xBullet;
		this.yPosition=yBullet;
		this.playerWidth=playerWidth;
		this.playerHeight=playerHeight;
		this.velocityX=velX;
		this.velocityY=velY;
		this.topIt = topIt;
		this.isBullet = isBullet;
	}
	
	public Enemy(int xPosition,int yPosition,int playerWidth,int playerHeight, int texture, boolean especificPosition, boolean isStatic) {
		
		// The middle is (35,18)
		
		if(especificPosition) {
		this.xPosition=xPosition;
		this.yPosition=yPosition;
		this.initialXPosition=xPosition;
		this.initialYPosition=yPosition;
		}else{
		// 71 width positions and 37 height positions (499x259)
		this.xPosition=(xPosition*7);
		this.yPosition=(yPosition*7);
		this.initialXPosition=(xPosition*7);
		this.initialYPosition=(yPosition*7);
		}
		this.playerWidth=playerWidth;
		this.playerHeight=playerHeight;
		this.isStatic = isStatic;
		if(texture != -1) {
		this.blockDefinedImage = blockImages.get(texture);
		}
	}

	public Rectangle toRect() {
		return new Rectangle(this.xPosition, this.yPosition, this.playerWidth, this.playerHeight);
	}
	
	public void paint(Graphics2D g2) {
	

		
		
		g2.drawImage(this.blockDefinedImage,this.xPosition+=this.velocityX ,  this.yPosition+=this.velocityY, this.playerWidth, this.playerHeight, null);
		g2.setColor(Colors.ENEMIGO.darker());
		g2.drawRect(this.xPosition, this.yPosition, this.playerWidth, this.playerHeight);
	
		if(!this.isStatic) {
		
//		I love this line fuk is my favourite line of the entire program.
//		System.out.println(it +"..."+this.movementRoute[it][0]+"."+this.movementRoute[it][1]+"..."+this.xPosition+"."+this.yPosition+"..."+this.speedRoute[it][0]+"."+this.speedRoute[it][1]);
		
		
		/********************************************************/
		
//		FIXER FOR SPEED/POSITION CONGRUENCE PROBLEM.
		
		if(this.movementRoute[it][0]-this.xPosition < this.speedRoute[it][0] && this.movementRoute[it][0]-this.xPosition > 0) {
			this.xPosition=this.movementRoute[it][0];
		}
		
		if(this.movementRoute[it][1]-this.yPosition < this.speedRoute[it][1] && this.movementRoute[it][1]-this.yPosition > 0) {
			this.yPosition=this.movementRoute[it][1];
		}
		/********************************************************/
		
		if(this.xPosition == this.movementRoute[it][0] && this.yPosition == this.movementRoute[it][1]) {
			
			it++;
			
		}
		
		if(!isLoop && it==this.movements) {
			this.isStatic=true;
			
		}
		if(isLoop && it==this.movements) {
			it=0;
		}
		
		
		this.velocityX=this.speedRoute[it][0];
		this.velocityY=this.speedRoute[it][1];
		
		if(this.xPosition >= this.movementRoute[it][0]) {
			this.velocityX = -this.velocityX;
		}
		
		if(this.yPosition >= this.movementRoute[it][1]) {
			this.velocityY = -this.velocityY;
		}
		
		
		}
	}
	
	
	public void setLoopMovement(int[][] movementRoute, int[][] speedRoute, boolean isLoop, int movements) {
		
		/*Make sure that movementRoute or speed doesnt be longer
		 * than this.mov this.sp ... paja hacer un ternario 
		 * y poner dos variables que determinen cual es el top */
		this.movements=movements;
		this.isLoop=isLoop;
		
		for(int i=0; i< movementRoute.length; i++) {
//			this.movementRoute[i][0] = movementRoute[i][0]; // ABSOLUTE MOVEMENT
//			this.movementRoute[i][1] = movementRoute[i][1];		
			this.movementRoute[i][0] = this.xPosition + movementRoute[i][0]; // RELATIVE MOVEMENT
			this.movementRoute[i][1] = this.yPosition + movementRoute[i][1];	
		}
		for(int i=0; i< speedRoute.length; i++) {
			this.speedRoute[i][0] = speedRoute[i][0];
			this.speedRoute[i][1] = speedRoute[i][1];
		}
		
	}
	
	/*********************************  OLD PAINT
	
	public void paint(Graphics2D g2) {
		
		if(!isBullet){
			
			if(this.blockDefinedImage == null) {
			g2.fillRect(this.xPosition+=velocityX, this.yPosition+=velocityY, this.playerWidth, this.playerHeight);
			}else {
			g2.drawImage(this.blockDefinedImage,this.xPosition+=velocityX ,  this.yPosition+=velocityY, this.playerWidth, this.playerHeight, null);
			g2.setColor(Colors.ENEMIGO.darker());
			g2.drawRect(this.xPosition, this.yPosition, this.playerWidth, this.playerHeight);
			}
				
			iterations++;
			if(iterations==topIt) {
				this.velocityX=-velocityX;
				this.velocityY=-velocityY;
				iterations=0;
			}
	
		
			
		} else {
			Color auxiliar = g2.getColor();
			g2.setColor(Colors.ENEMIGO.brighter());
			g2.fillRoundRect(this.xPosition+=velocityX, this.yPosition+=velocityY, this.playerWidth, this.playerHeight, 200, 200);
			g2.setColor(auxiliar);
			iterations++;
			if(iterations==topIt) {
				this.xPosition=xBullet;
				this.yPosition=yBullet;
				iterations=0;
			}
		
	
		}
	}
	
	************************************/
	
	}