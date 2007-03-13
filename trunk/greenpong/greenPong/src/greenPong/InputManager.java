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
	GameLevel nivel;
	int px, py; //Posicion previa en x y y.

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(MouseEvent e) {
		System.out.println("Se movio el mouse");
		int x1 = e.getX();
		int y1 = e.getY();
		if(px-x1>0){
			userBar.moveLeft(3);
		}else{
			userBar.moveRight(3);
		}
		px = x1;
		py = y1;
		e.translatePoint(0,0);
		
		
	}
	
	InputManager(Bar bar, GameLevel nNivel){
		userBar = bar;
		nivel = nNivel;
		px= 0;
		py =0;
	}
	
	

}
