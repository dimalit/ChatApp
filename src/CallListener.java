import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class CallListener {
    private String localNick,remoteNick;
    private boolean isBusy,isOnline;
    private String remoteIP;
    private int remotePort;
    private ServerSocket serverSocket;
    private Command lastCommand;
    private NickCommand nickCommand;

    public CallListener(String localNick){
        try {
            serverSocket = new ServerSocket(Protocol.PORT_NUMBER);
        } catch (IOException e) {
           //asd
        }
        this.localNick=localNick;
        this.isBusy= false;
        this.isOnline=true;
    }

    public Connection getConnection() throws IOException {
        Connection connection = new Connection(serverSocket.accept());
        return connection;
    }

    public void setBusy(boolean isBusy) {
        this.isBusy = isBusy;
    }
    
    public void setNick(String nick){
    	localNick=nick;
    }

    public String getRemoteNick(){
        return remoteNick;
    }

    public void setOnline(Boolean online){
        isOnline=online;
    }
}
