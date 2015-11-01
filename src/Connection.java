import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Connection {
	private Socket s;
	private int port = 28411;
	private String code = "UTF-8";
	private PrintWriter w;
	private String nickName;

	public Connection(String host, String nickName) {
		try {
			s = new Socket(host, port);
			w = new PrintWriter(s.getOutputStream());
			this.nickName=nickName;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendNickHello(String nick) throws UnsupportedEncodingException {
		String s="Hello, "+nick+", i'm "+nickName+"\n";
		byte[] buf = s.getBytes(code);
		w.println(buf);
		w.flush();
	}
	
	public void sendNickBusu(String nick) throws UnsupportedEncodingException{
		String s="Sorry, "+nick+" but i'm busy now :( \n";
		byte[] buf = s.getBytes(code);
		w.println(buf);
		w.flush();
	}
	
	public void accept(){
		w.println(true);
		w.flush();
	}
	
	public void reject(){
		w.println(false);
		w.flush();
	}
	public void sendMessage(String Message) throws UnsupportedEncodingException{
		Message+="\n";
		byte[] buf=Message.getBytes(code);
		w.println(buf);
		w.flush();
	}
	public void disconnect() throws IOException{
		s.close();
	}
}
