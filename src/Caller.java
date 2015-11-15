import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Caller {
    private String localNick;
    private InetAddress remoteIP;
    private int remotePort;
    private String remoteNick;
    private Command lastCommand;

    public Connection call() throws IOException {
        CallListener callListener = new CallListener();
        Connection connection = callListener.getConnection();
        lastCommand = connection.recieve();
        if (lastCommand.equals(new Command(CommandType.ACCEPT))){
            return connection;
        }
        else return null;
    }
}
