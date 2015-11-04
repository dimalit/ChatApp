import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.GridLayout;
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
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class MainForm {

	private JFrame frame;
	private JTextField textField;
	private JTextField nickField;
	private JTextField remoteLogiField;
	private JTextField remoteAddrField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	 */
	public MainForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 481, 243);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BoxLayout(top_panel, BoxLayout.X_AXIS));
		JPanel panel_login = new JPanel();
		panel_login.setLayout(new BoxLayout(panel_login,BoxLayout.Y_AXIS));
		JPanel panel_nick = new JPanel();
		panel_nick.setLayout(new BoxLayout(panel_nick,BoxLayout.X_AXIS));
		panel_login.add(panel_nick);
		
		JLabel loginLabel = new JLabel("local login");
		panel_nick.add(loginLabel);
		
		nickField = new JTextField();
		nickField.setMaximumSize(new Dimension(150, 20));
		panel_nick.add(nickField);
		nickField.setColumns(10);
		JPanel panel_connection=new JPanel();
		panel_connection.setMaximumSize(new Dimension(32767, 100));
		panel_connection.setLayout(new GridLayout(2,3));
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
		panel_connection.add(remoteLogiField);
		remoteLogiField.setColumns(10);
		
		JButton discButton = new JButton("Disconnect");
		discButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_connection.add(discButton);
		
		JLabel remoteAddrLabel = new JLabel("Remote addr");
		remoteAddrLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_connection.add(remoteAddrLabel);
		
		remoteAddrField = new JTextField();
		remoteAddrField.setMaximumSize(new Dimension(150, 20));
		panel_connection.add(remoteAddrField);
		remoteAddrField.setColumns(10);
		
		JButton connectButt = new JButton("connect");
		connectButt.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_connection.add(connectButt);
		JPanel main_panel= new JPanel();
		main_panel.setLayout(new GridLayout(1,1));
		JPanel bot_panel = new JPanel();
		bot_panel.setLayout(new BoxLayout(bot_panel,BoxLayout.X_AXIS));
		frame.getContentPane().add(main_panel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(255, 255, 204));
		textArea.setBorder(new LineBorder(Color.CYAN, 3));
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setRows(10);
		main_panel.add(textArea);
		frame.getContentPane().add(bot_panel);
		
		JTextArea messageArea = new JTextArea();
		messageArea.setMinimumSize(new Dimension(16, 4));
		messageArea.setMaximumSize(new Dimension(800, 100));
		messageArea.setLineWrap(true);
		bot_panel.add(messageArea);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.setMinimumSize(new Dimension(60, 25));
		btnNewButton.setMaximumSize(new Dimension(100, 50));
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		bot_panel.add(btnNewButton);
		//frame.pack();
	}

}
