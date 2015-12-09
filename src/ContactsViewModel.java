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
        logic.getMainGui().changeEnterIp(contact.getIP());
        logic.setRemoteIP(contact.getIP());
        logic.call();

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
        ServerConnection serverConnection = logic.getServerConnection();
        String[] nicks = serverConnection.getAllNicks();
        for (String nick : nicks){
            if (nick.equals(logic.getLocalNick())) continue;
            contactList.add(new Contact(this, nick, serverConnection.getIpForNick(nick)));
        }
        updateView();
    }


}
