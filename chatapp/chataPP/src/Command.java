
public class Command {

    private CommandType commandType;

    public Command(CommandType commandType) {
        this.commandType = commandType;
    }


    public String toString(){
        return commandType.toString();
    }

    static enum CommandType{
        ACCEPT, DISCONNECT, MESSAGE, NICK, REJECT
    }  
    
    
}