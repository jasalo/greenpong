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
		contenedor = ncontenedor;
		Dimension dim = new Dimension();
		dim.setSize(ANCHO, ALTO);
		setPreferredSize(dim.getSize());
		setSize( this.getPreferredSize());
	}

}
