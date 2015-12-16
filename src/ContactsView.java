

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;

public class ContactsView extends DefaultListModel  {
	private String[] str;
	private ContactsModel model;
	private List<ContactsModel> SCont;
	private ContactsModel m;
private ServerConnection server;

	ContactsView(ServerConnection server) throws IOException {
		this.server=server;
		SCont=new ArrayList<ContactsModel>();
		addServerNicks();

	}
public void addServerNicks()
{
	String arr[] = server.getAllNicks();
	str = new String[arr.length];
	for (int i = 0; i < arr.length; i++) {
		model = new ContactsModel(arr[i], server.isNickOnline(arr[i]));
		try {
			addElement(model.toString());
		} catch (NullPointerException e) {
			System.out.println("no one user online on server");
			break;
		}

	}

}


	String[] getStr() {
		return str;
	}

}
