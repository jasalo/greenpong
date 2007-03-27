/**
 * Este es el centro del programa, gestiona colisiones y realiza cualquier calculo
 * matemático. Mueve todas las tres partes del programa recibiendo constantemente
 * coordenadas de la clase InputManager y así puede unirse al VTS sin modificaciones
 */
package greenPong;

/**
 * @author ubuntu
 * 
 */
public class Brain extends Thread {

	Box contenedor;

	Bar userBar, computerBar;

	Ball gameBall;
	
	// Constantes y atributos pa movimiento vertical

	final static boolean ARRIBA = true;

	final static boolean ABAJO = false;

	boolean direccion = ABAJO;
	
	// Constantes y atributos pa movimiento horizontal
	
	final static boolean DERECHA = true;

	final static boolean IZQUIERDA = false;
	
	boolean movHorizontal = DERECHA;
	
	// Otros
	
	int XXX = 0;
	
	int der = Box.ANCHO - 15;

	boolean perdio = false;

	int velocidad = 1; // Pixeles del movimiento de la computerBar y la bola, por run
						//Si no es 1 da errores en los cálculos y no rebota la bola

	public Brain(String[] args) {
		super();
		GameWindow caja = new GameWindow(args);
		contenedor = caja.windowBox;
		userBar = contenedor.userBar;
		computerBar = contenedor.computerBar;
		gameBall = contenedor.gameBall;
		InputManager entrada = new InputManager(userBar, contenedor.nivel);
		System.out.println("Agregando listener");
		contenedor.addMouseMotionListener(entrada);
		direccion = ABAJO;
		movHorizontal = DERECHA;
	}

	public void run() {
		userBar.setLocation(0, userBar.getFinalYPosition());
		computerBar.setLocation(50, 10);
		userBar.centerInX();
		while (1 == 1) {
			try {
				sleep(Main.brainTime);
			} catch (InterruptedException e) {}
			
			if (XXX!=0) {
				evaluarNormas();
				moverBola();
			}
			else
			{
				moverBola();
				XXX = 1;
			}
		}

	}

	/**
	 * Este metodo evalua normas del juego o cosas que se tienen que cumplir.
	 * Si no se cumplen genera algo. Usa otro metodos.
	 */
	public void evaluarNormas() {
		
		// EVALUAR MOVIMIENTO HORIZONTAL
		
		if (movHorizontal && gameBall.rightExtreme()==der){
			reboteHorizontal();
		}
		
		if (!movHorizontal && gameBall.leftExtreme()==10){
			reboteHorizontal();
		}
		
		// EVALUAR MOVIMIENTO VERTICAL
		
		int bolaInf = Box.ALTO - gameBall.getLocation().y - Ball.ALTO - 25; // - 25 pa corregir el desfase raro
	
		if (direccion) {
			
			if (gameBall.getTopY() == computerBar.getKc()) {
				
				int cl = computerBar.leftExtreme();
				int cr = computerBar.rightExtreme();
				int bl = gameBall.leftExtreme();
				int br = gameBall.rightExtreme();

				if (bl >= cl && bl <= cr) {
					rebotar();
				} else if (br >= cl && br <= cr) {
					rebotar();
				}
			}
			else if (gameBall.getTopY() > computerBar.getKc())
			{
				perdioSubiendo();
			}
		}
		
		if (!direccion) {
					
			if ( bolaInf == userBar.getBarY() ) {
			
				int ul = userBar.leftExtreme();
				int ur = userBar.rightExtreme();
				int bl = gameBall.leftExtreme();
				int br = gameBall.rightExtreme();
				if (bl >= ul && bl <= ur ) {
					rebotar();
				} else if (br >= ul && br <= ur) {
					rebotar();
				}	
			}
			else if ( bolaInf < userBar.getBarY() )
			{
				perdioBajando();
			}
		}
	}

	public void moverBola() {
		if (direccion) {
			gameBall.moveUp(velocidad);
		} else {
			gameBall.moveDown(velocidad);
		}
		
		if (movHorizontal) {
			gameBall.moveRight(velocidad);
		} else {
			gameBall.moveLeft(velocidad);
		}
	}

	public void moverBarraPC() {

	}

	public void rebotar() {
		
		if (direccion) {
			direccion = ABAJO;
		} else {
			direccion = ARRIBA;
		}
		
	}
	
	public void reboteHorizontal() {
		
		if (movHorizontal) {
			movHorizontal = IZQUIERDA;
		} else {
			movHorizontal = DERECHA;
		}
		
	}

	public void perdioSubiendo() {
		perdio = true;
		System.out.println("PERDIO SUBIENDO");
		userBar.info.pause();
		gameBall.info.pause();

	}
	
	public void perdioBajando() {
		perdio = true;
		System.out.println("PERDIO BAJANDO");
		userBar.info.pause();
		gameBall.info.pause();

	}

	public void gano() {

	}

}
