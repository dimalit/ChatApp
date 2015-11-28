import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


class Connection {
	
	
	private Socket socket;
	
	public final static int port=28411;
	
	private Scanner in;
	private DataOutputStream out;
	
	
	
	public Connection(Socket socket) throws IOException{			
		this.socket=socket;		
		
		in=new Scanner(socket.getInputStream());
		out=new DataOutputStream(socket.getOutputStream());
	}
	
	
	Command recieve(){
		Command c;
		String s=in.nextLine();
		c=Command.callCommand(s);
		return c;
	}
	
	
	void accept(){
		try {
			out.writeUTF("Accpeted");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	void reject(){
		try {
			out.writeUTF("Rejected");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	void disconnect(){
		try {
			out.writeUTF("Disconnect");
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	void userGreeting(String NickName){
		try {
			out.writeUTF(new StringBuffer("ChatApp 2015").append(NickName).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	void userBusy(String NickName){
		try {
			out.writeUTF(new StringBuffer("ChatApp 2015").append(NickName).append("is busy").toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	void sendMessage(String message){
		try {
			out.writeUTF(new StringBuffer("Message").append("\n").append(message).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
