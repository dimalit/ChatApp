import java.io.IOException;

public class Logic{
    //ссылка на GUI
    private String localNick,remoteNick,remoteIP;
    private boolean isBusy;
    private Connection connection;
    private CallListener callListener;
    private Caller caller;

    public Logic(){
        //старт CallListenerThread
        //старт GUI
        //
    }

    public void setLocalNick(){
        /* localNick = GUI.getLocalNick() */;
    }

    public void setRemoteNick(String nick){
        remoteNick = nick;
    }

    public void setRemoteIP(){
        // remoteIP = GUI.getRemoteIP();        
    }

    public void setBusy(boolean isBusy) {
        this.isBusy = isBusy;
        callListener.setBusy(isBusy);
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





}
