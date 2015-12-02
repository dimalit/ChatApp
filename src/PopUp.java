import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUp extends JPopupMenu {

    JMenuItem delete,fav,call;
    Contact contact;


    public PopUp(Contact contact){
        this.contact = contact;
        createGUI();

    }

    private void createGUI(){
        delete = new JMenuItem("Delete");
        fav = new JMenuItem();
        if (contact.isFav()){
            fav.setText("Remove from favourites");
        }
        else{
            fav.setText("Add to favourites");
        }
        call = new JMenuItem("Call");

        add(call);
        add(fav);
        add(delete);

        call.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contact.call();
            }
        });
        fav.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contact.changeFav();
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contact.remove();
            }
        });
    }
}