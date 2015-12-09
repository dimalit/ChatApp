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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;

public class MainForm implements Observer {

	private JFrame frame;
	private JTextArea textLogin;
	private JTextArea textRLogin;
	private JTextArea textRAddr;
	private JTextArea textMess;
	private CallListenerThread callListenerThread;
	public static MainForm window;
	private DefaultListModel dlm;
	private JList list;
	private Connection connection;
	private ServerConnection server;
	private String[][] frendmass = new String[1000][3];
	private String[] headers = { "Name", "IP","Online" };
	private Integer size = 0;
	private File file = new File("Friends.txt");
	private JTable frends;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MainForm();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	public MainForm() throws FileNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		server = new ServerConnection();
		server.setServerAddress("jdbc:mysql://files.litvinov.in.ua/chatapp_server?characterEncoding..");
		server.connect();
		readFriends();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screen = kit.getScreenSize();
		int w = screen.width;
		int h = screen.height;
		frame = new JFrame("ChatApp");
		frame.setSize(800 ,  500);
		frame.setLocation(w / 4, h / 4);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		JPanel panel_1 = new JPanel(new GridLayout(1, 2, 50, 0));
		JPanel ltop = new JPanel(new GridLayout(2, 2));
		JPanel rtop = new JPanel(new GridLayout(2, 3));

		JLabel login = new JLabel();
		login.setText("Local Login");
		login.setHorizontalAlignment(JLabel.CENTER);
		login.setOpaque(true);
		login.setPreferredSize(new Dimension(70, 25));

		textLogin = new JTextArea(2, 0);
		textLogin.setEditable(true);
		JScrollPane scrollBar1 = new JScrollPane(textLogin);
		scrollBar1.setViewportView(textLogin);

		JButton apply = new JButton("Apply");

		JPanel conectFrends = new JPanel(new GridLayout(1, 2, 10, 0));

		JLabel time = new JLabel();
		time.setText("Time: 00:00:00");
		time.setOpaque(true);
		time.setPreferredSize(new Dimension(70, 25));

		ltop.add(login);
		ltop.add(scrollBar1);
		ltop.add(apply);
		ltop.add(time);

		JLabel remoteLogin = new JLabel();
		remoteLogin.setText("Remote Login");
		remoteLogin.setHorizontalAlignment(JLabel.CENTER);
		remoteLogin.setOpaque(true);
		remoteLogin.setPreferredSize(new Dimension(70, 25));

		textRLogin = new JTextArea(2, 0);
		textRLogin.setEditable(true);
		JScrollPane scrollBar2 = new JScrollPane(textRLogin);
		scrollBar2.setViewportView(textRLogin);

		JButton connect = new JButton("Connect");

		JLabel remoteAddr = new JLabel();
		remoteAddr.setText("Remote Addr");
		remoteAddr.setHorizontalAlignment(JLabel.CENTER);
		remoteAddr.setOpaque(true);
		remoteAddr.setPreferredSize(new Dimension(70, 25));

		textRAddr = new JTextArea(2, 0);
		textRAddr.setEditable(true);
		JScrollPane scrollBar3 = new JScrollPane(textRAddr);
		scrollBar3.setViewportView(textRAddr);

		JButton disconnect = new JButton("Disconnect");

		rtop.add(remoteLogin);
		rtop.add(scrollBar2);
		rtop.add(connect);
		rtop.add(remoteAddr);
		rtop.add(scrollBar3);
		rtop.add(disconnect);

		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.add(ltop);
		panel_1.add(rtop);

		frame.add(panel_1, BorderLayout.NORTH);

		JPanel panel_2 = new JPanel(new BorderLayout());
		JButton send = new JButton("Send");
		send.setPreferredSize(new Dimension(100, 30));

		textMess = new JTextArea(3,5);
		textMess.setEditable(true);
		textMess.setLineWrap(true);
		JScrollPane scrollBar4 = new JScrollPane(textMess);
		scrollBar4.setViewportView(textMess);

		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.add(scrollBar4,BorderLayout.CENTER);
		panel_2.add(send,BorderLayout.EAST);

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		frame.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane, BorderLayout.CENTER);

		list = new JList();
		scrollPane.setViewportView(list);

		JPanel frend = new JPanel(new BorderLayout());
		JPanel but = new JPanel(new GridLayout(1, 2, 25, 0));

		JLabel friends = new JLabel();
		friends.setText("Friends");
		friends.setBorder(new LineBorder(Color.BLACK, 1));
		friends.setHorizontalAlignment(JLabel.CENTER);
		friends.setPreferredSize(new Dimension(30, 40));

		frends = new JTable(frendmass, headers);
		frends.setColumnSelectionAllowed(false);
		frends.setRowSelectionAllowed(false);
		frends.setCellSelectionEnabled(true);
		frends.setPreferredScrollableViewportSize(new Dimension(200, 100));
		JScrollPane scrollBar6 = new JScrollPane(frends);
		scrollBar6.setViewportView(frends);


		JButton add = new JButton("Add");
		JButton delete = new JButton("Delete");
		add.setPreferredSize(new Dimension(30, 25));

		but.add(add);
		but.add(delete);

		
		frend.add(friends, BorderLayout.NORTH);
		frend.add(scrollBar6, BorderLayout.CENTER);
		frend.add(but, BorderLayout.SOUTH);

		frame.add(frend, BorderLayout.EAST);
		frame.add(panel_2, BorderLayout.SOUTH);

		dlm = new DefaultListModel();
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((textLogin.getText().equals("")) || (textRLogin.getText().equals(""))
						|| (textRAddr.getText().equals(""))) {
					JOptionPane.showMessageDialog(frame, "Not enough data for sending the message");
				} else {
					String name = new String();
					if (textLogin.getText().length() > 10) {
						try {
							name = textLogin.getText(0, 10);
						} catch (BadLocationException ignore) {
						}
						name = name + "...";
					} else
						name = textLogin.getText();
					long date = System.currentTimeMillis();
					dlm.addElement("<html>" + name + " " + new Date(date).toLocaleString() + ":<br>"
							+ textMess.getText() + " </span></html>");
					list.setModel(dlm);

					try {
						connection.sendMessage(textMess.getText());
						System.out.println("Sended");
					} catch (IOException ex) {
						System.out.println("No internet connection");
					}

				}
				textMess.setText("");
				textMess.requestFocus();
			}
		});

		textMess.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == (KeyEvent.VK_ENTER)) {
					send.doClick();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		disconnect.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (connection != null)
					try {
						connection.disconnect();
					} catch (IOException ignored) {
					}

			}
		});

		apply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (callListenerThread == null) {
					System.out.println("Added obs");
					callListenerThread = new CallListenerThread(new CallListener(textLogin.getText()));
					callListenerThread.addObserver(window);
				} else {
					callListenerThread.setLocalNick(textLogin.getText());
				}
			}
		});

		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addFriends();
				try {
					writeFriends();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			frends.repaint();
			}
		});

		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					deleteFriends();
					writeFriends();
				} catch (FileNotFoundException e1) {
			e1.printStackTrace();
				}
			frends.repaint();
			}
		});

		connect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Caller caller = new Caller(textLogin.getText(), textRAddr.getText());
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							connection = caller.call();

							if (caller.getStatus().toString().equals("OK"))
								textRLogin.setText(caller.getRemoteNick());
							else if (caller.getStatus().toString().equals("BUSY")) {
								JOptionPane.showMessageDialog(frame, "User " + caller.getRemoteNick() + " is busy");
							} else {
								JOptionPane.showMessageDialog(frame,
										"User " + caller.getRemoteNick() + " has declined your call.");
								connection = null;
							}

						} catch (IOException ex) {
							JOptionPane.showMessageDialog(frame,
									"Connection error. User with ip does not exist or there is no Internet connection");
							connection = null;
						}
					}
				}).start();
			}

		});
	}

	public void addFriends() {
		if (textRLogin.getText() != "") {
			frendmass[size][0] = textRLogin.getText();
			frendmass[size][1] = textRAddr.getText();
			online(size);
			size++;
		}
	}
	
	public void online(int i){
			boolean bol;
			bol=server.isNickOnline(frendmass[i][0]);
			if(bol){
				frendmass[size][2] = "Online";
			}
	}
	
	private void deleteFriends() throws FileNotFoundException {
		int row = frends.getSelectedRow();
		for (int i = row; i < size; i++) {
			frendmass[i][0]=frendmass[i+1][0];
			frendmass[i][1]=frendmass[i+1][1];
			frendmass[i][2]=frendmass[i+1][2];
		}
		size--;
	}

	public void readFriends() throws FileNotFoundException {
		String[] fr = new String[1000];
		String s;
		fr=server.getAllNicks();
		for(int i=0;i<fr.length;i++){
			frendmass[i][0] = fr[i];
			s=server.getIpForNick(fr[i]);
			frendmass[i][1]=s;
			online(i);
			size++;
		}
	}

	public void writeFriends() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(file);
		
		for (int i = 0; i < size; i++) {
			writer.print(frendmass[i][0] + " ");
			writer.print(frendmass[i][1] + " ");
			
			writer.flush();
		}
		writer.close();
	}

	public boolean question(String nick, String remoteAddress) {
		Object[] options = { "Receive", "Reject" };
		int dialogResult = JOptionPane.showOptionDialog(frame,
				"User " + nick + " with ip " + remoteAddress + " is trying to connect with you", "Recive connection",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (dialogResult == JOptionPane.YES_OPTION) {
			System.out.println("Receive");
			textRLogin.setText(nick);
			textRAddr.setText(remoteAddress);
			return true;
		}
		System.out.println("Rejected");
		return false;

	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof CallListener) {
			CallListener c = (CallListener) arg;
			callListenerThread.suspend();
			callListenerThread.setReceive(question(c.getRemoteNick(), c.getRemoteAddress()));
			callListenerThread.resume();
		} else if (arg instanceof Connection) {
			connection = (Connection) arg;
			System.out.println("Output connection created");
		} else {
			System.out.println("Receive message");
			System.out.println(arg.toString());
			Command command = (Command) arg;

			if (command instanceof MessageCommand) {
				dlm.addElement(
						"<html>" + textRLogin.getText() + " " + new Date(System.currentTimeMillis()).toLocaleString()
								+ ":<br>" + arg.toString() + " </span></html>");
				list.setModel(dlm);
			}
		}
	}
}
