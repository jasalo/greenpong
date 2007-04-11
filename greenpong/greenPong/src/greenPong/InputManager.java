/**
 * Clase que se comunica con el VTS (visual tracking system)
 * En el desarrollo inicial permite jugar greenpong con algún periferico común
 * ej: mouse, teclado.
 */
package greenPong;

import java.awt.event.MouseEvent;

import tempo.GameLevel;



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
	int x1;
	int y1;
	int lastXInRange=0;
	
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		//e.translatePoint(0,0);
		
		//System.out.println(x1);
		
		//moveMouse(x1,(int)Box.ALTO/2 );
		
		/*if(x1>Box.ANCHO-25-Bar.ANCHO) {
			moveMouse(Box.ANCHO-25-Bar.ANCHO,(int)Box.ALTO/2 );
		} else if(x1<10 && x1>=0) {
			moveMouse(10,(int)Box.ALTO/2 );
		}*/
		
		if(isInBoardSpace(x1)){
			userBar.setX(x1);
			lastXInRange = x1;
		}else{
			moveMouse(lastXInRange,(int)Box.ALTO/2 );
		}
		
		px = x1;
		py = y1;
		
		
		
		
	}
	
	private boolean isInBoardSpace(int xyz){
		if(xyz > 0 && xyz < Box.ANCHO){
			return true;
		}else{
			return false;
		}
	}
	
	InputManager(Bar bar, GameLevel nNivel){
		userBar = bar;
		nivel = nNivel;
		px=0;
		py=0;
		
	}
	
	public static void moveMouse(int x, int y){
		java.awt.Robot robot;
		try {
			robot = new java.awt.Robot();
			robot.mouseMove(x,y);
		} catch (java.awt.AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
