/*
 * Esta clase no corre porque es un proycto de netbeans, solo sirve para entender
 * una forma de crear la interfaz gráfica.
 */
package tempo;

public class Interfaz extends javax.swing.JFrame {
    
    /** Creates new form Interfaz */
    public Interfaz() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">
    private void initComponents() {
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("greenPong!");
        setResizable(false);
        jButton1.setIcon(new javax.swing.ImageIcon("/media/docs/docs/java/greenPong/img/ball.jpg"));
        jButton1.setBorder(null);

        jButton2.setIcon(new javax.swing.ImageIcon("/media/docs/docs/java/greenPong/img/bar.jpg"));
        jButton2.setBorder(null);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(99, 99, 99)
                .add(jButton1)
                .addContainerGap(293, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(180, Short.MAX_VALUE)
                .add(jButton2)
                .add(186, 186, 186))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(44, 44, 44)
                .add(jButton2)
                .add(49, 49, 49)
                .add(jButton1)
                .addContainerGap(189, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    // End of variables declaration
    
}

