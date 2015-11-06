public class Command {

	protected CommandType type;

	public static enum CommandType {ACCEPT, DISCONNECT, MESSAGE, NICK, REJECT;}

	public Command(CommandType t) {
		type = t;
	}
	@Override
	public String toString() {
		return type.toString();
	}
	public static void main(String[] args) {
		System.out.println(new Command(Command.CommandType.valueOf("max")));
	}
}
