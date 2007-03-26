/**
 * Clase que se comunica con el VTS (visual tracking system)
 * En el desarrollo inicial permite jugar greenpong con algún periferico común
 * ej: mouse, teclado.
 */
package greenPong;

import java.awt.event.MouseEvent;



/**
 * 
 * @author ubuntu
 *
 */
public class InputManager implements java.awt.event.MouseMotionListener{
	Bar userBar;
	Box contenedor;
	GameLevel nivel;
	int px, py; //Posicion previa en x y y.
	
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(MouseEvent e) {
		int x1 = e.getX();
		int y1 = e.getY();
		e.translatePoint(0,0);

		if(x1 > 0 && x1 < Box.ANCHO){
			userBar.setX(x1);
		}
		
		px = x1;
		py = y1;
		
		
		
	}
	
	InputManager(Bar bar, GameLevel nNivel){
		userBar = bar;
		nivel = nNivel;
		px=0;
		py=0;
	}
	
	/*public static void moveMouse(int x, int y){
		java.awt.Robot robot;
		try {
			robot = new java.awt.Robot();
			robot.mouseMove(x,y);
		} catch (java.awt.AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	

}
