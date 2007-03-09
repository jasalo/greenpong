/**
 * 
 */
package greenPong;

/**
 * Esta clase es el generico de una bola o de una barra.
 * Permite incluir la funcionalidad para obtener su posicion y 
 * asiganrle una imagen. Simplifica enormemente las clases bar y ball.
 */
public class pongGameObject extends javax.swing.JButton{
	 
	/** Asiga al boton una imagen. 
	* @param urlImagen Url de la imagen del Boton.
	*/
	public void init(String urlImage){
		setIcon(new javax.swing.ImageIcon(urlImage));
		setBorder(null);
	}
	
	

}
