/**
 * Clase principal, inicia la ejecución, se usa como una clase que no tiene otra
 * funcionalidad adicional a correr el hilo principal.
 */
package greenPong;

import java.awt.AWTException;

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
	}

	public static void main(String[] args) {
		Main app = new Main(args);
	}
	
	

}
