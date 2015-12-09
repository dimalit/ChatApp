public class Command {
    protected Command.CommandType type;

    Command(CommandType type) {
        this.type = type;
    }

    public enum CommandType {
        ACCEPT, DISCONNECT, MESSAGE, NICK, REJECT
    }

    public CommandType getType() {

        return type;
    }

    static Command createCommand(String s) {
        String text = s.toUpperCase();
        if (text.contains("ACCEPTED\n"))
            return new Command(CommandType.ACCEPT);
        else if (text.contains("REJECTED\n"))
            return new Command(CommandType.REJECT);
        else if (text.contains("DISCONNECT\n"))
            return new Command(CommandType.DISCONNECT);
        else if (text.contains("NICK\n"))
            return new Command(CommandType.NICK);
        else if (text.contains("MESSAGE\n"))
            return new Command(CommandType.MESSAGE);
        return null;
    }


}