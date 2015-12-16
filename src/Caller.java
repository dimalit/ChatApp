import java.net.*;
import java.io.*;

public class Caller {
    private Socket s;

    public Caller() throws IOException{
        s = new Socket(Protocol.IP,Protocol.PORT);
    }

    public Connection call() throws IOException {
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    	} catch (ClassNotFoundException e) {}
        Connection c = new Connection(s);
        c.sendNickHello(Protocol.localNick);
        return c;
    }
}
