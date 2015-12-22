import java.util.*;


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


    public String getContacts() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Contact contact : contactList){
            stringBuilder.append(" ").append(contact.getNick()).append(" ").append(contact.isFav());
        }
        return stringBuilder.toString();
    }
}
