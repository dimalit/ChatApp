public class MessageCommand extends Comand {
	String message;

	public MessageCommand(String message) {
		super(Comand.CommandType.MESSAGE);
		this.message = message;
	}

	public String toString() {
		return message + "\n";
	}

}