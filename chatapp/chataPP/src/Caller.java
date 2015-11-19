import java.net.*;
import java.io.*;
public class Caller 
{
    private String localNick;
    private InetSocketAddress remoteAddress;
    private String remoteNick;
    private CallStatus status;

    public Caller (String localNick){
        this.localNick = localNick;
    }

    public Caller(){
        this("Untitled");                       // How to create socket in this constructor and in the previous?
    }

    public Caller (String localNick, String ip){
        this(localNick);
        remoteAddress = new InetSocketAddress(ip, Constants.PORT);
    }

    public Caller (String localNick, InetSocketAddress remoteAddress){

        this(localNick);
        this.remoteAddress = remoteAddress;
    }

    public Connection call() throws IOException{            //supposedly method must be called in constructors
        Connection connection = new Connection(new Socket(remoteAddress.getAddress(), Constants.PORT));
        connection.sendNickHello(localNick);
        Command command = connection.receive();             //receive NickCommand
        System.out.println("Command = " + command.toString());

        if(command.getClass().equals(NickCommand.class)){   //If receive exactly NickCommand

            remoteNick = ((NickCommand)command).getNick();

            if(((NickCommand)command).isBusy()) {
                status = CALL_STATUS_HASH_MAP.get(Constants.ChatApp_VERSION);
            } else {
                command = connection.receive();             //if NOT busy then receive OK or REJECT
                status = CALL_STATUS_HASH_MAP.get(command.toString());
            }

            if(status.equals(CallStatus.OK)) {                //Only if Connection is accepted then return it
                System.out.println("Success connection");

                CommandListenerThread commandListenerThread = new CommandListenerThread(connection);
                commandListenerThread.addObserver(MainForm.window);

                return connection;
            }
            else
                return null;
        } else {
            status = CallStatus.NOT_ACCESSIBLE;             //or CallStatus.NO_SERVICE ???
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
        return String.valueOf(status);                      //not sure what exactly this method returns
    }

    static enum CallStatus {
        BUSY, NO_SERVICE, NOT_ACCESSIBLE, OK, REJECTED
    }
       
    
    ublic Connection call() throws IOException{          
        Connection connection = new Connection(new Socket(remoteAddress.getAddress(), Constants.PORT));
        connection.sendNickHello(localNick);
        Command command = connection.receive();             d
        System.out.println("Command = " + command.toString());

        if(command.getClass().equals(NickCommand.class)){  

            remoteNick = ((NickCommand)command).getNick();

            if(((NickCommand)command).isBusy()) {
                status = CALL_STATUS_HASH_MAP.get(Constants.ChatApp_VERSION);
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
    
    
       public static void main(String[] args) throws IOException, InterruptedException{
        Caller c = new Caller("Lammer", "localhost");
        Connection connection = c.call();
        connection.sendMessage("Suka blya");
//        connection.receive();
    } 
    
}
