import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintStream;
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
	private PrintStream outStream;
	private Scanner inStream;
	private String nickname;

	public Connection(Socket s, String nickname) throws IOException {
		this.socket = s;
		outStream = new PrintStream(s.getOutputStream(),true, ENCODING);
		inStream = new Scanner(s.getInputStream());
		this.nickname = nickname;

	}

	public boolean isOpen() {
		return !socket.isClosed();
	}

	public void sendNickHello(String nick) throws UnsupportedEncodingException, IOException {
		outStream.println("ChatApp 2015 user " + nick);
	}

	public void sendNickBusy(String nick) throws UnsupportedEncodingException, IOException {
		outStream.println("ChatApp 2015 user " + nick + " busy");
	}

	public void accept() throws IOException {
		outStream.println("Accepted");
	}

	public void reject() throws IOException {
		outStream.println("Rejected");
		outStream.close();
	}

	public void sendMessage(final String message) throws UnsupportedEncodingException, IOException {
		outStream.println("Message");
		outStream.println(message);
	}

	public void disconnect() throws IOException {
		outStream.println("Disconnect");
		outStream.close();
		socket.close();
	}

	public Command receive() throws IOException {
		String str;
		str=inStream.nextLine();
		if (str.toUpperCase().startsWith("CHATAPP 2015 USER")) {
			Scanner in = new Scanner(str);
			in.next();
			return new NickCommand(in.next(), in.skip(" [a-z,A-Z]{4} ").next(), str.toUpperCase().endsWith(" BUSY"));
		} else if (str.toUpperCase().equals("MESSAGE")) {
				str=inStream.nextLine();
			return new MessageCommand(str);
		} else {
			str = str.toUpperCase().replaceAll("[\r\n]","");
			for (Command.CommandType cc : Command.CommandType.values())
				if (cc.toString().equals(str))
					return new Command(Command.CommandType.valueOf(str.replaceAll("ED", "")));
		}
		return new Command(Command.CommandType.NULL);
	}

}
