
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

	}

	Command getLastCommand() {
		return lastCommand;
	}


	public void run() {
		while (!disconnect) {

			while (!disconnect) {

				try {
					synchronized (this) {
						this.lastCommand = connection.receive();
                                                    if (lastCommand != null)
                                                        if ((lastCommand.commandType == (Command.CommandType.DISCONNECT)
								|| (lastCommand.commandType == (Command.CommandType.REJECT)))) {
							disconnect = true;
							System.out.println("test");
						}
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