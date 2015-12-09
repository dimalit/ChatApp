
public class NickCommand extends Command{
    protected String nick;

    public NickCommand(CommandTypes ctp) {
        super(ctp);
    }

    public NickCommand(CommandTypes ctp, String nick) {
        super(ctp);
        String[] s1 = nick.split(" ");
        this.nick = s1[3];
    }

    public String intoString() {
        String s = "ChatApp 2015 user " + this.nick + "\n";
        return s;
    }

    public String getNick(){
        return nick;
    }
}
