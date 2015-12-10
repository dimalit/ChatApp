
public class MessageCommand extends Command {


	String message;
	
	
	MessageCommand(CommandType type, String message){
		super(type);
		this.setMessage(message);
	}
	
	
	MessageCommand(String message){
		super(CommandType.MESSAGE);
		this.message=message;
	}
	
	
	void setMessage(String message){
		this.message=message;
	}
	
	String getMessage(){
		return message;
	}
}
