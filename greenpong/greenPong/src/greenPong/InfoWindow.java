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
		
		JTextArea caja;
		
		public InfoWindow(String titulo){
			setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
			setTitle(titulo);
			setResizable(true);
			caja = new JTextArea("",10,10);
			//getContentPane().add(caja, BorderLayout.CENTER);
			setSize(200, 300);
			getContentPane().add(new JScrollPane(caja , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
			setVisible(true); 
			
			
		}
		
		public void info(String texto){
			caja.setText(caja.getText() + "\n" + texto);
		}
}
