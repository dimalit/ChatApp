import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

public class ContactsViewModel{
    ContactsView contactsView;
    ArrayList<Contact> contactList = new ArrayList<Contact>();


    public ContactsViewModel(ContactsView contactsView){
        this.contactsView=contactsView;
    }

    public void setContactsView(ContactsView cv){
        contactsView=cv;
    }

    public void removeContact(Contact contact, ContactPanel contactPanel){
        contactList.remove(contact);
        contactsView.delete(contactPanel);
    }

    public Collection getList(){
        return contactList;
    }


    public void add(Contact contact){
        contactList.add(contact);
        contactsView.addContact(contact);

    }


}
