package greenPong;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GPSplash extends Thread {

	javax.swing.JLabel imagen;
	javax.swing.JFrame splash;
	private int tiempo =0;
	Brain app;
	
	/*public static void main(String[] args) {
		GPSplash abc = new GPSplash();
		System.out.println("Inicio aplicacion temporal del Splash");
	}*/
	
	public GPSplash(Brain appB, String url){
		super();
		app = appB;
		splash = new javax.swing.JFrame();
		
		splash.setUndecorated(true);
		splash.setTitle("Loading [greenPong]...");
		
		imagen = new javax.swing.JLabel("Aqui va la img que se tiene que ver con el splash");
		splash.getContentPane().add(imagen, BorderLayout.CENTER);
		//Carga imagen
		try{
			
		
		File file = new File(url);
		BufferedImage input = ImageIO.read(file);
		int width = input.getWidth();
		int height = input.getHeight();
		Icon icon = new ImageIcon(input);
		splash.setSize(width, height);
		imagen.setIcon(icon);
		} catch (IOException e){
			//Mensaje de error.
		}
		//
		centerFrame(splash);
		splash.getContentPane().add(new javax.swing.JLabel(""), BorderLayout.SOUTH);
		splash.getContentPane().add(new javax.swing.JLabel(""), BorderLayout.NORTH);
		splash.getContentPane().add(new javax.swing.JLabel(""), BorderLayout.EAST);
		splash.getContentPane().add(new javax.swing.JLabel(""), BorderLayout.WEST);
		splash.setVisible(true);
	}
	
	public void run() {
		int contador = 0;
		app.cj.setVisible(false);
		while(1==1){
			try {
		
				sleep(1000);
			} catch (InterruptedException e) {}
			contador++;
			System.out.println(contador);
			if(contador == 1){
				splash.setVisible(false);
				app.cj.setVisible(true);
				
			}
			
			if(contador == 2){
				
				app.start();
				this.stop();
			}
			
		}
		
		
	}
	
	void centerFrame (JFrame f) {
		 java.awt.Toolkit tk = java.awt.Toolkit.getDefaultToolkit ();
		 java.awt.Dimension pantalla = tk.getScreenSize ();
		 int x = (pantalla.width-f.getWidth())/2;
		 int y = (pantalla.height-f.getHeight())/2;
		 f.setLocation(x,y);

	    
	  } // centerFrame

}
