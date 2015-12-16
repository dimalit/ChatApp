import java.util.*;
import java.io.*;

public class CommandListenerThread extends Observable implements Runnable {
    private Connection connection;
    private volatile Command lastCommand;
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
        return this.connection;
    }

    public CommandListenerThread(Connection connection) {
        this.connection = connection;
    }

    public Command getLastCommand() {
        return lastCommand;
    }

    @Override
    public void run() {
        this.addObserver(ChatWindow.observer);
        while (!isDisconnected()) {
            synchronized (this) {
                try {
                    this.lastCommand = connection.recieve();
                    setChanged();
                    this.notifyObservers(lastCommand);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
