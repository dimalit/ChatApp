import java.io.*;
import java.net.*;

public class CallListener {
    private String remoteNick;
    private Boolean isBusy;
    private final static String IP = "localhost";
    private SocketAddress remoteAddress;
    private SocketAddress localAddress;
    private ServerSocket serverSocket;


    public CallListener(String userName, String remoteAdress){
        Protocol.localNick = userName;
        remoteAddress = new InetSocketAddress(IP, Protocol.PORT);
    }

    public Connection getConnection() throws IOException{
        if (StatusBusy()){
            return null;
        }else{
            serverSocket = new ServerSocket(Protocol.PORT);
            return new Connection(serverSocket.accept());
        }
    }

    public SocketAddress getListenAddress() throws IOException{
        return serverSocket.getLocalSocketAddress();
    }

    public String getLocalNick() {
        return Protocol.localNick;
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
        return Protocol.PORT;
    }

    public String getRemoteNick() {
        return remoteNick;
    }
}