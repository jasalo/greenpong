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
	public static final int brainTime = 100;

	public Main() {

	}

	public static void main(String[] args) {

		Brain appBrain = new Brain(args);
		appBrain.start();
		System.out.println("greenPong iniciado");

	}

}
