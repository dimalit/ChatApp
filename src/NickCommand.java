public class NickCommand extends Command{
    String nick;

    public NickCommand(CommandType type){
        super(type);
    }

    public NickCommand(CommandType type, String nick){
        super(type);
        this.nick=nick;
    }

    public void setNick(String nick){
        this.nick=nick;
    }
}
