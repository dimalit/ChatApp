import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Caller {
    private String localNick;
    private String remoteIP;
    private String remoteNick;
    private Command lastCommand;
    private NickCommand nickCommand;
    private String lastError;

    public Caller(String localNick,String remoteIP){
        this.localNick=localNick;
        this.remoteIP=remoteIP;
    }

    public Connection call() throws IOException {
        Connection connection = new Connection(new Socket(remoteIP,Protocol.PORT_NUMBER));

        //Проверка, к тому ли мы подключились.
        lastCommand = connection.recieve();
        if (lastCommand.type==CommandType.NICK) {
            nickCommand = (NickCommand) lastCommand;
        }
        else{
            connection.disconnect();
            lastError="Wrong IP";
            return null;
        }

        //Проверка, занят ли тот пользователь
        remoteNick = nickCommand.getNick();
        if (nickCommand.isBusy()){
            lastCommand = connection.recieve();
            connection.disconnect();
            UltimateGUI busyGUI = new UltimateGUI(remoteNick);
            lastError="User is busy";
            remoteNick=null;
            return null;
        }

        //Если не занят, то отсылаем ник и ждём подтверждения
        connection.sendNickHello(localNick);
        lastCommand = connection.recieve();

        //Если Accept - вернуть connection
        if (lastCommand.type==CommandType.ACCEPT) return connection;
        else{
            UltimateGUI ultimateGUI = new UltimateGUI("Failed to connect");
            lastError="User rejected your call";
            connection.disconnect();
            return null;
        }

    }
    public String getRemoteNick(){
        return remoteNick;
    }
}
