import java.io.IOException;

public class CallListenerThread implements Runnable {

    private String localNick;
    private CallListener callListener;
    private boolean stop;
    private String remoteNick;
    private Connection remoteConnection;
    private IncomingCallForm form;
    Logic logic;



    public CallListenerThread(String localNick,Boolean isBusy,Logic logic){
        callListener = new CallListener(localNick);
        this.logic = logic;
    }

    public void run() {
        try {
            while (!stop) {

                remoteConnection = callListener.getConnection();
                if (remoteConnection == null) continue;
                remoteNick=callListener.getRemoteNick();
                form = new IncomingCallForm(remoteConnection,logic,remoteNick);
                System.out.println("AZAZA");
                logic.setRemoteNick(remoteNick);
            }
        }
            catch(IOException e){
                e.printStackTrace();
            }


    }


    public Connection getRemoteConnection(){
        return remoteConnection;
    }

    public void setBusy(Boolean isBusy){
        callListener.setBusy(isBusy);
    }

    public void setOnline(Boolean online){
        callListener.setOnline(online);
    }
    
    public void setNick(String nick){
    	localNick=nick;
    	callListener.setNick(localNick);
    }


    public void kill() {
        stop = true;
    }
}
