public class MessageCommand extends Command {
	String message;

	public MessageCommand(String message) {
		super(Command.CommandType.MESSAGE);
		this.message = message;
	}

	public String toString() {
		return message + "\n";
	}

}