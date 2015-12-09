import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
    public static void main (String[] args) throws IOException {
       
        BufferedReader in = null;
        ServerSocket servers = null;
        Socket fromclient = null;


        try {
            servers = new ServerSocket(28411);
        } catch (IOException e) {
            System.out.println("Couldn't listen to port 4444");
            System.exit(-1);
        }

        try {
            System.out.print("Waiting for a client...");
            fromclient= servers.accept();
            System.out.println("Client connected");
        } catch (IOException e) {
            System.out.println("Can't accept");
            System.exit(-1);
        }
        InputStreamReader isp =  new InputStreamReader(fromclient.getInputStream());
        in  = new BufferedReader(isp);

        System.out.println("Wait for messages");
        String str = "";

        while ((str = in.readLine()) != null) {
            if (str.equals("exit")) break;
            System.out.println(str);
            System.out.println("port = " +fromclient.getPort());
            System.out.println("ip = " +fromclient.getInetAddress());
        }
        in.close();
        fromclient.close();
        servers.close();
    }
}


