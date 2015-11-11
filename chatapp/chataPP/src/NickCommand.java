
public class NickCommand extends Command {
	private boolean isBusy;
	private String nick, version;

	public NickCommand(String version, String nick, boolean busy) {
		super(Command.CommandType.valueOf("NICK"));
		this.version = version;
		this.isBusy = busy;
		this.nick = nick;
	}

	public String getNick() {
		return nick;
	}

	public boolean isBusy() {
		return isBusy;
	}

	public String toString() {
		if (isBusy)
			return version + " user " + nick + " busy";
		else
			return version + " user " + nick;
	}
}
