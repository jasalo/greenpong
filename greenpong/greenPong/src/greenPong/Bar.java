/** Es una de las barras del juego. Deberia crearse una barra inteligente y otra
 * gestionada con la información del InputManager que es la del jugador.
 * 
 */
package greenPong;
import java.io.*;
/**
 * @author ubuntu
 *
 */
public class Bar extends pongGameObject{
	
	public static final String URLBAR = "../../img/<IMAGEN BARRA>";
	public static final int ANCHO =127;
	public static final int ALTO =34;
	
	Bar(){
		//Bloque necesario porque hay lectura de archivos, ergo posible error
		try{
			init(URLBAR);
		}catch(IOException  e){
			System.err.println(URLBAR + ": No es una imagen utilizable para Bar");			
		}
		java.awt.Dimension dimension = new java.awt.Dimension();
		dimension.setSize(ANCHO, ALTO);
		setMinimumSize(dimension);
		setPreferredSize(dimension);
		setMaximumSize(dimension);
	}

}
