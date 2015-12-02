
public class MessageCommand extends Command {


	String message;
	
	
	MessageCommand(){
		super(CommandType.MESSAGE);
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
