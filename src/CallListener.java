import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketAddress;

public class CallListener {
	private String localNick;
	private String localIp;
	private ServerSocket ss;

	public CallListener(String localnick, String localIp) throws IOException {
		this.localNick = localnick;
		this.localIp = localIp;
		ss = new ServerSocket(Connection.PORT);
	}

	public CallListener(String localnick) throws IOException {
		this(localnick, "127.0.0.1");
	}

	public CallListener() throws IOException {
		this("NickName", "127.0.0.1");
	}

	// TODO: make function
	public Connection getConnection() throws IOException {
		return new Connection(ss.accept(), localNick);
	}

	public SocketAddress getListenAddress() throws IOException {
		return ss.getLocalSocketAddress();
	}

	public String getLocalNick() {
		return localNick;
	}

	public SocketAddress getRemoteAddress() throws IOException {
		return ss.accept().getRemoteSocketAddress();
	}

	// TODO: Where the RemoteNick?
	public String getRemoteNick() {
		return null;
	}

	public boolean isBusy() {
		return ss.isBound();
	}

	// TODO: Some with it
	public void setBusy(boolean busy) {
	}

	// TODO: Some with it
	public void setListenAddress(SocketAddress lestanAddress) {
	}

	public void setLocalNick(String localNick) {
		this.localNick = localNick;
	}
	//TODO:String pattern?
	@Override
	public String toString() {
		return null;
	}

	public static void main(String[] args) {

	}
}
