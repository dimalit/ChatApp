import java.io.IOException;
import java.util.Observable;

public class CommandListenerThread extends Observable implements Runnable {
	private Connection connection;
	private Command lastCommand;
	private boolean falseConnection;

	public CommandListenerThread(Connection conection) {
		this.connection = cnonnection;
		private Command lastCommand;
	}

	public CommandListenerThread(Connection connection) {
		this.connection = connection;
		this.lastCommand = new Command();
	}

	public void setFalseConnection(boolean falseConnection) {
		this.falseConnection = falseConnection;
	}

	public Command getlastCommand() {
		return this.lastCommand;
	}

	public void start() {
		this.falseConnection = false;
		Thread thr = Thread(this);
		thr.start();
	}
	public void stop(){
		falseConnection = true;
	}
