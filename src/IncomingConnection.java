import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		final JLabel jLabel = new JLabel(
				"User XXX from IP Y.Y.Y.Y wants to chat");

		final JButton jButton = new JButton("Accept");
		final JButton jButton1 = new JButton("Decline");

	
		
		jButton.addActionListener(new ActionListener(){
			
			 @Override
			 public void actionPerformed(ActionEvent e){
	                //принять
				 

	            }
	        });
		
		
		jButton1.addActionListener(new ActionListener(){
			
			 @Override
			 public void actionPerformed(ActionEvent e){
	                //отклонить
				

	            }
	        });
		
		jButton1.setPreferredSize(new Dimension(100, 25));
		jButton.setPreferredSize(new Dimension(100, 25));
		
		jButton1.setBackground(new Color(116, 199, 209));
		jButton.setBackground(new Color(116, 199, 209));
		pan1.setBackground(new Color(220, 243, 246));
		pan2.setBackground(new Color(220, 243, 246));
		pan1.add(jLabel);
		pan2.add(jButton);
		pan2.add(jButton1);

		jPanel.add(pan1);
		jPanel.add(pan2);
	
		this.add(jPanel);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		IncomingConnection incomingConnection = new IncomingConnection();
		
	}
}
