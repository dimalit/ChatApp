import java.io.*;
import java.util.*;
import java.net.*;

public class CallListenerThread extends Observable implements Runnable {
    private Socket socket;
    private ServerSocket serverSocket;
    private volatile boolean disconnected;
    private Connection connection;

    public CallListenerThread(){

    }

    public void start() {
        this.disconnected = false;
        Thread t = new Thread(this);
        t.start();
    }

    public boolean isDisconnected() {
        return disconnected;
    }

    public void stop() {
        disconnected = true;
    }

    public Connection getConnection(){
        if(socket!=null){
            try {
                return new Connection(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public  void run(){
        try {
            serverSocket = new ServerSocket(Protocol.PORT);
            while (true){
                socket = serverSocket.accept();
                connection = new Connection(socket);
                if (connection.getSocket()!=null) {
                    CommandListenerThread clt = new CommandListenerThread(connection);
                    clt.addObserver(ChatWindow.observer);
                    clt.start();
                    connection.sendNickHello(Protocol.localNick);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDisconnected(boolean b){
        this.disconnected = b;
    }
}