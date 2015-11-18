import javax.swing.*;
import java.io.IOException;

public class Logic{
    private MainGui mainGui;
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
        mainGui = new MainGui(this);
    }

    public void setLocalNick(String nick){
        localNick=nick;
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
        System.out.println("GOT CONNECTION, YEY!");

    }

    public void reject(){
        connection.reject();

    }

    public void sendMessage(String message){
        connection.sendMessage(message);
    }

    public void call(){
        //блокировка кнопок Apply, Connect
        caller = new Caller(localNick,remoteIP);
        try {
            connection = caller.call();
        } catch (IOException e) {
            //не удалось дозвониться по какой-то причине
        }
        if (connection!=null){
            remoteNick=caller.getRemoteNick();
            commandListenerThread = new CommandListenerThread(connection,this);
            Thread comThread = new Thread(commandListenerThread);
            comThread.start();
            setBusy(true);
            System.out.println("CONNECTED, YEY");
        }
    }

    public void addMessage(String message){
        historyViewModel.addRemoteMessage(message);
    }

    public void disconnect(){
        setBusy(false);
        //разблокировка всякого
        //
        remoteNick=null;
        remoteIP=null;
        commandListenerThread.kill();
        connection.disconnect();
        historyViewModel.addSystemMessage("Disconnected");
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
