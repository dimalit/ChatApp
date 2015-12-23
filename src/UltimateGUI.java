import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class UltimateGUI extends JFrame{

    JLabel okButton;
    Font font;

    public UltimateGUI(String string){
        super("Error");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 100);
        setResizable(false);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        createGui(string);
        setUndecorated(true);
        setVisible(true);


    }

    public void createGui(String string){
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Colors.midGreen));
        panel.setBackground(Colors.softGreen);
        panel.setLayout(null);
        panel.setSize(450,150);

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Main.class.getClassLoader().getResourceAsStream("roboto-thin.ttf")).deriveFont(15f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel label = new JLabel(string);
        label.setLocation(0,0);
        label.setSize(450,50);
        label.setHorizontalAlignment(0);
        label.setFont(font);



        okButton = new JLabel("");
        okButton.setIcon(new ImageIcon(Main.class.getResource("/okN.png")));
        okButton.setLocation(175,50);
        okButton.setSize(97,27);
        okButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                okButton.setIcon(new ImageIcon(Main.class.getResource("/okP.png")));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                okButton.setIcon(new ImageIcon(Main.class.getResource("/okH.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                okButton.setIcon(new ImageIcon(Main.class.getResource("/okN.png")));
            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    okButton.setIcon(new ImageIcon(Main.class.getResource("/okP.png")));

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    dispose();
                }
            }
        });

        panel.add(label);
        panel.add(okButton);
        add(panel);



    }

    public static void main(String[] args) {
        UltimateGUI asd = new UltimateGUI("asd");
    }

    private void exit(){
        dispose();
    }
}
