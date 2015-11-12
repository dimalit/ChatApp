
import java.net.*;
import java.io.*;
public class Connection{
    private Socket s;
    private final static int PORT = 28411;
    private final static String ENCODING = "UTF-8";
    private String nick;
    private DataOutputStream out;
    private DataInputStream in;

    public Connection(String nick, String ip) throws IOException{
        s = new Socket(ip,PORT);
        out = new DataOutputStream(s.getOutputStream());
        in = new DataInputStream(s.getInputStream());
        this.nick = nick;
    }

    public void sendMessage(String message) throws IOException{
        out.writeUTF("Message\n" + message + "\n");
        out.flush();
    }

    public void Disconnect() throws IOException{
        out.writeUTF("Disconnect\n");
        out.close();
        in.close();
        s.close();
    }

    public void sendNickHello(String nick) throws IOException {
        out.writeUTF("ChatApp 2015 user " + nick + "\n");
        out.flush();
    }

    public void sendNickBusy() throws IOException {
        if(s.isConnected()){
            out.writeUTF("ChatApp 2015 user " + nick + " busy\n");
            out.flush();
        }
    }

    public void accept(Connection newUser) throws IOException{
        out.writeUTF("Accepted\n");
        out.flush();
    }

    public void reject() throws IOException {
        out.writeUTF("Rejected");
        out.flush();
    }
    public Command recieve(){
        return null;
    }
}
