import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Scanner;


public class Connection {
	private PrintWriter printer;
	private Scanner scanner; 
	private Socket socket;
	
	public Connection(Socket socket) throws UnsupportedEncodingException, IOException{
		this.socket = socket;
		this.printer = new PrintWriter(
				new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
		this.scanner = new Scanner(socket.getInputStream());
	}
	
	public Command receive(){
		return new Command(Command.commandType.valueOf(this.scanner.nextLine()));

	}
	// <HelloMsg> format : ChatApp 2015 user <nick> (busy)
	public void sendNickHello(String ver, String nick){
		StringBuilder str = new StringBuilder();
		str.append(ver + " user " + nick + "\n");
		printer.print(str.toString());
		printer.flush();
	}
	public void sendNickBusy(String ver, String nick){
		StringBuilder str = new StringBuilder();
		str.append(ver + " user " + nick + " busy" + "\n");
		printer.print(str.toString());
		printer.flush();
	}
	public void send(String message){
		printer.print("Message");
		printer.flush();
		printer.print(message);
		printer.flush();
	}
}
