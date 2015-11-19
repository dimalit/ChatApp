import java.util.*;
import java.io.*;

public class CommandListenerThread implements Runnable {
    private Connection connection;
    private Command lastCommand;
    private boolean flag;
    private HashMap<CommandTypes, CommandObserver> observers;
    /*private поле с объектом формочки*/

    public CommandListenerThread(Connection connection, /*здесь должен быть объект работающей формочки*/) {
        this.connection = connection;
        /*this.формочка = формочка*/
    }

    public void addCommandObserver(CommandObserver commandObserver) {
        observers.put(CommandObserver.getType(),commandObserver);
    }

    public void removeCommandObserver(CommandObserver commandObserver) {
        observers.remove(CommandObserver.getType());
    }

    public void stop() {
        flag = true;
    }

    public void run() {
        try {
            while (!flag) {
                lastCommand = connection.recieve();
                if (lastCommand.commandTypes == CommandTypes.message) {
                    MessageCommand messageCommand = (MessageCommand) lastCommand;
                    /*формочка добавляет сообщение*/
                }
                if (lastCommand.commandTypes == CommandTypes.disconnect) {
                    /*формочка дисконектится*/
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
