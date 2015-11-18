public class DisconnectCommandObserver extends CommandObserver {
    private CommandType type = CommandType.DISCONNECT;
    Connection connection;

    public DisconnectCommandObserver(CommandListenerThread clt, Connection connection){
        this.clt=clt;
        this.connection=connection;
    }

    public void update(Command command) {
       //херня с окном "User disconnected";
    }

    public CommandType getType() {
        return type;
    }
}
