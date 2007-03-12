/**
 * Clase que se comunica con el VTS (visual tracking system)
 * En el desarrollo inicial permite jugar greenpong con algún periferico común
 * ej: mouse, teclado.
 */
package greenPong;

import java.awt.event.KeyEvent;



/**
 * 
 * @author ubuntu
 *
 */
public class InputManager implements java.awt.event.KeyListener{
	Bar userBar;
	GameLevel nivel;

	public void keyTyped(KeyEvent e) {
		System.out.println(e.getKeyChar());
		
		
		
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	InputManager(Bar bar, GameLevel nNivel){
		userBar = bar;
		nivel = nNivel;
	}
	
	

}
