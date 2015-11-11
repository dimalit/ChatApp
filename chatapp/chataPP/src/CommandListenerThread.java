
import java.io.IOException;
import java.util.Observable;

public class CommandListenerThread  {
	private boolean stopFlag;
	private boolean disconnected;
	private Connection con;
	private Command lastCommand;

	CommandListenerThread() {

	}

	CommandListenerThread(Connection con) {
		this.con = con;

	}

	Command getLastCommand() {
		return lastCommand;
	}

	boolean isDisconnected() {
		return disconnected;
	}


}