public class Command {

	protected CommandType type;

	public static enum CommandType {
		ACCEPT, DISCONNECT, MESSAGE, NICK, REJECT;
	}

	public Command(CommandType t) {
		type = t;
	}

	@Override
	public String toString() {
		if ((type.toString().equals("ACCEPT")) || (type.toString().equals("REJECT"))) {
			return type.toString().concat("ed");
		} else
			return type.toString();
	}
}
