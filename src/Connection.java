import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Connection {

	private Socket socket;
	public static final int PORT = 28411;
	public static final String ENCODING = "UTF-8";
	public static final char EOL = '\n';
	private DataOutputStream outStream;
	private DataInputStream inStream;
	private String nickname;

	public Connection(Socket s, String nickname) throws IOException {
		this.socket = s;
		outStream = new DataOutputStream(s.getOutputStream());
		inStream = new DataInputStream(s.getInputStream());
		this.nickname = nickname;

	}

	public boolean isOpen() {
		return !socket.isClosed();
	}

	public void sendNickHello(String nick) throws UnsupportedEncodingException, IOException {
		outStream.write(("ChatApp 2015 user " + nick + EOL).getBytes(ENCODING));
		outStream.flush();
	}

	public void sendNickBusy(String nick) throws UnsupportedEncodingException, IOException {
		outStream.write(("ChatApp 2015 user " + nick + " busy" + EOL).getBytes(ENCODING));
		outStream.flush();
	}

	public void accept() throws IOException {
		outStream.write(("Accepted" + EOL).getBytes());
		outStream.flush();
	}

	public void reject() throws IOException {
		outStream.write(("Rejected" + EOL).getBytes());
		outStream.flush();
		outStream.close();
		socket.close();
	}

	public void sendMessage(final String message) throws UnsupportedEncodingException, IOException {
		outStream.write(("Message" + EOL).getBytes(ENCODING));
		outStream.write((message + EOL).getBytes(ENCODING));
		outStream.flush();
	}

	public void disconnect() throws IOException {
		outStream.write(("Disconnect" + EOL).getBytes(ENCODING));
		outStream.flush();
		outStream.close();
		socket.close();
	}

	public Command receive() throws IOException {
		StringBuffer sb = new StringBuffer();
		char c;
		while ((c = (char) inStream.readByte()) != EOL)
			sb.append(c);
		String str = sb.toString();
		if (str.toUpperCase().startsWith("CHATAPP 2015 USER")) {
			Scanner in = new Scanner(str);
			in.next();
			return new NickCommand(in.next(), in.skip(" [a-z,A-Z]{4} ").next(), str.toUpperCase().endsWith(" BUSY"));
		} else if ("MESSAGE".equalsIgnoreCase(str)) {
			sb = new StringBuffer();
			while ((c = (char) inStream.readByte()) != EOL)
				sb.append(c);
			return new MessageCommand(sb.toString());
		} else if (str.toUpperCase().lastIndexOf("ED") > -1)
			str = str.toUpperCase().replace("ED", "");
		return 0 == sb.length() | !isCorrectCommand(str) ? null : new Command(Command.CommandType.valueOf(str));
	}

	// TODO:Write a function that will verify the correctness of the
	// string(protocol)
	private boolean isCorrectCommand(final String s) {
		boolean c = false;
		Command.CommandType [] a = Command.CommandType.values();
		for (int i = 0; i < a.length; i++) {
			if ((a[i].toString().toLowerCase().equals("reject"))
					|| (a[i].toString().toLowerCase().equals("accept"))) {
					c = true;
					break;
			} else {
				if (a[i].toString().equals(s)) {
					c = true;
					break;
				}
			}
	}
		return c;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		// Test receive
		int i = 2;
		switch (i) {
		case 1: {
			ServerSocket ss = new ServerSocket(Connection.PORT);
			Socket s = ss.accept();
			Connection c = new Connection(s, "max");
			while (true) {
				Command cc = c.receive();
				System.out.printf("%s : %s\n", cc.getClass(), cc);
			}
			// break;
		}
		case 2: {
			Socket s = new Socket("127.0.0.1", Connection.PORT);
			Connection c = new Connection(s, "test");
			c.sendNickBusy("nickBusyTest");
			TimeUnit.SECONDS.sleep(1);
			c.sendNickHello("nickTest");
			TimeUnit.SECONDS.sleep(1);
			c.sendMessage("MyMessage");
			TimeUnit.SECONDS.sleep(1);
			c.accept();
			TimeUnit.SECONDS.sleep(1);
			c.reject();
			TimeUnit.SECONDS.sleep(1);
			break;
		}
		}

	}
}
