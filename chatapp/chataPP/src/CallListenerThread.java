import java.io.IOException;
import java.net.*;
import java.util.*;
public class CallListenerThread implements Runnable
{

private CallListener listener;
        private CallListener call;
    private volatile boolean isClose;
    private Caller.CallStatus callStatus;
    private Thread t;
    private volatile boolean  isReceive, flag;

    private Observable myObservable = new Observable(){
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(arg);
        }
    };

    public CallListenerThread(){
        t = new Thread(this);
        isClose = false;
        t.start();
    }

    public CallListenerThread(CallListener call){
        this.call = call;
        t = new Thread(this);
        isClose = false;
        t.start();
    }

    public SocketAddress getListenAddress(){
        return call.getListenAddress();
    }

    public String getLocalNick (){
        return call.getLocalNick();
    }

    public SocketAddress getRemoteAddress(){
        return null; //where it take?
    }

    public boolean isBusy (){
        return call.isBusy();
    }

    public void setBusy (boolean isBusy){
        call.setBusy(isBusy);
    }

    public void setListenAddress(InetSocketAddress newAddress){
        call.setListenAddress(newAddress);
    }

    public void setLocalNick (String newNick){
        call.setLocalNick(newNick);
    }

    public void run() {
        while (!isClose) {
            try {
                System.out.println("Before");
                Connection connection = call.getConnection();
                System.out.println("Get");
                myObservable.notifyObservers(call);
                waitAnswer();
                System.out.println("continued");

                if (!isReceive) {
                    call.setBusy(false);
                    System.out.println("False");
                    if (connection == null) {
                        callStatus = Caller.CallStatus.BUSY;
                    }
                    else {
                        callStatus = Caller.CallStatus.REJECTED;  
                        connection.reject();
                    }
                }
                else{
                    System.out.println("OK");
                    callStatus = Caller.CallStatus.OK;        

                    connection.accept();
                    myObservable.notifyObservers(connection);

                    CommandListenerThread commandListenerThread = new CommandListenerThread(connection);
                    commandListenerThread.addObserver(MainForm.window);
                }

            } catch (IOException e) {
                callStatus = Caller.CallStatus.NOT_ACCESSIBLE;  
            }
        }
    }

    public void stop(){
        isClose = true;
    }

    public void setReceive(boolean isReceive){
        this.isReceive = isReceive;
    }

    public void suspend(){
        flag = false;
    }

    private synchronized void waitAnswer(){
        if (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(t + " Thread interrupted: " + e);
            }
        }
    }

    public synchronized void resume(){
        flag = true;
        notify();
    }

    public void addObserver(Observer observer){
        myObservable.addObserver(observer);
    }

    public String getRemoteNick (){
        return call.getRemoteNick();
    }
}

