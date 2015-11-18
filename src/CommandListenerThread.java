import javafx.beans.InvalidationListener;

import java.util.HashMap;
import java.util.Observable;
import java.util.List;

public class CommandListenerThread  implements Runnable {
    private Command lastCommand;
    private Connection connection;
    private HashMap<CommandType,CommandObserver> observers;
    private boolean stop;

    public CommandListenerThread(Connection connection){
        this.connection=connection;
    }

    public void run() {
        while (!stop) {
            lastCommand = connection.recieve();
            observers.get(lastCommand.type).update(lastCommand);

        }
    }


    public void addCommandObserver(CommandObserver commandObserver){
        observers.put(commandObserver.getType(),commandObserver);
    }


    public void removeCommandObserver(CommandObserver commandObserver){
        observers.remove(commandObserver.getType());
    }

    public void kill(){
        stop = true;
    }
}
