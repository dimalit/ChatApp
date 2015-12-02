import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class LFrame extends JFrame {
	JPanel panel = new JPanel();
    Logic logic;

    private static final int Height = 600;
    private static final int Widht = 1000;
	
	JButton Send;
	JButton Apply;
	JButton Disconnect;
	JButton Connect;
	
	JLabel login;
	JLabel addr;
	HistoryView smth;
	
	JTextField textfieldlogin;	
	JTextField EnterIp;
    JTextField mass;

    ContactsView contactsView;

	String text;
	
	Font font = new Font("Verdana", Font.BOLD, 13);

	LineBorder linebord = new LineBorder(Color.BLACK, 1);

	LFrame(final Logic logic){
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
        // !!!!!!!!!
        // ВОТ ТУТ ЕЁ ^ НОРМАЛЬНО РАСПОЛОЖИ И В НЕЙ САМОЙ МОЖЕШЬ МЕНЯТЬ РАЗМЕРЫ. НО ТОЛЬКО РАЗМЕРЫ ПО ДРУГОМУ ПОВОДУ - СПРАШИВАЙ
        // !!!!!!!!!

		addr = new JLabel("remote addr");
		addr.setFont(font);
		addr.setBounds(400,35,100,50);
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
                    logic.setRemoteIP(EnterIp.getText());
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            logic.getHistoryViewModel().clearView();
                            logic.call();
                        }
                    });
                }
            }


            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
       
		mass = new JTextField();
	    mass.setHorizontalAlignment(JTextField.LEFT);
		mass.setBorder(linebord);
		mass.setBounds(10, 480, 650, 75);
        mass.addKeyListener(new KeyListener() {
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


		smth = new HistoryView(logic.getHistoryViewModel());
		smth.setLocation(10, 140);
		smth.setSize(740,300);


        JScrollPane scrollPane = new JScrollPane(smth);
        scrollPane.setLocation(10, 140);
        scrollPane.setSize(740,300);
        scrollPane.setBorder(linebord);
		
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
                logic.setRemoteIP(EnterIp.getText());
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        logic.getHistoryViewModel().clearView();
                        logic.call();

                    }
                });
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
		panel.add(mass);
		panel.add(scrollPane);
		panel.add(EnterIp);
		panel.add(addr);
		panel.add(Send);
		panel.add(Apply);
		panel.add(Disconnect);
        panel.add(Connect);


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
        text = mass.getText();
        if (Protocol.isMessageValid(text)) {
            logic.sendMessage(text);
            logic.getHistoryViewModel().addLocalMessage(text);
            mass.setText("");
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
        }
        else {
            Connect.setEnabled(true);
            Apply.setEnabled(true);
            Send.setEnabled(false);
            Disconnect.setEnabled(false);
            textfieldlogin.setEditable(true);
            EnterIp.setEditable(true);
        }
    }

    public void changeEnterIp(String ip){
        EnterIp.setText(ip);
    }

}
