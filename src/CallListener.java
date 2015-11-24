import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class CallListener {
    private String localNick,remoteNick;
    private boolean isBusy;
    private String remoteIP;
    private int remotePort;
    private ServerSocket serverSocket;
    private Command lastCommand;
    private NickCommand nickCommand;

    public CallListener(String localNick, boolean isBusy){
        try {
            serverSocket = new ServerSocket(Protocol.PORT_NUMBER);
        } catch (IOException e) {
           //asd
        }
        this.localNick=localNick;
        this.isBusy=isBusy;
    }

    public Connection getConnection() throws IOException {
        Connection connection = new Connection(serverSocket.accept());
        if (!isBusy){
            connection.sendNickHello(localNick);
            lastCommand=connection.recieve();
            if (lastCommand.type==CommandType.NICK){
                nickCommand = (NickCommand) lastCommand;
                remoteNick = nickCommand.getNick();
                return connection;
            }
            else{
                return null;
            }
        }
        else{
            connection.sendNickBusy(localNick);
            connection.disconnect();
            return null;
        }
    }

    public void setBusy(boolean isBusy) {
        this.isBusy = isBusy;
    }
    
    public void setNick(String nick){
    	localNick=nick;
    }

    public boolean getBusy(){
        return isBusy;
    }

    public String getRemoteNick(){
        return remoteNick;
    }
}
