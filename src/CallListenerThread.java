import java.io.IOException;

public class CallListenerThread implements Runnable {

   private String localNick;
   private CallListener callListener;
   private boolean isBusy = callListener.getBusy();//тонко, типа я не знаю, зачем он здесь
   private String remoteIP;
   private String lastAction;
   private boolean stop;
   private Connection remoteConnection;
   private boolean noConnection;
   private CommandType buttonPressed;


    public CallListenerThread(){
        callListener = new CallListener(localNick, false);
    }

    public void run() {
        try {
            while (!stop) {


                while (noConnection = true) {//я знаю, что может подключиться второй, но давай хотя бы пока так
                    remoteConnection = callListener.getConnection();
                    noConnection = false;
                }

                //генерация кнопок АССЕРТ и РИЖЕКТ
                if (buttonPressed.equals(CommandType.ACCEPT))
                    {
                    remoteConnection.accept();
                    callListener.setBusy(true);
                    }else if (buttonPressed.equals(CommandType.REJECT))
                    {
                    remoteConnection.reject();
                    remoteConnection.disconnect();
                    }




            }
        }
            catch(IOException e){
                e.printStackTrace();
            }


    }
    public void setButtonPressed(CommandType buttonPressed) {
        this.buttonPressed = buttonPressed;
    }


    public void kill() {
        stop = true;
    }
}
