package wonderland.networking;

import java.io.Serializable;

/**
 * This class creates the Object that is transfered between the Server and the Clients
 * 
 * @author Lukas Kannenberg, Lukas Peer
 * @since 14.10.2015
 * @version 0.1
 */
public class MessageObject implements Serializable{

	private static final long serialVersionUID = 2644652313355862668L;
	
	private int id;
	
	public MessageObject(int id){
		this.id = id;
	}
	
	public int getID(){
		return id;
	}
}
