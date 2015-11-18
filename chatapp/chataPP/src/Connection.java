import java.io.*;
import java.net.*;

public class Connection {
  
    private final int port = 28411;
	private OutputStream os;
	public Socket socket;
	private String nick;
	private DataOutputStream ds;
	private DataInputStream in;

	public void disconnect() throws IOException {
		ds.write("DISCONNECT\n".getBytes());
		ds.flush();
		ds.close();
		socket.close();
	}

	public Connection(String IP, String Nick) {
		try {
			socket = new Socket(InetAddress.getByName(IP), port);
			os = socket.getOutputStream();
			ds = new DataOutputStream(os);
			in = new DataInputStream(socket.getInputStream());
			//m.setTo(IP);
			//m.setFrom(socket.getInetAddress().getHostName());
			nick = Nick;
			System.out.println(IP + " is connected. Type message.");
		} catch (IOException ex) {
			System.out.println("Bad");
		}
	}
	
	public Connection(Socket s, String Nick) {
		try {
			socket = s;
			os = socket.getOutputStream();
			ds = new DataOutputStream(os);
			in = new DataInputStream(socket.getInputStream());
			nick = Nick;
			System.out.println(s.getInetAddress() + " is connected. Type message.");
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}
	
	public boolean isOpen(){
		return !socket.isClosed();
		}

	public void accept() throws IOException {
		ds.write("ACCEPT\n".getBytes());
		ds.flush();
	}

	public void reject() throws IOException {
		ds.write("REJECTED\n".getBytes());
		ds.flush();
	}

	public void sendNickHello(String nick1) throws IOException {
		ds.write(("ChatApp 2015 " + nick+ "\n").getBytes());
		ds.flush();
	}

	public void sendNickBusy(String nick1) throws IOException {
		ds.write(("ChatApp 2015 " + nick + " busy" + "\n").getBytes());
		ds.flush();
	}

	public void sendMessage(String text) throws IOException {
		ds.write("MESSAGE\n".getBytes());
		ds.write(text.getBytes());
		ds.flush();
	}

//	public Command receive() throws IOException {
//		String command = "";
//		Command comand;
//		int n;
//		while (true) {
//			if((n = in.read()) == '\n') {
//					break;
//			} else
//				command += (char) n;
//		}
//		comand = new Command(command.toUpperCase());
//		if (command.startsWith("ChatApp")) {
//			//command for nickname
//			comand = new Command("Nick");
//		}
//		
//		if(command.toUpperCase().equals("MESSAGE")) {
//            String message = "";
//            while (true) {
//                if((n = in.read()) == '\n')
//                    break;
//                else
//                	message += (char) n;
//            }
//            //command for message
//		}
//		return comand;
//	}
//    
    
}
