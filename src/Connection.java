import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Connection {
    private Socket s;
    private Scanner in;
    private PrintWriter out;
    public static final int inetPort  = 465;
    String nick;


    public Connection(Socket socket, String localNick) throws IOException {
        this.nick = localNick;
        this.s = socket;
        in = new Scanner(this.s.getInputStream(),"UTF-8");
        out = new PrintWriter(this.s.getOutputStream(), true);
    }

    public boolean isOpen() {
        return s != null;
    }

    public boolean isConnected() {
        return s.isConnected();
    }

    public void SendNickHello(String Nickname) throws IOException {
        out.write(("ChatApp 2015 user " + Nickname + '\n'));
        out.flush();
    }

    public void SendNickBusy(String Nickname) throws IOException {
        out.write(("ChatApp 2015 user " + Nickname + " busy" + '\n'));
    }

    public void sendMessage(String messege) throws IOException {
        out.write((messege + '\n'));
        out.flush();
    }

    public void accept() throws IOException {
        out.write(("Accepted"));
        out.write('\n');
        out.flush();
    }

    public void reject() throws IOException{
        out.write(("Rejected\n"));
        out.flush();
    }

    public void closeSocket()throws IOException{
        s.close();
    }

    public void disconnect() throws IOException{
        out.write(("Disconnected\n"));
        out.flush();

    }

    public Command receive() throws IOException{
        return Command.createCommand(in.nextLine());
    }

    public String waitMessage(){
        StringBuilder stringBuilder =new StringBuilder();
        while (in.hasNextLine())
            stringBuilder.append(in.nextLine());
        return stringBuilder.toString();
    }




}