import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by bresya on 15.11.2015.
 */
public class CallListener {
    private String remoteNick;
    private boolean isBusy;
    private InetAddress remoteIP;
    private int remotePort;

    public CallListener(){
        //посмотрим, что тут
    }

    public Connection getConnection() throws IOException {
        Connection connection = new Connection(new Socket(remoteIP,remotePort));
        Command command = connection.recieve();
        if (!isBusy){
            if (command.equals(new Command(CommandType.NICK))){
                connection.sendNickHello(remoteNick);
                return connection;
            }
            else return null;
        }
        else connection.sendNickBusy(remoteNick);
        return null;

    }
}
