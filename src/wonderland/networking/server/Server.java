package wonderland.networking.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * The Server. This Class opens a Java Server that will open at the given Port.
 * If a client request a connection it will accept it and process all its
 * messages.
 * 
 * @author Lukas Kannenberg, Lukas Peer
 * @since 14.10.2015
 * @vesion 0.1
 */
public class Server {

	private ServerSocket socket;
	private List<ClientConnector> clients;

	/**
	 * Creates a new Server object with the Timeout of 60 seconds. Also creates a new Thread.
	 * @param port The Port that the Server is going to listen on.
	 */
	public Server(int port) {
		try {
			clients = new ArrayList<>();
			socket = new ServerSocket(port);
			socket.setSoTimeout(60000);

			run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This Method waits for new Client request and processes new Connections
	 */
	private void run() {

		while (true) {
			Socket client = null;
			try {
				client = socket.accept();
				clients.add(new ClientConnector(client));
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (client != null)
					try {
						client.close();
					} catch (IOException e) {
					}
			}
		}
	}

	/**
	 * Closes the connection to all clients.
	 */
	public void closeAllConections() {
		for (ClientConnector clientConnector : clients) {
			clientConnector.close();
		}
	}
}
