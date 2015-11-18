public class DisconnectCommandObserver extends CommandObserver {
    private CommandType type = CommandType.DISCONNECT;
    Connection connection;

    public DisconnectCommandObserver(CommandListenerThread clt, Connection connection){
        this.clt=clt;
        this.connection=connection;
    }

    public void update(Command command) {
       //вывод в чат "remoteNick disconnected;
       //
    }

    public CommandType getType() {
        return type;
    }
}
