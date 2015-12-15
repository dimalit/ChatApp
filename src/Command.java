
public class Command {
    protected CommandTypes commandTypes;

    public Command(CommandTypes commandTypes) {
        this.commandTypes = commandTypes;
    }

    public enum CommandTypes {
        disconnect,
        accept,
        reject,
        nickname,
        message,
        busy
    }
    public static Command createCommand(String s){
        if(s.contains("Accepted")) return new Command(CommandTypes.accept);
        if(s.contains("Rejected")) return new Command(CommandTypes.reject);
        if(s.contains("ChatApp 2015")) {
            if (s.contains("Busy")) return new BusyCommand(CommandTypes.busy, s.replaceAll("ChatApp 2015 user ", ""));
            else return new NickCommand(CommandTypes.nickname, s.replaceAll("ChatApp 2015 user ", ""));
        }
        if(s.contains("Disconnect")) return new Command(CommandTypes.disconnect);
        if(s.contains("Message")) return new MessageCommand(CommandTypes.message,s);
        else return null;
    }

    public CommandTypes getType(){
        return commandTypes;
    }
}
