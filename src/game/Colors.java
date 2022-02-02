package game;

import java.awt.Color;

public abstract class Colors extends Color{
	
	public static Color FONDO = new Color(160,170,200);
	public static Color FONDO2 = new Color(130,140,180);
	public static Color JUGADOR = new Color(255,255,255);
	public static Color ENEMIGO = new Color(30,150,110);
	public static Color ENEMIGO_OLD = new Color(255,40,120);
	public static Color DOT = new Color(255,240,40);
	public static Color TURRET = new Color(100,110,20);

	public Colors(int r, int g, int b, int a) {
		super(r, g, b, a);
	}
	
	
}