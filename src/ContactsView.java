import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

public class ContactsView extends JPanel{

    private JPanel contactsP,favouritesP;

    JLabel contactsL = new JLabel("Contacts");
    JLabel favouritesL = new JLabel("Favourites");

    private ArrayList<ContactPanel> list = new ArrayList<ContactPanel>();

    public ContactsView(){
        createGUI();
    }

    private void createGUI(){
        setBackground(Colors.softGreen);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        setAlignmentX(Component.LEFT_ALIGNMENT);
        add(favouritesL);
        add(Box.createVerticalStrut(10));

        favouritesP = new JPanel();
        favouritesP.setLayout(new BoxLayout(favouritesP,BoxLayout.Y_AXIS));
        favouritesP.setBackground(Colors.softGreen);
        favouritesP.setAlignmentX(Component.LEFT_ALIGNMENT);
        favouritesP.setAutoscrolls(true);
        add(favouritesP);


        add(Box.createVerticalStrut(10));
        add(contactsL);
        add(Box.createVerticalStrut(10));

        contactsP = new JPanel();
        contactsP.setAutoscrolls(true);
        contactsP.setLayout(new BoxLayout(contactsP,BoxLayout.Y_AXIS));
        contactsP.setBackground(Colors.softGreen);
        contactsP.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(contactsP);

        favouritesP.add(new ContactPanel());
        for (int i=0;i<15;i++) {
            contactsP.add(new ContactPanel());
        }



    }

    public void setLabelFont(Font font){
        contactsL.setFont(font);
        favouritesL.setFont(font);
    }









}
