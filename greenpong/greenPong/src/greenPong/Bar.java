/** Es una de las barras del juego. Deberia crearse una barra inteligente y otra
 * gestionada con la información del InputManager que es la del jugador.
 * 
 */
package greenPong;

import java.awt.Dimension;

public class Bar extends PongGameObject {

	public static final int WIDTH = 127;

	public static final int HEIGHT = 34;
	private int finalYPosition;
	

	public Bar(String urlImage, Box ncontenedor) {
		super(urlImage, ncontenedor);
		url = urlImage;
		container = ncontenedor;
		Dimension dim = new Dimension();
		dim.setSize(WIDTH, HEIGHT);
		setPreferredSize(dim.getSize());
		setSize( this.getPreferredSize());
		finalYPosition = container.getHeight() - 71 ;//NPI PQ, PERO ES ASI
		centerInX();
	}
	
	public void setFinalYPosition(int y){
		finalYPosition = y;
	}
	
	public int getFinalYPosition(){
		return finalYPosition;
	}
	
	public void moveRight(int pixels){
		setLocation(this.getLocation().x+pixels, finalYPosition);
	}
	
	public void moveLeft(int pixels){
		setLocation(this.getLocation().x-pixels, finalYPosition);
	}
	
	public void centerInY(){
		//Evita que se pueda centrar una barra verticalmente;
	}
	
	public void center(){
		// Evita que se pueda centrar una barra verticalmente;
	}
	
	public int getKc(){
		int kc = Box.HEIGHT - this.getLocation().y - 25 - HEIGHT;
		return kc;
	}
	
	public void setX(int x){
		int der = Box.WIDTH - 15 - WIDTH; //5px de descuadre, por eso 15 y no 10
		if(x>=10 && x<=der){
			int y = getLocation().y;
			setLocation(x, y);
		}
	}
	
	// Métodos extremos y centro
	
	public int rightExtreme(){
		int rE = getCartesianX() + WIDTH;
		return rE;
	}
	
	public int leftExtreme(){
		int lE = getCartesianX();
		return lE;
	}
	
	public int getCenterInX(){
		int h = getCartesianX() + WIDTH/2;
		return h;
	}
	
	// Métodos parecidos a los de la clase PongGameObject
	
	public int getBarX(){
		return this.getLocation().x;
	}
	
	public int getBarY(){
		int r = Box.HEIGHT - this.getLocation().y - 25; // 25 Es el desfase vertical raro al dibujar la ventana
		return r;
	}
	
}
