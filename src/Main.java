
import java.net.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
//        InetAddress a = InetAddress.getByName(args[0]);
        ServerSocket ss = new ServerSocket();
        Socket s = new Socket("files.litvinov.in.ua",28411);
        Connection c = new Connection(s);

    }
}
