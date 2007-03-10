/**
 * Contenedor de la bola y las barras, es la interfaz gráfica y se comunica con
 * Brain constantemente.
 */
package greenPong;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

/**
 * fUNCION SIN METODOS POR AHORA.
 * 
 */
public class Box extends javax.swing.JPanel {
	// Definicion de constantes
	public static final int ANCHO = 446;

	public static final int ALTO = 580;

	// Definicion de las dos barras, una automatica y otra del usuario.
	Bar userBar, computerBar;

	// La bola del juego, no se llama ball para evitar errorer al escribir
	Ball gameBall;

	Box(String[] args, javax.swing.JFrame contenedor) {
		// Args: Util para recibir por consola las imagenes del juego
		// Se asigna memoria a los controles
		userBar = new Bar(args[0], this);
		computerBar = new Bar(args[0], this);
		gameBall = new Ball(args[1], this);
		setSize(ANCHO, ALTO); // Numeros tomados del estudio de proporciones
		
		this.setLayout(null);
		
		/*contenedor.getContentPane().*/add(userBar, BorderLayout.NORTH);
		/*contenedor.getContentPane().*/add(gameBall, BorderLayout.CENTER);
		/*contenedor.getContentPane().*/add(computerBar, BorderLayout.SOUTH);
		setBackground(java.awt.Color.RED);
		/**
		 * Falta por definir el layout, las posiciones iniciales de los objs.
		 * 
		 */
		//Ubica las barras verticalmente a la posicion en la q quedaran
		userBar.setLocation(10, getHeight() - 71);
		computerBar.setLocation(10,10);
		
		gameBall.setLocation(200,200);
		//gameBall.getLocation().get
		info("Box: " + this.getWidth() + ":" + this.getHeight());
		

	}
	
	public void info(String abc){
		System.out.println(abc);
	}

}
