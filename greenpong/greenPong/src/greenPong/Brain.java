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

	Box container;

	Bar userBar, computerBar;

	Ball gameBall;
	
	// Constantes y atributos pa movimiento vertical

	final static boolean UP = true;

	final static boolean DOWN = false;

	boolean direction = DOWN;
	
	// Constantes y atributos pa movimiento horizontal
	
	final static boolean RIGHT = true;

	final static boolean LEFT = false;
	
	boolean movHorizontal = LEFT;
	
	// Atributos para el movimiento de la bola
	
	int angle = 45;

	int tempo = 0;
	
	int tempo2 = 0;
	
	// Otros
	
	boolean firstRun = true;
	
	int der = Box.WIDTH - 15;
	GameWindow gameWindow; //Caja cone ljuego
	boolean lost = false;
	
	String[] args;
	int speed = 1; // Pixeles del movimiento de la computerBar y la bola, por run
						//Si no es 1 da errores en los cálculos y no rebota la bola

	public Brain(String[] args1) {
		super();
		args = args1;
		initializeValues();
	}

	public void run() {
		userBar.setLocation(0, userBar.getFinalYPosition());
		userBar.centerInX();
		while (lost == false) {
			try {
				sleep(Main.brainTime);
			} catch (InterruptedException e) {}
			
			tempo2++;
			
			if(tempo2%80==0)
				gameWindow.increaseScore(5);
			
			if (firstRun!=true) {
				evaluateGameRules();
				moveBall(angle);
				moveComputerBar();
			}
			else
			{
				try {
					sleep(1500);
				} catch (InterruptedException e) {}
				moveBall(angle);
				firstRun = false;
			}
		}

	}

	/**
	 * Este metodo evalua normas del juego o cosas que se tienen que cumplir.
	 * Si no se cumplen genera algo. Usa otro metodos.
	 */
	public void evaluateGameRules() {
		
		// EVALUAR MOVIMIENTO HORIZONTAL
		
		if (movHorizontal && gameBall.rightExtreme()==der){
			horizontalBounce();
		}
		
		if (!movHorizontal && gameBall.leftExtreme()==10){
			horizontalBounce();
		}
		
		// EVALUAR MOVIMIENTO VERTICAL
		
		int bl = gameBall.leftExtreme();
		int br = gameBall.rightExtreme();
		int bc = gameBall.getH();
		
		if (direction) {
			
			if (gameBall.getTopY() == computerBar.getKc()) {
				
				int cl = computerBar.leftExtreme();
				int cr = computerBar.rightExtreme();
				int cc = computerBar.getCenterInX();

				if ((bl >= cl && bl <= cr) || (br >= cl && br <= cr)) {
					bounce();
					if( (bl>(int)(1.5*(cl-Ball.WIDTH)) && br<(int)(1.5*(cl+Ball.WIDTH))) || (bl>(int)(1.5*(cr-Ball.WIDTH)) && br<(int)(1.5*(cr+Ball.WIDTH))) ) {
						if(angle==64)
							angle = 45;
						else
							angle = 26;
					} else if( bc>=(cc-Ball.WIDTH) && bc<=(cc+Ball.WIDTH)){
						angle = 64;
					}
					else {
						angle = 45;
					}
				}
			}
			else if (gameBall.getTopY() > computerBar.getKc())
			{
				lostGoingUp();
			}
		}
		
		if (!direction) {
					
			if ( gameBall.getBottomY() == userBar.getBarY() ) {
			
				int ul = userBar.leftExtreme();
				int ur = userBar.rightExtreme();
				int uc = userBar.getCenterInX();
				
				if ((bl >= ul && bl <= ur ) || (br >= ul && br <= ur)) {
					bounce();
					if( (bl>(ul-Ball.WIDTH) && br<(ul+Ball.WIDTH)) || (bl>(ur-Ball.WIDTH) && br<(ur+Ball.WIDTH)) ) {
						if(angle==64)
							angle = 45;
						else
							angle = 26;
					} else if( bc>=(uc-Ball.WIDTH) && bc<=(uc+Ball.WIDTH)){
						angle = 64;
					}
					else {
						angle = 45;
					}
				}	
			}
			else if ( gameBall.getBottomY() < userBar.getBarY() )
			{
				lostGoingDown();
			}
		}
	}
	
	/**
	 * Metodo que maneja el movimiento de la bola, ingresado uno de los 3 angulos disponibles
	 * @param angulo Angulo que puede ser 26, 45 o 64 para manejar el rebote de la bola
	 */
	
	public void moveBall(int angulo)
	{
		// PA ANGULO DE "26"
		if (angulo==26) {
			if (tempo%2!=0) {
				
				if (direction) {
					gameBall.moveUp(speed);
				} else if (!direction){
					gameBall.moveDown(speed);
				}
			
				if (movHorizontal) {				
					if((gameBall.rightExtreme()+1)<der) {
						gameBall.moveRight(speed);
						gameBall.moveRight(speed);
					} else {
						gameBall.moveRight(speed);
					}
				} else if (!movHorizontal) {
					if((gameBall.leftExtreme()-1)>10) {
						gameBall.moveLeft(speed);
						gameBall.moveLeft(speed);
					} else {
						gameBall.moveLeft(speed);
					}
				}
				
			} else {
				
				if (movHorizontal) {				
					if((gameBall.rightExtreme()+1)<der) {
						gameBall.moveRight(speed);
					}
				} else if (!movHorizontal) {
					if((gameBall.leftExtreme()-1)>10) {
						gameBall.moveLeft(speed);
					}
				}
				
			}
			tempo++;
		} else
		
		// PA ANGULO DE "64"
		if (angulo==64) {
			if (tempo%2!=0) {
				
				if (direction) {
					gameBall.moveUp(speed);
				} else if (!direction){
					gameBall.moveDown(speed);
				}
			
				if( (gameBall.getTopY()+1)<=computerBar.getKc() && (gameBall.getBottomY()-1)>=userBar.getBarY() ) {
					if (direction) {
						gameBall.moveUp(speed);
					} else {
						gameBall.moveDown(speed);
					}
				}
				if (movHorizontal) {
					gameBall.moveRight(speed);
				} else {
					gameBall.moveLeft(speed);
				}
				
			} else {
				
				if( (gameBall.getTopY()+1)<=computerBar.getKc() && (gameBall.getBottomY()-1)>=userBar.getBarY() ) {
					if (direction) {
						gameBall.moveUp(speed);
					} else {
						gameBall.moveDown(speed);
					}
				}
				
			}
			tempo++;
		} else
			
		// PA ANGULO DE 45
		if(angulo==45) {
			
			if (direction) {
				gameBall.moveUp(speed);
			} else if (!direction){
				gameBall.moveDown(speed);
			}
			
			if (movHorizontal) {
				gameBall.moveRight(speed);
			} else {
				gameBall.moveLeft(speed);
			}
		}
	}
		
	public void moveComputerBar() {
		if(gameBall.getLocation().y>=(int)(Box.HEIGHT/3)) {
			if(computerBar.leftExtreme()>=10 && computerBar.rightExtreme()<=der){
				if(movHorizontal && computerBar.rightExtreme()<der) {
					if(gameBall.rightExtreme()<=(int)(computerBar.leftExtreme()-Bar.WIDTH) && (computerBar.rightExtreme()+1)<der) {
						computerBar.moveRight(2*speed);
					} else {
						computerBar.moveRight(speed);
					}
				} else if(!movHorizontal && computerBar.leftExtreme()>10) {
					if(gameBall.leftExtreme()>=(int)(computerBar.rightExtreme()+Bar.WIDTH) && (computerBar.leftExtreme()-1)>10) {
						computerBar.moveLeft(2*speed);
					} else {
						computerBar.moveLeft(speed);
					}
				}
			}
		} else if ( (gameBall.getLocation().y<(int)(Box.HEIGHT/3)) && (gameBall.getLocation().y>(computerBar.getKc() + Bar.HEIGHT)) ){
			if(computerBar.leftExtreme()>=10 && computerBar.rightExtreme()<der){
				if(gameBall.rightExtreme()>=(int)(computerBar.leftExtreme()-Bar.WIDTH) && movHorizontal && (computerBar.leftExtreme()-1)>10 && gameBall.rightExtreme()<=computerBar.getCenterInX()) {
					computerBar.moveLeft(2*speed);					
				} else if(gameBall.leftExtreme()<=(int)(computerBar.rightExtreme()+Bar.WIDTH) && !movHorizontal && (computerBar.rightExtreme()+1)<der && gameBall.leftExtreme()>=computerBar.getCenterInX()) {
					computerBar.moveRight(2*speed);
				} else if(movHorizontal && (computerBar.rightExtreme()+1)<der) {
					computerBar.moveRight(speed);
				} else if(!movHorizontal && (computerBar.leftExtreme()-1)>10) {
					computerBar.moveLeft(speed);
				}
			}
		} else if (gameBall.getLocation().y<(computerBar.getKc() + Bar.HEIGHT)){
			if(computerBar.leftExtreme()>=10 && computerBar.rightExtreme()<=der){
				int bl = gameBall.leftExtreme();
				int br = gameBall.rightExtreme();
				int cl = computerBar.leftExtreme();
				int cr = computerBar.rightExtreme();
				if ( bl >= cl && br <= cr ) {
					if (movHorizontal && cr<der)
						computerBar.moveRight(speed);
					if (!movHorizontal && cl>10)
						computerBar.moveLeft(speed);
				} else {
					if (movHorizontal && gameBall.leftExtreme()<computerBar.leftExtreme())
						computerBar.moveLeft(2*speed);
					if (!movHorizontal && gameBall.rightExtreme()>computerBar.rightExtreme())
						computerBar.moveRight(2*speed);
				}
			}
		}
	}
	
	public void bounce() {
		
		if (direction) {
			direction = DOWN;
		} else {
			direction = UP;
		}
		
	}
	
	public void horizontalBounce() {
		
		if (movHorizontal) {
			movHorizontal = LEFT;
		} else {
			movHorizontal = RIGHT;
		}
		
	}

	public void lostGoingUp() {
		gameWindow.lifeUp();
		lost = true;
		javax.swing.JOptionPane.showMessageDialog(container, "Ganaste, se iniciara un nuevo juego", "Ganaste", javax.swing.JOptionPane.INFORMATION_MESSAGE);
		restart();
	}
	
	public void lostGoingDown() {
		gameWindow.lifeDown();
		lost = true;
		javax.swing.JOptionPane.showMessageDialog(container, "Perdiste, se iniciara un nuevo juego", "Perdiste", javax.swing.JOptionPane.ERROR_MESSAGE);
		restart();
	}
	
	

	public void initializeValues(){
		lost = false;
		gameWindow = new GameWindow(args);
		container = gameWindow.windowBox;
		userBar = container.userBar;
		computerBar = container.computerBar;
		gameBall = container.gameBall;
		InputManager entrada = new InputManager(userBar);
		container.addMouseMotionListener(entrada);
		direction = DOWN;
		movHorizontal = LEFT;
		firstRun = true;
		
		
	}
	
	public void restart(){
		this.interrupt() ;
		gameBall.center();
		computerBar.centerInX();
		userBar.centerInX();
		lost = false;
		direction = DOWN;
		movHorizontal = LEFT;
		firstRun = true;
		this.resume();
}
	
	

}
