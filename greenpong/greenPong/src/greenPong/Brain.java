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

	final static boolean ARRIBA = true;

	final static boolean ABAJO = false;

	int XXX = 0;

	boolean direccion;

	InfoWindow info;

	boolean perdio = false;

	int velocidad = 10; // Pixeles del movimiento de la comptuerBar y la bola
						// por run

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
		entrada.mouseInfo.setVisible(false);
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
		// computerBar.setX(10);
		userBar.centerInX();
		while (1 == 1) {
			try {
				sleep(Main.brainTime);
			} catch (InterruptedException e) {}
			
			if (gameBall.getCartesianY() - previousBallY > 0) {
				direccion = ARRIBA;
			} else {
				direccion = ABAJO;
			}
			if (XXX != 0) {
				moverBola();
				evaluarNormas();
				previousBallY = gameBall.getCartesianY();
			}else{ //Solo corre una vez
				System.out.println("Primer ejecucion");
				direccion = ABAJO;
				previousBallY = gameBall.getCartesianY();
				moverBola();
				XXX = 1;
				
			}
			

		}

	}

	/**
	 * Este metodo evalua normas del juego o cosas que se tienen que cumplir Si
	 * no se cumplen genera algo. Usa otro metodos.
	 * 
	 */
	public void evaluarNormas() {

		if (vaPaArriba()) {
			info.info("Se entro en el ciclo donde la borra vaPaArriba");
			if (gameBall.getCartesianY() >= kc()) {

				int cl = computerBar.leftExtreme();
				int cr = computerBar.rightExtreme();
				int bl = gameBall.leftExtreme();
				int br = gameBall.rightExtreme();

				if (bl >= cl && bl <= cr) {
					rebotar();
					info.info("Moviendo bola");
					moverBola();
				} else if (br >= cl && br <= cr) {
					rebotar();
					info.info("Moviendo bola");
					moverBola();
				} else {
					perdio();
				}

			}
		}

		if (!vaPaArriba()) {
			info.info("Se entro en el ciclo donde la borra vaPaAbajo");
			double bolaInf = gameBall.getLocation().getY()
					+ gameBall.getHeight();
			if (bolaInf >= userBar.getLocation().getY()) {
				int ul = userBar.leftExtreme();
				int ur = userBar.rightExtreme();
				int bl = gameBall.leftExtreme();
				int br = gameBall.rightExtreme();
				if (bl >= ul && bl <= ur) {
					rebotar();
					info.info("Moviendo bola");
					moverBola();
				} else if (br >= ul && br <= ur) {
					rebotar();
					info.info("Moviendo bola");
					moverBola();
				} else {
					perdio();

				}

			}
		}

	}

	public void moverBola() {
		if (direccion = ARRIBA) {
			info.info("movio paArriba");
			gameBall.moveUp(velocidad);
		} else {
			info.info("Movio paAbajo");
			gameBall.moveDown(velocidad);
		}

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
		info.info("La bola reboto. Iba para " + direccion);
		if (direccion == ARRIBA) {
			direccion = ABAJO;
		} else {
			direccion = ARRIBA;
		}

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

	public int kc() {
		// Retorna en cartesiano
		int kc = Box.ALTO - ((int) computerBar.getLocation().getY() + Bar.ALTO);
		if (vaPaArriba()) {
			info.info("kc=" + kc);
		}
		return kc;
	}

	public int ku() {
		// Retorna en cartesiano
		int ku = contenedor.getHeight() - ((int) userBar.getLocation().getY());
		if (!vaPaArriba()) {
			info.info("ku=" + ku);
		}
		return ku;
	}

}
