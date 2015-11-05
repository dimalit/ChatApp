public class Command {
    protected CommandType type ;

    public Command(){
    }

    public Command(CommandType type){
        this.type=type;
    }

    public static Command getCommand(String string){
        if (string.contains(Protocol.MESSAGE)) return new MessageCommand(CommandType.MESSAGE);
        if (string.contains(Protocol.ACCEPTED)) return new Command(CommandType.ACCEPT);
        if (string.contains(Protocol.REJECTED)) return new Command(CommandType.REJECT);
        if (string.contains(Protocol.GREETING)) return  new NickCommand(CommandType.NICK);
        if (string.contains(Protocol.DISCONNECT)) return new Command(CommandType.DISCONNECT);
        return null;
    }

}

