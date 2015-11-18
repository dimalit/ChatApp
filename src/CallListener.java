import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class CallListener {
	private String localNick;
	private String localIp;
	private ServerSocket sSocket;
	private Socket socket;
	private boolean isBusy;

	public CallListener(String localNick, String localIp) throws IOException {
		this.localNick = localNick;
		this.localIp = localIp;
		this.sSocket = new ServerSocket(Connection.PORT);
		this.isBusy=false;
	}

	public CallListener(String localNick) throws IOException {
		this(localNick, "127.0.0.1");
	}

	public CallListener() throws IOException {
		this.localNick = "NickName";
		this.localIp = "127.0.0.1";
		this.sSocket = new ServerSocket(Connection.PORT);
		this.isBusy=false;
	}

	// TODO: make function
	public Connection getConnection() throws IOException {
		socket = sSocket.accept();
		return new Connection(socket, localNick);
	}

	public SocketAddress getListenAddress() throws IOException {
		return sSocket.getLocalSocketAddress();
	}

	public String getLocalNick() {
		return localNick;
	}

	public SocketAddress getRemoteAddress() throws IOException {
		return socket.getRemoteSocketAddress();
	}

	public boolean isBusy() {
		return sSocket.isBound();
	}

	// TODO: Some with it
	public void setBusy(boolean busy) {
		this.isBusy=busy;
	}

	public void setLocalNick(String localNick) {
		this.localNick = localNick;
	}
	 //for test
	@Override
	public String toString() {
		return "CallListener [localNick=" + localNick + ", localIp=" + localIp + ", sSocket=" + sSocket + "]";
	}

	public static void main(String[] args) {

	}
}
