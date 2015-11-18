import java.net.*;
import java.io.*;

public class Connection{
    private Socket s;
    private final static int PORT = 28411;
    private final static String ENCODING = "UTF-8";
    private String nick;
    private OutputStream out;
    private PrintWriter sout;
    private DataInputStream reader;

    public Connection(Socket s, String nick) throws IOException{
        this.s = s;
        out = this.s.getOutputStream();
        sout = new PrintWriter(out);
        reader = new DataInputStream(this.s.getInputStream());
        this.nick = nick;
    }

    public void sendMessage(String message) throws IOException{
        sout.print("Message\n" + message + "\n");
        out.flush();
    }

    public void disconnect() throws IOException{
        sout.print("Disconnect\n");
        out.close();
        reader.close();
        s.close();
    }

    public void sendNickHello(String nick) throws IOException {
        sout.print("ChatApp 2015 user " + nick + "\n");
        out.flush();
    }

    public void sendNickBusy(String nick) throws IOException {
        if(s.isConnected()){
            sout.print("ChatApp 2015 user " + nick + " busy\n");
            out.flush();
        }
    }

    public void accept() throws IOException{
        sout.print("Accepted\n");
        out.flush();
    }

    public void reject() throws IOException {
        sout.print("Rejected\n");
        out.flush();
    }

    public String testRecieve() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream(),"UTF-8"));
        String string;
        string = reader.readLine();
        return string;
    }

    public Command recieve() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream(),"UTF-8"));
        String s = reader.readLine();
        if(s.contains("Accepted")) return new Command(CommandTypes.accept);
        if(s.contains("Rejected")) return new Command(CommandTypes.reject);
        if(s.contains("ChatApp 2015")) return new NickCommand(CommandTypes.nickname);
        if(s.contains("Disconnect")) return new Command(CommandTypes.disconnect);
        if(s.contains("Message")) return new MessageCommand(CommandTypes.message);
        else return null;
    }
}
