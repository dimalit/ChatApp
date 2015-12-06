import java.io.*;
import java.util.*;
import java.net.*;

public class CallListenerThread extends Observable implements Runnable {
    private Socket socket;
   // private ServerSocket serverSocket;
    private volatile boolean disconnected;
    private CallListener callListener;
    private Connection connection;
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
            //serverSocket = new ServerSocket(Protocol.PORT);
            callListener = new CallListener();
            while (true){

                 connection = callListener.getConnection();
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
}