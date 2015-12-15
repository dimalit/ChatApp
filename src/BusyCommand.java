
public class BusyCommand extends Command {
    private String nick;

    public BusyCommand(CommandTypes type, String nick) {
        super(type);
        this.nick = nick;
    }

    public String intoString() {
        String s = "User " + this.nick +" is busy at that moment" + "\n";
        return s;
    }

    public CommandTypes getType() {
        return commandTypes;
    }

    public void setType(CommandTypes type) {
        this.commandTypes = type;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
