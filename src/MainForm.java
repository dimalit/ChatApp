import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.UnexpectedException;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.omg.CORBA.portable.UnknownException;


import java.awt.Color;

public class MainForm<JForm> {

	private JFrame frame;

	private JTextField textField;
	private JTextField nickField;
	private JTextField remoteLogiField;
	private JTextField remoteAddrField;
	private HistoryView textArea;
	private JTextArea messageArea;
	private JButton send;
	private JButton discButton;
	private CallListener callListener;
	private JButton connectButt;
	private Caller caller;
	private Connection connection;
	private CallListenerThread callLT;
	private HistoryModel model;
	private CommandListenerThread commandLT;
	private int isPressed;
	private boolean forAccept;
	private ServerConnection server;
	private ContactsView friends;
	private LocalContactsView local;
	private JList list, list1;

	/**
	 * Launch the application.
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public MainForm() throws IOException {
		frame = new JFrame();

		frame.setBounds(100, 100, 850, 400);
		frame.addWindowListener(new WindowListener(){

			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				int reply=JOptionPane.showConfirmDialog(null,
						"Do you want to close programm", "",
						JOptionPane.YES_NO_OPTION);
				if (reply==0){
					try{
					commandLT.stop();
					callLT.stop();
					server.goOffline();
					}catch(NullPointerException e){
						System.out.println("CLT");
					}
					if (connection!=null)
						try {
							connection.disconnect();
						} catch (IOException e) {
						// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.exit(0);
				}
			}

			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BoxLayout(top_panel, BoxLayout.X_AXIS));
		JPanel panel_login = new JPanel();
		panel_login.setLayout(new BoxLayout(panel_login, BoxLayout.Y_AXIS));
		JPanel panel_nick = new JPanel();
		panel_nick.setLayout(new BoxLayout(panel_nick, BoxLayout.X_AXIS));
		panel_login.add(panel_nick);

		JLabel loginLabel = new JLabel("local login");
		panel_nick.add(loginLabel);

		nickField = new JTextField();
		nickField.setMaximumSize(new Dimension(150, 20));
		nickField.setToolTipText("You must write your nick for applying");
		panel_nick.add(nickField);
		nickField.setColumns(10);
		JPanel panel_connection = new JPanel();
		panel_connection.setMaximumSize(new Dimension(32767, 100));
		panel_connection.setLayout(new GridLayout(2, 3));
		mainPanel.add(top_panel);
		top_panel.add(panel_login);

		final JButton nickApplyButton = new JButton("Apply");
		panel_login.add(nickApplyButton);

		top_panel.add(panel_connection);

		JLabel remoteNickLabel = new JLabel("Remote login");
		remoteNickLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_connection.add(remoteNickLabel);

		remoteLogiField = new JTextField();
		remoteLogiField.setMaximumSize(new Dimension(150, 20));
		remoteLogiField.setEnabled(false);
		panel_connection.add(remoteLogiField);
		remoteLogiField.setColumns(10);

		discButton = new JButton("Disconnect");
		discButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_connection.add(discButton);
		discButton.setEnabled(false);
		JLabel remoteAddrLabel = new JLabel("Remote addr");
		remoteAddrLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_connection.add(remoteAddrLabel);
		remoteAddrField = new JTextField();
		remoteAddrField.setMaximumSize(new Dimension(150, 20));
		panel_connection.add(remoteAddrField);
		remoteAddrField.setColumns(10);
		remoteAddrField.setToolTipText("You must press Enter to continue");
		connectButt = new JButton("Connect");
		connectButt.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_connection.add(connectButt);
		connectButt.setEnabled(false);
		JPanel main_panel = new JPanel();
		main_panel.setLayout(new GridLayout(1, 1));
		JPanel bot_panel = new JPanel();
		bot_panel.setLayout(new BoxLayout(bot_panel, BoxLayout.X_AXIS));
		mainPanel.add(main_panel);
		model = new HistoryModel();
		textArea = new HistoryView(model);
		textArea.setBackground(Color.WHITE);
		textArea.setBorder(new LineBorder(null, 0));
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setRows(10);
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		main_panel.add(scroll);
		mainPanel.add(bot_panel);
		messageArea = new JTextArea();
		messageArea.setMinimumSize(new Dimension(16, 4));
		messageArea.setMaximumSize(new Dimension(800, 100));
		messageArea.setLineWrap(true);
		messageArea.setEnabled(false);
		bot_panel.add(messageArea);

		send = new JButton("Send");
		send.setMinimumSize(new Dimension(60, 25));
		send.setMaximumSize(new Dimension(100, 50));
		send.setAlignmentX(Component.CENTER_ALIGNMENT);
		send.setEnabled(false);
		bot_panel.add(send);

		final JPanel contactsPanel = new JPanel();
		frame.getContentPane().add(contactsPanel);
		contactsPanel.setPreferredSize(new Dimension(230, 400));
		// contactsPanel.setMinimumSize(new Dimension(50, 100));
		contactsPanel.setMaximumSize(new Dimension(800, 800));
		contactsPanel.setLayout(new BoxLayout(contactsPanel, BoxLayout.Y_AXIS));
		JLabel name = new JLabel("List of person on server");
		name.setHorizontalAlignment(JLabel.CENTER);
		contactsPanel.add(name);
		
		contactsPanel.setBorder(BorderFactory.createEtchedBorder());
		JPanel forButton1 = new JPanel();
		
		frame.getContentPane().add(mainPanel);
		


		discButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					if (connection != null) {

						connection.disconnect();
						forDisconnect();
						commandLT.stop();
						connection = null;
						if (!local.findNick(remoteLogiField.getText(), remoteAddrField.getText())) {
							int reply = JOptionPane.showConfirmDialog(null,
									"Do you want to save this person to your contact list", "",
									JOptionPane.YES_NO_OPTION);
							if (reply == 0) {
								ContactsModel modelForCont = new ContactsModel(remoteLogiField.getText(), remoteAddrField.getText());
								modelForCont.addLocalNick();
								local.addElement(modelForCont.toString());
								list1.setModel(local);
								frame.validate();
							}
						}

					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}

		});

		connectButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (remoteAddrField.getText() != "") {
					String login;
					login = nickField.getText();
					caller = new Caller(login, remoteAddrField.getText());
					try {
						connection = caller.call();
						if (connection != null) {
							commandLT.setConnection(connection);
							commandLT.start();
							//ThreadOfCommand();
							connection.sendNickHello(nickField.getText());
							forConnect();
						} else {
							JOptionPane.showMessageDialog(null, "Couldn't connect this ip ");
						}
					} catch (InterruptedException e1) {

						e1.printStackTrace();
					} catch (UnsupportedEncodingException e1) {

						e1.printStackTrace();
					} catch (IOException e1) {

						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null, "You must write remote address ");
				}
			}
		});
		messageArea.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						connection.sendMessage(messageArea.getText());
						model.addMessage(nickField.getText(), new Date(), messageArea.getText());
						textArea.update(model, new Object());
						messageArea.setText("");
						
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				} 
			}
		});
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!messageArea.getText().equals("")) {
						connection.sendMessage(messageArea.getText());
						model.addMessage(nickField.getText(), new Date(), messageArea.getText());

						textArea.update(model, new Object());
						messageArea.setText("");

					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		});
		nickApplyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login;
				if (nickField.getText().equals("")) {
					login = "unnamed";
				} else
					login = nickField.getText();
				boolean isCorrectLogin = false;
				for (int i = 0; i < login.toCharArray().length; i++)
					if (login.toCharArray()[i] != ' ') {
						isCorrectLogin = true;
						break;
					}
				if (!isCorrectLogin) {
					login = "unnamed";
				}
				while (login.charAt(0) == ' ')
					login = login.substring(1);
				nickField.setText(login);
				nickField.setEnabled(false);
				try {
					server = new ServerConnection(login);
					server.connect();
					server.goOnline();
					callLT = new CallListenerThread();
					callLT.start();
					commandLT = new CommandListenerThread();
					ThreadOfCall();
					ThreadOfCommand();
					nickApplyButton.setEnabled(false);
					friends = new ContactsView(server);
					list = new JList();
					list.setModel(friends);
					local = new LocalContactsView();
					local.writeLocalNicks();
					list1 = new JList();
					list1.setModel(local);
					JScrollPane scroll1 = new JScrollPane(list);
					scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
					JScrollPane scroll2 = new JScrollPane(list1);
					scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
					contactsPanel.add(scroll1);
					contactsPanel.add(scroll2);
					frame.validate();
					list.addListSelectionListener(new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent e) {
							if (connection == null) {
								String[] str = list.getSelectedValue().toString().split(" ");
								remoteLogiField.setText(str[0]);
								remoteAddrField.setText(server.getIpForNick(str[0]));
							} else {
								JOptionPane.showMessageDialog(null, "You must disconnect to choose");
							}
						}
					});
					list1.addListSelectionListener(new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent e) {
							if (connection == null) {
								String[] str = list1.getSelectedValue().toString().split("\\|");
								remoteLogiField.setText(str[0]);
								remoteAddrField.setText(str[1]);
							} else {
								JOptionPane.showMessageDialog(null, "You must disconnect to choose");
							}
						}
						
					});
					
				connectButt.setEnabled(true);}catch (BindException e2){
					JOptionPane.showMessageDialog(null,"You can't open two examples of one program");
					System.exit(0);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				callLT.setLocalNick(login);
			}
		});
	}

	public void ThreadOfCall() throws IOException {
		callLT.addObserver(new Observer() {
			public void update(Observable arg0, Object arg1) {
				// TODO:Thread, TimeOut;
				System.out.println("update listener");
				connection = callLT.getConnection();
				try {
					connection.sendNickHello(nickField.getText());
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				commandLT.setConnection(connection);
				commandLT.start();
				System.out.println("CLT started");
				long t1 = System.currentTimeMillis();
				long t2 = System.currentTimeMillis();
				boolean b = false;
				while (((t2 - t1) <= 100000) && !b) {
					Command command = commandLT.getLastCommand();
					try {
						System.out.println(command.getClass() + command.toString());
					} catch (NullPointerException e) {
						System.out.println("null");
					}
					if (command instanceof NickCommand) {
						int reply = JOptionPane.showConfirmDialog(null,
								"Do you want to accept incoming connection from user ".concat(command.toString()), "",
								JOptionPane.YES_NO_OPTION);
						System.out.println(reply);

						try {
							if (reply == 0) {
								b = true;
								connection.accept();
								remoteAddrField.setText(callLT.getRemoteAddress().toString());
								remoteLogiField.setText(command.toString());
								forConnect();
								break;
							} else {
								forDisconnect();
								b = true;
								connection.reject();
								commandLT.stop();
								connection = null;
								break;
							}

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						t2 = System.currentTimeMillis();
					}
				}
				System.out.println("Connection getted");
			}
		});

	}

	public void ThreadOfCommand() {
		System.out.println("testif");
		commandLT.addObserver(new Observer() {
			public void update(Observable arg0, Object arg1) {
				System.out.println("testobs");
				Command lastCommand = commandLT.getLastCommand();
				System.out.println(lastCommand.getClass() + " " + lastCommand.toString());
				if (lastCommand instanceof MessageCommand) {
					model.addMessage(remoteLogiField.getText(), new Date(), commandLT.getLastCommand().toString());
					textArea.update(model, new Object());
				} else if (lastCommand instanceof NickCommand) {
					remoteLogiField.setText(lastCommand.toString());
				} else if (lastCommand != null) {
					switch (lastCommand.type) {
					case ACCEPT: {
						model.addMessage(remoteLogiField.getText(), new Date(),"User was accepted");
						textArea.update(model, new Object());
						if (!local.findNick(remoteLogiField.getText(), remoteAddrField.getText())) {
							int reply = JOptionPane.showConfirmDialog(null,
									"Do you want to save this person to your contact list", "",
									JOptionPane.YES_NO_OPTION);
							if (reply == 0) {
								ContactsModel modelForCont = new ContactsModel(remoteLogiField.getText(), remoteAddrField.getText());
								try {
									modelForCont.addLocalNick();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								local.addElement(modelForCont.toString());
								list1.setModel(local);
								frame.validate();
							}
						}
						break;
					}
					case REJECT: {
						model.addMessage(remoteLogiField.getText(), new Date(),"User was rejected");
						textArea.update(model, new Object());
						commandLT.stop();
						forDisconnect();
						break;
					}
					case DISCONNECT: {
						model.addMessage(remoteLogiField.getText(), new Date(), "User was disconnected");
						textArea.update(model, new Object());
						commandLT.stop();
						forDisconnect();
						break;
					}
					}

				}

			}

		});
	}

	void forDisconnect() {
		send.setEnabled(false);
		remoteLogiField.setText("");
		messageArea.setEnabled(false);
		discButton.setEnabled(false);
		connectButt.setEnabled(true);
		remoteAddrField.setText("");
		remoteAddrField.setEnabled(true);
	}

	void forConnect() throws IOException {
		send.setEnabled(true);
		connectButt.setEnabled(false);
		messageArea.setEnabled(true);
		discButton.setEnabled(true);
		remoteAddrField.setEnabled(false);
	}

	boolean formForConnect(boolean b, final String nick) {
		boolean isconnect = false;
		final JFrame f = new JFrame();
		Container cp = f.getContentPane();
		f.setSize(400, 175);
		f.setLocation(200, 200);
		f.setVisible(b);
		JPanel panel = new JPanel();
		cp.setLayout(null);
		JLabel text = new JLabel("Do you want to accept incoming connection from user ".concat(nick));
		JButton yes = new JButton("Yes");
		JButton no = new JButton("No");
		text.setSize(500, 60);
		text.setLocation(20, 20);
		yes.setSize(90, 25);
		yes.setLocation(70, 95);
		no.setSize(90, 25);
		no.setLocation(220, 95);
		f.setContentPane(cp);
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection.accept();
					connection.sendNickHello(nickField.getText());
					remoteAddrField.setText(callListener.getRemoteAddress().toString());
					remoteLogiField.setText(nick);
					forConnect();
					f.setVisible(false);
					f.dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection.reject();
					forDisconnect();
					f.setVisible(false);
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					f.dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		text.setSize(500, 60);
		text.setLocation(20, 20);
		yes.setSize(90, 25);
		yes.setLocation(70, 95);
		no.setSize(90, 25);
		no.setLocation(220, 95);
		cp.add(text);
		cp.add(yes);
		cp.add(no);
		f.setContentPane(cp);
		return isconnect;

	}

	public void formForNewTalk(boolean b, String nick) {
		JFrame f = new JFrame();
		Container cp = f.getContentPane();
		f.setSize(400, 175);
		f.setLocation(200, 200);
		f.setVisible(b);
		JPanel panel = new JPanel();
		cp.setLayout(null);
		JLabel text = new JLabel("New user " + nick + " want to speak with you." + "\n");
		JLabel text1 = new JLabel("Do you want to reject current connection?");
		JButton yes = new JButton("Yes");
		JButton no = new JButton("No");

		text.setSize(500, 60);
		text.setLocation(100, 20);
		text1.setSize(500, 60);
		text1.setLocation(85, 40);
		yes.setSize(90, 25);
		yes.setLocation(70, 95);
		no.setSize(90, 25);
		no.setLocation(220, 95);
		cp.add(text);
		cp.add(text1);
		cp.add(yes);
		cp.add(no);
		f.setContentPane(cp);
	}

	void formWait(boolean b) {
		final JFrame f = new JFrame();
		Container cp = f.getContentPane();
		f.setSize(400, 175);
		f.setLocation(200, 200);
		f.setTitle("         Установка исходящего соединения");
		f.setVisible(b);
		JPanel panel = new JPanel();
		cp.setLayout(null);
		JButton v = new JButton("Прервать ");
		v.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (connection != null) {
						connection.disconnect();

					}
					f.dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JLabel text = new JLabel("Ожидание подтверждения запроса..");
		text.setSize(230, 60);
		text.setLocation(95, 20);
		v.setSize(150, 25);
		v.setLocation(125, 95);
		cp.add(text);
		cp.add(v);
		f.setContentPane(cp);
	}

}
