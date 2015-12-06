import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Connection{
    private Socket socket;
    private OutputStream out;
    private PrintWriter sout;
    private BufferedReader reader;

    public Connection(Socket s) throws IOException{
        this.socket = s;
        out = socket.getOutputStream();
        sout = new PrintWriter(out,true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
    }

    public void sendMessage(String message) throws IOException{
        sout.print("Message\n"+message+"\n");
        sout.flush();
    }

    public void disconnect() throws IOException{
        sout.print("Disconnect\n");
        out.close();
        reader.close();
        socket.close();
    }

    public void sendNickHello(String nick) throws IOException {
        sout.print("ChatApp 2015 user "+nick+"\n");
        sout.flush();
    }

    public void sendNickBusy(String nick) throws IOException {
        if(socket.isConnected()){
            sout.print("ChatApp 2015 user "+nick+" busy\n");
            sout.flush();
        }
    }

    public void accept() throws IOException{
        sout.print("Accepted\n");
        sout.flush();
    }

    public void reject() throws IOException {
        sout.print("Rejected\n");
        sout.flush();
    }

    public void testRecieve() throws IOException {
        PrintWriter w = new PrintWriter(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
        String string;
        Scanner r = new Scanner(new InputStreamReader(socket.getInputStream(),"UTF-8"));
        while(r.hasNextLine()) {
            System.out.println(r.nextLine());
        }
    }

    public Command recieve() throws IOException {
       // BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
        String s= reader.readLine();
        if(s.contains("Message")){
            s+=reader.readLine();
        }
        Command command = Command.createCommand(s);
        return command;
    }
    public Socket getSocket(){
        return socket;
    }
}
