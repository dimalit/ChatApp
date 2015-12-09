import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Caller {

    private String nick;
    private int localport;
    private InetAddress ip;
    private String NickCaller;
    public static enum CallStatus{OK, NOT_ACCESSIBLE, BUSY, REJECTED, NO_SERVICE};
    private CallStatus status;
    private SocketAddress address;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    private Socket socket;
    public Caller(){
        this("unnamed", new InetSocketAddress("127.0.0.1", 465));
    }

    public Caller(String localNick){
        this(localNick, new InetSocketAddress("127.0.0.1",465));
    }

    public Caller(String localNick, SocketAddress remoteAddress){
        this.nick=localNick;
        this.address=remoteAddress;
    }

    public Caller(String localNick, String ip){
        this(localNick, new InetSocketAddress(ip, 465));
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public int getLocalport() {
        return localport;
    }

    public void setLocalport(int localport) {
        this.localport = localport;
    }

    public String getNickCaller() {
        return NickCaller;
    }

    public void setNickCaller(String nickCaller) {
        NickCaller = nickCaller;
    }

    public CallStatus getStatus() {
        return status;
    }

    public void setStatus(CallStatus status) {
        this.status = status;
    }

    public SocketAddress getAddress() {
        return address;
    }

    public void setAddress(SocketAddress address) {
        this.address = address;
    }

    public Connection call() throws InterruptedException {
        String ip = address.toString(); // "/ip:port"
        try {
            Socket s = new Socket();
            s.connect(address, 1000);
            return  new Connection(s, nick);
        } catch (IOException e) {
            System.out.println("Not connected");
            return null;
        }
    }


}