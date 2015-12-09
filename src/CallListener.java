import java.io.*;
import java.net.*;

public class CallListener {
    private Boolean isBusy;
    private ServerSocket serverSocket;

    public CallListener(){
        setStatus(false);
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

    public Boolean StatusBusy(){
        return isBusy;
    }

    public void setStatus(Boolean statusBusy){
        this.isBusy = statusBusy;
    }
}