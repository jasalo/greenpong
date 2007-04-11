package tempo;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Dialogo {

  public Dialogo(String msg, String titulo) {
    JOptionPane pane = new JOptionPane(msg);
    JDialog dialog = pane.createDialog(new JFrame(), titulo);
    dialog.setVisible(true);
  }
}
