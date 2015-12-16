import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class ContactPanel extends JPanel{
    private Contact contact;
    private JLabel label = new JLabel();
    private Font font;

    public ContactPanel(){
        createGUI();
    }

    public ContactPanel(Contact contact){
        this.contact=contact;
        createGUI();
    }

    private void createGUI(){
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,new File("src/font/roboto-thin.ttf")).deriveFont(15f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLayout(null);
        setBackground(Colors.midGreen);
        setMinimumSize(new Dimension(200, 25));
        setPreferredSize(new Dimension(200,25));
        setMaximumSize(new Dimension(200, 25));
        label.setBounds(5, 3, 200, 20);
        label.setFont(font);
        label.setText("Contact exz");
        label.setIcon(new ImageIcon("src/images/on.png"));
        add(label);
    }
/*
    public boolean isFav(){
        return contact.isFav();
    }

    public void update(){
        if (contact.getNick()!=label.getText()) label.setText(contact.getNick());
        if (contact.isOnline()) label.setIcon(new ImageIcon("src/images/on.png"));
        else label.setIcon(new ImageIcon("src/images/off.png"));
    }

    public String getNick(){
        return contact.getNick();
    }

    public Contact getContact(){
        return contact;
    }
*/
    public static void main(String[] args) {
        final JFrame test = new JFrame();
        test.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        test.add(new ContactPanel());
        test.setVisible(true);

    }

}
