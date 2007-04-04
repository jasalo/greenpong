package greenPong;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GPSplash extends JFrame {

	javax.swing.JLabel imagen;
	public static void main(String[] args) {
		GPSplash abc = new GPSplash();
		System.out.println("Inicio aplicacion temporal del Splash");
	}
	
	public GPSplash(){
		super();
		setSize(200,500);
		setUndecorated(true);
		System.out.println("Fin");
		setTitle("Loading [greenPong]...");
		centerFrame(this);
		imagen = new javax.swing.JLabel("Aqui va la img");
		getContentPane().add(imagen, BorderLayout.CENTER);
		setVisible(true);
	}
	
	 void centerFrame (JFrame f) {
		 java.awt.Toolkit tk = java.awt.Toolkit.getDefaultToolkit ();
		 java.awt.Dimension pantalla = tk.getScreenSize ();
		 int x = (pantalla.width-f.getWidth())/2;
		 int y = (pantalla.height-f.getHeight())/2;
		 f.setLocation(x,y);

	    
	  } // centerFrame

}
