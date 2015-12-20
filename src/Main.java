import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends JFrame {

    MainGUI mainFrame;
    LogInWindow logInWindow;
    SignUpWindow signUpWindow;
    HistoryViewModel historyViewModel;
    ContactsViewModel contactsViewModel;
    public static Connection connection;

    boolean isConnected;

    public Main() {
        try {
            connection = ServerCaller.call();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (connection==null) {
            UltimateGUI ultimateGUI = new UltimateGUI("Could not connect to main server");
        }
        else {
            createMainFrame();
            createLogInWindow();
            createSignUpWindow();
            historyViewModel = new HistoryViewModel(mainFrame.getHistoryView());
            contactsViewModel = new ContactsViewModel(mainFrame.getContactsView());
        }
    }



    private void createLogInWindow(){
        logInWindow = new LogInWindow();

        logInWindow.getSigninBtn().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                logInWindow.getSigninBtn().setIcon(new ImageIcon("src/images/signingP.png"));
                String tmp = "";
                for (char c : logInWindow.getPassword().getPassword()){
                    tmp+=c;
                }
                tmp = "" + tmp.hashCode();
                connection.sendLogin(logInWindow.getLogin().getText(),tmp);
                Command command = connection.recieve();
                if (command.type==CommandType.ACCEPT) {
                    mainFrame.setLocalNick(logInWindow.getLogin().getText());
                    logInWindow.setVisible(false);
                    signUpWindow.dispose();
                    mainFrame.setVisible(true);
                    CommandListenerThread clt = new CommandListenerThread(connection);
                    clt.start();
                }
                else{
                    UltimateGUI ultimateGUI = new UltimateGUI("Wrong login\\password");
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                logInWindow.getSigninBtn().setIcon(new ImageIcon("src/images/signingN.png"));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                logInWindow.getSigninBtn().setIcon(new ImageIcon("src/images/signingH.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logInWindow.getSigninBtn().setIcon(new ImageIcon("src/images/signingN.png"));
            }
        });

        logInWindow.getSignupBtn().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                logInWindow.getSignupBtn().setIcon(new ImageIcon("src/images/signuptP.png"));
                logInWindow.dispose();
                signUpWindow.setVisible(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                logInWindow.getSignupBtn().setIcon(new ImageIcon("src/images/signuptN.png"));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                logInWindow.getSignupBtn().setIcon(new ImageIcon("src/images/signuptH.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logInWindow.getSignupBtn().setIcon(new ImageIcon("src/images/signuptN.png"));
            }
        });

        logInWindow.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                connection.disconnect();
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    private void createSignUpWindow(){
        signUpWindow = new SignUpWindow();

        signUpWindow.getSignupBtn().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                signUpWindow.getSignupBtn().setIcon(new ImageIcon("src/images/signuptP.png"));
                String tmp = "",tmp1 ="";
                for (char c : signUpWindow.getPassword().getPassword()){
                    tmp+=c;
                }
                for (char c : signUpWindow.getPasswordC().getPassword()){
                    tmp1+=c;
                }
                if (tmp.equals(tmp1)) {
                    tmp = "" + tmp.hashCode();
                    connection.sendSignUp(signUpWindow.getLogin().getText(), tmp);
                    Command command = connection.recieve();
                    System.out.println("Getting accept");
                    if (command.type == CommandType.ACCEPT) {
                        System.out.println("Got accept");
                        mainFrame.setLocalNick(signUpWindow.getLogin().getText());
                        signUpWindow.setVisible(false);
                        signUpWindow.dispose();
                        mainFrame.setVisible(true);
                        CommandListenerThread clt = new CommandListenerThread(connection);
                        clt.start();
                    } else {
                        UltimateGUI ultimateGUI = new UltimateGUI("User with such login already exists");
                    }

                }
                else{
                    UltimateGUI ultimateGUI = new UltimateGUI("Passwords must match!");
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                signUpWindow.getSignupBtn().setIcon(new ImageIcon("src/images/signuptN.png"));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                signUpWindow.getSignupBtn().setIcon(new ImageIcon("src/images/signuptH.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                signUpWindow.getSignupBtn().setIcon(new ImageIcon("src/images/signuptN.png"));
            }
        });

        signUpWindow.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                connection.disconnect();
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    private void createMainFrame(){
        mainFrame = new MainGUI();

        mainFrame.getDisconnectBtn().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getDisconnectBtn().setIcon(new ImageIcon("src/images/disconP.png"));
                    }
                });
            }

            @Override
            public void mousePressed(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getDisconnectBtn().setIcon(new ImageIcon("src/images/disconP.png"));
                    }
                });
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getDisconnectBtn().setIcon(new ImageIcon("src/images/disconN.png"));
                    }
                });
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getDisconnectBtn().setIcon(new ImageIcon("src/images/disconH.png"));
                    }
                });
            }

            @Override
            public void mouseExited(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getDisconnectBtn().setIcon(new ImageIcon("src/images/disconN.png"));
                    }
                });
            }
        });

        mainFrame.getLogoutBtn().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getLogoutBtn().setIcon(new ImageIcon("src/images/logoutP.png"));
                    }
                });
            }

            @Override
            public void mousePressed(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getLogoutBtn().setIcon(new ImageIcon("src/images/logoutP.png"));
                        connection.logout();
                        mainFrame.dispose();
                        createMainFrame();
                        createLogInWindow();
                        createSignUpWindow();
                    }
                });
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getLogoutBtn().setIcon(new ImageIcon("src/images/logoutN.png"));
                    }
                });
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getLogoutBtn().setIcon(new ImageIcon("src/images/logoutH.png"));
                    }
                });
            }

            @Override
            public void mouseExited(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getLogoutBtn().setIcon(new ImageIcon("src/images/logoutN.png"));
                    }
                });
            }
        });

        mainFrame.getSendBtn().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getSendBtn().setIcon(new ImageIcon("src/images/sendP.png"));
                    }
                });
            }

            @Override
            public void mousePressed(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getSendBtn().setIcon(new ImageIcon("src/images/sendP.png"));
                    }
                });
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getSendBtn().setIcon(new ImageIcon("src/images/sendN.png"));
                    }
                });

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getSendBtn().setIcon(new ImageIcon("src/images/sendH.png"));
                    }
                });

            }

            @Override
            public void mouseExited(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getSendBtn().setIcon(new ImageIcon("src/images/sendN.png"));
                    }
                });

            }
        });

        mainFrame.getMessageField().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        mainFrame.setDisconnected();

        mainFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                if (isConnected) {

                }
                else{
                    exit();
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    private void exit(){
        connection.disconnect();
        System.exit(0);
    }



    private class CommandListenerThread extends Thread{

        private Command lastCommand;
        private Connection connection;
        private boolean stop;


        public CommandListenerThread(Connection connection){
            this.connection=connection;

        }

        public void run() {

            connection.getContacts();

            while (!stop) {
                lastCommand = connection.recieve();
                if (lastCommand==null) continue;
                switch (lastCommand.type){
                    case CONTACTS:{
                        ContactsCommand tmp = (ContactsCommand) lastCommand;
                        ArrayList<Contact> tmpArr = tmp.getArrayList();
                        System.out.println("Got contacts");
                        for (Contact contact : tmpArr){
                            contact.setContactsViewModel(contactsViewModel);
                            contactsViewModel.add(contact);
                        }
                        break;
                    }
                    case LOGOUT:{
                        return;
                    }

                }
            }
        }

        public void kill(){
            stop = true;
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}