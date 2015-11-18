import java.io.*;
import java.net.Socket;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("files.litvinov.in.ua",28411);
        Connection c = new Connection(s,"azazaza");
        //Scanner in = new Scanner(System.in);
        String string;
        //while (true){
        c.sendNickHello("azazazaz");
        System.out.println(c.testRecieve());
        //c.accept();
        System.out.println(c.testRecieve());
        c.reject();
        System.out.println(c.testRecieve());
        //string = in.nextLine();
        c.disconnect();
        //c.sendMessage(string);
        //System.out.println(c.testRecieve());
        //}
    }
}
