import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

public class MainForm {

	private JFrame frame;
	private JButton apply;
	private JButton connect;
	private JButton disconnect;
	private JButton send;
	private JButton add;
	private JButton delete;
	private JLabel login;
	private JLabel remoteLogin;
	private JLabel remoteAddr;
	private JLabel time;
	private JLabel friends;
	private JTextArea textLogin;
	private JTextArea textRLogin;
	private JTextArea textRAddr;
	private JTextArea messArea;
	private JTextArea textMess;
	private JScrollPane scrollBar1;
	private JScrollPane scrollBar2;
	private JScrollPane scrollBar3;
	private JScrollPane scrollBar4;
	private JScrollPane scrollBar5;
	private JScrollPane scrollBar6;
	
	public MainForm() {
		start();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void start() {
		frame = new JFrame();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screen = kit.getScreenSize();
		int w = screen.width;
		int h = screen.height;
		frame = new JFrame("ChatApp");
		frame.setSize(w / 2, h / 2);
		frame.setLocation(w / 4, h / 4);
		
		JPanel top = new JPanel(new GridLayout(1,2,50,0));
		JPanel ltop = new JPanel(new GridLayout(2,2));
		JPanel rtop = new JPanel(new GridLayout(2,3));
		
		login = new JLabel();
		login.setText("Local Login");
		login.setHorizontalAlignment(JLabel.CENTER);
		login.setOpaque(true);
		login.setPreferredSize(new Dimension(70, 25));
		
		textLogin = new JTextArea(2,0);
		textLogin.setEditable(true);
		scrollBar1 = new JScrollPane(textLogin);
		scrollBar1.setViewportView(textLogin);
		
		apply = new JButton("Apply");
		
		time = new JLabel();
		time.setText("Time: 00:00:00");
		time.setOpaque(true);
		time.setPreferredSize(new Dimension(70, 25));
		
		ltop.add(login);
		ltop.add(scrollBar1);
		ltop.add(apply);
		ltop.add(time);
		
		remoteLogin = new JLabel();
		remoteLogin.setText("Remote Login");
		remoteLogin.setHorizontalAlignment(JLabel.CENTER);
		remoteLogin.setOpaque(true);
		remoteLogin.setPreferredSize(new Dimension(70, 25));
		
		textRLogin = new JTextArea(2,0);
		textRLogin.setEditable(true);
		scrollBar2 = new JScrollPane(textRLogin);
		scrollBar2.setViewportView(textRLogin);
		
		connect = new JButton("Connect");
		
		remoteAddr = new JLabel();
		remoteAddr.setText("Remote Addr");
		remoteAddr.setHorizontalAlignment(JLabel.CENTER);
		remoteAddr.setOpaque(true);
		remoteAddr.setPreferredSize(new Dimension(70, 25));
		
		textRAddr = new JTextArea(2,0);
		textRAddr.setEditable(true);
		scrollBar3 = new JScrollPane(textRAddr);
		scrollBar3.setViewportView(textRAddr);
		
		disconnect = new JButton("Disconnect");
		
		rtop.add(remoteLogin);
		rtop.add(scrollBar2);
		rtop.add(connect);
		rtop.add(remoteAddr);
		rtop.add(scrollBar3);
		rtop.add(disconnect);
		
		top.setBackground(Color.LIGHT_GRAY);
		top.add(ltop);
		top.add(rtop);
		
		JPanel bot = new JPanel(new GridLayout(1,2,10,0));
		
		send = new JButton("Send");
		send.setPreferredSize(new Dimension(30, 20));
		
		textMess = new JTextArea(3,5);
		textMess.setEditable(true);
		textMess.setLineWrap(true);
		scrollBar4 = new JScrollPane(textMess);
		scrollBar4.setViewportView(textMess);
		
		bot.setBackground(Color.LIGHT_GRAY);
		bot.add(scrollBar4);
		bot.add(send);
		
		messArea = new JTextArea();
		messArea.setEditable(false);
		messArea.setBorder(new LineBorder(Color.BLACK, 1));
		scrollBar5 = new JScrollPane(messArea);
		scrollBar5.setViewportView(messArea);
		
		JPanel frend = new JPanel(new BorderLayout());
		JPanel but = new JPanel(new GridLayout(1,2,25,0));
		
		friends = new JLabel();
		friends.setText("Friends");
		friends.setBorder(new LineBorder(Color.BLACK, 1));
		friends.setHorizontalAlignment(JLabel.CENTER);
		friends.setPreferredSize(new Dimension(30, 40));
		
		String[] elements = {"FRIENDS","FRIENDS","FRIENDS","FRIENDS","FRIENDS","FRIENDS","FRIENDS","FRIENDS","FRIENDS","FRIENDS","FRIENDS","FRIENDS","FRIENDS","FRIENDS"};
		JList northList = new JList(elements);
        northList.setLayoutOrientation(JList.VERTICAL);
        scrollBar6 = new JScrollPane(northList);
		scrollBar6.setViewportView(northList);
		
		add = new JButton("Add");
		delete = new JButton("Delete");
		add.setPreferredSize(new Dimension(30, 25));
		
		but.add(add);
		but.add(delete);
		
		frend.add(friends,BorderLayout.NORTH);
		frend.add(scrollBar6,BorderLayout.CENTER);
		frend.add(but,BorderLayout.SOUTH);
	
		frame.add(frend,BorderLayout.EAST);
		frame.add(scrollBar5, BorderLayout.CENTER);
		frame.add(top, BorderLayout.NORTH);
		frame.add(bot, BorderLayout.SOUTH);

	}

	public static void main(String[] args) {
		MainForm b = new MainForm();
	}
}
