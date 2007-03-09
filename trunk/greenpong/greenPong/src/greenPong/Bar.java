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
	
public static final String urlBar = "../../img/<IMAGEN BARRA>";
	
	Bar(){
		init(urlBar);
	}

}
