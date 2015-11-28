
public class MessageCommand extends Command {


	String message;
	
	
	MessageCommand(CommandType type){
		super(type);
	}
	
	
	MessageCommand(CommandType type, String message){
		super(type);
		this.message=message;
	}
	
	
	void setMessage(String message){
		this.message=message;
	}
}
