import java.io.*;
import java.net.Socket;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {

        Socket s = new Socket("files.litvinov.in.ua",28411);
        Connection c = new Connection(s,"azazaza");
        c.sendNickHello();
        Scanner in = new Scanner(System.in);
        String string;
        while (true){
            string = in.nextLine();
            c.sendMessage(string);
        }
}
}
