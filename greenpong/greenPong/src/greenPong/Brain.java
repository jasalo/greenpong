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

	public int previousBallY = 0;

	final static boolean ARRIBA = true;

	final static boolean ABAJO = false;

	int XXX = 0;

	boolean direccion = ABAJO;

	InfoWindow info;

	boolean perdio = false;

	int velocidad = 1; // Pixeles del movimiento de la computerBar y la bola, por run
						//Si no es 1 da errores en los cálculos y no rebota la bola

	public Brain(String[] args) {
		super();
		info = new InfoWindow("Brain");
		info.setVisible(true);
		GameWindow caja = new GameWindow(args);
		contenedor = caja.windowBox;
		userBar = contenedor.userBar;
		computerBar = contenedor.computerBar;
		gameBall = contenedor.gameBall;
		InputManager entrada = new InputManager(userBar, contenedor.nivel);
		System.out.println("Agregando listener");
		contenedor.addMouseMotionListener(entrada);
		direccion = ABAJO;
		int infox = (int)contenedor.getLocation().getX()+ Box.ANCHO + 20;
		int infoy = (int)contenedor.getLocation().getY();
		info.setLocation(infox, infoy);
		info.setSize(400,500);

	}

	public void run() {
		userBar.setLocation(0, userBar.getFinalYPosition());
		computerBar.centerInX();
		userBar.centerInX();
		while (1 == 1) {
			try {
				sleep(Main.brainTime);
			} catch (InterruptedException e) {}
			
			if (XXX > 0) {
				evaluarNormas();
				moverBola();
				previousBallY = gameBall.getTopY();
			}
			else //Solo corre una vez
			{
				System.out.println("Primer ejecucion donde la bola va pa: " + direccion);
				previousBallY = gameBall.getTopY();
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
	}

	public void moverBarraPC() {

	}

	public void rebotar() {
		
		if (direccion) {
			direccion = ABAJO;
		} else if (!direccion){
			direccion = ARRIBA;
		}
		
	}

	public void perdioSubiendo() {
		perdio = true;
		info.info("PERDIO SUBIENDO");
		info.pause();
		userBar.info.pause();
		gameBall.info.pause();

	}
	
	public void perdioBajando() {
		perdio = true;
		info.info("PERDIO BAJANDO");
		info.pause();
		userBar.info.pause();
		gameBall.info.pause();

	}

	public void gano() {

	}

}
