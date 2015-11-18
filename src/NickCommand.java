
public class NickCommand extends Command{
    protected String nick;

    public NickCommand(CommandTypes ctp) {
        super(ctp);
    }

    public NickCommand(CommandTypes ctp, String nick) {
        super(ctp);
        this.nick = nick;
    }
}
