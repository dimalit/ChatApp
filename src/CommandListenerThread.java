import java.util.HashMap;


public class CommandListenerThread  implements Runnable {
    private Command lastCommand;
    private Connection connection;
    private boolean stop;


    public CommandListenerThread(Connection connection){
        this.connection=connection;

    }

    public void run() {
        while (!stop) {
            lastCommand = connection.recieve();
            if (lastCommand==null) continue;
            if (lastCommand.type==CommandType.MESSAGE){
                MessageCommand mc = (MessageCommand) lastCommand;

            }

            if (lastCommand.type==CommandType.DISCONNECT){

            }
        }
    }




    public void kill(){
        stop = true;
    }
}
