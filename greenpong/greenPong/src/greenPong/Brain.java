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
	int XXX=0; //Funcion para que en el primer run del thread no evalue normas
	int direccion;
	
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
		userBar.setX(300);
		
		boolean contact = false;
		
		//BUCLE INFINITO
		while (1==1){
			try{
				sleep(Main.brainTime);
			}catch(InterruptedException e){}
			/**
			 * Codigo a correr cada vez q se cumple el tiempo
			 */
			if(gameBall.getCartesianY()-previousBallY > 0){
				direccion= ARRIBA;
			}else{
				direccion =  ABAJO;
			}
			System.out.println("Delta:" + (gameBall.getCartesianY()-previousBallY) + " = " + gameBall.getCartesianY() + "-" + previousBallY );
			previousBallY = gameBall.getCartesianY();
			if(XXX!=0){
				evaluarNormas();
			}
			XXX=1; //Hace que siempre se corra el bucle anterior menos en el 1er run
			/*System.out.println("Entrando al ciclo " + count);
			userBar.moveLeft(1);
			computerBar.moveRight(1);
			count++;*/
			// hace que se mueva la bola al ppio del juego
			//if(gameBall.locateInY()+17 < userBar.getFinalYPosition()-17)
			//{
				gameBall.moveDown(1);
				System.out.println("Va para arriba? "+ vaPaArriba());
			//	if (gameBall.locateInY()+17 == userBar.getFinalYPosition()-17)
			//	{
			//		contact = true;
			//	}
			//} else if (contact){
			//	gameBall.moveUp(2);
			//}
			System.out.println("---------------------");
		}
		
	}
	
	/**Este metodo evalua normas del juego o cosas que se tienen que cumplir
	 * Si no se cumplen genera algo. Usa otro metodos.
	 *
	 */
	public void evaluarNormas(){
		//Las normas que se evaluan cuando la bola vaPaArriba
		//Esto es un if NI EL HP, no diga
		if(vaPaArriba()){
			//El tope de la bola >= la linea que pasa por el borde ing de la computerBar
			if(gameBall.getCartesianY() >= computerBar.getKc()){
				//Hay que ver si alguno de los dos extremos de la gameBall esta entre
				//el intervalo cerrado de los exts de la computerBar
				//Aquí es donde empieza un if ni el hijueputa
				int cl = computerBar.leftExtreme();
				int cr = computerBar.rightExtreme();
				int bl = gameBall.leftExtreme();
				int br = gameBall.rightExtreme();
				//Si se cumple algunas de estas condiciones entonces hubo colision
				if(bl>=cl && bl<=cr){
					
				}else if(br>=cl && br<=cr){
					
				}else{
					System.out.println("Va pa arriba, \n tope:" + gameBall.getCartesianY() + " \nkc="+  computerBar.getKc() );
					perdio();
				}
				
			}
		}
		
		//Normas a evaluar cuando la bola va para abajo
		if(!vaPaArriba()){
			//El tope bajo de la bola >= la linea que pasa por el borde sup dl userBar
			if(gameBall.getCartesianY() - gameBall.ALTO <= computerBar.getKu()){
				//Hay que ver si alguno de los dos extremos de la gameBall esta entre
				//el intervalo cerrado de los exts de la computerBar
				//Aquí es donde empieza un if ni el hijueputa
				int ul = userBar.leftExtreme();
				int ur = userBar.rightExtreme();
				int bl = gameBall.leftExtreme();
				int br = gameBall.rightExtreme();
				//Si se cumple algunas de estas condiciones entonces hubo colision
				if(bl>=ul && bl<=ur){
					
				}else if(br>=ul && br<=ur){
					
				}else{
					perdio();
					System.out.println("Iba pa abajo");
				}
				
			}
		}
		
		
	}
	
	public boolean huboColision(){
		return true;
	}
	
	public void moverBola(){
		
	}
	
	public void moverBarraPC(){
		
	}
	
	public boolean vaPaArriba(){
		if(direccion == ARRIBA){
			return true;
		}else{
			return false;
		}		
	}
	
	/*public int direccionMovBola(){
		System.out.println("Delta:" + (gameBall.getCartesianY()-previousBallY) + " = " + gameBall.getCartesianY() + "-" + previousBallY );
		if(gameBall.getCartesianY()-previousBallY > 0){
			return ARRIBA;
		}else{
			return ABAJO;
		}
	}*/
	
	public void rebotar(){
		
	}
	
	public void perdio(){
		System.out.println("MUCHO AMOTRO! PERDIO");
		//System.exit(1);
		
	}
	
	public void gano(){
		
	}
	

}
