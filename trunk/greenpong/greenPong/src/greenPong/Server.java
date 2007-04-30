package greenPong;

/**
 * EL codigo del servidor en java esta basado en el codigo de:
 * http://neo.lcc.uma.es/evirtual/cdd/codigos/servidortcp.html
 */

import java.util.*;
import java.io.*;
import java.net.*;
import greenPong.Bar;

//Esta clase es la que implementa el socket del servidor (ServerSocket)
class Server {
	final static int SERVER_PORT = 8001;
	final static int CONNECTED=-1;
	final static int DISCONNECTED=0;
	final static int WAITING=1;
	final static int SENDING=2;
	static final int QUITING=3;
	static final int LISTENING=4;
	final static String VTS = "VTS-1.0";
	String clientRequest;

	BufferedReader reader;

	PrintWriter writer;
	/*public String clientIP;
	public String clientName;*/
	public int status = DISCONNECTED;
	private ServerSocket server;

	private Socket socket;

	public InputStream in;
	private Brain brain;
	public OutputStream out;
	public Bar userBar;
	public Server(Bar nUserBar, Brain nBrain) {
		try {
			userBar = nUserBar;
			brain = nBrain;
			server = new ServerSocket(SERVER_PORT);
			status = LISTENING;
			System.out.println("Server is now active. Listening on port " + SERVER_PORT);
			System.out.println("" + server + "\n");
			System.out.println("Status: Listening");
			// Espera suspendido hasta que un cliente establece una conexi�n
			socket = server.accept();
			status = CONNECTED;
			System.out.println("Status: Connected");
			in = socket.getInputStream();
			out = socket.getOutputStream();

			/**
			 * 
			 */
			reader = new BufferedReader(new InputStreamReader(in));
			writer = new PrintWriter(new OutputStreamWriter(out), true);
			/** SEGUNDA PARTE */
			beginService();

		} catch (IOException e) {
			System.out.println("Error creting the server: " + e);
			clientOffline();
		}
	}

	/**
	 * Este metodo deberia correrse cada vez que se recibe algo del cliente
	 * 
	 */
	public void manageRequest() {
	
			// El protocolo de nuestro servidor solo acepta ordenes : HELP,
			// QUIT,NAME,DATE 
			/*clientRequest.startsWith("HELP")
			 * InetAddress host;
						host = InetAddress.getLocalHost();
						writer
								.println("Nombre del host :"
										+ host.getHostName());
			 * 
			 */
		if(status==WAITING){
			if(clientRequest.startsWith("identifyas")){
				System.out.println("The client is identifying himself");
				String clientID = clientRequest.split(" ")[1];
				if(clientID.equals(VTS)){
					System.out.println("VTS-1.0 identified");
					status = WAITING;
					System.out.println("Status: Waiting");
					System.out.println("Requesting client to send coordenates");
					send("!send");
				}
			}else if(clientRequest.startsWith("sending")){
				status = SENDING;
				System.out.println("Status: Sending");
				System.out.println("Main thread will now run");
				brain.resume();
				
			}
		}else if(status==SENDING){
			/**cODIGO PARA GESTIONAR LAS COORDENADAS RECIBIDAS ENEL JUEGO*/
			
		}else if(clientRequest.startsWith("quit")){
			System.out.println("Client is quiting");
			try{ server.close();} catch(Exception e){ System.out.println("Error when closing server, however status has been set to disconnected.\nError: " + e); }
			clientOffline();
			
		}else{
			System.out.println("A client request has been made\nHowever the server doesn't understand it");
			System.out.println("request: " + clientRequest);
			send("requestError");
		}


	}

	/**
	 * Este metodo se corre cuando se dice algo al cliente
	 * 
	 * @param msg
	 */
	public void send(String msg) {
		writer.println(msg);

	}
	
	public void clientOffline(){
		status = DISCONNECTED;
		/**
		 * ACTIONS TO TAKE WHEN CLIENT IS OFFLINE
		 */
	}

	public void beginService() {
		send("welcome");
		send("identifyas greenpong");
		send("!identify");
		status = WAITING;
		System.out.println("Status: Waiting");
		

		while (status != DISCONNECTED) {
			try {
				// leemos del canal de entrada la petici�n del cliente
				clientRequest = reader.readLine();

				// Sacamos por pantalla la peticion del cliente
				System.out.println("[CLIENT]" + clientRequest);
				manageRequest();

				//				
			} catch (IOException e) {
				System.out.println("Server error: " + e);
				status = DISCONNECTED;
				System.out.println("Status: Disconnected");
				clientOffline();

			}
		}
	}
	
	/*public static void main(String[] args){
		Server server = new Server();
	}*/
	
	public void informStatus(){
		
	}
}
