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
        setBackground(Colors.softGreen);
        setMinimumSize(new Dimension(200, 25));
        setPreferredSize(new Dimension(200,25));
        setMaximumSize(new Dimension(200, 25));
        label.setBounds(5, 3, 200, 20);
        label.setFont(font);
        label.setText(contact.getNick());
        if (contact.isOnline()) label.setIcon(new ImageIcon("src/images/on.png"));
        else label.setIcon(new ImageIcon("src/images/off.png"));
        add(label);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PopUp popUp = new PopUp(getThis());
                popUp.show(label,e.getX(),e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Colors.mainGreen);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Colors.softGreen);
            }
        });
    }

    public boolean isFav(){
        return contact.isFav();
    }

    public void update(){
        if (contact.isOnline()) label.setIcon(new ImageIcon("src/images/on.png"));
        else label.setIcon(new ImageIcon("src/images/off.png"));
    }

    public String getNick(){
        return contact.getNick();
    }

    public Contact getContact(){
        return contact;
    }

    private ContactPanel getThis(){
        return this;
    }


}
