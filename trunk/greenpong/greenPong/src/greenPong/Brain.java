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
	
	boolean movHorizontal = IZQUIERDA;
	
	// Atributos para el angulo de movimiento de la bola
	
	int angulo = 45;
	
	// Otros
	
	int XXX = 0;
	
	int der = Box.ANCHO - 15;
	GameWindow cj; //Caja cone ljuego
	boolean perdio = false;
	
	String[] args;
	int velocidad = 1; // Pixeles del movimiento de la computerBar y la bola, por run
						//Si no es 1 da errores en los cálculos y no rebota la bola

	public Brain(String[] args1) {
		super();
		args = args1;
		iniciarValores();
	}

	public void run() {
		userBar.setLocation(0, userBar.getFinalYPosition());
		computerBar.setLocation(250, 10);
		userBar.centerInX();
		while (perdio == false) {
			try {
				sleep(Main.brainTime);
			} catch (InterruptedException e) {}
			
			if (XXX!=0) {
				evaluarNormas();
				moverBola(angulo);
				moverBarraPC();
			}
			else
			{
				try {
					sleep(3000);
				} catch (InterruptedException e) {}
				moverBola(angulo);
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
		
		int bl = gameBall.leftExtreme();
		int br = gameBall.rightExtreme();
		int bc = gameBall.getH();
		
		if (direccion) {
			
			if (gameBall.getTopY() == computerBar.getKc()) {
				
				int cl = computerBar.leftExtreme();
				int cr = computerBar.rightExtreme();
				int cc = computerBar.getCenterInX();

				if ((bl >= cl && bl <= cr) || (br >= cl && br <= cr)) {
					rebound();
					if( (bl>(int)(1.5*(cl-Ball.ANCHO)) && br<(int)(1.5*(cl+Ball.ANCHO))) || (bl>(int)(1.5*(cr-Ball.ANCHO)) && br<(int)(1.5*(cr+Ball.ANCHO))) ) {
						angulo = 26;
					} else if( bc>=(cc-Ball.ANCHO) && bc<=(cc+Ball.ANCHO)){
						angulo = 64;
					}
					else {
						angulo = 45;
					}
				}
			}
			else if (gameBall.getTopY() > computerBar.getKc())
			{
				perdioSubiendo();
			}
		}
		
		if (!direccion) {
					
			if ( gameBall.getBottomY() == userBar.getBarY() ) {
			
				int ul = userBar.leftExtreme();
				int ur = userBar.rightExtreme();
				int uc = userBar.getCenterInX();
				
				if ((bl >= ul && bl <= ur ) || (br >= ul && br <= ur)) {
					rebound();
					if( (bl>(ul-Ball.ANCHO) && br<(ul+Ball.ANCHO)) || (bl>(ur-Ball.ANCHO) && br<(ur+Ball.ANCHO)) ) {
						angulo = 26;
					} else if( bc>=(uc-Ball.ANCHO) && bc<=(uc+Ball.ANCHO)){
						angulo = 64;
					}
					else {
						angulo = 45;
					}
				}	
			}
			else if ( gameBall.getBottomY() < userBar.getBarY() )
			{
				perdioBajando();
			}
		}
	}
	
	/**
	 * Metodo que maneja el movimiento de la bola, ingresado uno de los 3 angulos disponibles
	 * @param angulo Angulo que puede ser 26, 45 o 64 para manejar el rebote de la bola
	 */
	
	public void moverBola(int angulo)
	{
		// PA ANGULO DE "26"
		if (angulo==26) {
			if (direccion) {
				gameBall.moveUp(velocidad);
			} else if (!direccion){
				gameBall.moveDown(velocidad);
			}
			
			if (movHorizontal) {				
				if((gameBall.rightExtreme()+1)<der) {
					gameBall.moveRight(velocidad);
					gameBall.moveRight(velocidad);
				} else {
					gameBall.moveRight(velocidad);
				}
			} else if (!movHorizontal) {
				if((gameBall.leftExtreme()-1)>10) {
					gameBall.moveLeft(velocidad);
					gameBall.moveLeft(velocidad);
				} else {
					gameBall.moveLeft(velocidad);
				}
			}
		} else
		// PA ANGULO DE "64"
		if (angulo==64) {
			if (direccion) {
				gameBall.moveUp(velocidad);
			} else if (!direccion){
				gameBall.moveDown(velocidad);
			}
			
			if( (gameBall.getTopY()+1)<=computerBar.getKc() && (gameBall.getBottomY()-1)>=userBar.getBarY() ) {
				if (direccion) {
					gameBall.moveUp(velocidad);
				} else {
					gameBall.moveDown(velocidad);
				}
			}
			if (movHorizontal) {
				gameBall.moveRight(velocidad);
			} else {
				gameBall.moveLeft(velocidad);
			}
		} else
		// PA ANGULO DE 45
		if(angulo==45) {
			
			if (direccion) {
				gameBall.moveUp(velocidad);
			} else if (!direccion){
				gameBall.moveDown(velocidad);
			}
			
			if (movHorizontal) {
				gameBall.moveRight(velocidad);
			} else {
				gameBall.moveLeft(velocidad);
			}
		}
	}
		
	public void moverBarraPC() {
		if(gameBall.getLocation().y>=(int)(Box.ALTO/3) && direccion) {
			if(computerBar.leftExtreme()>=10 && computerBar.rightExtreme()<=der){
				if(movHorizontal && computerBar.rightExtreme()<der) {
					if(gameBall.rightExtreme()<=(int)(computerBar.leftExtreme()-Bar.ANCHO) && (computerBar.rightExtreme()+1)!=der) {
						computerBar.moveRight(2*velocidad);
					} else {
						computerBar.moveRight(velocidad);
					}
				} else if(!movHorizontal && computerBar.leftExtreme()>10) {
					if(gameBall.leftExtreme()>=(int)(computerBar.rightExtreme()+Bar.ANCHO) && (computerBar.leftExtreme()-1)!=10) {
						computerBar.moveLeft(2*velocidad);
					} else {
						computerBar.moveLeft(velocidad);
					}
				}
			}
		} else if (gameBall.getLocation().y<(int)(Box.ALTO/3) && direccion){
			if(computerBar.leftExtreme()>=10 && computerBar.rightExtreme()<=der){
				if(gameBall.rightExtreme()>=(int)(computerBar.leftExtreme()-Bar.ANCHO) && movHorizontal && (computerBar.leftExtreme()+1)!=10 && gameBall.rightExtreme()<=computerBar.getCenterInX()) {
					computerBar.moveLeft(2*velocidad);					
				} else if(gameBall.leftExtreme()<=(int)(computerBar.rightExtreme()+Bar.ANCHO) && !movHorizontal && (computerBar.rightExtreme()+1)!=der && gameBall.leftExtreme()>=computerBar.getCenterInX()) {
					computerBar.moveRight(2*velocidad);
				} else if(movHorizontal) {
					computerBar.moveRight(velocidad);
				} else if(!movHorizontal) {
					computerBar.moveLeft(velocidad);
				}
			}
		}
	}
	
	public void rebound() {
		
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
		new Dialogo("¡Ganaste! Se iniciará un nuevo juego", "¡Ganaste!");
		iniciarValores();
	}
	
	public void perdioBajando() {
		perdio = true;
		new Dialogo("Perdiste, se iniciará un nuevo juego", "¡Perdiste!");
		iniciarValores();
	}

	public void gano() {

	}
	
	

	public void iniciarValores(){
		perdio = false;
		cj = new GameWindow(args);
		contenedor = cj.windowBox;
		userBar = contenedor.userBar;
		computerBar = contenedor.computerBar;
		gameBall = contenedor.gameBall;
		InputManager entrada = new InputManager(userBar, contenedor.nivel);
		System.out.println("Agregando listener");
		contenedor.addMouseMotionListener(entrada);
		direccion = ABAJO;
		movHorizontal = IZQUIERDA;
		XXX = 0;
		angulo = 45;
		
	}
	
	

}
