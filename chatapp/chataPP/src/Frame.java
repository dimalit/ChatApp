import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Frame {

	private JFrame frame;
	private JTextField text;
	private JTextField nick;
	private JTextField Logi;
	private JTextField Add;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame f = new Frame();
					f.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Frame() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame("ChatApp");
		frame.setBounds(100, 100, 500, 400);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		
		JPanel login = new JPanel();
		login.setLayout(new BoxLayout(login,BoxLayout.Y_AXIS));
		
		JPanel panelNick = new JPanel();
		panelNick.setLayout(new BoxLayout(panelNick,BoxLayout.X_AXIS));
		
		login.add(panelNick);
		JLabel loginLabel = new JLabel("Логин");
		panelNick.add(loginLabel);	
				
		nick = new JTextField();
		nick.setMaximumSize(new Dimension(150, 20));
		panelNick.add(nick);
		nick.setColumns(10);
		
		JPanel panel_connection=new JPanel();
		panel_connection.setMaximumSize(new Dimension(32767, 100));
		panel_connection.setLayout(new GridLayout(2,3));
		frame.getContentPane().add(topPanel);
		topPanel.add(login);
		
		JButton nickApplyButton = new JButton("Войти");
		login.add(nickApplyButton);
		topPanel.add(panel_connection);
		
		JLabel time = new JLabel("Время общения");
		login.add(time);
		
		JLabel remoteNickLabel = new JLabel("Удаленный логин");
		remoteNickLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_connection.add(remoteNickLabel);
		
		Logi = new JTextField();
		Logi.setMaximumSize(new Dimension(150, 20));
		panel_connection.add(Logi);
		Logi.setColumns(10);
		
		JButton connect = new JButton("Соединиться");
		connect.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_connection.add(connect);
		
		JLabel remoteAddrLabel = new JLabel("IP-адрес");
		remoteAddrLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_connection.add(remoteAddrLabel);
		
		Add= new JTextField();
		Add.setMaximumSize(new Dimension(100, 20));
		panel_connection.add(Add);
		Add.setColumns(10);
		
		JButton disc = new JButton("Разьединиться");
		disc.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_connection.add(disc);
		
		JPanel main_panel= new JPanel();
		main_panel.setLayout(new GridLayout(1,1));
		
		JPanel bot_panel = new JPanel();
		bot_panel.setLayout(new BoxLayout(bot_panel,BoxLayout.X_AXIS));
		frame.getContentPane().add(main_panel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new LineBorder(Color.BLACK, 3));
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setRows(10);
		main_panel.add(textArea);
		frame.getContentPane().add(bot_panel);
		
		JTextArea text = new JTextArea();
		text.setMinimumSize(new Dimension(16, 40));
		text.setMaximumSize(new Dimension(300, 100));
		text.setLineWrap(true);
		bot_panel.add(text);
		
		JButton button = new JButton("Отправить");
		button.setMinimumSize(new Dimension(60, 25));
		button.setMaximumSize(new Dimension(100, 50));
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		bot_panel.add(button);

	}

}
