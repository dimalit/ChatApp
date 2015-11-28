
public class Command {
	
	CommandType type;
	
	Command(CommandType type){
		this.type=type;
	}
	
	
	public static Command callCommand(String s){
		if(s.contains("Accepted")) {
			return new Command(CommandType.ACCEPT);
		}
		if(s.contains("Rejected")) {
			return new Command(CommandType.REJECT);
		}
		if(s.contains("Disconnect")) {
			return new Command(CommandType.DISCONNECT);
		}
		if(s.contains("ChatApp 2015")) {
			return new NickCommand(CommandType.NICK);
		}
		if(s.contains("Message")) {
			return new MessageCommand(CommandType.MESSAGE);
		}
		return null;
	}
	
	
}
