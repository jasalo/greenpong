/** Esta clase es la bola, el cerebro debe ser el encargado de moverla porque es
 * quien recibe todos los datos sobre posiciones y colisiones. La bola solo deberia
 * poseer lo necesario para presentarse adecuadamente y proveer a la otras clases
 * de los datos que resulten importantes.
 * 
 */
package greenPong;

import java.awt.geom.Dimension2D;

/**
 * @author ubuntu
 *
 */
public class Ball extends pongGameObject{
	
	public static final String URLBALL = "../../img/<IMAGEN BOLA>";
	public static final int ANCHO = 28;
	public static final int ALTO = 28;
		
	Ball(){
		init(URLBALL);
		java.awt.Dimension dimension = new java.awt.Dimension();
		dimension.setSize(ANCHO, ALTO);
		setMinimumSize(dimension);
		setPreferredSize(dimension);
		setMaximumSize(dimension);
	}

}
