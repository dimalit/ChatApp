import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UltimateGUI extends JFrame{

    public UltimateGUI(String string){
        super();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,120);
        setResizable(false);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setTitle("Error"); //?
        createGui(string);
        setVisible(true);

    }

    public void createGui(String string){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(300,150);

        JLabel label = new JLabel(string);
        label.setLocation(0,0);
        label.setSize(300,50);
        label.setHorizontalAlignment(0);

        JButton okButton = new JButton("OK");
        okButton.setLocation(110,50);
        okButton.setSize(75,25);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
        okButton.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER)
                    exit();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        panel.add(label);
        panel.add(okButton);
        add(panel);



    }

    private void exit(){
        dispose();
    }
}
