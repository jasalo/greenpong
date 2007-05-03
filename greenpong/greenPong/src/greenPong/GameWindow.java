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



/**
 * @author ubuntu
 * 
 */
public class GameWindow extends javax.swing.JFrame {
	Box windowBox;
	JPanel scorePanel;
	JLabel status, score, lives;
	public static int WIDTH=528 ;
	public static int HEIGHT= 687;
	String[] args;
	
	GameWindow(String[] args1) {
		args = args1;
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("[greenPong]");
		setResizable(false);
		windowBox = new Box(args, this);
		scorePanel = new JPanel();
		status = new JLabel();
		score = new JLabel();
		lives = new JLabel();
		setLayout(new BorderLayout());
		getContentPane().add(windowBox, BorderLayout.CENTER);
		createScoreFrame();
		getContentPane().add(scorePanel, BorderLayout.SOUTH);
		setBackground(Color.WHITE);
		windowBox.setPreferredSize(new java.awt.Dimension(Box.WIDTH, Box.HEIGHT));
		pack();
		setVisible(true); 
	}
	
	/**
	 * Crea el frame del puntaje con todos su detalles
	 *
	 */
	private void createScoreFrame(){
		scorePanel.setBackground(Color.WHITE);
		scorePanel.setLayout(new FlowLayout());
		JLabel imgBola = new JLabel();
		setLabelIcon(imgBola, args[1]);
		scorePanel.add(status);
		scorePanel.add(imgBola);
		scorePanel.add(lives);
		scorePanel.add(new JLabel("  "));
		scorePanel.add(score);
		JLabel pts = new JLabel("pts.");
		pts.setFont(new Font("Arial", Font.BOLD, 19));
		scorePanel.add(pts);
		/*TEMPORAL*/
		status.setText("");
		lives.setText("x3");
		score.setText("0");
		status.setFont(new Font("Arial", Font.BOLD, 22));
		lives.setFont(new Font("Arial", Font.BOLD, 22));
		score.setFont(new Font("Arial", Font.BOLD, 22));
		
		lives.setForeground(Color.BLUE);
		score.setForeground(Color.GRAY);
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
	
	public void won() {
		status.setForeground(new Color(0, 150, 0));
		status.setText("GANASTE!");
	}
	
	public void lost() {
		if ((getScore()-1)==-1)
			status.setText("");
		else {
			status.setForeground(Color.RED);
			status.setText("PERDISTE!");
		}
	}
	
	public void cleanStatus() {
		status.setText("");
	}
	
	public void lifeUp(){
		String vidasActuales = lives.getText();
		//LA siguiente linea toma las vidas como enteor porque se agrega
		//una x Siempre para las vidas.
		vidasActuales = (String)vidasActuales.subSequence(1, vidasActuales.length());
		
		int numeroVidas = Integer.parseInt(vidasActuales);
		numeroVidas++;
		lives.setText("x" + numeroVidas);
	}
	
	public void lifeDown(){
		String vidasActuales = lives.getText();
		//LA siguiente linea toma las vidas como enteor porque se agrega
		//una x Siempre para las vidas.
		vidasActuales = (String)vidasActuales.subSequence(1, vidasActuales.length());
		
		int numeroVidas = Integer.parseInt(vidasActuales);
		numeroVidas--;
		if(numeroVidas==-1){
			lives.setText("x3");
			status.setForeground(new Color(0, 30, 60));
			status.setText("GAME OVER!");
			JOptionPane.showMessageDialog(this, "Has perdido todas tus vidas, gracias por jugar [greenPong]", "GAME OVER", JOptionPane.ERROR_MESSAGE);
			cleanStatus();
		} else {
			lives.setText("x" + numeroVidas);
		}
	}
	
	public void increaseScore(int increasement){
		int puntajeActual = Integer.parseInt(score.getText());
		puntajeActual += increasement;
		if(puntajeActual>=0)
			score.setText(""+puntajeActual);
		else
			score.setText("0");
		
	}
	
	public int getScore(){
		return Integer.parseInt(score.getText());
	}

}
