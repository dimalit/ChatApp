public class NickCommand extends Command{
    String nick;
    Boolean busy=false;

    public NickCommand(){
        super(CommandType.NICK);
    }

    public NickCommand(String string){
        super(CommandType.NICK);
        if (string.contains(" busy")){
            busy = true;
            string=string.replace(" busy","");
        }
        string = string.replace(Protocol.GREETING,"");
        nick = string;
    }

    public boolean isBusy(){
        return busy;
    }

    public String getNick() {
        return nick;
    }
}
