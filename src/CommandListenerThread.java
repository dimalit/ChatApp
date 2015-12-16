import java.io.IOException;
import java.util.Observable;

public class CommandListenerThread extends Observable implements Runnable {
	private boolean disconnected;
	private Connection connection;
	private Command lastCommand = new Command();

	public CommandListenerThread() {

	}

	public CommandListenerThread(Connection c) {
		ownInit(c);
	}

	void setConnection(Connection c) {
		ownInit(c);
	}

	private void ownInit(Connection connection) {
		this.disconnected = false;
		this.connection = connection;
		this.lastCommand = new Command();
	}

	Command getLastCommand() {
		assert lastCommand == null;
		return lastCommand;
	}

	boolean isDisconnected() {
		return disconnected;
	}

	@Override
	public void run() {
		while (!disconnected) {
			try {
				lastCommand = connection.receive();
				assert lastCommand == null;
				if (lastCommand != null) 
					switch (lastCommand.type) {
					case MESSAGE:
					case NICK:
					case ACCEPT:
						disconnected = false;
						break;
					case DISCONNECT:
					case REJECT:
						disconnected = true;
						break;
					default: {
						connection.reject();
						disconnected = true;
						break;
					}
					}
				this.setChanged();
				this.notifyObservers();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	void start() {
		disconnected = false;
		Thread t = new Thread(this);
		t.start();
	}

	void stop() {
		disconnected = true;
	}

}
