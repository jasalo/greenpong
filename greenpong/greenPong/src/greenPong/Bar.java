/** Es una de las barras del juego. Deberia crearse una barra inteligente y otra
 * gestionada con la información del InputManager que es la del jugador.
 * 
 */
package greenPong;

import java.awt.Dimension;

public class Bar extends PongGameObject {

	public static final int ANCHO = 127;

	public static final int ALTO = 34;
	private int finalYPosition;
	

	public Bar(String urlImage, Box ncontenedor) {
		super(urlImage, ncontenedor);
		info = new InfoWindow("Barra");
		url = urlImage;
		contenedor = ncontenedor;
		Dimension dim = new Dimension();
		dim.setSize(ANCHO, ALTO);
		setPreferredSize(dim.getSize());
		setSize( this.getPreferredSize());
		finalYPosition = contenedor.getHeight() - 71 ;//NPI PQ, PERO ES ASI
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
		int kc = Box.ALTO - this.getLocation().y - 25 - ALTO;
		return kc;
	}
	
	public void setX(int x){
		int der = Box.ANCHO - 15 - ANCHO; //5px de descuadre, por eso 15 y no 10
		if(x>=10 && x<=der){
			int y = getLocation().y;
			setLocation(x, y);
		}
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
	
	// Métodos parecidos a los de la clase PongGameObject
	
	public int getBarX(){
		return this.getLocation().x;
	}
	
	public int getBarY(){
		int r = Box.ALTO - this.getLocation().y - 25; // 25 Es el desfase vertical raro al dibujar la ventana
		return r;
	}
	
}
