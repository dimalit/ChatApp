

import java.io.IOException;
import java.net.*;

public class CallListener {
	private ServerSocket server;

	public CallListener(ServerSocket server) {
		this.server = server;
	}


	public void setServerSocket(ServerSocket server) {
		this.server = server;
	}

	public Socket getSocket(){
		Socket socket = null;
		try{
		 socket = server.accept();
		} catch (IOException e){}
		return socket;
	}
}