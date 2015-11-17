/**
 * Created by Pavel on 17.11.2015.
 */
public class Command {
    private String command;
    private String returning;

    public Command(String s) {
        command = s;
    }

    public void getresult() {
        switch (command) {
            case "sendMsg": {
                returning = "cmdsendMsg";
                break;
            }
            case "Accepted":{
                returning="cmdAccept";
                break;
            }
            case"Busy":{
                returning="cmdBusy"
                break;
            }
            case "Rejected":{
                returning="cmdReject";
                break;
            }
            case "Disconnected":{
                returning="cmdDiscon";
                break;
            }
            case ""

        }

    }
}
