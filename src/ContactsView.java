import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(200, 600));
        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top,BoxLayout.Y_AXIS));
        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom,BoxLayout.Y_AXIS));

        JLabel favouritesL = new JLabel("Favourites");
        JLabel contactsL = new JLabel("Contacts");
        Font font = new Font("Verdana",Font.BOLD,14);
        favouritesL.setFont(font);
        favouritesL.setSize(new Dimension(200, 50));
        contactsL.setFont(font);
        contactsL.setSize(new Dimension(200, 50));

        contacts = new JPanel();
        contacts.setLayout(new BoxLayout(contacts,BoxLayout.Y_AXIS));
        contacts.setMinimumSize(new Dimension(200, 250));

        favourites = new JPanel();
        favourites.setLayout(new BoxLayout(favourites,BoxLayout.Y_AXIS));
        favourites.setMinimumSize(new Dimension(200, 250));

        JScrollPane contactsS = new JScrollPane(contacts);
        contactsS.setMinimumSize(new Dimension(200, 250));
        JScrollPane favouritesS = new JScrollPane(favourites);
        favouritesS.setMinimumSize(new Dimension(200, 250));

        bottom.setPreferredSize(new Dimension(200,300));
        top.setPreferredSize(new Dimension(200, 300));

        top.add(favouritesL);
        top.add(favouritesS);
        bottom.add(contactsL);
        bottom.add(contactsS);

        add(top);
        add(bottom);

        setMinimumSize(new Dimension(200, 600));

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
    }

    public void onlineUpdate(){
        updateUI();
    }


}
