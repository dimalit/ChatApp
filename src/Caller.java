import java.net.*;
import java.io.*;
public class Caller {
    private Socket s;
    private String nick;
    private int port;
    private InetAddress IP;
    private String distNick;

    public Caller(InetAddress ip, int port) throws IOException{
        this.IP = ip;
        this.port = port;
        s = new Socket(IP,port);
    }

    public Connection call() throws IOException {
        Connection c = new Connection(s,nick);
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

    public void setPort(int port) {
        this.port = port;
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
