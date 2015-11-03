import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Caller {
	private String localNick;
	private String ip;
	private SocketAddress remoteAddress;

	private static enum CallStatus {
		BUSY, NO_SERVICE, NOT_ACCeSIBLE, OK, REJECTED
	}

	public Caller() {
		localNick = "NickName";
		ip = "localhost";
		remoteAddress = new InetSocketAddress("127.0.0.1", Connection.PORT);
	}

	public Caller(String localNick) {
		this.localNick = localNick;
		ip = "localhost";
		remoteAddress = new InetSocketAddress("127.0.0.1", Connection.PORT);
	}

	public Caller(String localNick, SocketAddress remoteAddress) {
		this.localNick = localNick;
		ip = "localhost";
		this.remoteAddress = remoteAddress;
	}

	public Caller(String localNick, String ip) {
		this.localNick = localNick;
		this.ip = ip;
		remoteAddress = new InetSocketAddress("127.0.0.1", Connection.PORT);
	}

	public Connection call() throws IOException, InterruptedException {
		StringBuffer ip = new StringBuffer();
		char ch;
		int i = 1;
		while ((ch = remoteAddress.toString().charAt(i)) != ':') {
			ip.append(ch);
			i++;
		}
		Socket s = new Socket(ip.toString(), Connection.PORT);
		wait(1000);
		if (s.isConnected()) {
			Connection c = new Connection(s, localNick);
			return c;
		}
		return null;
	}

	public String getLocalNick() {
		return localNick;
	}

	public SocketAddress getRemoteAddess() {
		return remoteAddress;
	}

	// TODO:wher is RemoteNick?
	public String getRemoteNick() {
		return null;
	}

	public void setLocalNick(String localNick) {
		this.localNick = localNick;
	}

	public void setRemoteAddress(SocketAddress remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	// TODO: What toString?
	public String toString() {
		return null;
	}

	// TODO:What Status?
	public CallStatus getStatus() {
		return null;
	}
}
