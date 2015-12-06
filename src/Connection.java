import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Connection{
    private Socket socket;
    private OutputStream out;
    private DataOutputStream sout;
    private BufferedReader reader;

    public Connection(Socket s) throws IOException{
        this.socket = s;
        out = socket.getOutputStream();
        sout = new DataOutputStream(socket.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
    }

    public void sendMessage(String message) throws IOException{
        sout.write(new StringBuilder("Message\n").append(message).append("\n").toString().getBytes());
        sout.flush();
    }

    public void disconnect() throws IOException{
        sout.write(new StringBuilder("Disconnect\n").toString().getBytes());
        out.close();
        reader.close();
        socket.close();
    }

    public void sendNickHello(String nick) throws IOException {
        sout.write(new StringBuilder("ChatApp 2015 user "+nick+"\n").toString().getBytes());
        sout.flush();
    }

    public void sendNickBusy(String nick) throws IOException {
        if(socket.isConnected()){
            sout.write(new StringBuilder("ChatApp 2015 user ").append(nick).append(" busy\n").toString().getBytes());
            sout.flush();
        }
    }

    public void accept() throws IOException{
        sout.write(new StringBuilder("Accepted\n").toString().getBytes());
        sout.flush();
    }

    public void reject() throws IOException {
        sout.write(new StringBuilder("Rejected\n").toString().getBytes());
        sout.flush();
    }

    public Command recieve() throws IOException {
       // BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
        String s = reader.readLine();
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
