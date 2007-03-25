/**
 * 
 */
package greenPong;

import java.awt.BorderLayout;


import javax.swing.*;

/**
 * @author ubuntu
 *
 */
public class InfoWindow extends javax.swing.JFrame {
		private boolean pause=false;
		private JTextArea caja;
		
		public InfoWindow(String titulo){
			setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
			setTitle(titulo);
			setResizable(true);
			caja = new JTextArea("",1000,10);
			//getContentPane().add(caja, BorderLayout.CENTER);
			setSize(200, 300);
			JScrollPane abc = new JScrollPane(caja , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			abc.setAutoscrolls(true);
			getContentPane().add(abc, BorderLayout.CENTER);
			setVisible(true); 
			
			
		}
		
		public void info(String texto){
			if(pause==false){
				caja.setText(texto + "\n" + caja.getText());
			}
		}
		
		public void pause(){
			pause = true;
		}
}
