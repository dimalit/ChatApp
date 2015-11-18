import java.util.*;

public class CallListenerThread extends Observable implements Runnable {
    private String nick;
    private final static int port = 28411;
    private final static String ip = "localhost";
    private String available = "Available";

    public void addConnectionObserver(Observer o) {
        addObserver(o);
    }

    public void removeConnectionObserver(Observer o) {
        deleteObserver(o);
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