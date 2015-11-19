import java.io.*;
import java.net.*;

public class CallListener {
    private String localNick = "default";
    private String remoteNick;
    private Boolean isBusy;
    private static final int localPort = 28411;
    private final static String IP = "localhost";
    private SocketAddress remoteAddress;
    private SocketAddress localAddress;
    private ServerSocket serverSocket;


    public CallListener(String userName, String remoteAdress){
        localNick = userName;
        remoteAddress = new InetSocketAddress(IP, localPort);
    }

    public Connection getConnection() throws IOException{
        if (StatusBusy()){
            return null;
        }else{
            serverSocket = new ServerSocket(localPort);
            return new Connection(serverSocket.accept(), localNick);
        }
    }

    public SocketAddress getListenAddress() throws IOException{
        return serverSocket.getLocalSocketAddress();
    }

    public String getLocalNick() {
        return localNick;
    }

    public SocketAddress getLocalAddress(){
        return localAddress;
    }

    public Boolean StatusBusy(){
        return isBusy;
    }

    public void setStatus(Boolean statusBusy){
        this.isBusy = statusBusy;
    }

    public SocketAddress getRemoteAddress(){
        return remoteAddress;
    }

    public void setRemoteAddress(SocketAddress remoteAddress){
        this.remoteAddress = remoteAddress;
    }

    public int getLocalPort(){
        return localPort;
    }

    public String getRemoteNick() {
        return remoteNick;
    }
}