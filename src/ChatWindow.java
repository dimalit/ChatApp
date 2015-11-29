import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class ChatWindow extends JFrame implements Observer {
	private CallListenerThread callt;
	private CommandListenerThread comt;
	public static Observer observer;
	private Connection connection;

	final JPanel panel = new JPanel();
	final JPanel field1 = new JPanel();
	final JPanel field2 = new JPanel();
	final JPanel field3 = new JPanel();
	final JPanel fieldmess = new JPanel();
	final JPanel field = new JPanel();
	final JPanel messArea = new JPanel();
	final JButton apply = new JButton("Apply");
	final JButton connect = new JButton("Connect");
	final JButton disconnect = new JButton("Disconnect");
	final JButton sendb = new JButton("Send");

	final JLabel locallogin = new JLabel("local login");
	final JLabel remotelogin = new JLabel("remote login");
	final JLabel txt3 = new JLabel("remote addr");

	final JTextField text1 = new JTextField();
	final JTextField text2 = new JTextField();
	final JTextField text3 = new JTextField();
	final JTextArea textmess = new JTextArea();
	final JTextArea mess = new JTextArea();

	public ChatWindow() throws IOException {
		observer = this;
		this.setSize(650, 500);
		this.setTitle("This is CHAT ");
		ImageIcon image = new ImageIcon("F:\\chaticon.jpg");
		this.setIconImage(image.getImage());
		callt = new CallListenerThread();
		callt.start();



		messArea.setLayout(new BoxLayout(messArea, BoxLayout.X_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		field.setLayout(new BoxLayout(field, BoxLayout.X_AXIS));
		fieldmess.setLayout(new BoxLayout(fieldmess, BoxLayout.X_AXIS));
		field1.setLayout(new BoxLayout(field1, BoxLayout.Y_AXIS));
		field2.setLayout(new BoxLayout(field2, BoxLayout.Y_AXIS));
		field3.setLayout(new BoxLayout(field3, BoxLayout.Y_AXIS));

		textmess.setLineWrap(true);
		mess.setCaretPosition(0);
		mess.setLineWrap(true);
		mess.setEditable(false);

		textmess.setMaximumSize(new Dimension(1500, 50));
		textmess.setPreferredSize(new Dimension(70, 50));
		text1.setMaximumSize(new Dimension(100, 25));
		text2.setMaximumSize(new Dimension(100, 25));
		text3.setMaximumSize(new Dimension(100, 25));

		apply.setPreferredSize(new Dimension(100, 25));
		apply.setMaximumSize(new Dimension(100, 25));
		connect.setPreferredSize(new Dimension(100, 25));
		connect.setMaximumSize(new Dimension(100, 25));
		disconnect.setPreferredSize(new Dimension(100, 25));
		disconnect.setMaximumSize(new Dimension(100, 25));
		sendb.setMaximumSize(new Dimension(70, 50));
		sendb.setPreferredSize(new Dimension(70, 50));
		disconnect.setEnabled(false);

		apply.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		text1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		locallogin.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		connect.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		text2.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		remotelogin.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		disconnect.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		text3.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		txt3.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		field1.add(locallogin);
		field2.add(remotelogin);
		field3.add(txt3);
		field1.add(text1);
		field2.add(text2);
		field3.add(text3);
		field1.add(apply);
		field2.add(connect);
		field3.add(disconnect);
		messArea.add(textmess);
		messArea.add(sendb);

		panel.setBackground(new Color(220, 243, 246));
		field1.setBackground(new Color(220, 243, 246));
		field2.setBackground(new Color(220, 243, 246));
		field3.setBackground(new Color(220, 243, 246));
		messArea.setBackground(new Color(220, 243, 246));
		mess.setBackground(new Color(237, 245, 246));

		apply.setBackground(new Color(116, 199, 209));
		connect.setBackground(new Color(116, 199, 209));
		disconnect.setBackground(new Color(116, 199, 209));
		sendb.setBackground(new Color(116, 199, 209));

		class SendAction implements ActionListener {
			private String message;



			public void actionPerformed(ActionEvent event) {
				message = textmess.getText();
				try {
					if(comt!=null) {
						mess.append("Message:\n" + message + "\n");
						comt.getConnection().sendMessage(message);
					}else{
						callt.getConnection().sendMessage(message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				textmess.setText("");
			}
		}

		class ApplyAction implements ActionListener {
			private String text;



			public void actionPerformed(ActionEvent event) {
				text = text1.getText();
				Protocol.nickname = text;
			}
		}

		class ConnectAction implements ActionListener {
			private String textc;


			public void actionPerformed(ActionEvent event) {
				String ip = text3.getText();
				connect.setEnabled(false);
				disconnect.setEnabled(true);
				try {
					Caller caller = new Caller(ip);
					connection = caller.call();
					if (connection != null) {
						comt = new CommandListenerThread(connection);
						comt.addObserver(ChatWindow.this);
						mess.append("connected");
						comt.start();

					} else {
						mess.append("IP: " + ip + " inaccessible");

					}
				}catch (IOException e){}

			}
		}
		class DisconnectAction implements ActionListener {

			DisconnectAction() {
			}

			public void actionPerformed(ActionEvent event) {
				sendb.setEnabled(false);
				disconnect.setEnabled(false);
				connect.setEnabled(true);
				apply.setEnabled(true);
			}
		}
		SendAction send = new SendAction();
		sendb.addActionListener(send);
		ApplyAction applyact = new ApplyAction();
		apply.addActionListener(applyact);
		ConnectAction connectact = new ConnectAction();
		connect.addActionListener(connectact);
		DisconnectAction disconnectact = new DisconnectAction();
		disconnect.addActionListener(disconnectact);

		fieldmess.add(mess);
		final JScrollPane scrollPane = new JScrollPane(mess);
		fieldmess.add(scrollPane);

		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		field.add(field1);
		field.add(field2);
		field.add(field3);
		panel.add(field);
		panel.add(fieldmess);
		panel.add(messArea);

		this.add(panel);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args)  {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new ChatWindow();
				}catch (IOException e){}
			}
		});
	}

	@Override
	public void update(Observable o, Object arg) {
		sendb.setEnabled(true);
		connect.setEnabled(false);
		NickCommand c;
		MessageCommand mescom;
		if(arg instanceof NickCommand){
			c = (NickCommand) arg;
			textmess.append(c.intoString()+"\n");
		}
		if(arg instanceof MessageCommand){
			mescom = (MessageCommand) arg;
			mess.append("Message: "+mescom.getMessagetext()+"\n");
		}
	}
}
