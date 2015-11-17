import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Pavel on 12.11.2015.
 */
public class Connection {
    private PrintWriter pw;
    private Scanner scr;
    private Socket sc;

    public Connection(Socket soc) throws IOException {
        sc =soc;
        pw = new PrintWriter(sc.getOutputStream());
        scr = new Scanner(new InputStreamReader(sc.getInputStream()));
    }

    public void sendNickHello(String ver,String nick) {
        pw.println("sendMsg");
        pw.println(ver+" user "+nick);
        pw.flush();
    }
    public void sendNickBusy(String ver,String nick){
        pw.println("sendMsg");
        pw.println(ver+" user "+nick+" busy");
        pw.println("Busy");
    }
    public void accept(){
        pw.println("Accepted");
        pw.flush();
    }
    public void reject() throws IOException {
        pw.println("sendMsg");
        pw.println("User rejected you");
        pw.println("Rejected");
        pw.flush();
        pw.close();
        sc.close();
    }
    public void Disconnect(String nick) throws IOException {
        pw.println("sendMsg");
        pw.println("User "+nick+"Was disconnected");
        pw.println("Disconnected");
        pw.flush();
        pw.close();
        sc.close();
    }

    public Command cmd receive() throws IOException {

    }
    public void sendMsg(String msg){
        System.out.println(msg);
        pw.println(msg);
        pw.flush();
    }

}
