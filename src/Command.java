public class Command {

	private Command.CommandType type;

	public static enum CommandType {ACCEPT, DISCONNECT, MESSAGE, NICK, REJECT;}

	public Command(Command.CommandType t) {
		type = t;
	}

	public String toString() {
		return type.toString();
	}
}
