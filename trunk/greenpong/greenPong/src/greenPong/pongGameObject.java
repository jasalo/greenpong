/**
 * 
 */
package greenPong;

import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.*;
/**
 * Esta clase es el generico de una bola o de una barra. Permite incluir la
 * funcionalidad para obtener su posicion y asiganrle una imagen. Simplifica
 * enormemente las clases bar y ball.
 */
public class pongGameObject extends javax.swing.JLabel {

	/**
	 * Asiga al boton una imagen. Genera una exepcion si la url no sirve
	 * 
	 * @param urlImagen
	 *            Url de la imagen del Boton.
	 */
	public void init(String urlImage) throws IOException {
		/**
		 * Codigo tomado de:
		 * http://java.sun.com/developer/JDCTechTips/2004/tt0217.html Falta revision
		 * de si constituye violacion de Author rights leer:
		 * http://www.sun.com/termsofuse.jsp#g2_12 (numero 10.4)
		 */
		//Gestion del eror en la URL
		if (urlImage.length()!= 1){
			System.err.println("URL de imagen errada" + urlImage);
			System.exit(-1);
		}
		// Read
		File file = new File(urlImage);
		BufferedImage input = ImageIO.read(file);
		// Convert (Aplica sharpness filter??)
		Kernel sharpKernel = new Kernel(3, 3, new float[] {
		          0.0f, -1.0f,  0.0f,
		          -1.0f,  5.0f, -1.0f,
		         0.0f, -1.0f,  0.0f
		       });
		ConvolveOp convolveOp = new ConvolveOp(sharpKernel, ConvolveOp.EDGE_NO_OP, null);
		int width = input.getWidth();
		int height = input.getHeight();
		BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		convolveOp.filter(input, output);
		// Make screen
		Icon icon = new ImageIcon(output);
		/**
		* Fin del codigo de Sun
		*/
		setIcon(icon);
		setBorder(null);
	}
}
