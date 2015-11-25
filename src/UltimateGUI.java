import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        ///panel.add(Box.createVerticalGlue());
        panel.add(label);
        // panel.add(Box.createVerticalGlue());
        panel.add(okButton);
        // panel.add(Box.createVerticalGlue());
        //add(Box.createHorizontalGlue());

        add(panel);
        //add(Box.createHorizontalGlue());
    }

    private void exit(){
        dispose();
    }
}
