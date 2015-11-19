import java.io.*;

public class CallListenerThread implements Runnable {
    private String nick;
    private String lastAction;
    private String ip;
    private String remoteNick;
    private boolean isBusy;
    private Connection remoteConnection;
    private CallListener callListener;
    private boolean flag;
    private IncomingConnection IC;
    /*private поле с объектом формочки*/

    public CallListenerThread(String nick,/*адресс типо*/ /*здесь должен быть объект работающей формочки*/) {
        callListener = CallListener(nick, /*адресс типо*/);
        /*this.формочка = формочка*/
    }

    public Connection getRemoteConection() {
        return remoteConnection;
    }

    public void Stop() {
        flag = true;
    }

    public void run() {
        try {
            while (!flag) {
                remoteConnection = callListener.getConnection();
                if (remoteConnection == null) {
                    continue;
                }
                remoteNick = callListener.getRemoteNick();
                IC = new IncomingConnection(remoteConnection, /*формочка*/);
                /*формочка.setRemoteNick(remoteNick)*/

                /*в этой форме будет метод
                public void setRemoteNick(String nick){
                    remoteNick = nick;
                } */

            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}