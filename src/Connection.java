import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Connection{
    private Socket s;
    private final static int PORT = 28411;
    private final static String ENCODING = "UTF-8";
    private String nick;
    private OutputStream out;
    private DataOutputStream sout;
    private DataInputStream reader;

    public Connection(Socket s, String nick) throws IOException{
        this.s = s;
        out = this.s.getOutputStream();
        sout = new DataOutputStream(out);
        reader = new DataInputStream(this.s.getInputStream());
        this.nick = nick;
    }

    public void sendMessage(String message) throws IOException{
        sout.write(new StringBuilder("Message\n").append(message).append("\n").toString().getBytes());
        out.flush();
    }

    public void disconnect() throws IOException{
        sout.write(new StringBuilder("Disconnect\n").toString().getBytes());
        out.close();
        reader.close();
        s.close();
    }

    public void sendNickHello(String nick) throws IOException {
        sout.write(new StringBuilder("ChatApp 2015 user ").append(nick).append("\n").toString().getBytes());
        out.flush();
    }

    public void sendNickBusy(String nick) throws IOException {
        if(s.isConnected()){
            sout.write(new StringBuilder("ChatApp 2015 user ").append(nick).append(" busy\n").toString().getBytes());
            out.flush();
        }
    }

    public void accept() throws IOException{
        sout.write(new StringBuilder("Accepted\n").toString().getBytes());
        out.flush();
    }

    public void reject() throws IOException {
        sout.write(new StringBuilder("Rejected\n").toString().getBytes());
        out.flush();
    }

    public void testRecieve() throws IOException {
        PrintWriter w = new PrintWriter(s.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream(),"UTF-8"));
        String string;
        Scanner r = new Scanner(new InputStreamReader(s.getInputStream(),"UTF-8"));
        while(r.hasNextLine()) {
            System.out.println(r.nextLine());
        }
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
