
public class Command {

    public Command.CommandType type;

    public static enum CommandType {ACCEPT, DISCONNECT, MESSAGE, NICK, REJECT;}

    public Command(Command.CommandType t) {
        type = t;
    }

    public void sendNickHello(String nick) {
        // TODO
    }

    public void sendNickBusy(String nick) {
        // TODO
    }

    public void accept() {

    }

    public void reject() {

    }

    public void sendMessage(String Message) {

    }

    public void disconnect() {

    }
}
