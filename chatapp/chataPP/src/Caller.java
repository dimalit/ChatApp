import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Caller 
{
    private static final int remotePort = 9999;
    private Socket socket;
    private String localNick;
    private String remoteNick;
    private String remoteAdress;
    
    
    public Caller() {
    }

    public void Call() 
    {
        try {
            socket = new Socket(remoteAdress, remotePort);
        } catch (IOException ex) {
            Logger.getLogger(Caller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	public void setLocalNick(String localNick) 
        {
		this.localNick = localNick;
	}

	public void setRemoteNick(String remoteNick) 
        {
		this.remoteNick = remoteNick;
	}

	public void setRemoteAdress(String remoteAdress) 
        {
		this.remoteAdress = remoteAdress;
	}
	
	public Socket getSocket()
        {
		return socket;
	}
    
        private enum CallStatus
        {
            BUSY, NO_SERVICE, NOT_ACCESSIBLE, OK, REJECTED
        };
        
    
    
}
