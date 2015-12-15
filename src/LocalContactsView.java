import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;

public class LocalContactsView extends DefaultListModel {
	private ContactsModel m;
	private int number;

	LocalContactsView() throws FileNotFoundException {
	}

	public void writeLocalNicks() throws IOException {
		File file = new File("LocalContacts.txt");
		if (file.exists()) {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			String[] arr;
			while ((line = reader.readLine()) != null) {
				addElement(line);
				number++;
			}
		}
	}

	public boolean findNick(String nick, String ip) {
		if (!isEmpty()) {
			String str = nick + "|" + ip;
			if (contains(str))
				return true;
		}
		return false;
	}

}
