import java.io.IOException;
import java.net.Socket;

public class ServerCaller {

    public static Connection call() throws IOException {
        Command lastCommand;
        Connection connection = new Connection(new Socket(Protocol.SERVER_IP,Protocol.PORT_NUMBER));
        //Проверка есть ли подключение и туда ли мы присоединились.
        lastCommand = connection.recieve();
        if (lastCommand.type==CommandType.HELLO_CLIENT){
            connection.sendServerHello();
            return connection;
        }
        return null;
    }
}
