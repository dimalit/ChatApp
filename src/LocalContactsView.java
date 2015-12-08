import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

public class LocalContactsView extends DefaultListModel {
	private ContactsModel m;
	private List<ContactsModel> loc;

	LocalContactsView() throws FileNotFoundException {
		loc = new ArrayList<ContactsModel>();
	}

	public void writeLocalNicks() throws IOException {
File file=new File("LocalContacts.txt");
if (file.exists() )
{		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		String[] arr;
		while ((line = reader.readLine()) != null) {
			arr = line.split("|");
			m = new ContactsModel(arr[0], arr[1]);
			loc.add(m);
			addElement(line);
		}
}	}

	public boolean findNick(String ip)
	{if (loc.size()!=0)
	{
		for (ContactsModel model: loc)
		{
			if (model.getIp().equals(ip))
						return true;
		}
	}
	else
	{
		return false;
	}
		return false;
	}

}
