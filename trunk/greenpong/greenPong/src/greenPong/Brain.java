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

	/**
	 * The container of the game where objects are shown
	 */
	Box container;

	/**
	 * The user's bar 
	 */
	Bar userBar;
	
	/**
	 * The computer's bar
	 */
	Bar computerBar;

	/**
	 * The ball in the game 
	 */
	Ball gameBall;
	
	/**
	 * The window for the game application 
	 */
	GameWindow gameWindow;
	
	/**
	 * Attribute to indicate if the ball is going up
	 */
	boolean ISGOINGUP = false;
	
	/**
	 * Attribute to indicate if the ball is going to the right
	 */
	boolean ISGOINGRIGHT = false;
	
	/**
	 * Attribute to indicate de direction angle fo the gameBall
	 */

	int angle = 45;
	
	/**
	 * Attribute to manage the simulation for the movement of the gameBall in an indicated angle
	 */
	int tempo = 0;
	
	/**
	 * Attribute to manage the points
	 */
	int tempo2 = 0;
	
	/**
	 * Attribute that indicates if it is the first movement fo the gameBall in the game
	 */
	boolean firstRun = true;
	
	/**
	 * Attribute to indicate the right limit for the objects to move
	 */
	int right = Box.WIDTH - 15;
	
	/**
	 * Attribute that indicates if player has lost a game
	 */
	boolean lost = false;
	
	String[] args;
	int speed = 1; // Pixeles del movimiento de la computerBar y la bola, por run
						//Si no es 1 da errores en los c�lculos y no rebota la bola

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
		
		if (ISGOINGRIGHT && gameBall.rightExtreme()==right){
			horizontalBounce();
		}
		
		if (!ISGOINGRIGHT && gameBall.leftExtreme()==10){
			horizontalBounce();
		}
		
		// EVALUAR MOVIMIENTO VERTICAL
		
		int bl = gameBall.leftExtreme();
		int br = gameBall.rightExtreme();
		int bc = gameBall.getH();
		
		if (ISGOINGUP) {
			
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
		
		if (!ISGOINGUP) {
					
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
				
				if (ISGOINGUP) {
					gameBall.moveUp(speed);
				} else if (!ISGOINGUP){
					gameBall.moveDown(speed);
				}
			
				if (ISGOINGRIGHT) {				
					if((gameBall.rightExtreme()+1)<right) {
						gameBall.moveRight(speed);
						gameBall.moveRight(speed);
					} else {
						gameBall.moveRight(speed);
					}
				} else if (!ISGOINGRIGHT) {
					if((gameBall.leftExtreme()-1)>10) {
						gameBall.moveLeft(speed);
						gameBall.moveLeft(speed);
					} else {
						gameBall.moveLeft(speed);
					}
				}
				
			} else {
				
				if (ISGOINGRIGHT) {				
					if((gameBall.rightExtreme()+1)<right) {
						gameBall.moveRight(speed);
					}
				} else if (!ISGOINGRIGHT) {
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
				
				if (ISGOINGUP) {
					gameBall.moveUp(speed);
				} else if (!ISGOINGUP){
					gameBall.moveDown(speed);
				}
			
				if( (gameBall.getTopY()+1)<=computerBar.getKc() && (gameBall.getBottomY()-1)>=userBar.getBarY() ) {
					if (ISGOINGUP) {
						gameBall.moveUp(speed);
					} else {
						gameBall.moveDown(speed);
					}
				}
				if (ISGOINGRIGHT) {
					gameBall.moveRight(speed);
				} else {
					gameBall.moveLeft(speed);
				}
				
			} else {
				
				if( (gameBall.getTopY()+1)<=computerBar.getKc() && (gameBall.getBottomY()-1)>=userBar.getBarY() ) {
					if (ISGOINGUP) {
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
			
			if (ISGOINGUP) {
				gameBall.moveUp(speed);
			} else if (!ISGOINGUP){
				gameBall.moveDown(speed);
			}
			
			if (ISGOINGRIGHT) {
				gameBall.moveRight(speed);
			} else {
				gameBall.moveLeft(speed);
			}
		}
	}
		
	public void moveComputerBar() {
		if(gameBall.getLocation().y>=(int)(Box.HEIGHT/3)) {
			if(computerBar.leftExtreme()>=10 && computerBar.rightExtreme()<=right){
				if(ISGOINGRIGHT && computerBar.rightExtreme()<right) {
					if(gameBall.rightExtreme()<=(int)(computerBar.leftExtreme()-Bar.WIDTH) && (computerBar.rightExtreme()+1)<right) {
						computerBar.moveRight(2*speed);
					} else {
						computerBar.moveRight(speed);
					}
				} else if(!ISGOINGRIGHT && computerBar.leftExtreme()>10) {
					if(gameBall.leftExtreme()>=(int)(computerBar.rightExtreme()+Bar.WIDTH) && (computerBar.leftExtreme()-1)>10) {
						computerBar.moveLeft(2*speed);
					} else {
						computerBar.moveLeft(speed);
					}
				}
			}
		} else if ( (gameBall.getLocation().y<(int)(Box.HEIGHT/3)) && (gameBall.getLocation().y>(computerBar.getKc() + Bar.HEIGHT)) ){
			if(computerBar.leftExtreme()>=10 && computerBar.rightExtreme()<right){
				if(gameBall.rightExtreme()>=(int)(computerBar.leftExtreme()-Bar.WIDTH) && ISGOINGRIGHT && (computerBar.leftExtreme()-1)>10 && gameBall.rightExtreme()<=computerBar.getCenterInX()) {
					computerBar.moveLeft(2*speed);					
				} else if(gameBall.leftExtreme()<=(int)(computerBar.rightExtreme()+Bar.WIDTH) && !ISGOINGRIGHT && (computerBar.rightExtreme()+1)<right && gameBall.leftExtreme()>=computerBar.getCenterInX()) {
					computerBar.moveRight(2*speed);
				} else if(ISGOINGRIGHT && (computerBar.rightExtreme()+1)<right) {
					computerBar.moveRight(speed);
				} else if(!ISGOINGRIGHT && (computerBar.leftExtreme()-1)>10) {
					computerBar.moveLeft(speed);
				}
			}
		} else if (gameBall.getLocation().y<(computerBar.getKc() + Bar.HEIGHT)){
			if(computerBar.leftExtreme()>=10 && computerBar.rightExtreme()<=right){
				int bl = gameBall.leftExtreme();
				int br = gameBall.rightExtreme();
				int cl = computerBar.leftExtreme();
				int cr = computerBar.rightExtreme();
				if ( bl >= cl && br <= cr ) {
					if (ISGOINGRIGHT && cr<right)
						computerBar.moveRight(speed);
					if (!ISGOINGRIGHT && cl>10)
						computerBar.moveLeft(speed);
				} else {
					if (ISGOINGRIGHT && gameBall.leftExtreme()<computerBar.leftExtreme())
						computerBar.moveLeft(2*speed);
					if (!ISGOINGRIGHT && gameBall.rightExtreme()>computerBar.rightExtreme())
						computerBar.moveRight(2*speed);
				}
			}
		}
	}
	
	public void bounce() {
		
		if (ISGOINGUP) {
			ISGOINGUP = false;
		} else {
			ISGOINGUP = true;
		}
		
	}
	
	public void horizontalBounce() {
		
		if (ISGOINGRIGHT) {
			ISGOINGRIGHT = false;
		} else {
			ISGOINGRIGHT = true;
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
		ISGOINGUP = false;
		ISGOINGRIGHT = false;
		firstRun = true;
		
		
	}
	
	public void restart(){
		this.interrupt() ;
		gameBall.center();
		computerBar.centerInX();
		userBar.centerInX();
		lost = false;
		ISGOINGUP = false;
		ISGOINGRIGHT = false;
		firstRun = true;
		this.resume();
}
	
	

}
