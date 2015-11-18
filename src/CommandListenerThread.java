import java.util.*;

public class CommandListenerThread extends Thread {
    private Connection connection;
    private Command lastCommand;
    private List<Observer> observers;
    private Observable observable = new Observable();

    public CommandListenerThread() {
        observers = new ArrayList<Observer>();
    }

    public void addCommandObserver(Observer o) {
        observers.add(o);
    }

    public void removeCommandObserver(Observer o) {
        observers.remove(o);
    }

    public void run() {
        try {
            while (true) {
                if () {

                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
