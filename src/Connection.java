
import java.net.*;
import java.io.*;
public class Connection{
    private ServerSocket ss;
    private Socket s;
    private final static int PORT = 28411;
    private String distNick;
    private String myNick;

    public Connection(String nick, String ip) throws IOException{
        ss = new ServerSocket(PORT);
        s = ss.accept();
    }

    public void sendMessage(String message){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream(),"UTF-8"));
            PrintWriter out = new PrintWriter(s.getOutputStream(),true);
            String input;
            while((input = in.readLine()) != null){
                out.write(message);
                input = in.readLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void Disconnect(){
        try {
            s.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void SetConnetion(Connection newUser) throws IOException{
        Connection user = new Connection("2","2");
        getNick(newUser);
        sendNick(newUser);
    }

    public String getDistNick() {
        return distNick;
    }

    public String getMyNick() {
        return myNick;
    }

    public void getNick(Connection user){
        this.distNick = user.getMyNick();
    }

    public void sendNick(Connection user){
        user.distNick = getMyNick();
    }

    public void HelloMessage(String s){
        sendMessage("ChatApp 2015 user " + this.getMyNick());
    }

    public void Busy(){
        if(s.isConnected()){
            sendMessage("ChatApp 2015 user " + this.getMyNick() + " busy");
        }
    }

    public void accept(Connection newUser) throws IOException{
        SetConnetion(newUser);
        HelloMessage(newUser.getDistNick());
    }

    public void decline(){
        Busy();
        Disconnect();
    }

}
