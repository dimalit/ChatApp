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
		return disconnected;
	}

	public void run() {
		while (!isDisconnected()) {

			try {
				synchronized (this) {
					this.lastCommand = con.receive();
					
					setChanged();
					notifyObservers();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}

		}

	}

	void start()
	{
		disconnected= false;
		Thread t=new Thread();
		t.start();
	}

	void stop() {
		disconnected = true;

	}

}
