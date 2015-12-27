import java.io.IOException;

public class CallListenerThread {
    /*

    private String localNick;
    private CallListener callListener;
    private boolean stop;
    private String remoteNick;
    private Connection remoteConnection;
    private IncomingCallForm form;




    public CallListenerThread(String localNick,Boolean isBusy){
        callListener = new CallListener(localNick);

    }

    public void run() {
        try {
            while (!stop) {

                remoteConnection = callListener.getConnection();
                if (remoteConnection == null) continue;
                remoteNick=callListener.getRemoteNick();
                form = new IncomingCallForm(remoteConnection,remoteNick);
                System.out.println("AZAZA");

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
    }*/
}
