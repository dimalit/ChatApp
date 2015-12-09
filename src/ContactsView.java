import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

public class ContactsView extends JPanel{

    private JPanel contacts,favourites;
    private ContactsViewModel contactsViewModel;
    private ArrayList<ContactPanel> list = new ArrayList<ContactPanel>();

    public ContactsView(ContactsViewModel cvm){
        contactsViewModel=cvm;
        contactsViewModel.setContactsView(this);
        createGUI();
    }

    private void createGUI(){
        setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel top = new JPanel();
        top.setBackground(Color.WHITE);
        top.setLayout(new BoxLayout(top,BoxLayout.Y_AXIS));
        JPanel bottom = new JPanel();
        bottom.setBackground(Color.WHITE);
        bottom.setLayout(new BoxLayout(bottom,BoxLayout.Y_AXIS));

        JLabel favouritesL = new JLabel("Favourites");
        JLabel contactsL = new JLabel("Contacts");
        Font font = new Font("Verdana",Font.BOLD,14);
        favouritesL.setFont(font);
        favouritesL.setSize(new Dimension(100, 50));
        favouritesL.setHorizontalTextPosition(0);
        contactsL.setFont(font);
        contactsL.setSize(new Dimension(100, 50));

        contacts = new JPanel();
        contacts.setBackground(Color.WHITE);
        contacts.setLayout(new BoxLayout(contacts,BoxLayout.Y_AXIS));
        contacts.setMinimumSize(new Dimension(175, 150));

        favourites = new JPanel();
        favourites.setLayout(new BoxLayout(favourites,BoxLayout.Y_AXIS));
        favourites.setMinimumSize(new Dimension(175, 150));
        favourites.setBackground(Color.WHITE);

        JScrollPane contactsS = new JScrollPane(contacts);
        contactsS.setMinimumSize(new Dimension(175, 150));
        JScrollPane favouritesS = new JScrollPane(favourites);
        favouritesS.setMinimumSize(new Dimension(175, 150));

        bottom.setPreferredSize(new Dimension(175,200));
        top.setPreferredSize(new Dimension(175, 200));

        top.add(favouritesL);
        top.add(favouritesS);
        bottom.add(contactsL);
        bottom.add(contactsS);

        add(top);
        add(bottom);



    }

    public void fullUpdate(){
        contacts.removeAll();
        favourites.removeAll();
        list.clear();
        ArrayList<Contact> contactsList = (ArrayList<Contact>) contactsViewModel.getList();
        for (Contact contact :  contactsList){
            list.add(new ContactPanel(contact));

        }
        for (ContactPanel cp : list){
            if (cp.isFav()) favourites.add(cp);
            else contacts.add(cp);
        }
        updateUI();
      //  contactsViewModel.onlineUpdate();

    }

    public void onlineUpdate(){
        for (ContactPanel cp : list){
            cp.update();
        }
    }

    public void delete(ContactPanel contact){
        if (contact.isFav()) favourites.remove(contact);
        else contacts.remove(contact);
        list.remove(contact);
        updateUI();
    }

    public void addContact(Contact contact){
        ContactPanel cp = new ContactPanel(contact);
        list.add(cp);
        if (cp.isFav()) favourites.add(cp);
        else contacts.add(cp);
    }


}
