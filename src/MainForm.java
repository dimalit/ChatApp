import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.UnexpectedException;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Container;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import org.omg.CORBA.portable.UnknownException;

import com.sun.glass.events.MouseEvent;

import java.awt.Color;

public class MainForm {

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
	private CommandListenerThread com;
	private CallListenerThread callLT;
	private HistoryModel model;

	/**
	 * Launch the application.
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
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
		callLT = new CallListenerThread();
		callLT.start();
		frame = new JFrame();
		frame.setBounds(100, 100, 481, 243);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
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
		frame.getContentPane().add(top_panel);
		top_panel.add(panel_login);

		JButton nickApplyButton = new JButton("Apply");
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
		connectButt.setEnabled(false);
		panel_connection.add(connectButt);

		JPanel main_panel = new JPanel();
		main_panel.setLayout(new GridLayout(1, 1));
		JPanel bot_panel = new JPanel();
		bot_panel.setLayout(new BoxLayout(bot_panel, BoxLayout.X_AXIS));
		frame.getContentPane().add(main_panel);
		model = new HistoryModel();
		textArea = new HistoryView(model);
		textArea.setBackground(new Color(255, 255, 204));
		textArea.setBorder(new LineBorder(Color.CYAN, 3));
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setRows(10);
		main_panel.add(textArea);
		frame.getContentPane().add(bot_panel);

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
		// frame.pack();
		remoteAddrField.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					connectButt.setEnabled(true);
				}
			}

		});
		discButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					if (connection != null) {
						connection.disconnect();
						forDisconnect();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}

		});

		connectButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (remoteAddrField.getText() != "") {
					// formWait(true);
					String login;
					if (nickField.getText().equals(""))
						login = "unnamed";
					else
						login = nickField.getText();
					caller = new Caller(login, remoteAddrField.getText());
					try {
						connection = caller.call();
						if (connection != null) {
							connection.sendNickHello(nickField.getText());
							com = new CommandListenerThread(connection);
							com.start();
							ThreadOfCommand();
							forConnect();

						}
					} catch (InterruptedException e1) {

						e1.printStackTrace();
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
					nickField.setText(login);
				} else
					login = nickField.getText();
				nickField.setEnabled(false);
				try {
					ThreadOfCall();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
	}

	public void ThreadOfCall() throws IOException {

		callLT = new CallListenerThread(nickField.getText());
		callLT.addObserver(new Observer() {

			public void update(Observable arg0, Object arg1) {
				connection = callLT.getConnection();
				forConnect();
				try {

					connection.sendNickHello(nickField.getText());
					Command command;
					command = connection.receive();
					if (command instanceof NickCommand) {
						// if(callLT.getCallStatus().toString().equals("BUSY"))
						remoteLogiField.setText(com.getLastCommand().toString());
						remoteAddrField.setText(callListener.getRemoteAddress().toString());
						connection.accept();
						forConnect();
						// formForConnect(true, command.toString());
						// else
						// formForNewTalk(true);
					} else {
						connection.reject();
						forDisconnect();
					}
				} catch (IOException e) {

					e.printStackTrace();
				}

			}

		});

	}

	public void ThreadOfCommand() {

		com.addObserver(new Observer() {
			public void update(Observable ob, Object obj) {

				if (com.getLastCommand() instanceof MessageCommand) {
					textArea.getModel().addMessage(remoteLogiField.getText(), new Date(),
							com.getLastCommand().toString());
				} else {

					switch (com.getLastCommand().type) {
					case ACCEPT: {
						textArea.append("User is accepted");
						break;
					}
					case REJECT: {
						textArea.append("rejected");
						forDisconnect();
						break;
					}
					case DISCONNECT: {
						textArea.append("User was disconnected");
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
		remoteAddrField.setText("");
		remoteAddrField.setEnabled(true);
	}

	void forConnect() {
		send.setEnabled(true);
		connectButt.setEnabled(false);
		messageArea.setEnabled(true);
		discButton.setEnabled(true);
		remoteAddrField.setEnabled(false);
	}

	void formForConnect(boolean b, String nick) {
		JFrame f = new JFrame();
		Container cp = f.getContentPane();
		f.setSize(400, 175);
		f.setLocation(200, 200);
		f.setVisible(b);
		JPanel panel = new JPanel();
		cp.setLayout(null);
		JLabel text = new JLabel("Do you want to accept incoming connection from user ".concat(nick));
		JButton yes = new JButton("Yes");
		JButton no = new JButton("No");
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection.accept();
					remoteAddrField.setText(callListener.getRemoteAddress().toString());
					Command command = connection.receive();
					if (command instanceof NickCommand)
						remoteLogiField.setText(command.toString());

					ThreadOfCommand();
					forConnect();
					f.setVisible(!b);
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
					f.setVisible(!b);
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
		JFrame f = new JFrame();
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
