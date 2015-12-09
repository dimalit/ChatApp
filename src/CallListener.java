import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class CallListener {
    public static final int port = 28411;
    public String localNick;
    public String localAddress;
    private boolean busy;
    private SocketAddress listenAddress,remoteAdress;
    private ServerSocket serverSocket;
    public String remoteNick;
    public Socket socket;
    public CallListener() throws IOException  {
        this("without name");
    }

    public CallListener(String localNick)  {
        this.localNick = localNick;
        this.serverSocket = null;

    }

    public CallListener(String localNick, String localAdress)throws IOException{
        try {
            serverSocket = new ServerSocket(28411);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (localAdress != null)
            serverSocket.bind(new InetSocketAddress(localAdress, 28411));
        this.localNick=localNick;
        this.listenAddress =new InetSocketAddress(localAdress,port);
    }

    public Connection getConnection() throws IOException {
        socket = serverSocket.accept();
        return new Connection(socket, localNick);
    }

    public SocketAddress getListenAddress() {
        return listenAddress;
    }

    public void setListenAddress(SocketAddress listenAddress) {
        this.listenAddress = listenAddress;
    }

    public String getLocalNick() {
        return localNick;
    }

    public void setLocalNick(String localNick) {
        this.localNick = localNick;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public SocketAddress getRemoteAdress() {
        return remoteAdress;
    }

    public void setRemoteAdress(SocketAddress remoteAdress) {
        this.remoteAdress = remoteAdress;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public String getRemoteNick() {
        return remoteNick;
    }

    public void setRemoteNick(String remoteNick) {
        this.remoteNick = remoteNick;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }
}

