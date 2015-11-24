import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
	private BufferedOutputStream outStream;
	private BufferedInputStream inStream;
	private String nickname;

	public Connection(Socket s, String nickname) throws IOException {
		this.socket = s;
		outStream = new BufferedOutputStream(s.getOutputStream());
		inStream = new BufferedInputStream(s.getInputStream());
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
	}

	public void sendMessage(final String message) throws UnsupportedEncodingException, IOException {
		outStream.write(("Message" + EOL).getBytes(ENCODING));
		outStream.write((message + EOL).getBytes(ENCODING));
		outStream.flush();
	}

	public void disconnect() throws IOException {
		outStream.write(("Disconnect" + EOL).getBytes(ENCODING));
		outStream.flush();
		// outStream.close();
		// socket.close();
	}

	public Command receive() throws IOException {
		StringBuffer sb = new StringBuffer();
		char c;
		while ((c = (char) inStream.read()) != EOL)
			sb.append(c);
		String str = sb.toString();
		System.out.println(str);
		if (str.toUpperCase().startsWith("CHATAPP 2015 USER")) {
			Scanner in = new Scanner(str);
			in.next();
			return new NickCommand(in.next(), in.skip(" [a-z,A-Z]{4} ").next(), str.toUpperCase().endsWith(" BUSY"));
		} else if ("MESSAGE".equalsIgnoreCase(str)) {
			sb = new StringBuffer();
			while ((c = (char) inStream.read()) != EOL)
				sb.append(c);
			return new MessageCommand(sb.toString());
		} else {
			str = str.toUpperCase().replaceAll("[\r\n]","");
			for (Command.CommandType cc : Command.CommandType.values())
				if (cc.toString().equals(str))
					return new Command(Command.CommandType.valueOf(str.replaceAll("ED", "")));
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(Connection.PORT);
		Socket s = ss.accept();
		Connection c = new Connection(s, "max");

		Command cc = c.receive();
		System.out.printf("%s : %s\n", cc.getClass(), cc);
		cc = c.receive();
		System.out.printf("%s : %s\n", cc.getClass(), cc);

	}

}
