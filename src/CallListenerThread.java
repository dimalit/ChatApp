import java.io.IOException;
import java.sql.*;

public class CallListenerThread implements Runnable {

   private String localNick;
   private CallListener callListener;
   private boolean isBusy;
   private String remoteIP;
   private String lastAction;
   private boolean stop;
   private Connection remoteConnection;


    public CallListenerThread(String localNick,Boolean isBusy){
        callListener = new CallListener(localNick, false);
    }

    public void run() {
        try {
            while (!stop) {

                remoteConnection = callListener.getConnection();
                if (remoteConnection == null) continue;
                //генерация кнопок АССЕРТ и РИЖЕКТ

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
        this.isBusy=isBusy;
        callListener.setBusy(isBusy);
    }


    public void kill() {
        stop = true;
    }
}
