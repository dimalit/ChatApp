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
        Connection connection = new Connection(new Socket(remoteIP,remotePort));
        connection.sendNickHello(localNick);
        lastCommand = connection.recieve();
        if (lastCommand.equals(new Command(CommandType.NICK))){
            if (lastCommand.equals(new Command(CommandType.ACCEPT))){
                return connection;
            }
            else return null;
        }
        else return null;
    }
}
