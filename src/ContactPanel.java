import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ContactPanel extends JPanel{
    private Contact contact;
    private JLabel label;
    private ImageIcon imageIcon;

    public ContactPanel(Contact contact){
        this.contact=contact;
        createGUI();
    }

    private void createGUI(){
        imageIcon  = new ImageIcon("src/images/off.png");
        label = new JLabel(contact.getNick());
        label.setIcon(imageIcon);
        setBackground(Color.WHITE);
        final PopUp menu = new PopUp(this);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
                setBackground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Color.WHITE);
            }
        });
        setMinimumSize(new Dimension(200,25));
        setMaximumSize(new Dimension(200,25));
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

    public String getNick(){
        return contact.getNick();
    }

    public Contact getContact(){
        return contact;
    }

}
