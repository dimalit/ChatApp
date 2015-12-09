import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

public class Function extends MainForm {

	private static JFrame frame;
	private static JTextArea textLogin;
	private static JTextArea textRLogin;
	private static JTextArea textRAddr;
	private static JTextArea textMess;
	public static MainForm window;
	private JList list;
	private JTable frends;
	private Friends f = new Friends();
	private CallListenerThread callListenerThread;
	private Connection connection;
	private ServerConnection server;
	private DefaultListModel dlm;

	public Function() throws FileNotFoundException {
		super(frame, textLogin, textLogin, textLogin, textLogin, window);
	}

	public void Server() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		server = new ServerConnection();
		server.setServerAddress("jdbc:mysql://files.litvinov.in.ua/chatapp_server?characterEncoding..");
		server.connect();
		f.addFriends();
	}

	public void Send() {
		dlm = new DefaultListModel();
		if ((textLogin.getText().equals("")) || (textRLogin.getText().equals("")) || (textRAddr.getText().equals(""))) {
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
			dlm.addElement("<html>" + name + " " + new Date(date).toLocaleString() + ":<br>" + textMess.getText()
					+ " </span></html>");
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

		public void Disconnect(){

			if (connection != null)
				try {
					connection.disconnect();
				} catch (IOException ignored) {
				
				}
		}
		
		public void Apply(){
			apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if (callListenerThread == null) {
				System.out.println("Added obs");
				callListenerThread = new CallListenerThread(new CallListener(textLogin.getText()));
				callListenerThread.addObserver((Observer) window);
			} else {
				callListenerThread.setLocalNick(textLogin.getText());
			}
		}
		
			}
		public void Add(){
			f.addFriends();
			try {
				f.writeFriends();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			frends.repaint();
		}
		
	public void Connect(){

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

	public void update(Observable o, Object arg) {
		dlm = new DefaultListModel();
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
