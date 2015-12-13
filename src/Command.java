/**
 * Created by user on 03.11.2015.
 */
public class Command {
    CommandType commandType;

    static enum  CommandType{
        ACCEPT , DISCONNECT , MESSAGE , NICK , REJECT;


    }

    public Command(CommandType commandType) {
        this.commandType = commandType;
    }

    public String  toString() {
       return commandType.toString();
   }

}

