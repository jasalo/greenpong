/**
 * 
 */
package greenPong;

import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.*;

/**
 * IMPORTANTE: Todo control esta ubicado en cartesiano por esquina sup izq
 */

/**
 * Esta clase es el generico de una bola o de una barra. Permite incluir la
 * funcionalidad para obtener su posicion y asiganrle una imagen. Simplifica
 * enormemente las clases bar y ball.
 */

public class PongGameObject extends javax.swing.JLabel {
	
	public static final int ANCHO = 50;
	public static final int ALTO = 50;
	public Box contenedor;
	public String url;
	
	public PongGameObject(String urlImage, Box ncontenedor){
		url = urlImage;
		contenedor = ncontenedor; 
		try{
			init();
		} catch (IOException e){
			//Mensaje de error.
		}
	}
	

	public void dimensionar(){
		java.awt.Dimension dimension = new java.awt.Dimension();
		dimension.setSize(ANCHO, ALTO);
	}
	

	public void init() throws IOException {
		/**
		 * Codigo tomado de:
		 * http://java.sun.com/developer/JDCTechTips/2004/tt0217.html Falta revision
		 * de si constituye violacion de Author rights leer:
		 * http://www.sun.com/termsofuse.jsp#g2_12 (numero 10.4)
		 */
		if (url.equals("")){
			System.err.println("URL de imagen errada " + url);
			System.exit(-1);
		}
		File file = new File(url);
		BufferedImage input = ImageIO.read(file);
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
		Icon icon = new ImageIcon(output);
		/**
		* Fin del codigo de Sun
		*/
		setIcon(icon);
		setBorder(null);
	}
	
	public void centerInX(){
		int y = (int)getLocation().getY(); //posicion actual en y
		int x = (int)((contenedor.getWidth() - getWidth() )/2);
		setLocation(x,y);
	}
	
	public void centerInY(){
		int x = (int)getLocation().getX(); //posicion actual en y
		int y = (int)((contenedor.getHeight() - getHeight() )/2);
		setLocation(x,y);
	}
	
	public void center(){
		centerInY();
		centerInX();
	}
	
	public int getCartesianX(){
		return (int)getLocation().getX();
	}
	
	public int getCartesianY(){
		int r = Box.ALTO - (int)getLocation().getY();
		return r;
	}
	
	public void setX(int x){
		int y = (int)getLocation().getY();
		setLocation(x, y);
	}
	

	
	// Métodos extremos
	
	public int rightExtreme(){
		int rE = getCartesianX() + ANCHO;
		return rE;
	}
	
	public int leftExtreme(){
		int lE = getCartesianX();
		return lE;
	}
} //Fin clase
