import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewContactFrame extends JFrame{

    JButton Cancel;
    JButton Accept;

    JTextArea nickArea;
    JTextArea ipArea;


    public NewContactFrame(ContactsViewModel cvm){
        super("Create new contact");
        createGUI(cvm);
    }

    private void createGUI(final ContactsViewModel cvm){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300,150);
        setResizable(false);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);





        JPanel panel = new JPanel();
        panel.setLayout(null);


        nickArea = new JTextArea("Nick");
        nickArea.setLocation(18, 15);
        nickArea.setSize(250,20);
        ipArea = new JTextArea("IP");
        ipArea.setLocation(18, 46);
        ipArea.setSize(250,20);


        Accept = new JButton("Accept");
        Accept.setLocation(18, 80);
        Accept.setSize(130,30);
        Accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Contact c = new Contact(cvm,nickArea.getText(),ipArea.getText());
                cvm.add(c);
                close();
            }
        });

        Cancel = new JButton("Cancel");
        Cancel.setLocation(150, 80);
        Cancel.setSize(130, 30);
        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });


        panel.add(Accept);
        panel.add(Cancel);
        panel.add(nickArea);
        panel.add(ipArea);



        this.add(panel);
        setVisible(true);
    }

    public void close(){
        dispose();
    }

}
