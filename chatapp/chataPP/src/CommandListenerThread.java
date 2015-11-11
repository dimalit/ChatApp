
import java.io.IOException;
import java.util.Observable;

public class CommandListenerThread extends Observable implements Runnable {
	private boolean stopFlag;
	private boolean disconnect;
	private Connection connection;
	private Command lastCommand;

	CommandListenerThread() {

	}

	CommandListenerThread(Connection connection) {
		this.connection = connection;

	}

	Command getLastCommand() {
		return lastCommand;
	}

	boolean isDisconnected() {
		return disconnect;
	}

	public void run() {
		while (!isDisconnected()) {

			while (!isDisconnected()) {

				try {
					synchronized (this) {
						this.lastCommand = connection.receive();

						setChanged();
						notifyObservers();
					}
				} catch (IOException e) {

					e.printStackTrace();

				}
			}
		}

	}

	void start() {
		disconnect = false;
		Thread t = new Thread();
		t.start();
	}

	void stop() {
		disconnect = true;

	}

}