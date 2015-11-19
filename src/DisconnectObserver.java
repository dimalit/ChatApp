
public class DisconnectObserver extends CommandObserver {
    private CommandTypes type = CommandTypes.disconnect;
    Connection connection;

    public DisconnectObserver(CommandListenerThread commandListenerThread, Connection connection) {
        this.commandListenerThread = commandListenerThread;
        this.connection = connection;
    }

    public void update(Command command) {

    }

    public CommandTypes getType() {
        return type;
    }
}
