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
	InfoWindow info;

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
		double nx = this.getLocation().getX() + pixels;
		setLocation((int)nx, finalYPosition);
	}
	
	public void moveLeft(int pixels){
		double nx = this.getLocation().getX() - pixels; 
		setLocation((int)nx, finalYPosition);
	}
	
	public void centerInY(){
		//Evita que se pueda centrar una barra verticalmente;
	}
	
	public void center(){
		// Evita que se pueda centrar una barra verticalmente;
	}
	
	public int getKc(){
		int kc = getCartesianY() - ALTO;
		System.out.println("kc=" + kc);
		return kc;
		
	}
	
	public int getKu(){
		int ku = getCartesianY();
		System.out.println("ku=" + this.getLocation().getX());
		return ku - 100;
	}
	
	public void setX(int x){
		info.info("Solimov: "+ x);
		if(x>=10 && x<(Box.ANCHO-10)){
			int y = (int)getLocation().getY();
			setLocation(x, y);
		}
	}
	
}
