/**
 * Contenedor de la bola y las barras, es la interfaz gráfica y se comunica con
 * Brain constantemente.
 */
package greenPong;

//import javax.swing.*;
//import javax.swing.border.Border;



import java.awt.*;

/**
 * fUNCION SIN METODOS POR AHORA.
 * 
 */
public class Box extends javax.swing.JPanel {
	// Definicion de constantes
	public static final int WIDTH = 446;
	public static final int HEIGHT = 580;

	// Definicion de las dos barras, una automatica y otra del usuario.
	Bar userBar, computerBar;

	// La bola del juego, no se llama ball para evitar errorer al escribir
	Ball gameBall;
	Dimension center; //centro de la pantalla
	//GameWindow gWindow;

	Box(String[] args, javax.swing.JFrame contenedor) {
		//gWindow = (GameWindow)contenedor;
		// Args: Util para recibir por consola las imagenes del juego
		// Se asigna memoria a los controles
		center = new Dimension();
		userBar = new Bar(args[0], this);
		computerBar = new Bar(args[0], this);
		gameBall = new Ball(args[1], this);
		setSize(WIDTH, HEIGHT); // Numeros tomados del estudio de proporciones

		setLayout(null);

		add(userBar, BorderLayout.NORTH);
		add(gameBall, BorderLayout.CENTER);
		add(computerBar, BorderLayout.SOUTH);
		
		adjustComponents();	
		Robot robot;
		try {
			robot = new Robot();
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			// Se halla el centro de la pantalla
			int w = getSize().width;
			int h = getSize().height;
			center.setSize( ((dim.width-w)/2), ((dim.height-h)/2));
			//Se ubica ventana y mouse al centro;
			setLocation((int)center.getWidth(), (int)center.getHeight() );
			robot.mouseMove((int)(WIDTH/2), (int)(HEIGHT/2) );
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setBackground(Color.WHITE);
		
		
	}

	public void adjustComponents() {
		// Ubica las barras verticalmente a la posicion en la q quedaran
		userBar.setFinalYPosition(HEIGHT-71);
		userBar.setLocation(10, userBar.getFinalYPosition());
		//info("Userbar Y final position:" + userBar.getFinalYPosition());
		userBar.centerInX();
		userBar.moveRight(20);

		computerBar.setFinalYPosition(10);
		computerBar.setLocation(10, computerBar.getFinalYPosition());
		//info("Computerbar Y final position:" + computerBar.getFinalYPosition());
		computerBar.centerInX();
		computerBar.moveLeft(20);

		gameBall.setLocation(200, 200);
		gameBall.center();
		// gameBall.getLocation().get
		//info("Box: " + this.getWidth() + ":" + this.getHeight());

	}

	public void info(String abc) {
		System.out.println(abc);
	}

}
