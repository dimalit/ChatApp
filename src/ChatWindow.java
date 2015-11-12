import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatWindow extends JFrame {

	public ChatWindow() {
		this.setSize(900, 700);
		this.setTitle("This is CHAT");

		final JPanel panel = new JPanel();
		final JPanel field1 = new JPanel();
		final JPanel field2 = new JPanel();
		final JPanel field3 = new JPanel();
		final JPanel messArea = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		final JLabel txt1 = new JLabel("    local login");
		final JLabel txt2 = new JLabel("remote login");
		final JLabel txt3 = new JLabel("remote addr");
		
		final JTextField text1 = new JTextField(10);
		final JTextField text2 = new JTextField(10);
		final JTextField text3 = new JTextField(10);
		final JTextArea textArea = new JTextArea(3, 72);
		final JTextArea mess = new JTextArea(15, 75);
		textArea.setLineWrap(true);
		mess.setLineWrap(true);
		
		final JButton but1 = new JButton("Apply");
		final JButton but2 = new JButton("Connect");
		final JButton but3 = new JButton("Disconnect");
		final JButton sendb = new JButton("Send");

		but1.setPreferredSize(new Dimension(100, 25));
		but2.setPreferredSize(new Dimension(100, 25));
		but3.setPreferredSize(new Dimension(100, 25));
		sendb.setPreferredSize(new Dimension(70, 50));
		
		field1.add(txt1);
		field2.add(txt2);
		field3.add(txt3);
		field1.add(text1);
		field2.add(text2);
		field3.add(text3);
		field1.add(but1);
		field2.add(but2);
		field3.add(but3);
		messArea.add(textArea);
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
		
		class SendAction implements ActionListener{
			private String message;
			
			public void actionPerformed(ActionEvent event) {
				message = textArea.getText();
				mess.append(message + "\n");
				textArea.setText("");
			}
		}

		class ApplyAction implements ActionListener{
			private String apply;

			public void actionPerformed(ActionEvent event) {
				apply = txt1.getText();
				text1.setText("");

			}
		}

		class ConnectAction implements ActionListener{
			private String connect;

			public void actionPerformed(ActionEvent event) {
				connect = txt2.getText();
				text2.setText("");

			}
		}

		class DisconnectAction implements ActionListener{
			private String disconnect;

			public void actionPerformed(ActionEvent event) {
				disconnect = txt3.getText();
				text3.setText("");

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

		panel.add(field1);
		panel.add(field2);
		panel.add(field3);
		panel.add(mess);
		panel.add(messArea);

		this.add(panel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		ChatWindow chatWindow = new ChatWindow();

		/*ImageIcon image = new ImageIcon("F:\\chaticon.jpg");
		chatWindow.setIconImage(image.getImage());*/
	}
}
