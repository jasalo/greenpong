/** Esta clase es la bola, el cerebro debe ser el encargado de moverla porque es
 * quien recibe todos los datos sobre posiciones y colisiones. La bola solo deberia
 * poseer lo necesario para presentarse adecuadamente y proveer a la otras clases
 * de los datos que resulten importantes.
 * 
 */
package greenPong;

import java.awt.Dimension;

public class Ball extends PongGameObject {

	public static final int WIDTH = 28;

	public static final int HEIGHT = 28;

	public Ball(String urlImage, Box ncontenedor) {
		super(urlImage, ncontenedor);
		url = urlImage;
		container = ncontenedor;
		Dimension dim = new Dimension();
		dim.setSize(WIDTH, HEIGHT);
		setPreferredSize(dim.getSize());
		setSize( this.getPreferredSize());
	}
	
	public void moveRight(int pixels){
		setLocation(this.getLocation().x+pixels, this.getLocation().y);
	}
	
	public void moveLeft(int pixels){
		setLocation(this.getLocation().x-pixels, this.getLocation().y);
	}
	
	public void moveDown(int pixels){
		setLocation(this.getLocation().x, this.getLocation().y+pixels);
	}
	
	public void moveUp(int pixels){
		setLocation(this.getLocation().x, this.getLocation().y-pixels);
	}
	
	public int locateInY(){
		return this.getLocation().y;
	}
	
	// Métodos para conocer el centro (h,k)
	
	public int getH(){
		int h = this.getLocation().x + (int)(WIDTH/2);
		return h;
	}
	
	public int getK(){
		int k = getCartesianY() + (int)(HEIGHT/2);
		return k;
	}
	
// Métodos extremos
	
	public int rightExtreme(){
		int rE = getCartesianX() + WIDTH;
		return rE;
	}
	
	public int leftExtreme(){
		int lE = getCartesianX();
		return lE;
	}
	
// Métodos parecidos a los de la clase PongGameObject
	
	public int getBallX(){
		return this.getLocation().x;
	}
	
	public int getTopY(){
		int top = Box.HEIGHT - this.getLocation().y - 25; // 25 Es el desfase vertical raro al dibujar la ventana
		return top;
	}
	
	public int getBottomY(){
		int bottom = Box.HEIGHT - this.getLocation().y - Ball.HEIGHT - 25; // - 25 pa corregir el desfase raro
		return bottom;
	}

}
