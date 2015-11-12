import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Pavel on 12.11.2015.
 */
public class Connection {
    PrintWriter pw;

    public Connection(Socket soc) throws IOException {
        Socket sc =soc;
        pw = new PrintWriter(soc.getOutputStream());
    }

    public void sendNickHello(String ver,String nick) {
        pw.println(ver+" user "+nick);
    }
    public void sendNickBusy(String ver,String nick){
        pw.println(ver+" user "+nick+" busy");
    }
    public void accept(){
        pw.println("Accepted");
    }
    public void reject(){
        pw.println("Rejected");
        pw.close();
    }
    public void Disconnect(String nick){
        pw.println("User "+nick+"Was disconnected");
        pw.close();
    }

public void receive{

    }

}
