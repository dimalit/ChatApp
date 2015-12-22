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
    Connection connection;


    boolean isConnected;
    private String remoteNick=null;

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

    public Main getThis(){
        return this;
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
                        connection.sendMessage(mainFrame.getMessageField().getText());
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
        connection.sendContacts(contactsViewModel.getContacts());
        connection.disconnect();
        System.exit(0);

    }

    public void call(String nick) {

        connection.sendCall(nick);
        remoteNick = nick;
    }

    private class CommandListenerThread extends Thread{

        private Command lastCommand;
        private Connection connection;
        private boolean stop;


        public CommandListenerThread(Connection connection){
            this.connection=connection;

        }



        public void run() {

            connection.getContacts("");

            while (!stop) {
                lastCommand = connection.recieve();
                if (lastCommand==null) continue;
                switch (lastCommand.type){
                    case MY_CONTACTS:{
                        MyContactsCommand tmp = (MyContactsCommand) lastCommand;
                        ArrayList<Contact> tmpArr = tmp.getArrayList();
                        System.out.println("Got contacts");
                        for (Contact contact : tmpArr){
                            contact.setContactsViewModel(contactsViewModel);
                            contact.setMain(getThis());
                            contactsViewModel.add(contact);
                        }
                        break;
                    }
                    case LOGOUT:{
                        return;
                    }
                    case BUSY:{
                        UltimateGUI ultimateGUI = new UltimateGUI(remoteNick + " is busy");

                        break;
                    }
                    case OFFLINE:{
                        UltimateGUI ultimateGUI = new UltimateGUI(remoteNick + " is offline");
                        mainFrame.setEnabled(true);
                        break;
                    }
                    case CONTACTS:{
                        ArrayList<Contact> tmp = ((ContactsCommand) lastCommand).getArrayList();
                        for (Contact contact : tmp){
                            contact.setContactsViewModel(contactsViewModel);
                            contact.setMain(getThis());
                        }
                        ContactsFindView contactsFindView = new ContactsFindView(tmp);
                        break;
                    }
                    case DISCONNECT:{
                        System.out.println("Got disconnect");
                        System.exit(0);
                        break;
                    }
                    case DISCONNECT_FROM_USER:{
                        mainFrame.setDisconnected();
                        historyViewModel.addSystemMessage("Disconnected");
                        break;
                    }
                    case CALL:{
                        CallCommand callCommand = (CallCommand) lastCommand;
                        IncomingCallForm incomingCallForm = new IncomingCallForm(callCommand.getNick());
                        break;
                    }
                    case MESSAGE:{
                        historyViewModel.addRemoteMessage(((MessageCommand) lastCommand).message);
                        break;
                    }
                    case ACCEPT:{
                        mainFrame.setConnected();
                        break;
                    }
                    case REJECT:{
                        UltimateGUI ultimateGUI = new UltimateGUI("User rejected your call");
                        break;
                    }


                }
            }
        }

        public void kill(){
            stop = true;
        }
    }

    private class ContactsFindView extends JPanel{
        private JPanel contactsP;

        JLabel contactsL = new JLabel("Contacts");

        private ArrayList<ContactFindPanel> list = new ArrayList<ContactFindPanel>();

        public ContactsFindView(ArrayList<Contact> contacts){
            JFrame frame = new JFrame();
            createGUI();
            for (Contact contact : contacts){
                addContact(contact);
            }

            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setSize(290, 500);
            frame.setResizable(false);
            frame.setLocationRelativeTo(mainFrame);
            frame.add(this);
            frame.setVisible(true);
        }

        private void createGUI(){
            setBackground(Colors.softGreen);
            setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

            setAlignmentX(Component.LEFT_ALIGNMENT);
            add(Box.createVerticalStrut(10));
            add(contactsL);
            contactsL.setFont(mainFrame.getBigFont());
            add(Box.createVerticalStrut(10));

            contactsP = new JPanel();
            JScrollPane scrollPane = new JScrollPane(contactsP);
            scrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
            scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(15, Integer.MAX_VALUE));
            scrollPane.setBorder(null);
            contactsP.setAutoscrolls(true);
            contactsP.setLayout(new BoxLayout(contactsP,BoxLayout.Y_AXIS));
            contactsP.setBackground(Colors.softGreen);
            contactsP.setAlignmentX(Component.LEFT_ALIGNMENT);
            add(scrollPane);
        }

        public void addContact(Contact contact){
            ContactFindPanel tmp = new ContactFindPanel(contact);
            contactsP.add(tmp);
        }

        public void delete(ContactFindPanel contact){
            contactsP.remove(contact);
            list.remove(contact);
            updateUI();
        }

        /////////////////////////////////////////////////////////////////////////////////////////////

        private class ContactFindPanel extends JPanel {
            private Contact contact;
            private JLabel label = new JLabel();

            public ContactFindPanel(){
                createGUI();
            }

            public ContactFindPanel(Contact contact){
                this.contact=contact;
                createGUI();
            }

            private void createGUI(){


                setLayout(null);
                setBackground(Colors.softGreen);
                setMinimumSize(new Dimension(200, 25));
                setPreferredSize(new Dimension(200,25));
                setMaximumSize(new Dimension(200, 25));
                label.setBounds(5, 3, 200, 20);
                label.setFont(mainFrame.getSmallFont());
                label.setText(contact.getNick());
                if (contact.isOnline()) label.setIcon(new ImageIcon("src/images/on.png"));
                else label.setIcon(new ImageIcon("src/images/off.png"));
                add(label);

                addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        setBackground(Colors.midGreen);
                        delete(getThis());
                        contactsViewModel.add(contact);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                setBackground(Colors.softGreen);
                            }
                        });
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                setBackground(Colors.mainGreen);
                            }
                        });

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                setBackground(Colors.softGreen);
                            }
                        });
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

            private ContactFindPanel getThis(){
                return this;
            }

        }


    }

    private class IncomingCallForm extends JFrame {


        private static final int Height = 150;
        private static final int Widht = 300;

        JPanel panel = new JPanel();

        JLabel accept;
        JLabel dismiss;
        JLabel nickName;

        IncomingCallForm(final String username){

            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            setSize(Widht, Height);
            setResizable(false);
            setUndecorated(true);
            setAlwaysOnTop(true);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setBackground(Color.pink);

            nickName = new JLabel(username+ " is calling");
            nickName.setLocation(60, 10);
            nickName.setFont(mainFrame.getSmallFont());
            nickName.setSize(200, 54);

            accept = new JLabel("");
            accept.setLocation(18, 80);
            accept.setSize(97, 27);
            accept.setIcon(new ImageIcon("src/images/acceptN.png"));
            accept.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    connection.accept();
                    System.out.println("Sending Accept!");
                    mainFrame.setEnabled(true);
                    mainFrame.setConnected();
                    mainFrame.setRemoteNick(username);
                    dispose();
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            accept.setIcon(new ImageIcon("src/images/acceptH.png"));
                        }
                    });
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            accept.setIcon(new ImageIcon("src/images/acceptN.png"));
                        }
                    });
                }
            });


            dismiss = new JLabel("");
            dismiss.setLocation(150, 80);
            dismiss.setSize(97, 27);
            dismiss.setIcon(new ImageIcon("src/images/rejectN.png"));
            dismiss.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    connection.reject();
                    System.out.println("Sending Reject!");
                    mainFrame.setEnabled(true);
                    dispose();
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {


                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            dismiss.setIcon(new ImageIcon("src/images/rejectH.png"));
                        }
                    });

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            dismiss.setIcon(new ImageIcon("src/images/rejectN.png"));
                        }
                    });

                }
            });


            panel.add(accept);
            panel.add(dismiss);
            panel.add(nickName);



            this.add(panel);
            setVisible(true);
        }

    }



    public static void main(String[] args) {
        Main main = new Main();
    }
}