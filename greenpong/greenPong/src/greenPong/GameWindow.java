/**
 * La clase gameWindow aunque parezca innecesaria resulta muy util para
 * agregar menus a la aplicacion y controles para gestioanr puntaje o vidas.
 */
package greenPong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

/**
 * @author ubuntu
 * 
 */
public class GameWindow extends javax.swing.JFrame {
	Box windowBox;
	
	GameWindow(String[] args) {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("[greenPong]");
		setResizable(false);
		windowBox = new Box(args, this);
		getContentPane().add(windowBox, BorderLayout.CENTER);
		
		//
		//getContentPane().add(new javax.swing.JButton("Yo Estorbo!!"), BorderLayout.SOUTH);
		setBackground(Color.BLUE);
		//
		setSize(Box.ANCHO, Box.ALTO);
		
		setVisible(true); // INICIA EL JUEGO!!
	}

}
