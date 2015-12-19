import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

public class OptionsFrame extends JFrame {

    JCheckBox autoAddContacts;
    JCheckBox contacts;
    JCheckBox history;
    JCheckBox nick;
    static Font font;

    static {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/roboto-thin.ttf")).deriveFont(15f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public OptionsFrame(){
        super("Options");
        createGUI();
    }

    private void createGUI(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 140);
        setResizable(false);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Colors.softGreen);

        autoAddContacts = new JCheckBox("Add Contacts automatically");
        contacts = new JCheckBox("Save Contacts");
        history = new JCheckBox("Save History");
        nick = new JCheckBox("Save Nick");

        setStyle(autoAddContacts);
        setStyle(contacts);
        setStyle(history);
        setStyle(nick);

        if (Options.saveContacts) contacts.setSelected(true);
        if (Options.saveHistory) history.setSelected(true);
        if (Options.saveNick) nick.setSelected(true);
        if (Options.autoSaveContacts) autoAddContacts.setSelected(true);

        contacts.setLocation(10,10);
        contacts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (contacts.isSelected()) Options.saveContacts = true;
                else Options.saveContacts = false;
            }
        });

        history.setLocation(10, 30);
        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (history.isSelected()) Options.saveHistory=true;
                else Options.saveHistory=false;
            }
        });

        nick.setLocation(10,50);
        nick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nick.isSelected()) Options.saveNick=true;
                else Options.saveNick=false;
            }
        });

        autoAddContacts.setLocation(10,70);
        autoAddContacts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (autoAddContacts.isSelected()) Options.autoSaveContacts=true;
                else Options.autoSaveContacts=false;
            }
        });

        panel.add(contacts);
        panel.add(history);
        panel.add(nick);
        panel.add(autoAddContacts);

        add(panel);
        setVisible(true);
    }

    private static void setStyle(JCheckBox checkBox){
        checkBox.setIcon(new ImageIcon("src/images/notchecked.png"));
        checkBox.setSelectedIcon(new ImageIcon("src/images/checked.png"));
        checkBox.setRolloverIcon(new ImageIcon("src/images/notcheckedH.png"));
        checkBox.setRolloverSelectedIcon(new ImageIcon("src/images/checkedH.png"));
        checkBox.setFont(font.deriveFont(15f));
        checkBox.setBackground(Colors.softGreen);
        checkBox.setIconTextGap(8);
        checkBox.setSize(250,15);
        checkBox.setBorderPainted(false);
    }

    public static void main(String[] args) {
        OptionsFrame op = new OptionsFrame();
    }
}
