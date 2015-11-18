
public class NickCommand extends Command {
	private boolean isBusy;
	private String nick, version;

	public NickCommand(String version, String nick, boolean busy) {
		super(Command.CommandType.NICK);
		this.version = version;
		this.isBusy = busy;
		this.nick = nick;
	}
        
	public String toString() {
		return nick;
	}
}
