public class CallCommand extends Command {

    String nick;

    public CallCommand(){
        super(CommandType.CALL);
    }

    public CallCommand(String nick) {
        super(CommandType.CALL);
        this.nick = nick;
    }

    public String getNick() {
        return nick;
    }
}
