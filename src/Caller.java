import java.net.*;
import java.io.*;

public class Caller {
    private Socket s;
    private String nick;
    private final static int port = 28411;
    private InetAddress IP;
    private String distNick;

    public Caller(InetAddress ip, int port) throws IOException{
        this.IP = ip;
        s = new Socket(IP,port);
    }

    public Connection call(InetAddress ip, int port,String nick) throws IOException {
        Connection c = new Connection(new Socket(ip,port),nick);
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

    public InetAddress getIP() {
        return IP;
    }

    public void setIP(InetAddress IP) {
        this.IP = IP;
    }

    public String getDistNick() {
        return distNick;
    }

    public void setDistNick(String distNick) {
        this.distNick = distNick;
    }
}
