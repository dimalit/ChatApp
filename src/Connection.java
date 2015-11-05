import java.io.*;
import java.net.Socket;
import java.util.Scanner;

class Connection {
    private Socket socket;
    private Scanner in;
    private DataOutputStream out;

    private Connection(Socket socket){
        this.socket=socket;
        try {
            in = new Scanner(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            //ошибка при попытке создать Connection, как-то показать
        }
    }


    void sendNickHello(String nick){
        try {
            out.writeUTF(new StringBuilder(Protocol.GREETING).append(nick).toString()); //"nick" - временно, потом заменим на непосредственно некую перемненную
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendNickBusy(String nick){
        try {
            out.writeUTF(new StringBuilder(Protocol.GREETING).append(nick).append(" busy").toString()); //"nick" - временно, потом заменим на непосредственно некую перемненную
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    void accept(){
        try {
            out.writeUTF(Protocol.ACCEPTED);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void reject(){
        try {
            out.writeUTF(Protocol.REJECTED);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendMessage(String message){
        try {
            out.writeUTF(new StringBuilder(Protocol.MESSAGE).append("\n").append(message).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void disconnect(){
        try{
            out.writeUTF(Protocol.DISCONNECT);
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    Command recieve(){
        Command command;
        String recievedString = in.nextLine();
        command = Command.getCommand(recievedString);
        String temporary;
        //если сообщение или ник, то еще читать его.
        return  command;
    }

    //Делалось совместно с Сергеем Александровичем через Skype


}
