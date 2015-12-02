import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;

public class ContactsViewModel implements Runnable {
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
        logic.call();
    }

    public void removeContact(Contact contact){
        contactList.remove(contact);
        updateView();
    }

    public void updateView(){
        contactsView.fullUpdate();
    }


    public void run() {
        Timer timer = new Timer();
    }
}
