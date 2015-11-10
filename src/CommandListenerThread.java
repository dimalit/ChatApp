import java.io.IOException;
import java.util.Observable;

public class CommandListenerThread extends Observable implements Runnable {
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
		if (disconnected)
			return true;
		else
			return false;
	}

	public void run() {
		while (!stopFlag) {

			try {
				synchronized (this) {
					this.lastCommand = con.recive();
					setChanged();
					notifyObservers();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}

		}

	}

	void start() {
		stopFlag = false;
		run();
	}

	void stop() {
		stopFlag = true;

	}

}
