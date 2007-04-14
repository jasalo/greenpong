/**
 * La clase gameWindow aunque parezca innecesaria resulta muy util para
 * agregar menus a la aplicacion y controles para gestioanr puntaje o vidas.
 */
package greenPong;



import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

import javax.swing.border.LineBorder;

/**
 * @author ubuntu
 * 
 */
public class GameWindow extends javax.swing.JFrame {
	Box windowBox;
	JPanel puntajeFrame;
	JLabel puntaje, vidas;
	public static int ANCHO=528 ;
	public static int ALTO= 687;
	String[] args;
	
	GameWindow(String[] args1) {
		args = args1;
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("[greenPong]");
		setResizable(false);
		windowBox = new Box(args, this);
		puntajeFrame = new JPanel();
		puntaje = new JLabel();
		vidas = new JLabel();
		setLayout(new BorderLayout());
		getContentPane().add(windowBox, BorderLayout.CENTER);
		crearPuntajeFrame();
		getContentPane().add(puntajeFrame, BorderLayout.SOUTH);
		setBackground(Color.WHITE);
		windowBox.setPreferredSize(new java.awt.Dimension(Box.ANCHO, Box.ALTO));
		pack();
		setVisible(true); 
	}
	
	/**
	 * Crea el frame del puntaje con todos su detalles
	 *
	 */
	private void crearPuntajeFrame(){
		puntajeFrame.setBackground(Color.WHITE);
		puntajeFrame.setLayout(new FlowLayout());
		JLabel imgBola = new JLabel();
		setLabelIcon(imgBola, args[1]);
		puntajeFrame.add(imgBola);
		puntajeFrame.add(vidas);
		puntajeFrame.add(new JLabel("  "));
		puntajeFrame.add(puntaje);
		JLabel pts = new JLabel("pts.");
		pts.setFont(new Font("Arial", Font.BOLD, 19));
		puntajeFrame.add(pts);
		/*TEMPORAL*/
		vidas.setText("x58");
		puntaje.setText("54874");
		vidas.setFont(new Font("Arial", Font.BOLD, 22));
		puntaje.setFont(new Font("Arial", Font.BOLD, 22));
	}
	
	
	
	private void setLabelIcon(javax.swing.JLabel label, String url){
		/**
		 * Codigo tomado de:
		 * http://java.sun.com/developer/JDCTechTips/2004/tt0217.html Falta revision
		 * de si constituye violacion de Author rights leer:
		 * http://www.sun.com/termsofuse.jsp#g2_12 (numero 10.4)
		 */
		if (url.equals("")) {
			System.err.println("URL de imagen errada " + url);
			System.exit(-1);
		}
		try {
			File file = new File(url);
			
			BufferedImage input = ImageIO.read(file);
			Kernel sharpKernel = new Kernel(3, 3, new float[] { 0.0f, -1.0f,
					0.0f, -1.0f, 5.0f, -1.0f, 0.0f, -1.0f, 0.0f });
			ConvolveOp convolveOp = new ConvolveOp(sharpKernel,
					ConvolveOp.EDGE_NO_OP, null);
			int width = input.getWidth();
			int height = input.getHeight();
			BufferedImage output = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_ARGB);
			convolveOp.filter(input, output);
			Icon icon = new ImageIcon(input);
			/**
			 * Fin del codigo de Sun
			 */
			label.setIcon(icon);
			label.setBorder(null);
		} catch (IOException e) {
			
		}
	}
	
	/** METODOS PARA CONTROLAR LAS VIDAS Y EL PUNTAJE **/
	
	public void lifeUp(){
		String vidasActuales = vidas.getText();
		//LA siguiente linea toma las vidas como enteor porque se agrega
		//una x Siempre para las vidas.
		vidasActuales = (String)vidasActuales.subSequence(1, vidasActuales.length());
		
		int numeroVidas = Integer.parseInt(vidasActuales);
		numeroVidas++;
		vidas.setText("x" + numeroVidas);
	}
	
	public void lifeDown(){
		String vidasActuales = vidas.getText();
		//LA siguiente linea toma las vidas como enteor porque se agrega
		//una x Siempre para las vidas.
		vidasActuales = (String)vidasActuales.subSequence(1, vidasActuales.length());
		
		int numeroVidas = Integer.parseInt(vidasActuales);
		numeroVidas--;
		vidas.setText("x" + numeroVidas);
	}
	
	public void increaseScore(int increasement){
		int puntajeActual = Integer.parseInt(puntaje.getText());
		puntajeActual += increasement;
		puntaje.setText(""+puntajeActual);
		
	}

}
