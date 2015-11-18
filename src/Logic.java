import java.io.IOException;

public class Logic{
    //ссылка на GUI
    private String localNick = "default",remoteNick,remoteIP;
    private boolean isBusy;
    private Connection connection;
    private Caller caller;
    private CallListenerThread callListenerThread;
    private Thread callThread;

    public Logic(/*ссылка на GUI*/){
        callListenerThread = new CallListenerThread(localNick,isBusy);
        callThread = new Thread(callListenerThread);
        callThread.start();
        //this.GUI = GUI;
    }

    public void setLocalNick(){
        /* localNick = GUI.getLocalNick() */
    }

    public void setRemoteNick(String nick){
        remoteNick = nick;
    }

    public void setRemoteIP(){
        // remoteIP = GUI.getRemoteIP();        
    }

    public void setBusy(boolean isBusy) {
        this.isBusy = isBusy;
        callListenerThread.setBusy(isBusy);

    }

    public void accept(){
        connection.accept();
       // callListenerThread.setButtonPressed(CommandType.ACCEPT);
    }

    public void reject(){
        connection.reject();
       // callListenerThread.setButtonPressed(CommandType.REJECT);
    }

    public void call(){
        setRemoteIP();
        //блокировка кнопок Apply, Connect
        caller = new Caller(localNick,remoteIP);
        try {
            connection = caller.call();
        } catch (IOException e) {
            //не удалось дозвониться по какой-то причине
        }
        if (connection!=null){
            CommandListenerThread commandListenerThread = new CommandListenerThread(connection);
            Thread comThread = new Thread(commandListenerThread);
            comThread.start();
            setBusy(true);
        }
    }

    public void disconnect(){
        setBusy(false);
        //разблокировка всякого
        //
        remoteNick=null;
        remoteIP=null;
        connection.disconnect();
    }

    public void updateGUI(){
        //
    }





}
