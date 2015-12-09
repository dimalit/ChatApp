import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class OptionsFrame extends JFrame {

    JCheckBox autoAddContacts;
    JCheckBox contacts;
    JCheckBox history;
    JCheckBox nick;

    public OptionsFrame(){
        super("Options");
        createGUI();
    }

    private void createGUI(){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(200, 140);
        setResizable(false);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        autoAddContacts = new JCheckBox("Add Contacts automatically");
        contacts = new JCheckBox("Save Contacts");
        history = new JCheckBox("Save History");
        nick = new JCheckBox("Save Nick");

        contacts.setLocation(10,10);
        contacts.setSize(120, 15);
        if (Options.saveContacts) contacts.setSelected(true);
        if (Options.saveHistory) history.setSelected(true);
        if (Options.saveNick) nick.setSelected(true);
        if (Options.autoSaveContacts) autoAddContacts.setSelected(true);
        contacts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (contacts.isSelected()) Options.saveContacts = true;
                else Options.saveContacts = false;
            }
        });
        history.setLocation(10, 30);
        history.setSize(120, 15);
        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (history.isSelected()) Options.saveHistory=true;
                else Options.saveHistory=false;
            }
        });
        nick.setLocation(10,50);
        nick.setSize(120,15);
        nick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nick.isSelected()) Options.saveNick=true;
                else Options.saveNick=false;
            }
        });

        autoAddContacts.setLocation(10,70);
        autoAddContacts.setSize(195,15);
        autoAddContacts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (autoAddContacts.isSelected()) Options.autoSaveContacts=true;
                else Options.autoSaveContacts=false;
            }
        });

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                close();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        panel.add(contacts);
        panel.add(history);
        panel.add(nick);
        panel.add(autoAddContacts);

        add(panel);
        setVisible(true);
    }

    public void close(){
        dispose();
    }


}
