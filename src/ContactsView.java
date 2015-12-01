import javax.swing.DefaultListModel;

public class ContactsView extends DefaultListModel {
private String [] str;
private ContactsModel model;
ContactsView()
{
	
}
ContactsView(ServerConnection server)
{
	for (int i=0;i<server. getAllNicks().length;i++)
	{model=new ContactsModel(server.getAllNicks()[i],server.isNickOnline(server.getAllNicks()[i])) ;
	try{
	str[i]=model.toString();
	addElement(model.toString());}
	catch(NullPointerException e){
		System.out.println("no one user online on server");
		break;
	}
			
	}
	
}
String[] getStr()
{
	return str;
}

}
