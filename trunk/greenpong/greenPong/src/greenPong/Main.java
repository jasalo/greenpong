/**
 * Clase principal, inicia la ejecución, se usa como una clase que no tiene otra
 * funcionalidad adicional a correr el hilo principal.
 */
package greenPong;

/**
 * 
 * @author ubuntu
 * 
 */
public class Main {

	public Main() {

	}

	public static void main(String[] args) {
		GameWindow caja = new GameWindow(args);
		System.out.println("El programa ha sido corrido");

	}

}
