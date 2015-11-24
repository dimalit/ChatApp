import javax.swing.*;
import java.io.IOException;

public class Logic{
    private LFrame mainGui;
    private String localNick = "default",remoteNick,remoteIP;
    private boolean isBusy;
    private Connection connection;
    private Caller caller;
    private CallListenerThread callListenerThread;
    private Thread callThread;
    private HistoryViewModel historyViewModel = new HistoryViewModel(this);
    private CommandListenerThread commandListenerThread;



    public Logic(){
        callListenerThread = new CallListenerThread(localNick,isBusy,this);
        callThread = new Thread(callListenerThread);
        callThread.start();
        mainGui = new LFrame(this);
    }

    public void setLocalNick(String nick){
    	localNick=nick;
    	callListenerThread.setNick(localNick);
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
        mainGui.setBusy(isBusy);
    }

    public void accept(Connection connection){
        System.out.println("logic got Connection!");
        this.connection=connection;
        commandListenerThread = new CommandListenerThread(this.connection,this);
        Thread thread = new Thread(commandListenerThread);
        thread.start();
        setBusy(true);
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
        //блокировка кнопок Apply, Connect
        caller = new Caller(localNick,remoteIP);
        try {
            connection = caller.call();
        } catch (IOException e) {

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
            UltimateGUI ultimateGUI = new UltimateGUI("Failed to connect");
        }
    }

    public void addMessage(String message){
        historyViewModel.addRemoteMessage(message);
    }

    public void disconnect(){
        setBusy(false);
        //разблокировка всякого
        //
        historyViewModel.addSystemMessage("Disconnected");
        historyViewModel.writeHistoryFile();
        remoteNick=null;
        remoteIP=null;
        commandListenerThread.kill();
        connection.disconnect();
        mainGui.setConnected(false);
        setBusy(false);

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

    public static void main(String[] args) {
        Logic logic = new Logic();
    }









}
