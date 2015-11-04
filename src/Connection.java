import java.net.Socket;
import java.util.Scanner;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;

public class Connection {

	private Socket s;
	public static final int PORT = 28411;
	public static final String ENCODING = "UTF-8";
	private DataOutputStream outStream;
	private DataInputStream inStream;
	private String nickname;

	public Connection(Socket s, String nickname) throws IOException {
		this.s = s;
		outStream = new DataOutputStream(s.getOutputStream());
		inStream = new DataInputStream(s.getInputStream());
		this.nickname = nickname;

	}

	public boolean isOpen() {
		return !s.isClosed();
	}

	public void sendNickHello(String nick) throws UnsupportedEncodingException, IOException {
		outStream.write(("ChatApp 2015 user " + nickname + "\n").getBytes(ENCODING));
		outStream.flush();
	}

	public void sendNickBusy(String nick) throws UnsupportedEncodingException, IOException {
		outStream.write(("ChatApp 2015 user " + nickname + " busy\n").getBytes(ENCODING));
		outStream.flush();
	}

	public void accept() throws IOException {
		outStream.write("Accepted\n".getBytes());
		outStream.flush();
	}

	public void reject() throws IOException {
		outStream.write("Rejected\n".getBytes());
		outStream.flush();
	}

	public void sendMessage(final String message) throws UnsupportedEncodingException, IOException {
		outStream.write("Message\n".getBytes(ENCODING));
		outStream.write((message + "\n").getBytes(ENCODING));
		outStream.flush();
	}

	public void disconnect() throws IOException {
		outStream.write("Disconnect\n".getBytes(ENCODING));
		outStream.flush();
		outStream.close();
		s.close();
	}

	// TODO:Version;
	public Command recive() throws IOException {
		StringBuffer sb = new StringBuffer();
		Command command = null;
		String version = null;
		char c;
		while ((c = (char) inStream.readByte()) != ((char) (10))) {
			sb.append(c);
			if (sb.toString().toLowerCase().indexOf("ed") > -1)
				sb = new StringBuffer(sb.toString().toLowerCase().replace("ed", ""));
			else if (sb.toString().toLowerCase().contains("chatapp 2015 user")) {
				sb = new StringBuffer();
				String nick;
				while ((c = (char) inStream.readByte()) != (char) (10) || c != (char) (32)) {
					sb.append(c);
				}
				nick = new String(sb.toString());
				if (c == (char) (10)) {
					command = new NickCommand(version, nick, false);
				} else {
					command = new NickCommand(version, nick, true);
				}
				return command;
			}else if (sb.toString().equals("Message")){
				sb=new StringBuffer();
				while ((c = (char) inStream.readByte()) != ((char) (10))) {
					sb.append(c);
				}
				command = new MessageCommand(sb.toString());
			}
		}
		return 0 == sb.length() ? null : new Command(Command.CommandType.valueOf(sb.toString()));
	}
}
