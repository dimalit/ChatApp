import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Scanner;

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
		outStream.write(("ChatApp 2015 user " + nickname + EOL).getBytes(ENCODING));
		outStream.flush();
	}

	public void sendNickBusy(String nick) throws UnsupportedEncodingException, IOException {
		outStream.write(("ChatApp 2015 user " + nickname + " busy" + EOL).getBytes(ENCODING));
		outStream.flush();
	}

	public void accept() throws IOException {
		outStream.write(("Accepted" + EOL).getBytes());
		outStream.flush();
	}

	public void reject() throws IOException {
		outStream.write(("Rejected" + EOL).getBytes());
		outStream.flush();
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

	public Command recive() throws IOException {
		StringBuffer sb = new StringBuffer();
		char c;
		while ((c = (char) inStream.readByte()) != EOL)
			sb.append(c);
		String str = sb.toString().toLowerCase();
		// FIXME: if user nick have ED substring it's a problem
		if (str.lastIndexOf("ed") > -1)
			sb = new StringBuffer(str.replace("ed", ""));
		else if (str.contains("chatapp 2015 user")) {
			Scanner in = new Scanner(str);
			in.next();
			return new NickCommand(in.next(), in.skip("user").next(), str.contains("busy"));
		} else if ("message".equals(str)) {
			sb = new StringBuffer();
			while ((c = (char) inStream.readByte()) != EOL)
				sb.append(c);
			return new MessageCommand(sb.toString());
		}
		return 0 == sb.length() | !isCorrectCommand(str) ? null : new Command(Command.CommandType.valueOf(str));
	}

	// TODO:Write a function that will verify the correctness of the line(protocol)
	private boolean isCorrectCommand(final String s) {
		return false;
	}

}
