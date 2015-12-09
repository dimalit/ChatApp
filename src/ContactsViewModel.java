import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;

public class ContactsViewModel{
    ContactsView contactsView;
    ArrayList<Contact> contactList = new ArrayList<Contact>();
    Logic logic;

    public ContactsViewModel(Logic logic){
        this.logic=logic;
    }

    public void setContactsView(ContactsView cv){
        contactsView=cv;
    }

    public Collection getList(){
        return contactList;
    }

    public void call(Contact contact){
        logic.call(contact.getIP());

    }

    public void removeContact(Contact contact, ContactPanel contactPanel){
        contactList.remove(contact);
        contactsView.delete(contactPanel);
    }

    public void updateView(){
        contactsView.fullUpdate();
    }

    public void onlineUpdate(){
        ServerConnection serverConnection = logic.getServerConnection();
        for (Contact contact : contactList){
            if (serverConnection.isNickOnline(contact.getNick())){
                contact.setOnline(true);
            }
        }
        contactsView.onlineUpdate();
    }

    public void getData(){
       // ServerConnection serverConnection = logic.getServerConnection();
       // String[] nicks = serverConnection.getAllNicks();
       // for (String nick : nicks){
       //     if (nick.equals(logic.getLocalNick())) continue;
       //     contactList.add(new Contact(this, nick, serverConnection.getIpForNick(nick)));
       // }
        contactList.add(new Contact(this,"Vasy","234"));
        contactList.add(new Contact(this,"Vasert","2384"));
        contactList.add(new Contact(this,"Vautr","2334"));
        contactList.add(new Contact(this,"Vaityw","2234"));
        updateView();
    }

    public void add(Contact contact){
        contactList.add(contact);
        contactsView.addContact(contact);
    }


}
