/** Esta clase es la bola, el cerebro debe ser el encargado de moverla porque es
 * quien recibe todos los datos sobre posiciones y colisiones. La bola solo deberia
 * poseer lo necesario para presentarse adecuadamente y proveer a la otras clases
 * de los datos que resulten importantes.
 * 
 */
package greenPong;

import java.awt.Dimension;

public class Ball extends PongGameObject {

	public static final int ANCHO = 28;

	public static final int ALTO = 28;

	public Ball(String urlImage, Box ncontenedor) {
		super(urlImage, ncontenedor);
		url = urlImage;
		info = new InfoWindow("Bola");
		contenedor = ncontenedor;
		Dimension dim = new Dimension();
		dim.setSize(ANCHO, ALTO);
		setPreferredSize(dim.getSize());
		setSize( this.getPreferredSize());
	}
	
	public void moveRight(int pixels){
		double nx = this.getLocation().getX() + pixels;
		setLocation((int)nx, (int)this.getLocation().getY());
	}
	
	public void moveLeft(int pixels){
		double nx = this.getLocation().getX() - pixels; 
		setLocation((int)nx, (int)this.getLocation().getY());
	}
	
	public void moveDown(int pixels){
		double ny = this.getLocation().getY() + pixels;
		setLocation((int)this.getLocation().getX(), (int)ny);
	}
	
	public void moveUp(int pixels){
		double ny = this.getLocation().getY() - pixels; 
		setLocation((int)this.getLocation().getX(), (int)ny);
	}
	
	public int locateInY(){
		return (int)(this.getLocation().getY());
	}
	
	// Métodos para conocer el centro (h,k)
	
	public int getH(){
		int h = getCartesianX() + (int)(ANCHO/2);
		return h;
	}
	
	public int getK(){
		int k = getCartesianY() + (int)(ALTO/2);
		return k;
	}
	
// Métodos extremos
	
	public int rightExtreme(){
		int rE = getCartesianX() + ANCHO;
		info.info("rExt: " + rE);
		return rE;
	}
	
	public int leftExtreme(){
		int lE = getCartesianX();
		info.info("lExt: " + lE);
		return lE;
	}

}
