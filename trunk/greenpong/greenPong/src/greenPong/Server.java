package greenPong;

/**
 * EL codigo del servidor en java esta basado en el codigo de:
 * http://neo.lcc.uma.es/evirtual/cdd/codigos/servidortcp.html
 */

import java.util.*;
import java.io.*;
import java.net.*;

//Esta clase es la que implementa el socket del servidor (ServerSocket)
class Server {
	final static int SERVER_PORT = 8001;

	String clientRequest;

	BufferedReader reader;

	PrintWriter writer;

	private ServerSocket server;

	private Socket socket;

	public InputStream in;

	public OutputStream out;

	public Server() {
		try {
			server = new ServerSocket(SERVER_PORT);
			System.out.println("Servidor Java Activo! \n");
			System.out.println("" + server + "\n");
			// Espera suspendido hasta que un cliente establece una conexi�n
			socket = server.accept();

			in = socket.getInputStream();
			out = socket.getOutputStream();

			/**
			 * 
			 */
			reader = new BufferedReader(new InputStreamReader(in));
			writer = new PrintWriter(new OutputStreamWriter(out), true);
			/** SEGUNDA PARTE */
			iniciarServicio();

		} catch (IOException e) {
			System.out.println("Excepci�n en el constructor server: " + e);
		}
	}

	/**
	 * Este metodo deberia correrse cada vez que se recibe algo del cliente
	 * 
	 */
	public void manageRequest() {
		try {
			// El protocolo de nuestro servidor solo acepta ordenes : HELP,
			// QUIT,NAME,DATE
			if (clientRequest.startsWith("HELP")) {
				writer.println("�rdenes: HELP QUIT NAME DATE");
			} else {
				if (clientRequest.startsWith("QUIT")) {
					System.exit(0);
				} else {
					if (clientRequest.startsWith("NAME")) {
						InetAddress host;
						host = InetAddress.getLocalHost();
						writer
								.println("Nombre del host :"
										+ host.getHostName());
					} else if (clientRequest.startsWith("DATE")) {
						Date miFecha = new Date();
						writer.println("Fecha del sistema :"
								+ miFecha.toString());
					}

					else {
						writer.println("ERROR: Comando :'" + clientRequest
								+ "' no reconocido, use HELP");
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Excepci�n en el servidor " + e);
			System.exit(0);

		}

	}

	/**
	 * Este metodo se corre cuando se dice algo al cliente
	 * 
	 * @param msg
	 */
	public void send(String msg) {

	}

	public void iniciarServicio() {
		writer.println("Bienvenido al Servidor: " + new Date() + "/n");

		while (true) {
			try {
				// leemos del canal de entrada la petici�n del cliente
				clientRequest = reader.readLine();

				// Sacamos por pantalla la peticion del cliente
				System.out.println("Recibido :" + clientRequest);
				manageRequest();

				//				
			} catch (IOException e) {
				System.out.println("Excepci�n en el servidor " + e);
				System.exit(0);

			}
		}
	}
	
	public static void main(String[] args){
		Server server = new Server();
	}
}
