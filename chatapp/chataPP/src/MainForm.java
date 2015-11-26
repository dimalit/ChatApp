import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.IOException;
import java.sql.Date;
import java.awt.GridBagConstraints;
import java.awt.Color;
import javax.swing.text.BadLocationException;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
/*import org.eclipse.wb.swing.FocusTraversalOnArray;*/
import java.util.Observable;
import java.util.Observer;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class MainForm implements Observer{

	private JFrame frame;
	private JTextField loclog;
	private JTextField remlog;
	private JTextField remadr;
	private JTextField msg;
	private CallListenerThread callListenerThread;
        public static MainForm window;
	private DefaultListModel dlm;
	private JList list;
	private Connection connection;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MainForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainForm() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setMinimumSize(new Dimension(454, 432));
		frame.setMinimumSize(new Dimension(470, 470));
		frame.setBounds(100, 100, 470, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setName("panel");
		panel.setBackground(new Color(255, 255, 255));
		panel.setOpaque(false);
		panel.setBounds(25, 25, 404, 382);
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel.add(panel_1, BorderLayout.NORTH);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{75, 75, 13, 80, 75, 75, 0};
		gbl_panel_1.rowHeights = new int[]{20, 20, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);

		JLabel lblNewLabel = new JLabel("local login");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);

		loclog = new JTextField();
		GridBagConstraints gbc_loclog = new GridBagConstraints();
		gbc_loclog.fill = GridBagConstraints.BOTH;
		gbc_loclog.insets = new Insets(0, 0, 5, 5);
		gbc_loclog.gridx = 1;
		gbc_loclog.gridy = 0;
		panel_1.add(loclog, gbc_loclog);
		loclog.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Remote login");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 0;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);

		remlog = new JTextField();
		GridBagConstraints gbc_remlog = new GridBagConstraints();
		gbc_remlog.fill = GridBagConstraints.BOTH;
		gbc_remlog.insets = new Insets(0, 0, 5, 5);
		gbc_remlog.gridx = 4;
		gbc_remlog.gridy = 0;
		panel_1.add(remlog, gbc_remlog);
		remlog.setColumns(10);

		JButton DisconBut = new JButton("Disconnect");
		GridBagConstraints gbc_DisconBut = new GridBagConstraints();
		gbc_DisconBut.fill = GridBagConstraints.BOTH;
		gbc_DisconBut.insets = new Insets(0, 0, 5, 0);
		gbc_DisconBut.gridx = 5;
		gbc_DisconBut.gridy = 0;
		panel_1.add(DisconBut, gbc_DisconBut);

		JButton ApplyBut = new JButton("Apply");
		GridBagConstraints gbc_ApplyBut = new GridBagConstraints();
		gbc_ApplyBut.fill = GridBagConstraints.BOTH;
		gbc_ApplyBut.insets = new Insets(0, 0, 0, 5);
		gbc_ApplyBut.gridx = 0;
		gbc_ApplyBut.gridy = 1;
		panel_1.add(ApplyBut, gbc_ApplyBut);

		JLabel lblNewLabel_2 = new JLabel("Address");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 1;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);

		remadr = new JTextField();
		GridBagConstraints gbc_remadr = new GridBagConstraints();
		gbc_remadr.insets = new Insets(0, 0, 0, 5);
		gbc_remadr.fill = GridBagConstraints.BOTH;
		gbc_remadr.gridx = 4;
		gbc_remadr.gridy = 1;
		panel_1.add(remadr, gbc_remadr);
		remadr.setColumns(10);

		JButton ConBut = new JButton("Connect");
		GridBagConstraints gbc_ConBut = new GridBagConstraints();
		gbc_ConBut.fill = GridBagConstraints.BOTH;
		gbc_ConBut.gridx = 5;
		gbc_ConBut.gridy = 1;
		panel_1.add(ConBut, gbc_ConBut);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel.add(panel_2, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{331, 75, 0};
		gbl_panel_2.rowHeights = new int[]{23, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);

		msg = new JTextField();
		GridBagConstraints gbc_msg = new GridBagConstraints();
		gbc_msg.fill = GridBagConstraints.BOTH;
		gbc_msg.insets = new Insets(0, 0, 0, 5);
		gbc_msg.gridx = 0;
		gbc_msg.gridy = 0;
		panel_2.add(msg, gbc_msg);
		msg.setColumns(10);

		JButton SendBut = new JButton("Send");
		GridBagConstraints gbc_SendBut = new GridBagConstraints();
		gbc_SendBut.fill = GridBagConstraints.BOTH;
		gbc_SendBut.gridx = 1;
		gbc_SendBut.gridy = 0;
		panel_2.add(SendBut, gbc_SendBut);

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_3.setBorder(new EmptyBorder(5, 0, 5, 0));
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane, BorderLayout.CENTER);

		list = new JList();
		scrollPane.setViewportView(list);

		JPanel fonpanel = new JPanel();
		fonpanel.setName("fonpanel");
		fonpanel.setBackground(new Color(204, 255, 153));
		fonpanel.setBounds(0, 0, 454, 432);
//		frame.getContentPane().add(fonpanel);
		fonpanel.setLayout(new BorderLayout(0, 0));

		JLabel label = new JLabel("");
		fonpanel.add(label, BorderLayout.SOUTH);

		JLayeredPane work = new JLayeredPane();
		work.setBounds(0, 0, 10, 10);



		frame.getContentPane().addComponentListener(new ComponentListener() {

				@Override
				public void componentResized(ComponentEvent e) {
					fonpanel.setSize(frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
					panel.setSize(frame.getContentPane().getWidth()-50, frame.getContentPane().getHeight()-50);

				}

				@Override
				public void componentMoved(ComponentEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void componentShown(ComponentEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void componentHidden(ComponentEvent e) {
					// TODO Auto-generated method stub

				}
	        });

		frame.addWindowStateListener(new WindowStateListener(){

			@Override
			public void windowStateChanged(WindowEvent e) {
				fonpanel.setSize(frame.getWidth()-16, frame.getHeight()-38);
				panel.setSize(frame.getWidth()-66, frame.getHeight() - 88);
			}

		});

		dlm = new DefaultListModel();
		SendBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((loclog.getText().equals("")) || (remlog.getText().equals("")) || (remadr.getText().equals(""))){
					JOptionPane.showMessageDialog(frame, "Not enough data for sending the message");
				}
				else {
					String name = new String();
					if (loclog.getText().length()>10){
						try {
							name = loclog.getText(0, 10);
						} catch (BadLocationException ignore) {}
						name = name + "...";
					}
					else name = loclog.getText();
					long date = System.currentTimeMillis();
					dlm.addElement("<html>" + name + " " + new Date(date).toLocaleString() + ":<br>" + msg.getText() + " </span></html>");
					list.setModel(dlm);

					try {
						connection.sendMessage(msg.getText());
						System.out.println("Sended");
					} catch (IOException ex){
						System.out.println("No internet connection");
					}

				}
				msg.setText("");
				msg.requestFocus();
			}
		});

		msg.addKeyListener(new KeyListener(){

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					SendBut.doClick();
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

		ApplyBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (callListenerThread == null) {
                    System.out.println("Added obs");
                    callListenerThread = new CallListenerThread(new CallListener(loclog.getText()));
                    callListenerThread.addObserver(window);
                }
				else {
					callListenerThread.setLocalNick(loclog.getText());
				}
			}
		});

		ConBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Caller caller = new Caller(loclog.getText() ,remadr.getText());
					new Thread (new Runnable() {
						@Override
						public void run() {
							try {
								connection = caller.call();

								if(caller.getStatus().toString().equals("OK"))
									remlog.setText(caller.getRemoteNick());
								else
								 if (caller.getStatus().toString().equals("BUSY")){
									 JOptionPane.showMessageDialog(frame, "User " + caller.getRemoteNick() + " is busy");
								 }
								else
								{
									JOptionPane.showMessageDialog(frame, "User " + caller.getRemoteNick() + " has declined your call.");
									connection = null;
								}

							} catch (IOException ex) {                    //Show message that remote user is offline or wrong ip
								JOptionPane.showMessageDialog(frame, "Connection error. User with ip does not exist or there is no Internet connection");
								connection = null;
							}
						}
					}).start();
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
			remlog.setText(nick);
			remadr.setText(remoteAddress);
			return true; // Receive
		}
		System.out.println("Rejected");
			return false; //Reject

	}

	@Override
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
				dlm.addElement("<html>" + remlog.getText() + " " + new Date(System.currentTimeMillis()).toLocaleString() + ":<br>" + arg.toString() + " </span></html>");
				list.setModel(dlm);
			}
		}
	}
}
