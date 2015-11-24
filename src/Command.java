
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
		}
	}

	public Command(CommandType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(new Command(Command.CommandType.valueOf("REJECT")).getClass());
	}
}
