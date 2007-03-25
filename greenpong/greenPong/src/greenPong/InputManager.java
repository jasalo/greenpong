/**
 * Clase que se comunica con el VTS (visual tracking system)
 * En el desarrollo inicial permite jugar greenpong con algún periferico común
 * ej: mouse, teclado.
 */
package greenPong;

import java.awt.event.KeyEvent;
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
		System.out.println("Se movio el mouse");
		int x1 = e.getX();
		int y1 = e.getY();
		e.translatePoint(0,0);
		if(e.getX() > 364)
		{
			userBar.setX(contenedor.ANCHO-userBar.ANCHO-10);
		}
		else if(e.getX() < 74)
		{
			userBar.setX(10);
		}
		else if(e.getX() <= 309 && e.getX() >= 74)
		{
			userBar.setX(x1-64);
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
	
	

}
