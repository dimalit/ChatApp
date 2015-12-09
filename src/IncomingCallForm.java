
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class IncomingCallForm extends JFrame {

    private static final int Height = 150;
    private static final int Widht = 300;
    private CallListenerThread callListenerThread;

    JPanel panel = new JPanel();
	
	JButton Accept;
	JButton Dismiss;
	JLabel NickName;

    public void close(){
        dispose();
    }
	


	IncomingCallForm(final Connection connection, final Logic logic, String username){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(Widht, Height);
        setResizable(false);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setTitle("Incoming call");

		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.pink);
		
		NickName = new JLabel(username+ " is calling");
		NickName.setLocation(60, 10);
		NickName.setSize(200,54);
	
	    Accept = new JButton("Accept");
		Accept.setLocation(18, 80);
		Accept.setSize(130,30);
        Accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connection.accept();
                logic.accept(connection);
                close();
            }
        });
		
		Dismiss = new JButton("Reject");
		Dismiss.setLocation(150,80);
		Dismiss.setSize(130,30);
        Dismiss.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connection.reject();
                close();
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                connection.reject();
                close();
            }
        });
		
		panel.add(Accept);
		panel.add(Dismiss);
		panel.add(NickName);

	
		
		this.add(panel);
        setVisible(true);
	}

}