

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {
    private ServerSocket serverSocket;
    private Socket socket;
    private Connection user;
    private String nick;
    private String myNick;

    public Connection(Connection user) throws IOException {
        serverSocket = new ServerSocket(28411);
        socket = user.serverSocket.accept();

    }

    public String getNick(){
        return nick;
    }

    public String getMyNick() {
        return myNick;
    }

    public void sendNickHello(Connection user){
        sendMessage("ChatApp 2015 user "+ this.getMyNick());
    }

    public void sendNickBusy(Connection user){
        if (socket.isConnected()){
            sendMessage("Busy");
        }

    }

    void accept(){
        sendNickHello(nick);
    }

    void reject(){
        sendNickBusy(nick);
        disconnect();
    }

    public void sendMessage(String message){
        try{
            serverSocket = new ServerSocket();
            socket = serverSocket.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String instr;
            while (true){
                out.writeUTF(message);
                out.flush();
                System.out.println("sending..");
                instr= in.readUTF();
                System.out.println(instr);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void disconnect(){
        try{
            socket.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
