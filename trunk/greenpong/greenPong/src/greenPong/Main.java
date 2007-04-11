/**
 * Clase principal, inicia la ejecución, se usa como una clase que no tiene otra
 * funcionalidad adicional a correr el hilo principal.
 */
package greenPong;

import java.awt.AWTException;
import java.awt.Cursor;
import java.awt.Toolkit;

import javax.accessibility.AccessibleComponent;
import javax.accessibility.AccessibleExtendedComponent;

/**
 * 
 * @author ubuntu
 * 
 */
public class Main {

	// Cantidad de milisegundos entre cada calculo matematico de los cerebros
	public static final int brainTime = 1;
	private Brain appBrain;
	public String[] args;

	public Main(String[] args1) {
		args = args1;
		appBrain = new Brain(args);
		GPSplash splash = new GPSplash(appBrain, args1[2]);
		splash.start();
		//appBrain.start();
		System.out.println("greenPong iniciado");
		//OCULTAR MOUSE PRUEBA
		java.awt.Robot robot;
		java.awt.Image cursorImage = java.awt.Toolkit.getDefaultToolkit().getImage(args[3]);
		Cursor blankCursor = java.awt.Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new java.awt.Point( 0, 0), "" );
		AccessibleExtendedComponent abc;
		appBrain.contenedor.setCursor(blankCursor);
		
	}

	public static void main(String[] args) {
		Main app = new Main(args);
	}
	
	

}
