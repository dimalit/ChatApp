import java.net.*;
import java.io.*;

public class Caller {
    private Socket s;
    private String nick;
    private final static int port = 28411;
    private String IP;
    private String distNick;

    public Caller(String ip) throws IOException{
        this.IP = ip;
        s = new Socket(IP,port);
    }

    public Connection call() throws IOException {
        Connection c = new Connection(new Socket(IP,port));
        c.sendNickHello(nick);
        return c;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getPort() {
        return port;
    }

    public String getDistNick() {
        return distNick;
    }

    public void setDistNick(String distNick) {
        this.distNick = distNick;
    }
}
