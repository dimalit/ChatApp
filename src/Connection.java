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
            out.write(new StringBuilder(Protocol.GREETING).append(nick).append("\n").toString().getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNickBusy(String nick){
        try {
            out.write(new StringBuilder(Protocol.GREETING).append(nick).append(" busy").append("\n").toString().getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void accept(){
        try {
            out.write(new StringBuilder(Protocol.ACCEPTED).append("\n").toString().getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reject(){
        try {
            out.write(new StringBuilder(Protocol.REJECTED).append("\n").toString().getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message){
        try {
            out.write(new StringBuilder(Protocol.MESSAGE).append("\n").append(message).append("\n").toString().getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try{
            out.write(new StringBuilder(Protocol.DISCONNECT).append("\n").toString().getBytes("UTF-8"));
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Command recieve(){
        return  Command.getCommand(in);
    }
}
