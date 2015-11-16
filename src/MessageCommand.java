/**
 * Created by user on 03.11.2015.
 */
public class MessageCommand extends Command {
    private  String message;

    public MessageCommand(String message) {
        super(CommandType.MESSAGE);
        this.message = message;
    }

    public String toString(){
        return  message;
    }
}
