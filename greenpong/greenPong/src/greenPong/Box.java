/**
 * Contenedor de la bola y las barras, es la interfaz gráfica y se comunica con
 * Brain constantemente.
 */
package greenPong;

/**
 * fUNCION SIN METODOS POR AHORA.
 *
 */
public class Box extends javax.swing.JFrame {
	//Definicion de constantes
	public static final int ANCHO = 446;
	public static final int ALTO = 580;
			
	//Definicion de las dos barras, una automatica y otra del usuario.
	Bar userBar, computerBar;
	//La bola del juego, no se llama ball para evitar errorer al escribir
	Ball gameBall;
	
	Box(){
		//Se asigna memoria a los controles
		userBar = new Bar();
		computerBar = new Bar();
        gameBall = new Ball();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("[greenPong]");
        setResizable(false);
        setSize(ANCHO,ALTO); //Numeros tomados del estudio de proporciones
        /**
         * Falta por definir el layout, las posiciones de los objs.
         * 
         */
        setVisible(true); //INICIA TODO EL JUEGO!!
	}

}
