
public class MessageCommand extends Command {
    protected String messagetext;

    public MessageCommand(CommandTypes type) {
        super(type);
    }

    public MessageCommand(CommandTypes type, String messagetext) {
        super(type);
        this.setMessagetext(messagetext) ;
    }

    public String getMessagetext() {
        return messagetext;
    }

    public void setMessagetext(String messagetext) {
        this.messagetext = messagetext;
    }
}
