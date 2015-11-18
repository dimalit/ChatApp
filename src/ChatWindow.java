import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.*;

public class ChatWindow extends JFrame {

	public ChatWindow() {
		this.setSize(650, 500);
		this.setTitle("This is CHAT ");
		ImageIcon image = new ImageIcon("F:\\chaticon.jpg");
		this.setIconImage(image.getImage());
		
		final JPanel panel = new JPanel();
		final JPanel field1 = new JPanel();
		final JPanel field2 = new JPanel();
		final JPanel field3 = new JPanel();
		final JPanel fieldmess = new JPanel();
		final JPanel field = new JPanel();
		final JPanel messArea = new JPanel();
		final JButton but1 = new JButton("Apply");
		final JButton but2 = new JButton("Connect");
		final JButton but3 = new JButton("Disconnect");
		final JButton sendb = new JButton("Send");

		final JLabel txt1 = new JLabel("local login");
		final JLabel txt2 = new JLabel("remote login");
		final JLabel txt3 = new JLabel("remote addr");

		final JTextField text1 = new JTextField();
		final JTextField text2 = new JTextField();
		final JTextField text3 = new JTextField();
		final JTextArea textmess = new JTextArea();
		final JTextArea mess = new JTextArea();

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

		textmess.setMaximumSize(new Dimension(1500, 50));
		textmess.setPreferredSize(new Dimension(70, 50));
		text1.setMaximumSize(new Dimension(100, 25));
		text2.setMaximumSize(new Dimension(100, 25));
		text3.setMaximumSize(new Dimension(100, 25));

		but1.setPreferredSize(new Dimension(100, 25));
		but1.setMaximumSize(new Dimension(100, 25));
		but2.setPreferredSize(new Dimension(100, 25));
		but2.setMaximumSize(new Dimension(100, 25));
		but3.setPreferredSize(new Dimension(100, 25));
		but3.setMaximumSize(new Dimension(100, 25));
		sendb.setMaximumSize(new Dimension(70, 50));
		sendb.setPreferredSize(new Dimension(70, 50));

		but1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		text1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		txt1.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		but2.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		text2.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		txt2.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		but3.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		text3.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		txt3.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		field1.add(txt1);
		field2.add(txt2);
		field3.add(txt3);
		field1.add(text1);
		field2.add(text2);
		field3.add(text3);
		field1.add(but1);
		field2.add(but2);
		field3.add(but3);
		messArea.add(textmess);
		messArea.add(sendb);

		panel.setBackground(new Color(220, 243, 246));
		field1.setBackground(new Color(220, 243, 246));
		field2.setBackground(new Color(220, 243, 246));
		field3.setBackground(new Color(220, 243, 246));
		messArea.setBackground(new Color(220, 243, 246));
		mess.setBackground(new Color(237, 245, 246));

		but1.setBackground(new Color(116, 199, 209));
		but2.setBackground(new Color(116, 199, 209));
		but3.setBackground(new Color(116, 199, 209));
		sendb.setBackground(new Color(116, 199, 209));

		class SendAction implements ActionListener {
			private String messege;

			SendAction() {
			}

			public void actionPerformed(ActionEvent event) {
				messege = textmess.getText();
				mess.append(messege + "\n");
				textmess.setText("");
				//send
			}
		}
		class ApplyAction implements ActionListener {
			private String text;

			ApplyAction() {
			}

			public void actionPerformed(ActionEvent event) {
				text = txt1.getText();
				//apply
				
				
			}
		}
		class ConnectAction implements ActionListener {
			private String textc;

			ConnectAction() {
			}

			public void actionPerformed(ActionEvent event) {
				textc = txt2.getText();
				//connect
			}
		}
		class DisconnectAction implements ActionListener {

			DisconnectAction() {
			}

			public void actionPerformed(ActionEvent event) {
				// Disconnect
			}
		}
		SendAction send = new SendAction();
		sendb.addActionListener(send);
		ApplyAction applyact = new ApplyAction();
		but1.addActionListener(applyact);
		ConnectAction connectact = new ConnectAction();
		but2.addActionListener(connectact);
		DisconnectAction disconnectact = new DisconnectAction();
		but3.addActionListener(disconnectact);

		fieldmess.add(mess);
		final JScrollPane scrollPane = new JScrollPane(mess);
		fieldmess.add(scrollPane);

		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
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

	public static void main(String[] args) {
		ChatWindow a = new ChatWindow();
		
	}
}
