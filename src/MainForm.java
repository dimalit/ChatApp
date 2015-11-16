import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class MainForm extends JFrame{
	
	private JTextArea dialog = new JTextArea(); // dialogue history
	private JTextField msg = new JTextField(); // new message
	private JTextField locNick = new JTextField(); // local nick 
	private JTextField rmtAddr = new JTextField(); // remote address
	private JTextField rmtNick = new JTextField();// remote nick
	private JLabel localN = new JLabel("Local nick");
	private JLabel remoteAdr = new JLabel("Remote address");
	private JLabel remoteN = new JLabel("Remote nick");
	private JButton apply = new JButton("Apply");
	private JButton con = new JButton("Connect");
	private JButton discon = new JButton("Disconnect"); 
	
	public MainForm(){
		setTitle("ChatApp 2015");
		setResizable(false);
		setBounds(100, 100, 800,500);
		setLayout(null);
		
		Font font = localN.getFont().deriveFont(15f);
		
		localN.setBounds(10, 20, 130, 20);
		localN.setFont(font);
		add(localN);
		locNick.setBounds(135, 15, 150, 30);
		add(locNick);
		apply.setBounds(290, 15, 90, 30);
		add(apply);
		
		remoteAdr.setBounds(10, 60, 130, 20);
		remoteAdr.setFont(font);
		add(remoteAdr);
		rmtAddr.setBounds(135, 55, 150, 30);
		add(rmtAddr);
		con.setBounds(290, 55, 90, 30);
		add(con);
		
		remoteN.setBounds(410, 20, 130, 20);
		remoteN.setFont(font);
		add(remoteN);
		rmtNick.setBounds(520, 15, 150, 30);
		add(rmtNick);
		discon.setBounds(675, 15, 110, 30);
		add(discon);

		dialog.setBounds(50, 100, 700, 250);
		add(dialog);
		
		msg.setBounds(100, 380, 600, 60);
		add(msg);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				MainForm form = new MainForm();
			}
		});
	}
	
}
