/**
 * Created by user on 03.11.2015.
 */
public class NickCommand extends Command {
    private boolean	busy;
    private String	nick;
    private String	version;

    public NickCommand(boolean busy, String nick, String version) {
        super(CommandType.NICK);
        this.busy = busy;
        this.nick = nick;
        this.version = version;
    }

    public String toString(){
        if(busy)
            return version + " nick " + nick + " busy";
        else
            return version + " nick " + nick;
    }
}
