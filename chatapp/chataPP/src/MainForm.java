import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

public class MainForm {

	private JFrame frame;

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

		JPanel nick = new JPanel(new GridLayout(1, 2));
		JLabel t1 = new JLabel();
		JTextArea t2 = new JTextArea(2,0);
		t1.setText("Nick");
		t1.setHorizontalAlignment(JLabel.CENTER);
		t1.setBorder(new LineBorder(Color.BLACK, 1));
		t1.setBackground(Color.LIGHT_GRAY);
		t1.setOpaque(true);
		t1.setPreferredSize(new Dimension(70, 25));
		t2.setBorder(new LineBorder(Color.BLACK, 1));
		JScrollPane scrollBar1 = new JScrollPane(t2);
		scrollBar1.setViewportView(t2);
		nick.add(t1);
		nick.add(scrollBar1);

		JPanel nick2 = new JPanel(new GridLayout(2, 2));
		JButton b = new JButton("Apply");
		JTextArea texttime = new JTextArea("00:00:00");
		texttime.setBorder(new LineBorder(Color.BLACK, 2));
		texttime.setEditable(false);
		JLabel tt1 = new JLabel();
		tt1.setText("Time");
		tt1.setHorizontalAlignment(JLabel.CENTER);
		tt1.setBorder(new LineBorder(Color.BLACK, 1));
		tt1.setBackground(Color.LIGHT_GRAY);
		tt1.setOpaque(true);
		b.setBackground(Color.GREEN);
		b.setBorder(new LineBorder(Color.BLACK, 1));
		nick2.add(nick);
		nick2.add(tt1);
		nick2.add(b);
		nick2.add(texttime);

		JPanel conect = new JPanel(new GridLayout(2, 3));
		JLabel t11 = new JLabel();
		JLabel t12 = new JLabel();
		JTextArea t13 = new JTextArea(2,0);
		JTextArea t14 = new JTextArea(2,0);
		JScrollPane scrollBar2 = new JScrollPane(t13);
		scrollBar2.setViewportView(t13);
		JScrollPane scrollBar3 = new JScrollPane(t14);
		scrollBar3.setViewportView(t14);
		JButton b1 = new JButton("Connect");
		b1.setBackground(Color.GREEN);
		b1.setBorder(new LineBorder(Color.BLACK, 1));
		JButton b2 = new JButton("Disconnect");
		b2.setBackground(Color.RED);
		b2.setBorder(new LineBorder(Color.BLACK, 1));
		t11.setText("Remote IP");
		t11.setHorizontalAlignment(JLabel.CENTER);
		t11.setBackground(Color.LIGHT_GRAY);
		t11.setOpaque(true);
		t11.setBorder(new LineBorder(Color.BLACK, 1));
		t12.setText("Remote Nick");
		t12.setHorizontalAlignment(JLabel.CENTER);
		t12.setBackground(Color.LIGHT_GRAY);
		t12.setOpaque(true);
		t12.setBorder(new LineBorder(Color.BLACK, 1));
		t13.setBorder(new LineBorder(Color.BLACK, 1));
		t14.setBorder(new LineBorder(Color.BLACK, 1));
		conect.add(t11);
		conect.add(scrollBar2);
		conect.add(b1);
		conect.add(t12);
		conect.add(scrollBar3);
		conect.add(b2);
		conect.setForeground(Color.WHITE);
		JPanel conect2 = new JPanel(new GridLayout(1, 2,100,0));
//                conect2.setBackground(Color.cyan);
		conect2.add(nick2);
		conect2.add(conect);

		JPanel mess2 = new JPanel(new GridLayout(1, 2));
		JButton bm = new JButton("Send");
		bm.setBackground(Color.YELLOW);
		bm.setBorder(new LineBorder(Color.BLACK, 1));
		JLabel bag2 = new JLabel();
		mess2.add(bag2);
		mess2.add(bm);

		JPanel mess = new JPanel(new GridLayout(1,2));
		JTextArea m = new JTextArea("Message",4,5);
		JScrollPane scrollBar = new JScrollPane(m);
		scrollBar.setViewportView(m);
		m.setLineWrap(true);
		m.setBorder(new LineBorder(Color.BLACK, 1));
		mess.add(scrollBar);
		mess.add(mess2);


		JTextArea text = new JTextArea();
		text.setBorder(new LineBorder(Color.BLACK, 2));
		text.setEditable(false);
		JScrollPane scrollBar4 = new JScrollPane(text);
		scrollBar4.setViewportView(text);
		
		frame.add(scrollBar4, BorderLayout.CENTER);
		frame.add(conect2, BorderLayout.NORTH);
		frame.add(mess, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		MainForm b = new MainForm();
	}
}
