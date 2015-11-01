
import java.net.Socket;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;

public class Connection {
<<<<<<< HEAD

    private Socket s;
    private final int PORT = 28411;
    private final String ENCODING = "UTF-8";
    private DataOutputStream outStream;
    private String nickname;

    public Connection(String host, String nickName) throws IOException {
        s = new Socket(host, PORT);
        outStream = new DataOutputStream(s.getOutputStream());
        this.nickname = nickName.trim();

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
        outStream.writeBoolean(true);
        outStream.flush();
    }

    public void reject() throws IOException {
        outStream.writeBoolean(false);
        outStream.flush();
    }

    public void sendMessage(final String Message) throws UnsupportedEncodingException, IOException {
        outStream.write(Message.getBytes(ENCODING));
        outStream.flush();
    }

    public void disconnect() throws IOException {
        s.close();
    }
=======
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
>>>>>>> 170c860f21b370d37851e29a2d467e18cafc58e5
}
