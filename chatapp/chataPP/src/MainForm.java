import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.Observable;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;

public class MainForm {

	private JFrame frame;
	private JButton apply;
	private JButton connect;
	private JButton disconnect;
	private JButton send;
	private JButton add;
	private JButton delete;
	private JLabel login;
	private JLabel remoteLogin;
	private JLabel remoteAddr;
	private JLabel time;
	private JLabel friends;
	private JList list;
	private JTextArea textLogin;
	private JTextArea textRLogin;
	private JTextArea textRAddr;
	private JTextArea textMess;
	private JScrollPane scrollBar1;
	private JScrollPane scrollBar2;
	private JScrollPane scrollBar3;
	private JScrollPane scrollBar4;
	private JScrollPane scrollBar6;
	private DefaultListModel dlm;
        private CallListenerThread callListenerThread;
        private Connection connection;
        public static MainForm main;
	
	public MainForm() {
		start();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void start() {
		frame = new JFrame();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screen = kit.getScreenSize();
		int w = screen.width;
		int h = screen.height;
		frame = new JFrame("ChatApp");
		frame.setSize(w / 2, h / 2);
		frame.setLocation(w / 4, h / 4);
		
		JPanel top = new JPanel(new GridLayout(1,2,50,0));
		JPanel ltop = new JPanel(new GridLayout(2,2));
		JPanel rtop = new JPanel(new GridLayout(2,3));
		
		login = new JLabel();
		login.setText("Local Login");
		login.setHorizontalAlignment(JLabel.CENTER);
		login.setOpaque(true);
		login.setPreferredSize(new Dimension(70, 25));
		
		textLogin = new JTextArea(2,0);
		textLogin.setEditable(true);
		scrollBar1 = new JScrollPane(textLogin);
		scrollBar1.setViewportView(textLogin);
		
		apply = new JButton("Apply");
		
		time = new JLabel();
		time.setText("Time: 00:00:00");
		time.setOpaque(true);
		time.setPreferredSize(new Dimension(70, 25));
		
		ltop.add(login);
		ltop.add(scrollBar1);
		ltop.add(apply);
		ltop.add(time);
		
		remoteLogin = new JLabel();
		remoteLogin.setText("Remote Login");
		remoteLogin.setHorizontalAlignment(JLabel.CENTER);
		remoteLogin.setOpaque(true);
		remoteLogin.setPreferredSize(new Dimension(70, 25));
		
		textRLogin = new JTextArea(2,0);
		textRLogin.setEditable(true);
		scrollBar2 = new JScrollPane(textRLogin);
		scrollBar2.setViewportView(textRLogin);
		
		connect = new JButton("Connect");
		
		remoteAddr = new JLabel();
		remoteAddr.setText("Remote Addr");
		remoteAddr.setHorizontalAlignment(JLabel.CENTER);
		remoteAddr.setOpaque(true);
		remoteAddr.setPreferredSize(new Dimension(70, 25));
		
		textRAddr = new JTextArea(2,0);
		textRAddr.setEditable(true);
		scrollBar3 = new JScrollPane(textRAddr);
		scrollBar3.setViewportView(textRAddr);
		
		disconnect = new JButton("Disconnect");
		
		rtop.add(remoteLogin);
		rtop.add(scrollBar2);
		rtop.add(connect);
		rtop.add(remoteAddr);
		rtop.add(scrollBar3);
		rtop.add(disconnect);
		
		top.setBackground(Color.LIGHT_GRAY);
		top.add(ltop);
		top.add(rtop);
		
		JPanel bot = new JPanel(new GridLayout(1,2,10,0));
		
		send = new JButton("Send");
		send.setPreferredSize(new Dimension(30, 20));
		
		textMess = new JTextArea(3,5);
		textMess.setEditable(true);
		textMess.setLineWrap(true);
		scrollBar4 = new JScrollPane(textMess);
		scrollBar4.setViewportView(textMess);
		
		bot.setBackground(Color.LIGHT_GRAY);
		bot.add(scrollBar4);
		bot.add(send);
		
		JScrollPane scrollPane = new JScrollPane();

		list = new JList();
		scrollPane.setViewportView(list);
		
		JPanel frend = new JPanel(new BorderLayout());
		JPanel but = new JPanel(new GridLayout(1,2,25,0));
		
		friends = new JLabel();
		friends.setText("Friends");
		friends.setBorder(new LineBorder(Color.BLACK, 1));
		friends.setHorizontalAlignment(JLabel.CENTER);
		friends.setPreferredSize(new Dimension(30, 40));
		
		String[] elements = {"FRIENDSFRIENDSFRIENDSFRIENDS","FRIENDS","FRIENDS","FRIENDS","FRIENDS","FRIENDS","FRIENDS","FRIENDS","FRIENDS","FRIENDS","FRIENDS","FRIENDS","FRIENDS","FRIENDS"};
		JList northList = new JList(elements);
        northList.setLayoutOrientation(JList.VERTICAL);
        scrollBar6 = new JScrollPane(northList);
		scrollBar6.setViewportView(northList);
		
		add = new JButton("Add");
		delete = new JButton("Delete");
		add.setPreferredSize(new Dimension(30, 25));
		
		but.add(add);
		but.add(delete);
		
		frend.add(friends,BorderLayout.NORTH);
		frend.add(scrollBar6,BorderLayout.CENTER);
		frend.add(but,BorderLayout.SOUTH);
	
		frame.add(frend,BorderLayout.EAST);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.add(top, BorderLayout.NORTH);
		frame.add(bot, BorderLayout.SOUTH);
		
		dlm = new DefaultListModel();
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((login.getText().equals("")) || (remoteLogin.getText().equals("")) || (remoteAddr.getText().equals(""))){
					JOptionPane.showMessageDialog(frame, "Not enough data for sending the message");
				}
				else {
					String name = new String();
					if (login.getText().length()>10){
						try {
							name = login.getText();
						} catch (BadLocationException ignore) {}
						name = name + "...";
					}
					else name = login.getText();
					long date = System.currentTimeMillis();
					dlm.addElement("<html>" + name + " " + new Date(date).toLocaleString() + ":<br>" + textMess.getText() + " </span></html>");
					list.setModel(dlm);

					try {
						connection.sendMessage(textMess.getText());
						System.out.println("Sended");
					} catch (IOException ex){
						System.out.println("No internet connection");
					}

				}
				textMess.setText("");
				textMess.requestFocus();
			}
		});
		
		textMess.addKeyListener(new KeyListener(){

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					send.doClick();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		
		apply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (callListenerThread == null) {
                    System.out.println("Added obs");
                    callListenerThread = new CallListenerThread(new CallListener(login.getText()));
                    callListenerThread.addObserver(main);
                }
				else {
					callListenerThread.setLocalNick(login.getText());
				}
			}
		});

		connect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Caller caller = new Caller(login.getText() ,remoteAddr.getText());
					new Thread (new Runnable() {
						@Override
						public void run() {
							try {
								connection = caller.call();

								if(caller.getStatus().toString().equals("OK"))
									remoteAddr.setText(caller.getRemoteNick());
								else
								 if (caller.getStatus().toString().equals("BUSY")){
									 JOptionPane.showMessageDialog(frame, "User " + caller.getRemoteNick() + " is busy");
								 }
								else
								{
									JOptionPane.showMessageDialog(frame, "User " + caller.getRemoteNick() + " has declined your call.");
									connection = null;
								}

							} catch (IOException ex) {                   
								JOptionPane.showMessageDialog(frame, "Connection error. User with ip does not exist or there is no Internet connection");
								connection = null;
							}
						}
					}).start();
			}


		});
		
		disconnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remoteLogin.setText("");
				try {
					connection.disconnect();
					callListenerThread.setBusy(false);
				} catch (IOException ex){

				}
				catch (NullPointerException ex){
					System.out.println("Already disconnect");               
				}
			}
		});
		
	}
	
	public boolean question (String nick, String remoteAddress){
		Object[] options = {"Receive","Reject"};
		int dialogResult = JOptionPane.showOptionDialog(frame,"User "+ nick + " with ip " + remoteAddress +
						" is trying to connect with you","Recive connection",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,options,options[0]);
		if(dialogResult == JOptionPane.YES_OPTION) {
			System.out.println("Receive");
			remoteLogin.setText(nick);
			remoteAddr.setText(remoteAddress);
			return true; 
		}
		System.out.println("Rejected");
			return false; 

	}
	
	public void update(Observable o, Object arg) {
		if(arg instanceof CallListener)
		{
			CallListener c = (CallListener) arg;
			callListenerThread.suspend();
			callListenerThread.setReceive(question(c.getRemoteNick(), c.getRemoteAddress()));
			callListenerThread.resume();
		}
		else
			if (arg instanceof Connection){
				connection = (Connection) arg;
				System.out.println("Output connection created");
			}
		else
		{
			System.out.println("Receive message");
			System.out.println(arg.toString());
			Command command = (Command) arg;

			if (command instanceof MessageCommand) {
				dlm.addElement("<html>" + remoteLogin.getText() + " " + new Date(System.currentTimeMillis()).toLocaleString() + ":<br>" + arg.toString() + " </span></html>");
				list.setModel(dlm);
			}
			else {
				Command check = new Command(Command.CommandType.DISCONNECT);
				if (command.toString().equals("DISCONNECT") || command.toString().equals("REJECTED")){
					JOptionPane.showMessageDialog(frame, "User " + remoteLogin.getText() + " was disconnected");
					callListenerThread.setBusy(false);
					remoteLogin.setText("");
					remoteAddr.setText("");
				}
			}
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                                     main = new MainForm();
                                     main.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
