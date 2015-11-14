
public class Command {
	CommandType commandType;

	public Command(CommandType commandType) {
		this.commandType = commandType;
	}

	public String toString() {
		return commandType.toString();
	}

	public static enum CommandType {
		ACCEPT, DISCONNECT, MESSAGE, NICK, REJECT;

		public static boolean contains(String test) {

			for (CommandType c : CommandType.values()) {
				if (c.name().equals(test)) {
					return true;
				}
			}
			return false;
		}

	}
}