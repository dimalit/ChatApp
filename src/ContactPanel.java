import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ContactPanel extends JPanel{
    private Contact contact;
    private JLabel label;
    private ImageIcon imageIcon;

    public ContactPanel(final Contact contact){
        this.contact=contact;
        imageIcon  = new ImageIcon("src/images/off.png");
        label = new JLabel(contact.getNick());
        label.setIcon(imageIcon);
        setBackground(Color.getHSBColor(188, 0, 97));
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PopUp menu = new PopUp(contact);
                menu.show(e.getComponent(), e.getX(), e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Color.getHSBColor(188,32,100));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Color.getHSBColor(188,0,97));
            }
        });
        setMinimumSize(new Dimension(200,50));
        add(label);
    }

    public boolean isFav(){
        return contact.isFav();
    }

    public void update(){
        if (contact.getNick()!=label.getText()) label.setText(contact.getNick());
        if (contact.isOnline()) label.setIcon(new ImageIcon("src/images/on.png"));
        else label.setIcon(new ImageIcon("src/images/off.png"));
    }

}
