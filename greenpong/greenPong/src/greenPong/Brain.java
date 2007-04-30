/**
 * Este es el centro del programa, gestiona colisiones y realiza cualquier calculo
 * matemático. Mueve todas las tres partes del programa recibiendo constantemente
 * coordenadas de la clase InputManager y así puede unirse al VTS sin modificaciones
 */
package greenPong;

import javax.swing.JOptionPane;

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
	 * The server that connects to the VTS
	 */
	Server server;

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
	boolean IS_GOING_UP = false;
	
	/**
	 * Attribute to indicate if the ball is going to the right
	 */
	boolean IS_GOING_RIGHT = false;
	
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
	 * Constant to indicate the right limit for the objects to move
	 */
	static final int leftLimit = 10;
	
	/**
	 * Constant to indicate the right limit for the objects to move
	 */
	static final int rightLimit = Box.WIDTH - 15;
	
	/**
	 * Attribute that indicates if player has lost a game
	 */
	boolean lost = false;
	
	/**
	 * String array for console given commands
	 */
	String[] args;
	
	/**
	 * Pixels per run for gameBall and computerBar
	 */	
	int speed = 1;

	// ----------------------
	// CONSTRUCTOR
	// ----------------------
	
	/**
	 * Constructor method for class Brain, calls intializeValues() method
	 * @param args1 Array of console given commands
	 */
	public Brain(String[] args1) {
		super();
		args = args1;
		initializeValues();
	}

	// ----------------------
	// METHODS
	// ----------------------
	
	/**
	 * Method to run the game:<br>
	 * <li>Evaluates game rules<br>
	 * <li>Moves the ball and the computer's bar<br>
	 * On first run sets an initial user's bar location and waits 1500ms for the game to start
	 */
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
	 * This method evaluates the game rules
	 */
	public void evaluateGameRules() {
		
		// EVALUATES HORIZONTAL MOEVEMENT
		if (IS_GOING_RIGHT && gameBall.rightExtreme()==rightLimit){
			horizontalBounce();
		}
		
		if (!IS_GOING_RIGHT && gameBall.leftExtreme()==leftLimit){
			horizontalBounce();
		}
		
		// EVALUATES VERTICAL MOVEMENT
		
		int bl = gameBall.leftExtreme();
		int br = gameBall.rightExtreme();
		int bc = gameBall.getH();
		
		if (IS_GOING_UP) {
			
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
						if(angle==26)
							angle = 45;
						else
							angle = 64;
					}
					else {
						angle = 45;
					}
				}
			}
			else if (gameBall.getTopY() > computerBar.getKc())
			{
				won();
			}
		}
		
		if (!IS_GOING_UP) {
					
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
						if(angle==26)
							angle = 45;
						else
							angle = 64;
					}
					else {
						angle = 45;
					}
				}	
			}
			else if ( gameBall.getBottomY() < userBar.getBarY() )
			{
				lost();
			}
		}
	}
	
	/**
	 * Method that manages the ball movement, using one of three avilable angles (26, 45 or 64)
	 * @param angulo Angulo que puede ser 26, 45 o 64 para manejar el rebote de la bola
	 */
	
	public void moveBall(int angulo)
	{
		// 26 approx. degrees angle
		if (angulo==26) {
			if (tempo%2!=0) {
				
				if (IS_GOING_UP) {
					gameBall.moveUp(speed);
				} else if (!IS_GOING_UP){
					gameBall.moveDown(speed);
				}
			
				if (IS_GOING_RIGHT) {				
					if((gameBall.rightExtreme()+1)<rightLimit) {
						gameBall.moveRight(speed);
						gameBall.moveRight(speed);
					} else {
						gameBall.moveRight(speed);
					}
				} else if (!IS_GOING_RIGHT) {
					if((gameBall.leftExtreme()-1)>leftLimit) {
						gameBall.moveLeft(speed);
						gameBall.moveLeft(speed);
					} else {
						gameBall.moveLeft(speed);
					}
				}
				
			} else {
				
				if (IS_GOING_RIGHT) {				
					if((gameBall.rightExtreme()+1)<rightLimit) {
						gameBall.moveRight(speed);
					}
				} else if (!IS_GOING_RIGHT) {
					if((gameBall.leftExtreme()-1)>leftLimit) {
						gameBall.moveLeft(speed);
					}
				}
				
			}
			tempo++;
		} else		
		// 64 approx. degrees angle
		if (angulo==64) {
			if (tempo%2!=0) {
				
				if (IS_GOING_UP) {
					gameBall.moveUp(speed);
				} else if (!IS_GOING_UP){
					gameBall.moveDown(speed);
				}
			
				if( (gameBall.getTopY()+1)<=computerBar.getKc() && (gameBall.getBottomY()-1)>=userBar.getBarY() ) {
					if (IS_GOING_UP) {
						gameBall.moveUp(speed);
					} else {
						gameBall.moveDown(speed);
					}
				}
				if (IS_GOING_RIGHT) {
					gameBall.moveRight(speed);
				} else {
					gameBall.moveLeft(speed);
				}
				
			} else {
				
				if( (gameBall.getTopY()+1)<=computerBar.getKc() && (gameBall.getBottomY()-1)>=userBar.getBarY() ) {
					if (IS_GOING_UP) {
						gameBall.moveUp(speed);
					} else {
						gameBall.moveDown(speed);
					}
				}
				
			}
			tempo++;
		} else		
		// 45 degree angle
		if(angulo==45) {
			
			if (IS_GOING_UP) {
				gameBall.moveUp(speed);
			} else if (!IS_GOING_UP){
				gameBall.moveDown(speed);
			}
			
			if (IS_GOING_RIGHT) {
				gameBall.moveRight(speed);
			} else {
				gameBall.moveLeft(speed);
			}
		}
	}
	
	/**
	 * Algorithms for moving the computer bar
	 */
	public void moveComputerBar() {
		if(gameBall.getLocation().y>=(int)(Box.HEIGHT/3)) {
			if(computerBar.leftExtreme()>=leftLimit && computerBar.rightExtreme()<=rightLimit){
				if(IS_GOING_RIGHT && computerBar.rightExtreme()<rightLimit) {
					if(gameBall.rightExtreme()<=(int)(computerBar.leftExtreme()-Bar.WIDTH) && (computerBar.rightExtreme()+1)<rightLimit) {
						computerBar.moveRight(2*speed);
					} else {
						computerBar.moveRight(speed);
					}
				} else if(!IS_GOING_RIGHT && computerBar.leftExtreme()>leftLimit) {
					if(gameBall.leftExtreme()>=(int)(computerBar.rightExtreme()+Bar.WIDTH) && (computerBar.leftExtreme()-1)>leftLimit) {
						computerBar.moveLeft(2*speed);
					} else {
						computerBar.moveLeft(speed);
					}
				}
			}
		} else if ( (gameBall.getLocation().y<(int)(Box.HEIGHT/3)) && (gameBall.getLocation().y>(computerBar.getKc() + Bar.HEIGHT)) ){
			if(computerBar.leftExtreme()>=leftLimit && computerBar.rightExtreme()<rightLimit){
				if(gameBall.rightExtreme()>=(int)(computerBar.leftExtreme()-Bar.WIDTH) && IS_GOING_RIGHT && (computerBar.leftExtreme()-1)>leftLimit && gameBall.rightExtreme()<=computerBar.getCenterInX()) {
					computerBar.moveLeft(2*speed);					
				} else if(gameBall.leftExtreme()<=(int)(computerBar.rightExtreme()+Bar.WIDTH) && !IS_GOING_RIGHT && (computerBar.rightExtreme()+1)<rightLimit && gameBall.leftExtreme()>=computerBar.getCenterInX()) {
					computerBar.moveRight(2*speed);
				} else if(IS_GOING_RIGHT && (computerBar.rightExtreme()+1)<rightLimit) {
					computerBar.moveRight(speed);
				} else if(!IS_GOING_RIGHT && (computerBar.leftExtreme()-1)>leftLimit) {
					computerBar.moveLeft(speed);
				}
			}
		} else if (gameBall.getLocation().y<(computerBar.getKc() + Bar.HEIGHT)){
			if(computerBar.leftExtreme()>=leftLimit && computerBar.rightExtreme()<=rightLimit){
				int bl = gameBall.leftExtreme();
				int br = gameBall.rightExtreme();
				int cl = computerBar.leftExtreme();
				int cr = computerBar.rightExtreme();
				if ( bl >= cl && br <= cr ) {
					if (IS_GOING_RIGHT && cr<rightLimit)
						computerBar.moveRight(speed);
					if (!IS_GOING_RIGHT && cl>leftLimit)
						computerBar.moveLeft(speed);
				} else {
					if (IS_GOING_RIGHT && gameBall.leftExtreme()<computerBar.leftExtreme())
						computerBar.moveLeft(2*speed);
					if (!IS_GOING_RIGHT && gameBall.rightExtreme()>computerBar.rightExtreme())
						computerBar.moveRight(2*speed);
				}
			}
		}
	}
	
	/**
	 * Method called if the ball bounces against any bar
	 */	
	public void bounce() {
		
		if (IS_GOING_UP) {
			IS_GOING_UP = false;
		} else {
			IS_GOING_UP = true;
		}
	}
	
	/**
	 * Method called if the ball bounces against a left or right limit
	 */	
	public void horizontalBounce() {
		
		if (IS_GOING_RIGHT) {
			IS_GOING_RIGHT = false;
		} else {
			IS_GOING_RIGHT = true;
		}
		
	}

	public void won() {
		gameWindow.lifeUp();
		lost = true;
		javax.swing.JOptionPane.showMessageDialog(container, "Ganaste, se iniciara un nuevo juego", "Ganaste", javax.swing.JOptionPane.INFORMATION_MESSAGE);
		restart();
	}
	
	public void lost() {
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
		
				
		IS_GOING_UP = false;
		IS_GOING_RIGHT = false;
		firstRun = true;
		/**This block of code defines whether the game will be controlled by a VTS
		 * or by a Mouse
		 */
		int runWithVTS = JOptionPane.showConfirmDialog(new javax.swing.JLabel(),"You can play this game with your mouse or using a VTS-1.0.\nDo you like to play with a VTS? Press no to use your mouse.", "Game mode selection", JOptionPane.YES_NO_OPTION);
		if(runWithVTS == JOptionPane.YES_OPTION){
			this.interrupt();
			server = new Server(userBar, this);
		}else{
			InputManager entrada = new InputManager(userBar);
			container.addMouseMotionListener(entrada);
		}
		
		
	}
	
	public void restart(){
		this.interrupt();
		gameBall.center();
		computerBar.centerInX();
		userBar.centerInX();
		lost = false;
		IS_GOING_UP = false;
		IS_GOING_RIGHT = false;
		firstRun = true;
		this.resume();
}
	
	

}
