import java.io.IOException;
import java.util.Observable;

public class CommandListenerThread extends Observable implements Runnable {
	private boolean stopFlag;
	private boolean disconnected;
	private Connection con;
	private Command lastCommand = new Command();

	public CommandListenerThread() {
		// TODO Auto-generated constructor stub
	}

	public CommandListenerThread(Connection connection) {
		this.disconnected = false;
		this.con = connection;
		this.lastCommand = new Command();
	}

	void setConnection(Connection con) {
		disconnected = false;
		this.con = con;
		lastCommand = new Command();
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
				Command tmp = con.receive();
				if (lastCommand != null) {
					//System.out.println(lastCommand.getClass() + lastCommand.toString());
					if ((lastCommand.type == (Command.CommandType.DISCONNECT)
							|| (lastCommand.type.toString().equals("Rejected")))) {
						disconnected = true;
						System.out.println("test");

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
		Thread t = new Thread(this);
		t.start();
	}

	void stop() {
		disconnected = true;

	}

}
