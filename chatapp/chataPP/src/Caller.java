import java.net.*;
import java.io.*;
public class Caller 
{
    private static final int remotePort = 9999;
    private Socket socket;
    private String localNick;
    private String remoteNick;
    private String remoteAdress;
    
    
    public Caller() {
    }

    public void Call() throws UnknownHostException, IOException {
		socket = new Socket(remoteAdress, remotePort);
	}

	public void setLocalNick(String localNick) {
		this.localNick = localNick;
	}

	public void setRemoteNick(String remoteNick) {
		this.remoteNick = remoteNick;
	}

	public void setRemoteAdress(String remoteAdress) {
		this.remoteAdress = remoteAdress;
	}
	
	public Socket getSocket(){
		return socket;
	}
    
    
    
}
