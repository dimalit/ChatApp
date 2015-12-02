import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Logic{
    private LFrame mainGui;
    private String localNick = "default",remoteNick,remoteIP;
    private boolean isBusy;
    private Connection connection = null;
    private Caller caller;
    private CallListenerThread callListenerThread;
    private Thread callThread;
    private HistoryViewModel historyViewModel = new HistoryViewModel(this);
    private CommandListenerThread commandListenerThread;
    private ServerConnection serverConnection = new ServerConnection();
    private ContactsViewModel contactsViewModel = new ContactsViewModel(this);

    public Logic(){
        String tmp = getNickFromFile();
        if (tmp!=null) localNick=tmp;
        else localNick="default";
        callListenerThread = new CallListenerThread(localNick,isBusy,this);
        callThread = new Thread(callListenerThread);
        callThread.start();
        mainGui = new LFrame(this);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        serverConnection.setServerAddress("jdbc:mysql://files.litvinov.in.ua/chatapp_server?characterEncoding=utf-8&useUnicode=true");
        serverConnection.connect();
        serverConnection.setLocalNick(localNick);
        serverConnection.goOnline();

        contactsViewModel.getData();
    }

    public void setLocalNick(String nick){
    	localNick=nick;
    	callListenerThread.setNick(localNick);
        serverConnection.setLocalNick(localNick);
        writeNickToFile();
    }

    public void setRemoteNick(String nick){
        remoteNick = nick;
    }

    public void setRemoteIP(String IP){
        remoteIP = IP;
    }

    public void setBusy(boolean isBusy) {
        this.isBusy = isBusy;
        callListenerThread.setBusy(isBusy);
    }

    public void accept(Connection connection){
        System.out.println("logic got Connection!");
        this.connection=connection;
        commandListenerThread = new CommandListenerThread(this.connection,this);
        Thread thread = new Thread(commandListenerThread);
        thread.start();
        setBusy(true);
        historyViewModel.clearView();
        historyViewModel.addSystemMessage("Connected to "+remoteNick);
        System.out.println("GOT CONNECTION, YEY!");
        mainGui.setConnected(true);


    }

    public void reject(){
        connection.reject();

    }

    public void sendMessage(String message){
        connection.sendMessage(message);
    }

    public void call(){
        mainGui.setConnected(true);
        setBusy(true);
        caller = new Caller(localNick,remoteIP,this);
        try {
            connection = caller.call();
        } catch (IOException e) {
            UltimateGUI ultimateGUI = new UltimateGUI("Failed to connect");
        }
        if (connection!=null){
            remoteNick=caller.getRemoteNick();
            commandListenerThread = new CommandListenerThread(connection,this);
            Thread comThread = new Thread(commandListenerThread);
            comThread.start();
            historyViewModel.addSystemMessage("Connected to "+remoteNick);
            System.out.println("CONNECTED, YEY");
        }
        else{
            mainGui.setConnected(false);
            setBusy(false);

        }
    }

    public void addMessage(String message){
        historyViewModel.addRemoteMessage(message);
    }

    public void disconnect() {
        if (isConnected()) {
            setBusy(false);
            historyViewModel.addSystemMessage("Disconnected");
            historyViewModel.writeHistoryFile();
            remoteNick = null;
            remoteIP = null;
            commandListenerThread.kill();
            connection.disconnect();
            connection = null;
            mainGui.setConnected(false);
        }

    }

    public boolean isBusy(){
        return isBusy;
    }

    public void updateGUI(){
        //
    }

    public String getLocalNick(){
        return localNick;
    }

    public String getRemoteNick(){
        return remoteNick;
    }

    public HistoryViewModel getHistoryViewModel(){
        return historyViewModel;
    }

    public LFrame getMainGui(){
        return mainGui;
    }

    public void writeNickToFile(){
        FileWriter out = null;
        try {
            out = new FileWriter("nick.txt");
            out.write(localNick);
            out.close();
        } catch (IOException e) {
            System.out.println("Could not create nick.txt");
        }

    }

    public String getNickFromFile(){
        BufferedReader in = null;
        try{
            in = new BufferedReader(new FileReader("nick.txt"));
            String nick = in.readLine();
            in.close();
            if (nick!=null){
                return nick;
            }
            else {
                return null;
            }

        } catch (IOException e){
            System.out.println("Failed to find a file nick.txt");
            return null;
        }

    }

    public static void main(String[] args) {
        Logic logic = new Logic();
    }

    public boolean isConnected(){
        if (connection!=null) return true;
        else return false;
    }

    public ContactsViewModel getContactsViewModel(){
        return contactsViewModel;
    }

    public void exit(){
        disconnect();
        serverConnection.goOffline();
        serverConnection.disconnect();
    }

    public ServerConnection getServerConnection(){
        return serverConnection;
    }
}
