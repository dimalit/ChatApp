
import java.net.Socket;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;

public class Connection {
	
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
        outStream.write("Accepted\n".getBytes());
        outStream.flush();
    }

    public void reject() throws IOException {
        outStream.write("Rejected\n".getBytes());
        outStream.flush();
    }

    public void sendMessage(final String message) throws UnsupportedEncodingException, IOException {
    	outStream.write("Message\n".getBytes(ENCODING));
        outStream.write((message+"\n").getBytes(ENCODING));
        outStream.flush();
    }

    public void disconnect() throws IOException {
    	outStream.write("Disconnect\n".getBytes(ENCODING));
    	outStream.flush();
    	outStream.close();
        s.close();
    }
}
