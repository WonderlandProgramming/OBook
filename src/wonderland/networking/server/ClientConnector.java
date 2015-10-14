package wonderland.networking.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import wonderland.networking.MessageObject;

/**
 * This class contains a single Connection to a Client and manages the
 * MessageObjects.
 * 
 * @author Lukas Kannenberg, Lukas Peer
 * @since 14.10.2015
 * @vesion 0.1
 */
class ClientConnector implements Runnable {

	// This Receivethread
	private Thread thisThread;

	private Socket socket;

	private boolean isActive = true;

	/**
	 * Package only constructor!
	 * Creates a new ClientConnector. Normally just called by the Server.
	 * @param client {@link Socket} of the Client that connected
	 */
	ClientConnector(Socket client) {
		thisThread = new Thread(this);
		this.socket = client;

		thisThread.start();
	}

	/**
	 * Processes incoming messages
	 * @param message The MessageObject that was send
	 */
	private void processMessageObject(Object message) {

	}

	/**
	 * Closes the Connection
	 */
	public void close() {
		this.isActive = false;
		try {
			this.thisThread.join();
		} catch (InterruptedException e) {
		}
	}

	/**
	 * (non-javadoc)
	 */
	@Override
	public void run() {
		while (isActive) {
			ObjectInputStream in;
			try {
				in = new ObjectInputStream(socket.getInputStream());
				processMessageObject(in.readObject());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Sends a messageObject to the client
	 * @param message the message that should be transmitted
	 */
	public void send(MessageObject message) {
		if (socket != null) {
			try {
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(message);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
