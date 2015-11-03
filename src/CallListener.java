import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketAddress;

public class CallListener {
	private String localNick;
	private String localIp;
	private ServerSocket ss;

	public CallListener() throws IOException {
		localNick="NickName";
		localIp="localhost";
		ss = new ServerSocket(Connection.PORT);
	}
	public CallListener(String localnick) throws IOException{
		this.localNick=localnick;
		localIp="localhost";
		ss = new ServerSocket(Connection.PORT);
	}
	
	public CallListener(String localnick, String localIp) throws IOException{
		this.localNick=localnick;
		this.localIp=localIp;
		ss = new ServerSocket(Connection.PORT);
	}
	//TODO: make function
	public Connection getConnection() throws IOException{
		Connection c= new Connection(ss.accept(),localNick);
		return c;
	}
	public SocketAddress getListenAddress() throws IOException{
		return ss.getLocalSocketAddress();
	}
	public String getLocalNick(){
		return localNick;
	}
	public SocketAddress getRemoteAddress() throws IOException{
		return ss.accept().getRemoteSocketAddress();
	}
	//TODO: Where the RemoteNick?
	public String getRemoteNick(){
		return null;
	}
	//TODO: Some with it
	public boolean isBusy(){
		return ss.isBound();
	}
	//TODO: Some with it
	public void setBusy(boolean busy){
	}
	//TODO: Some with it
	public void setListenAddress(SocketAddress lestanAddress){
	}
	public void setLocalNick(String localNick){
		this.localNick=localNick;
	}
}
