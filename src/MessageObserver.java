
public class MessageObserver extends CommandObserver{
    private CommandTypes type = CommandTypes.accept;
    private MessageCommand messageCommand;

    public MessageObserver(CommandListenerThread commandListenerThread /*ссылка на форму*/){
        this.commandListenerThread = commandListenerThread;
        commandListenerThread.addCommandObserver(this);
    }

    public void update(Command command) {
        messageCommand = (MessageCommand) command;

    }

    public CommandTypes getType() {
        return type;
    }
}
