public class MessageCommand extends Command {
	   private String message;

	    public MessageCommand(String message) {
	        super(Command.CommandType.valueOf("MESSAGE"));
	        this.message = message;
	    }

	    public String toString (){
	        return message;
	    }

}