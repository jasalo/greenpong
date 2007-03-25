/**
 * Este es el centro del programa, gestioa colisiones y realiza cualquier calculo
 * matemático. Mueve todas las tres partes del programa recibiendo constantemente
 * coordenadas de la clase InputManager y así puede unirse al VTS sin modificaciones
 */
package greenPong;

import java.awt.event.MouseEvent;

/**
 * @author ubuntu
 * 
 */
public class Brain extends Thread {

	Box contenedor;
	Bar userBar, computerBar;
	Ball gameBall;
	public int previousBallY = 0;
	final static int ARRIBA = 1;
	final static int ABAJO = -1;
	int XXX = 0; 
	int direccion;
	InfoWindow info;
	boolean perdio = false;

	public Brain(String[] args) {
		super();
		info = new InfoWindow("Brain");
		GameWindow caja = new GameWindow(args);
		contenedor = caja.windowBox;
		userBar = contenedor.userBar;
		computerBar = contenedor.computerBar;
		gameBall = contenedor.gameBall;
		computerBar.info.setVisible(false);
		InputManager entrada = new InputManager(userBar, contenedor.nivel); 
		System.out.println("Agregando listener");
		contenedor.addMouseMotionListener(entrada);
	}

	public void run() {
		userBar.setLocation(0, userBar.getFinalYPosition());
		computerBar.centerInX();
		computerBar.setX(10);
		userBar.centerInX();
		while (1 == 1) {
			try {
				sleep(Main.brainTime);
			} catch (InterruptedException e) {			}
			if (gameBall.getCartesianY() - previousBallY > 0) {
				direccion = ARRIBA;
			} else {
				direccion = ABAJO;
			}
			System.out.println("Delta:"
					+ (gameBall.getCartesianY() - previousBallY) + " = "
					+ gameBall.getCartesianY() + "-" + previousBallY);
			previousBallY = gameBall.getCartesianY();
			if (XXX != 0) {
				evaluarNormas();
			}
			XXX = 1;
			if(perdio == false){
			gameBall.moveDown(12);}
			System.out.println("Va para arriba? " + vaPaArriba());

			System.out.println("---------------------");
		}

	}

	/**
	 * Este metodo evalua normas del juego o cosas que se tienen que cumplir Si
	 * no se cumplen genera algo. Usa otro metodos.
	 * 
	 */
	public void evaluarNormas() {

		if (vaPaArriba()) {

			if (gameBall.getCartesianY() >= kc()) {

				int cl = computerBar.leftExtreme();
				int cr = computerBar.rightExtreme();
				int bl = gameBall.leftExtreme();
				int br = gameBall.rightExtreme();

				if (bl >= cl && bl <= cr) {

				} else if (br >= cl && br <= cr) {

				} else {
					perdio();
				}

			}
		}

		if (!vaPaArriba()) {
			int cartBolaInf = gameBall.getCartesianY() - Ball.ALTO; 
			info.info("CartBolaInf:" + (gameBall.getCartesianY() - Ball.ALTO));
			info.info("Es cartBol < ku? " + (cartBolaInf < ku()));
			if (cartBolaInf <= ku()) {
				int ul = userBar.leftExtreme();
				int ur = userBar.rightExtreme();
				int bl = gameBall.leftExtreme();
				int br = gameBall.rightExtreme();
				if (bl >= ul && bl <= ur) {

				} else if (br >= ul && br <= ur) {

				} else {
					perdio();
					
				}

			}
		}

	}

	public boolean huboColision() {
		return true;
	}

	public void moverBola() {

	}

	public void moverBarraPC() {

	}

	public boolean vaPaArriba() {
		if (direccion == ARRIBA) {
			return true;
		} else {
			return false;
		}
	}

	public void rebotar() {

	}

	public void perdio() {
		perdio = true;
		info.info("PERDIO");
		info.pause();
		userBar.info.pause();
		gameBall.info.pause();

	}

	public void gano() {

	}
	
	public int kc(){
		//Retorna en cartesiano
		int kc = Box.ALTO - ((int)computerBar.getLocation().getY() + Bar.ALTO);
		if(vaPaArriba()){info.info("kc=" + kc);}
		return kc;
	}
	
	public int ku(){
		//Retorna en cartesiano
		int ku = Box.ALTO - ((int)userBar.getLocation().getY());
		if(!vaPaArriba()){info.info("ku=" + ku);}
		return ku;
	}

}
