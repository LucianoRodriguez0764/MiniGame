package game;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import game.entities.*;

public class Level {
	
	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public ArrayList<Dot> dots = new ArrayList<Dot>();
	public ArrayList<Dot> dotsCollected = new ArrayList<Dot>();
	public ArrayList<Triangle> triangles = new ArrayList<Triangle>();

	public short levelNumber;
	public Image backgroundImage;
	public GameWindow gameWindow;
	public static int HEIGHT;
	public static int WIDTH;
	public static short DOTS_COLLECTED;
	public static short TOTAL_DOTS;
    public static int TOTAL_TIME; // In seconds
    public static long INITIAL_TIME;
    public static boolean GREEN_TAKED = false;
    public static long INITIAL_PAUSED_TIME;
    public static long ELLAPSED_PAUSED_TIME;
    public static int PLAYER_DEATHS = 0;


	
	public Level(GameWindow gameWindow) {
		
		this.gameWindow= gameWindow;
		Level.HEIGHT= gameWindow.getHeight();
		Level.WIDTH= gameWindow.getWidth();
		
	}
	
	public Level() {
		
		
	}
	
	
	public void getLevel(short i) {
		
		resetLevel();
				
		switch(i) {
		
		case 1:
			
			this.levelNumber=1;
			
			createEnemies();
			
			createDots();
			
			createTriangles();
			
			backgroundImage = new ImageIcon(getClass().getResource("/backgrounds/1green.jpg")).getImage();

			INITIAL_TIME = System.currentTimeMillis();
			
			TOTAL_TIME= 25;
			FooterWindow.BLINK_TIME = 5;
			FooterWindow.DANGER_TIME = 14;
			FooterWindow.TOO_DANGER_TIME= 9;

			
			break;
			
		case 2:
			
			this.levelNumber=2;
			
			createEnemies2();
			
			createDots2();
			
			INITIAL_TIME = System.currentTimeMillis();

			TOTAL_TIME = 30;
						
			backgroundImage = new ImageIcon(getClass().getResource("/backgrounds/2.jpg")).getImage();

			break;
			
		case 3:
			
			this.levelNumber=3;
			
//			createEnemies();
			
			createDots();
						
			backgroundImage = new ImageIcon(getClass().getResource("/backgrounds/1.jpg")).getImage();
			break;

		}
		
	
		
	}



	private void resetLevel() {

		enemies.clear();
		dots.clear();
		dotsCollected.clear();
		triangles.clear();
		DOTS_COLLECTED = 0;
		TOTAL_DOTS = 0;
		backgroundImage = null;
		levelNumber = 0;

	}

	private void createEnemies() {

		Enemy enemigo1 = new Enemy(8,11,28,28,3,false,false);
		Enemy enemigo2 = new Enemy(22,21,28,28,3,false,false);
		Enemy enemigo3 = new Enemy(36,11,28,28,3,false,false);
		Enemy enemigo4 = new Enemy(50,21,28,28,3,false,false);
		Enemy enemigo5 = new Enemy(65,0,42,42,5,false,false);
		Enemy enemigo6 = new Enemy(0,31,42,42,5,false,false);


		enemigo1.setLoopMovement(new int[][] {{70,0},{70,70},{0,70},{0,0}}, new int[][] {{5,0},{0,5},{5,0},{0,5}}, true,4);
		enemigo2.setLoopMovement(new int[][] {{0,-70},{70,-70},{70,0},{0,0}}, new int[][] {{0,5},{5,0},{0,5},{5,0}}, true,4);
		enemigo3.setLoopMovement(new int[][] {{70,0},{70,70},{0,70},{0,0}}, new int[][] {{5,0},{0,5},{5,0},{0,5}}, true,4);
		enemigo4.setLoopMovement(new int[][] {{0,-70},{70,-70},{70,0},{0,0}}, new int[][] {{0,5},{5,0},{0,5},{5,0}}, true,4);
		enemigo5.setLoopMovement(new int[][] {{-500+42,0},{-500+42,260-42},{0,260-42},{0,0}}, new int[][] {{4,0},{0,4},{4,0},{0,4}}, true,4);
		enemigo6.setLoopMovement(new int[][] {{500-42,0},{500-42,-260+42},{0,-260+42},{0,0}}, new int[][] {{4,0},{0,4},{4,0},{0,4}}, true,4);
	
		enemies.add(enemigo1);
		enemies.add(enemigo2);
		enemies.add(enemigo3);
		enemies.add(enemigo4);
		enemies.add(enemigo5);
		enemies.add(enemigo6);


	}
	
	private void createEnemies2() {

		Enemy enemigo1 = new Enemy(5,0,28,28,3,false,false);
		enemigo1.setLoopMovement(new int[][] {{0,260-28},{0,0}}, new int[][] {{0,7},{0,7}}, true,2);
		enemies.add(enemigo1);
		Enemy enemigo2 = new Enemy(9,35,28,28,3,false,false);
		enemigo2.setLoopMovement(new int[][] {{0,-260+28},{0,0}}, new int[][] {{0,7},{0,7}}, true,2);
		enemies.add(enemigo2);
		Enemy enemigo3 = new Enemy(13,0,28,28,3,false,false);
		enemigo3.setLoopMovement(new int[][] {{0,260-28},{0,0}}, new int[][] {{0,7},{0,7}}, true,2);
		enemies.add(enemigo3);
		Enemy enemigo4 = new Enemy(17,35,28,28,3,false,false);
		enemigo4.setLoopMovement(new int[][] {{0,-260+28},{0,0}}, new int[][] {{0,7},{0,7}}, true,2);
		enemies.add(enemigo4);
		Enemy enemigo5 = new Enemy(21,0,28,28,3,false,false);
		enemigo5.setLoopMovement(new int[][] {{0,260-28},{0,0}}, new int[][] {{0,7},{0,7}}, true,2);
		enemies.add(enemigo5);
		Enemy enemigo6 = new Enemy(25,35,28,28,3,false,false);
		enemigo6.setLoopMovement(new int[][] {{0,-260+28},{0,0}}, new int[][] {{0,7},{0,7}}, true,2);
		enemies.add(enemigo6);



	}
	
	
	/********************  OLD LEVEL, with OLD PAINT
	
	Enemy e1 = new Enemy(100,100,30,20,5,0,130);
	Enemy e2 = new Enemy(300,50,30,20,3,0,130);
	Enemy e3 = new Enemy(190,100,30,20,0,3,50);
	Enemy e4 = new Enemy(20,200,30,20,3,0,80);
	Enemy e5 = new Enemy(390,200,80,20,0,-2,65); // Stick
	Enemy e6 = new Enemy(340,180,30,70,3,0,70); // Stick 2
	Enemy e7 = new Enemy(220,210,50,50,0,-3,70); // Middle Cube
	Enemy e8 = new Enemy(20,110,25,10,4,-4,27); // Diagonal
	Enemy e9 = new Enemy(80,250,20,15,0,0,10); // Static pistol
	Enemy e10 = new Enemy(86,255,7,7,0,-8,75, true); // Bullet
	Enemy e11 = new Enemy(120,250,20,15,0,0,10); // Static pistol
	Enemy e12 = new Enemy(126,275,7,7,0,-8,65, true); // Bullet
	
	********************/

	public void createDots() {

		DOTS_COLLECTED=0;		
		
		if(!dots.isEmpty()) {
			dots.clear();
		}

//		Dot y1 = new Dot();
//		Dot y2 = new Dot();
//		Dot y3 = new Dot();
//		Dot y4 = new Dot();
//		Dot y5 = new Dot();
//		Dot y6 = new Dot();
//		Dot y7 = new Dot();
//		Dot y8 = new Dot();
//		Dot y9 = new Dot();
//		Dot y10 = new Dot();
//		
//		dots.add(y1);
//		dots.add(y2);
//		dots.add(y3);
//		dots.add(y4);
//		dots.add(y5);
//		dots.add(y6);
//		dots.add(y7);
//		dots.add(y8);
//		dots.add(y9);
//		dots.add(y10);
		
		Dot d1 = new Dot(15*7-3,18*7-3,7,7,0,true);
		Dot d2 = new Dot(29*7-3,18*7-3,7,7,0,true);
		Dot d3 = new Dot(43*7-3,18*7-3,7,7,0,true);
		Dot d4 = new Dot(57*7-3,18*7-3,7,7,0,true);
		
		Dot d5 = new Dot(68*7-3,3*7-3,7,7,0,true);
		Dot d6 = new Dot(3*7-3,34*7-3,7,7,0,true);

		dots.add(d1);
		dots.add(d2);
		dots.add(d3);
		dots.add(d4);
		dots.add(d5);
		dots.add(d6);

		
//		for(int i=0; i<100; i++) {
//			dots.add(new Dot());
//		}
		
		TOTAL_DOTS = (short)dots.size();

	}
	
	public void createDots2() {

		DOTS_COLLECTED=0;		
		
		if(!dots.isEmpty()) {
			dots.clear();
		}

		
		Dot d1 = new Dot(15*7-3,18*7-3,7,7,0,true);
		Dot d2 = new Dot(29*7-3,18*7-3,7,7,0,true);
		Dot d3 = new Dot(43*7-3,18*7-3,7,7,0,true);
		Dot d4 = new Dot(57*7-3,18*7-3,7,7,0,true);
		
		Dot d5 = new Dot(68*7-3,3*7-3,7,7,0,true);
		Dot d6 = new Dot(3*7-3,34*7-3,7,7,0,true);

		dots.add(d1);
		dots.add(d2);
		dots.add(d3);
		dots.add(d4);
		dots.add(d5);
		dots.add(d6);
		
		TOTAL_DOTS = (short)dots.size();

	}

//	public void remakeDots() {
//
//		for(Dot y : dotsCollected) {
//			dots.add(y);
//			dotsCollected.remove(y);
//		}
//		
//	}
	
	private void createTriangles() {
		
		Triangle t1 = new Triangle(54+220, 236,21,21,Triangle.UP);
		Triangle t2 = new Triangle(75+220, 236,21,21,Triangle.UP);
		Triangle t3 = new Triangle(96+220, 236,21,21,Triangle.UP);
		
		triangles.add(t1);
		triangles.add(t2);
		triangles.add(t3);
		
		Triangle t4 = new Triangle(343, 2,21,21,Triangle.DOWN);
		Triangle t5 = new Triangle(364, 2,21,21,Triangle.DOWN);
		Triangle t6 = new Triangle(385, 2,21,21,Triangle.DOWN);
		
		triangles.add(t4);
		triangles.add(t5);
		triangles.add(t6);
		
		Triangle t7 = new Triangle(2, 69+30,21,21,Triangle.RIGHT);
		Triangle t8 = new Triangle(2, 90+30,21,21,Triangle.RIGHT);
		Triangle t9 = new Triangle(2, 111+30,21,21,Triangle.RIGHT);
		
		triangles.add(t7);
		triangles.add(t8);
		triangles.add(t9);
	}
	
	public void activeGreenOrb() {
		
		dots.add(new Dot(10,10,true));
		TOTAL_DOTS = -1;
		Dot.IS_LAST_ORB_ACTIVATED=true;
		
	}
	

}

 


 
 
/*
 * 
 * 
 *  Menu system://///////////////
 * La idea sería en cuanto se gane o toque alguna tecla 
 * reemplazar el panel gameWindow por un gameMenu o algo así
 * en cuanto se clickee o elija el nivel, se reemplaza al revés, 
 * con el parámetro de nivel deseado.
 * 
 * Level system: /////////////////////////
 * Crear todos los niveles posibles en el switch case y cuando
 * se cree un new Level(2) el switch indica que objetos tiene
 * 
 * Enemies update: 
 * Algunos enemigos están anclados, algunos son bullet y demás. 
 * Los anclados como las torretas deberían estar aparte o dibujarse
 * primero para que las bullets no interfieran
 * 
 * UI/UX: ////////////////////////////////
 * Mejorar los triangulos, las coins, el Player principal, etc.
 * La idea es que Player sea algun cubo de gd o bloque, las coins
 * más simples como algún glow amarillo y los triangulos pinches
 * 
 * Animation: //////////////////////////////
 * No quiero luchar pero la idea sería que cuando pierdas, haya una 
 * animación de 1 segundo que interrumpa la aparición del jugador (o no).
 * 
 * 
 * */
 
 
 /*
  * Level Update:
  * Crear varios niveles, por lo menos dejar la base hecha
  * 
  * Objetivos: //////////////
  * La idea ahora que se creo la barra de abajo para lograr
  * el 500x300 sería agregar tiempo
  * 
  *    ///////////////
  * Volver a algun punto o inicio es una buena adicion con la orbe verde
  * 
  * Enemigos:
  * Imágenes fijas, mejorar los sprites, etc.
  * 
  * Orbe roja es buena idea
  * 
  * Más torretas con sprites propios
  * 
  * Decoración:
  * Terminar bien definida la decoración, fondo, etc
  * 
  * Spikes:
  * Sacar del scope de los yellow dots a los spikes nuevos.
  * 
  */
 
 
 
 /**
  * 
  *  Bugs & improvements fixed:
  * - Red clock on start
  * - Controls now resets when losing
  * - Animation finish where player dies for second time instead of finish
  *   where occurs for first 
  * 
  * 
  * 
  * 
  * 
  * 
  * 
  * 
  * 
  * 
  * 
  * 
  * 
  * 
  * 
  * 
  * 
  * 
  * 
  */

