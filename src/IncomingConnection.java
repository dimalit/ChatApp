import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class IncomingConnection extends JFrame {

	public IncomingConnection() {
		this.setSize(380, 150);
		this.setTitle("Incoming Connection");
		ImageIcon image = new ImageIcon("F:\\chaticon.jpg");
		this.setIconImage(image.getImage());
		final JPanel jPanel = new JPanel();
		jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
		final JPanel pan1 = new JPanel();
		final JPanel pan2 = new JPanel();
		final JLabel jLabel = new JLabel("User "+ Protocol.remoteNick +" wants to chat");
		final JButton acceptb = new JButton("Accept");
		final JButton declineb = new JButton("Decline");

		acceptb.addActionListener(new ActionListener(){
			
			 @Override
			 public void actionPerformed(ActionEvent e){
	                Protocol.statusBusy = true;


	            }
	        });
		
		
		declineb.addActionListener(new ActionListener(){
			
			 @Override
			 public void actionPerformed(ActionEvent e){
	                //���������
				

	            }
	        });
		
		declineb.setPreferredSize(new Dimension(100, 25));
		acceptb.setPreferredSize(new Dimension(100, 25));
		
		declineb.setBackground(new Color(116, 199, 209));
		acceptb.setBackground(new Color(116, 199, 209));
		pan1.setBackground(new Color(220, 243, 246));
		pan2.setBackground(new Color(220, 243, 246));
		pan1.add(jLabel);
		pan2.add(acceptb);
		pan2.add(declineb);

		jPanel.add(pan1);
		jPanel.add(pan2);
	
		this.add(jPanel);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/*public static void main(String[] args) {
		IncomingConnection incomingConnection = new IncomingConnection();
	}*/
}
