import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.DefaultListModel;

public class ContactsView extends DefaultListModel {
	private String[] str;
	private ContactsModel model;
	private RandomAccessFile f;

	ContactsView() {

	}

	ContactsView(ServerConnection server) {
		String arr[] = server.getAllNicks();
		str = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			model = new ContactsModel(arr[i], server.isNickOnline(arr[i]));
			try {
				str[i] = model.toString();
				addElement(model.toString());
			} catch (NullPointerException e) {
				System.out.println("no one user online on server");
				break;
			}

		}

	}


}
