
public class Command {
	public static enum CommandType {
		ACCEPT, DISCONNECT, MESSAGE, NICK, REJECT;
		/*
		 * public static CommandType valueOf(String s){ switch (s){ case
		 * "Accepted":{ return ACCEPT; } case "Dicsonnect":{ return DISCONNECT;
		 * } case "Message":{ return MESSAGE; } case "Nick":{ return NICK; }
		 * case "Rejected":{ return REJECT; } } return null; }
		 */
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
