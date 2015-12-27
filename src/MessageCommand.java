public class MessageCommand extends Command{
    String message;

    public MessageCommand(){
        super(CommandType.MESSAGE);
    }

    public MessageCommand(String message){
        super(CommandType.MESSAGE);
        this.message=message;
    }
}
