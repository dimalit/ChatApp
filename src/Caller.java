import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class Caller {
	
	
	InetAddress IP;
	String NickName;
	
	
	Caller(String ip, String NickName) throws UnknownHostException{
		IP=InetAddress.getByName(ip);
		this.NickName=NickName;
	}
	
	
	Connection call() throws IOException{
		Connection c=new Connection(new Socket(IP, Connection.port));
		c.chatApp2015(NickName);
		return c;
	}
}
