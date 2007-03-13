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
	
	public Brain(String[] args){
		super();
		GameWindow caja = new GameWindow(args);
		contenedor = caja.windowBox;
		userBar = contenedor.userBar;
		computerBar = contenedor.computerBar;
		gameBall = contenedor.gameBall;
		InputManager entrada = new InputManager(userBar, contenedor.nivel); //Se entrega la barra que se controlara
		System.out.println("Agregando listener");
		contenedor.addMouseMotionListener(entrada);
	}
	
	public void run() {
		userBar.setLocation(0, userBar.getFinalYPosition());
		
		computerBar.setX(0);
		userBar.setX(contenedor.getWidth()-150);
		//BUCLE INFINITO
		while (1==1){
			try{
				sleep(Main.brainTime);
			}catch(InterruptedException e){}
			/**
			 * Codigo a correr cada vez q se cumple el tiempo
			 */
			/*System.out.println("Entrando al ciclo " + count);
			userBar.moveLeft(1);
			computerBar.moveRight(1);
			count++;*/
		}
		
	}
	
	/**Este metodo evalua normas del juego o cosas que se tienen que cumplir
	 * Si no se cumplen genera algo. Usa otro metodos.
	 *
	 */
	public void evaluarNormas(){
		
	}
	
	public boolean huboColision(){
		return true;
	}
	
	public void moverBola(){
		
	}
	
	public void moverBarraPC(){
		
	}
	


}
