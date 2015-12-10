import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class Caller {
	
	
	InetAddress IP;
	
	
	Caller(String ip) throws UnknownHostException{
		IP=InetAddress.getByName(ip);
	}
	
	
	Connection call() throws IOException{
		Connection c=new Connection(new Socket(IP, Connection.port));
		c.chatApp2015(Connection.NickName);
		return c;
	}
}
