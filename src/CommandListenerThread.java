import java.util.*;

public class CommandListenerThread extends Observable implements Runnable {
    private Connection connection;
    private Command lastCommand;

    public void addCommandObserver(Observer o) {
        addObserver(o);
    }

    public void removeCommandObserver(Observer o) {
        deleteObserver(o);
    }

    public void run() {
        try {
            while (true) {
                if (connection.accept()) {
                    addCommandObserver();
                }
                else if (connection.reject();) {

                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}