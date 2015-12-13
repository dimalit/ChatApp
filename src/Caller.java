import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
 
 public class Caller {
	 private final int port = 28411;
     private String ip;
	 
     private String localNick;
     private SocketAddress remoteAddress;
    
     private String remoteNick;
     private CallStatus status;
 
    public Caller(){
    	this.localNick = "unnamed";
        this.remoteAddress = getRemoteAddress();
    }
    public String getLocalNick(){
        return localNick;
    }
     private SocketAddress getRemoteAddress() {
		// TODO Auto-generated method stub
		return remoteAddress;
	}
	public Caller(String localNick,SocketAddress remoteAddress){
         this.localNick = localNick;
         this.remoteAddress = remoteAddress;
     }
     public Caller(String localNick,String ip){
         this.localNick = localNick;
        this.ip = ip;
     }
 
    public Connection call() throws Exception{
        Connection connection = new Connection(new Socket(InetAddress.getByName(ip),port));
        return connection;

    }
     }
