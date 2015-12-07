import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ContactsModel {
	private String nick, ip;
	private boolean isOnline;
	private RandomAccessFile f;

	ContactsModel(String nick, boolean online) {
		this.nick = nick;
		this.isOnline = online;
	}

	ContactsModel(String nick, String ip) {
		this.ip = ip;
		this.nick = nick;
	}

	public String getNick() {
		return nick;
	}

	public String getIp() {
		return ip;

	}

	public void addLocalNick(String nick, String ip) throws IOException {
	f = new RandomAccessFile("LocalContacts.txt", "rw");
		this.ip = ip;
		this.nick = nick;
		f.seek(f.length());
		String str = nick + "|" + ip + "\n";
		f.write(str.getBytes());
		f.close();
	}

	public boolean isOnline() {
		return isOnline;
	}

	public String toString() {
		if (isOnline)
			return nick + " online";
		return nick;
	}
}
