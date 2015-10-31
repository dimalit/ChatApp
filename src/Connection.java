

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class Connection {
    private ServerSocket serverSocket;
    private Socket socket;
    private String userNick;
    private String myNick;

    private Connection(Connection user) throws IOException {
        serverSocket = new ServerSocket(28411);
        socket = user.serverSocket.accept();
    }

    public String getUserNick(){
        return userNick;
    }

    String getMyNick() {
        return myNick;
    }

    void getNick(Connection newUser) {
        this.userNick = newUser.getMyNick();
    }
    void sendNick(Connection newUser){
        newUser.userNick=getMyNick();
    }

    void sendNickHello(String user){
        sendMessage("ChatApp 2015 user "+ this.getMyNick());
    }

    void sendNickBusy(String user){
        if (socket.isConnected()){
            sendMessage("Busy");
        }

    }


    void setConnection(Connection newUser){
        try {
            Connection user = new Connection(newUser);
            getNick(newUser);
            sendNick(newUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void accept(){
        sendNickHello(userNick);
    }

    void reject(){
        sendNickBusy(userNick);
        disconnect();
    }

    void sendMessage(String message){
        try{
            Connection(newUser);
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
