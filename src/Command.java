
public class Command {

	CommandType type;

	public static enum CommandType {
		ACCEPT {
			@Override
			public String toString() {
				return "ACCEPTED";
			}
		},
	DISCONNECT, MESSAGE, NICK, REJECT {
			@Override
			public String toString() {
				return "REJECTED";
			}
		}, NULL
	}

	public Command(CommandType type) {
		this.type = type;
	}

	public Command() {
		type = CommandType.NULL;
	}

	@Override
	public String toString() {
		return type.toString();
	}
	
}
