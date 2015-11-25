import java.io.*;
import java.util.*;
import java.net.*;

public class CallListenerThread extends Observable implements Runnable {
    private CallListener callListener;
    private String nick;
    private Socket socket;
    private ServerSocket serverSocket;
    private volatile boolean disconnected;

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
                return new Connection(socket, nick);
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
                Connection connection = new Connection(socket, nick);
                if (connection!=null) {
                    CommandListenerThread clt = new CommandListenerThread(connection);
                    clt.addObserver(ChatWindow.observer);
                    clt.start();
                    connection.sendNickHello(Protocol.nickname);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}