import java.util.*;

public class CallListenerThread extends Thread {
    private String nick;
    private final static int port = 28411;
    private final static String ip = "localhost";
    private String available = "Available";
    private List<Observer> observers;
    private Observable observable = new Observable();

    public CallListenerThread() {
        observers = new ArrayList<Observer>();
    }

    public void addConnectionObserver(Observer o) {
        observers.add(o);
    }

    public void removeConnectionObserver(Observer o) {
        observers.remove(o);
    }

    public void run() {
        try {
            while (true) {
                if () {

                }
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
