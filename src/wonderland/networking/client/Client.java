package wonderland.networking.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import wonderland.networking.MessageObject;

/**
 * TODO
 * 
 * @author Lukas Kannenberg, Lukas Peer
 * @since 14.10.2015
 * @vesion 0.1
 */
public class Client {
	// This Receivethread
	private Thread receivThread;

	private Socket socket;

	/**
	 * Creates a new Client that connects to the IPAdress and Port and allows communcation to the Server.
	 * @param ipAdress IP Adress as String like "<code>192.168.0.50</code>" or "<code>localhost</code>"
	 * @param port The port that the Server is listening on.
	 */
	public Client(String ipAdress, int port) {
		try {
			socket = new Socket(ipAdress, port);
			startReceiveThread();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Processes incoming messages
	 * 
	 * @param message
	 *            The MessageObject that was send
	 */
	private void processMessageObject(Object message) {

	}

	/**
	 * Starts the Reiceivthread.
	 */
	private void startReceiveThread() {
		receivThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					ObjectInputStream in;
					try {
						in = new ObjectInputStream(socket.getInputStream());
						processMessageObject(in.readObject());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		receivThread.start();
	}

	/**
	 * Closes the connection to the Server
	 */
	public void close() {
		try {
			receivThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends a messageObject to the client
	 * 
	 * @param message
	 *            the message that should be transmitted
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
