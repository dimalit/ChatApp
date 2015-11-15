import java.io.*;
import java.net.Socket;
import java.util.Scanner;

class Connection{
    private Socket socket;
    private Scanner in;
    private DataOutputStream out;

    public Connection(Socket socket){
        this.socket=socket;
        try {
            in = new Scanner(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            //ошибка при попытке создать Connection, как-то показать
        }
    }


    public void sendNickHello(String nick){
        try {
            out.writeUTF(new StringBuilder(Protocol.GREETING).append(nick).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNickBusy(String nick){
        try {
            out.writeUTF(new StringBuilder(Protocol.GREETING).append(nick).append(" busy").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void accept(){
        try {
            out.writeUTF(Protocol.ACCEPTED);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reject(){
        try {
            out.writeUTF(Protocol.REJECTED);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message){
        try {
            out.writeUTF(new StringBuilder(Protocol.MESSAGE).append("\n").append(message).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try{
            out.writeUTF(Protocol.DISCONNECT);
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Command recieve(){
        return  Command.getCommand(in);
    }
}
