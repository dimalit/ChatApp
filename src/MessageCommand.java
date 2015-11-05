public class MessageCommand extends Command{
    String message;

    public MessageCommand(CommandType type){
        super(type);
    }

    public MessageCommand(CommandType type, String message){
        super(type);
        this.message=message;
    }

    public  void setMessage(String message){
        this.message=message;
    }
}
