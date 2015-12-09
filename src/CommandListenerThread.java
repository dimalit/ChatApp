import java.io.IOException;
import java.util.Observable;

/**
 * Created by Никита on 19.11.2015.
 */
public class CommandListenerThread extends Observable implements Runnable {

    Command lastCommand;
    Connection connection;
    boolean stopped;
public CommandListenerThread(){

}
    public CommandListenerThread(Connection con) {
        connection = con;
    }

    public Command getLastCommand() {
        return lastCommand;
    }

    public boolean isDisconnected() {
        return stopped;
    }

    public void start() {
        run();
    }

    public void stop() {
        stopped = true;
    }

    public void run() {
        do {
            try {
                Command checked = connection.receive();
                System.out.println(connection);
                if (checked != null) {
                    lastCommand = checked;
                    notifyObservers();
                }
            } catch (IOException ex) {
                stopped = true;
            }
        } while (!stopped);
    }
}