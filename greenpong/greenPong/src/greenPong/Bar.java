/** Es una de las barras del juego. Deberia crearse una barra inteligente y otra
 * gestionada con la información del InputManager que es la del jugador.
 * 
 */
package greenPong;

/**
 * @author ubuntu
 *
 */
public class Bar extends pongGameObject{
	
	public static final String URLBAR = "../../img/<IMAGEN BARRA>";
	public static final int ANCHO =127;
	public static final int ALTO =34;
	
	Bar(){
		init(URLBAR);
		java.awt.Dimension dimension = new java.awt.Dimension();
		dimension.setSize(ANCHO, ALTO);
		setMinimumSize(dimension);
		setPreferredSize(dimension);
		setMaximumSize(dimension);
	}

}
