import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class MainGUI extends JFrame {
	JPanel panel = new JPanel();
    Logic logic;

    private static final int Height = 600;
    private static final int Widht = 1000;
	
	JButton Send;
	JButton Apply;
	JButton Disconnect;
	JButton Connect;
    JButton AddNewContact;
    JButton Options;
    JToggleButton toggleOffOnline;
	
	JLabel login;
	JLabel remoteAdress;
	HistoryView historyView;
	
	JTextField textfieldlogin;	
	JTextField EnterIp;
    JTextField messageArea;

    ContactsView contactsView;

	String text;
	
	Font font = new Font("Verdana", Font.BOLD, 13);

	LineBorder linebord = new LineBorder(Color.BLACK, 1);

	MainGUI(final Logic logic){
        this.logic=logic;

		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		login = new JLabel("login:");
		login.setFont(font);
		login.setBounds(35, 40, 60, 30);
		textfieldlogin = new JTextField(logic.getLocalNick());
		textfieldlogin.setBounds(80, 40, 115, 30);
		textfieldlogin.setBorder(linebord);
        textfieldlogin.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    applyNick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        contactsView = new ContactsView(logic.getContactsViewModel());

        contactsView.setBounds(0,20,175,400);


		remoteAdress = new JLabel("remote remoteAdress");
		remoteAdress.setFont(font);
		remoteAdress.setBounds(400,35,100,50);
		EnterIp = new JTextField("files.litvinov.in.ua");
		EnterIp.setBounds(500,40,150,30);
		EnterIp.setBorder(linebord);
        EnterIp.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            logic.getHistoryViewModel().clearView();
                            logic.call(EnterIp.getText());
                        }
                    });
                }
            }


            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        Options = new JButton("Options");
        Options.setLocation(820,60);
        Options.setSize(150,25);
        Options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionsFrame opf = new OptionsFrame();
            }
        });
       
		messageArea = new JTextField();
	    messageArea.setHorizontalAlignment(JTextField.LEFT);
		messageArea.setBorder(linebord);
		messageArea.setBounds(10, 480, 650, 75);
        messageArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    send();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


		historyView = new HistoryView(logic.getHistoryViewModel());
		historyView.setLocation(10, 140);
		historyView.setSize(740,300);

        toggleOffOnline = new JToggleButton();

        toggleOffOnline.setLocation(200,48);
        toggleOffOnline.setSize(17, 17);
        toggleOffOnline.setBorderPainted(false);
        toggleOffOnline.setFocusable(false);
        toggleOffOnline.setBorder(null);
        toggleOffOnline.setMargin(new Insets(0, 0, 0, 0));
        toggleOffOnline.setPressedIcon(new ImageIcon("src/images/off.png"));
        toggleOffOnline.setDisabledIcon(new ImageIcon("src/images/off.png"));
        toggleOffOnline.setContentAreaFilled(false);
        toggleOffOnline.setFocusPainted(false);
        toggleOffOnline.setIcon(new ImageIcon("src/images/on.png"));

        toggleOffOnline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (logic.isOnline()) {
                    toggleOffOnline.setIcon(new ImageIcon("src/images/off.png"));
                    toggleOffOnline.setPressedIcon(new ImageIcon("src/images/on.png"));
                     logic.setOnline(false);
                }
                else{
                    toggleOffOnline.setIcon(new ImageIcon("src/images/on.png"));
                    toggleOffOnline.setPressedIcon(new ImageIcon("src/images/off.png"));
                    logic.setOnline(true);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(historyView);
        scrollPane.setLocation(10, 140);
        scrollPane.setSize(740,300);
        scrollPane.setBorder(linebord);

        AddNewContact = new JButton("Add new Contact");
        AddNewContact.setLocation(820,30);
        AddNewContact.setSize(150,25);
        AddNewContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewContactFrame ncf = new NewContactFrame(logic.getContactsViewModel());
            }
        });
		
		Apply = new JButton("Apply");
		Apply.setLocation(80, 80);
		Apply.setSize(75,30);
		Apply.setFont(font);
        Apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyNick();
            }
        });
		
		Disconnect = new JButton("Disconnect");
		Disconnect.setLocation(600,80);
		Disconnect.setSize(130,30);
		Disconnect.setFont(font);
        Disconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.disconnect();
            }
        });


		
	    Connect  = new JButton("Connect");
	    Connect.setLocation(450,80);
	    Connect.setSize(100, 30);
	    Connect.setFont(font);
        Connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.getHistoryViewModel().clearView();
                logic.call(EnterIp.getText());
            }
        });

		Send = new JButton("Send");
		Send.setLocation(680, 530);
		Send.setSize(100,30);
		Send.addActionListener(new ActionListener( ) {
        	public void actionPerformed(ActionEvent ae) {
        		send();
        	}
        });

        final JFrame frame = this;
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (logic.isConnected()){
                    if (JOptionPane.showConfirmDialog(frame,
                            "You have an established connection with another user.\n Are you sure you want to close the program?", "Warning",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                        exit();
                    }
                }
                else{
                    exit();
                }

            }
        });
		contactsView.setLocation(800,100);
		panel.add(contactsView);
		panel.add(login);
		panel.add(textfieldlogin);
		panel.add(messageArea);
		panel.add(scrollPane);
		panel.add(EnterIp);
		panel.add(remoteAdress);
        panel.add(toggleOffOnline);
		panel.add(Send);
		panel.add(Apply);
		panel.add(Disconnect);
        panel.add(Connect);
        panel.add(AddNewContact);
        panel.add(Options);


        this.add(panel);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(Widht, Height);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("ChatApp");
        setConnected(false);
        setVisible(true);
		
	
	}

    public void exit(){
        logic.exit();
        System.exit(0);
    }

    public void send(){
        text = messageArea.getText();
        if (Protocol.isMessageValid(text)) {
            logic.sendMessage(text);
            logic.getHistoryViewModel().addLocalMessage(text);
            messageArea.setText("");
        }
    }

    public void applyNick(){
        text=textfieldlogin.getText().replaceAll("\\s+", "_").replaceAll("\\-+","-").replaceAll("_+","_");
         if (text.endsWith("_")) text=text.substring(0,text.length()-1);
        textfieldlogin.setText(text);
        if (Protocol.isNickValid(text))logic.setLocalNick(text);
        else {
            UltimateGUI ultimateGUI = new UltimateGUI("Only latin, numbers and \"_\" \"-\" ");
        }
    }

    public void setConnected(boolean b){
        if (b){
            Connect.setEnabled(false);
            Apply.setEnabled(false);
            Send.setEnabled(true);
            Disconnect.setEnabled(true);
            textfieldlogin.setEditable(false);
            EnterIp.setEditable(false);
            messageArea.setEnabled(true);
            toggleOffOnline.setEnabled(false);
        }
        else {
            Connect.setEnabled(true);
            Apply.setEnabled(true);
            Send.setEnabled(false);
            Disconnect.setEnabled(false);
            messageArea.setEnabled(false);
            textfieldlogin.setEditable(true);
            EnterIp.setEditable(true);
            toggleOffOnline.setEnabled(true);
        }
    }

    public void changeEnterIp(String ip){
        EnterIp.setText(ip);
    }

    public void setOffline(){
        Connect.setEnabled(false);
        Apply.setEnabled(true);
        Send.setEnabled(false);
        Disconnect.setEnabled(false);
        textfieldlogin.setEditable(true);
        EnterIp.setEditable(false);
    }

}
