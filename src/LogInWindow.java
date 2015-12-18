import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class LogInWindow extends JFrame {
    JTextArea login;
    JPasswordField password;

    JLabel signupBtn,signinBtn;

    public LogInWindow(){
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(new Dimension(300,200));
        setLocation(getX()-150,getY()-100);
        setAlwaysOnTop(true);
        setResizable(false);
        createGUI();
        setVisible(true);
    }

    private void createGUI(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Colors.softGreen);
        panel.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Colors.midGreen));
        add(panel);

        Font font = null;
        try {
           font = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/roboto-thin.ttf")).deriveFont(15f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        login = new JTextArea();
        login.setFont(font);
        login.setForeground(Color.lightGray);
        login.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Colors.midGreen));
        login.setBackground(Color.white);
        login.setText("Login");
        login.setBounds(55, 30, 182, 30);
        //login.

        login.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (login.getText().equals("Login")) {
                    login.setForeground(Color.black);
                    login.setText("");
                    password.setForeground(Color.black);
                    password.setText("");
                    signinBtn.setEnabled(true);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        login.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (login.getText().equals("Login")) {
                    login.setForeground(Color.black);
                    login.setText("");
                    password.setForeground(Color.black);
                    password.setText("");
                    signinBtn.setEnabled(true);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        panel.add(login);

        password = new JPasswordField();
        password.setFont(font);
        password.setBounds(55,65,182,30);
        password.setForeground(Color.lightGray);
        password.setBackground(Color.white);
        password.setText("password");
        password.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Colors.midGreen));
        panel.add(password);

        password.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                String tmp ="";
                for ( char c : password.getPassword() ){
                    tmp=tmp+c;
                }
                System.out.println(tmp);
                if (tmp.equals("password")){
                    password.setForeground(Color.black);
                    password.setText("");
                    login.setForeground(Color.black);
                    login.setText("");
                    signinBtn.setEnabled(true);
                }


            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        signinBtn = new JLabel("");
        signinBtn.setIcon(new ImageIcon("src/images/signingN.png"));
        signinBtn.setDisabledIcon(new ImageIcon("src/images/signingD.png"));
        signinBtn.setBounds(40,105,97,27);
        signinBtn.setEnabled(false);
        panel.add(signinBtn);

        signupBtn = new JLabel("");
        signupBtn.setIcon(new ImageIcon("src/images/signuptN.png"));
        signupBtn.setDisabledIcon(new ImageIcon("src/images/signuptD.png"));
        signupBtn.setBounds(157,105,97,27);
        signupBtn.setEnabled(true);
        panel.add(signupBtn);

    }

    public JLabel getSignupBtn() {
        return signupBtn;
    }

    public JLabel getSigninBtn() {
        return signinBtn;
    }

    public static void main(String[] args) {
        LogInWindow asd = new LogInWindow();
    }



}