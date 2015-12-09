import java.net.*;
import java.io.*;
import java.util.HashMap;
public class Caller 
{
private String localNick;
    private InetSocketAddress remoteAddress;
    private String remoteNick;
    private CallStatus status;
    private static final int port = 28411;

    public Caller (String localNick){
        this.localNick = localNick;
    }

    public Caller(){
        this("Untitled");          
    }

    public Caller (String localNick, String ip){
        this(localNick);
        remoteAddress = new InetSocketAddress(ip, Const.PORT);
    }

    public Caller (String localNick, InetSocketAddress remoteAddress){

        this(localNick);
        this.remoteAddress = remoteAddress;
    }

    public Connection call() throws IOException{            
        Connection connection = new Connection(new Socket(remoteAddress.getAddress(), Const.PORT));
        connection.sendNickHello(localNick);
        Command command = connection.receive();            
        System.out.println("Command = " + command.toString());

        if(command.getClass().equals(NickCommand.class)){   

            remoteNick = ((NickCommand)command).getNick();

            if(((NickCommand)command).isBusy()) {
                status = CALL_STATUS_HASH_MAP.get(Const.ChatApp_VERSION);
            } else {
                command = connection.receive();             
                status = CALL_STATUS_HASH_MAP.get(command.toString());
            }

            if(status.equals(CallStatus.OK)) {               
                System.out.println("Success connection");

                CommandListenerThread commandListenerThread = new CommandListenerThread(connection);
                commandListenerThread.addObserver(MainForm.window);

                return connection;
            }
            else
                return null;
        } else {
            status = CallStatus.NOT_ACCESSIBLE;             
            return null;
        }
    }

    public void setLocalNick(String localNick) {
        this.localNick = localNick;
    }

    public void setRemoteAddress(InetSocketAddress remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public CallStatus getStatus() {
        return status;
    }

    public String getRemoteNick() {
        return remoteNick;
    }

    public SocketAddress getRemoteAddress() {
        return remoteAddress;
    }

    public String getLocalNick() {
        return localNick;
    }

    public String toString(){
        return String.valueOf(status);                     
    }

    static enum CallStatus {
        BUSY, NO_SERVICE, NOT_ACCESSIBLE, OK, REJECTED
    }

    
    static final HashMap<String, CallStatus> CALL_STATUS_HASH_MAP = new HashMap<String, CallStatus>(){{
        put(Const.ChatApp_VERSION, CallStatus.BUSY);
        put(Command.CommandType.ACCEPT.toString(), CallStatus.OK);
        put(Command.CommandType.REJECT.toString(), CallStatus.REJECTED);
    }};

    public static void main(String[] args) throws IOException, InterruptedException{
        Caller c = new Caller("Lammer", "localhost");
        Connection connection = c.call();
        connection.receive();
    }
    
}
