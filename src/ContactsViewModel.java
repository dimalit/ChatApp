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
    Logic logic;

    public ContactsViewModel(Logic logic){
        this.logic=logic;
        Thread onlineChecker = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        wait(20000);
                        onlineUpdate();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
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
        ServerConnection serverConnection = logic.getServerConnection();
        String[] nicks = serverConnection.getAllNicks();
        for (String nick : nicks){
            if (nick.equals(logic.getLocalNick())) continue;
            Contact c = new Contact(this, nick, serverConnection.getIpForNick(nick));
            if (contactList.contains(c)) continue;
            contactList.add(c);
        }
        updateView();
    }

    public void add(Contact contact){
        contactList.add(contact);
        contactsView.addContact(contact);
    }

    public void writeToFile() {
        FileWriter out = null;
        try {
            out = new FileWriter("Contacts.txt");
            for (Contact c : contactList){
                out.write(new StringBuilder(c.getNick()).append(" ").append(c.getIP()).append(" ").append(c.isFav()).append("\n").toString());
                out.flush();
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile(){
        Scanner in = null;
        try{
            in = new Scanner(new FileReader("Contacts.txt"));
            String[] tmp;
            while (in.hasNextLine()){
                tmp = in.nextLine().split(" ");
                Contact c = new Contact(this,tmp[0],tmp[1]);
                if (tmp[2].equals("true")) c.setFav(true);
                contactList.add(c);
            }
            in.close();

        } catch (IOException e){
            System.out.println("Failed to find a file Contacts.txt");
        }
    }
}
