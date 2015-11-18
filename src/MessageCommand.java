
public class MessageCommand extends Command {
    protected String messagetext;

    public MessageCommand(CommandTypes type) {
        super(type);
    }

    public MessageCommand(CommandTypes type, String messagetext) {
        super(type);
        this.messagetext = messagetext;
    }
}
