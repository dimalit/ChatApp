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
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_5;

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
		
		JLabel lblNewLabel = new JLabel("local login");
		panel_nick.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setMaximumSize(new Dimension(150, 20));
		panel_nick.add(textField_1);
		textField_1.setColumns(10);
		JPanel panel_connection=new JPanel();
		panel_connection.setMaximumSize(new Dimension(32767, 100));
		panel_connection.setLayout(new GridLayout(2,3));
		frame.getContentPane().add(top_panel);
		top_panel.add(panel_login);
		
		JButton btnNewButton_1 = new JButton("Apply");
		panel_login.add(btnNewButton_1);
		top_panel.add(panel_connection);
		
		JLabel lblNewLabel_1 = new JLabel("Remote login");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_connection.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setMaximumSize(new Dimension(150, 20));
		panel_connection.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Disconnect");
		btnNewButton_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_connection.add(btnNewButton_2);
		
		JLabel lblNewLabel_2 = new JLabel("Remote addr");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_connection.add(lblNewLabel_2);
		
		textField_5 = new JTextField();
		textField_5.setMaximumSize(new Dimension(150, 20));
		panel_connection.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("connect");
		btnNewButton_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_connection.add(btnNewButton_3);
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
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setMinimumSize(new Dimension(16, 4));
		textArea_1.setMaximumSize(new Dimension(800, 100));
		textArea_1.setLineWrap(true);
		bot_panel.add(textArea_1);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.setMinimumSize(new Dimension(60, 25));
		btnNewButton.setMaximumSize(new Dimension(100, 50));
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		bot_panel.add(btnNewButton);
		//frame.pack();
	}

}
