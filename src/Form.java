import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


public class Form implements Observer{
	
	 JFrame frame = new JFrame("ChatApp2015");
	
	
	 JLabel TextNick = new JLabel();
	 JLabel TextIP = new JLabel();
	 JLabel forText3 = new JLabel();
	 JLabel forText4 = new JLabel();	
	
	
	 JFrame frame1 = new JFrame(); //up wdw
	 JPanel mainPanel = new JPanel();
	 JPanel nickip = new JPanel(); //ник айпи(?)
	 JPanel nickPanel = new JPanel();
	 JPanel ipPanel = new JPanel(); 
	 JPanel inputPanel = new JPanel(); 
	 JPanel conPanel = new JPanel(); 
	 JPanel outputPanel = new JPanel(); 
	 JPanel panel1 = new JPanel(); 
	 JPanel ioPanel = new JPanel();
	 JPanel panel2 = new JPanel();
	
	
	 TextField textfieldloclogin = new TextField(35);
	 TextField textfieldIP = new TextField(35);
	 JTextArea textArea = new JTextArea(); 
	 JTextArea textfieldentermess = new JTextArea();
	
	
	 JButton disconnect = new JButton("Disconnect");
	 JButton accept = new JButton("Accept");
	 JButton reject = new JButton("Reject");
	 JButton apply = new JButton("Apply");
	 JButton connect = new JButton("Connect");
	 JButton send = new JButton("Send");

	 JScrollPane areaScrollPane = new JScrollPane(textArea);

	
	private CommandListenerThread comlt;
	private CallListenerThread clt;
	public static Observer obj;
	

	public String NickName;
	public String ip;

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable(){
	        public void run(){
	            new Form();
	        }
	    });
	}
	
	public Form(){
		obj=this;
		
		clt=new CallListenerThread();
		clt.start();
		
		
		 Toolkit kit = Toolkit.getDefaultToolkit();
		 Dimension screenSize = kit.getScreenSize();
		 int screenWidth = screenSize.width;
		 int screenHeight = screenSize.height;
		 frame.setSize(400, 200);

		 
		 mainPanel.setLayout(new BorderLayout());




		 nickip.setOpaque(false);
		 nickip.setMaximumSize(new Dimension(screenWidth, screenHeight));
		 nickip.setPreferredSize(new Dimension(screenWidth /3 ,screenHeight / 8));//input
		 nickip.setLayout(new BoxLayout(nickip, BoxLayout.X_AXIS));



		 nickPanel.setOpaque(false);
		 nickPanel.add(Box.createVerticalStrut(15));
		 nickPanel.setLayout(new BoxLayout(nickPanel, BoxLayout.Y_AXIS));
		 nickPanel.setPreferredSize(new Dimension(screenWidth/8,screenHeight / 8));//nickpanel
		 nickPanel.setMaximumSize(new Dimension(screenWidth/4,screenHeight / 4));
		 nickPanel.setMinimumSize(new Dimension(screenWidth/8,screenHeight / 8));
		 nickPanel.setOpaque(false);

		 TextNick.setText("Nickname: ");
		 nickPanel.add(TextNick);
		 textfieldloclogin.setMaximumSize(new Dimension(200 , 25));
		 nickPanel.add(textfieldloclogin);
		 nickPanel.add(apply);

		 
		 ipPanel.setOpaque(false);
		 ipPanel.add(Box.createVerticalStrut(15));
		 ipPanel.setLayout(new BoxLayout(ipPanel, BoxLayout.Y_AXIS));
		 ipPanel.setPreferredSize(new Dimension(screenWidth/8,screenHeight / 8));
		 ipPanel.setMaximumSize(new Dimension(screenWidth/4,screenHeight / 4));
		 ipPanel.setMinimumSize(new Dimension(screenWidth/8,screenHeight / 8));
		 ipPanel.setOpaque(false);
		 
		 TextIP.setText("IP for conection: ");
		 ipPanel.add(TextIP);
		 textfieldIP.setMaximumSize(new Dimension(200 , 25));
		 ipPanel.add(textfieldIP, BorderLayout.SOUTH);


		 inputPanel.setOpaque(false);
		 inputPanel.setMaximumSize(new Dimension(screenWidth /2, screenHeight / 25));
		 inputPanel.setPreferredSize(new Dimension(screenWidth /2 ,screenHeight / 25));
		 inputPanel.setMinimumSize(new Dimension(screenWidth/2,screenHeight / 25));

		 inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

		 inputPanel.add(new JScrollPane(textfieldentermess));
		 send.setEnabled(false);
		 inputPanel.add(send);


		 outputPanel.setOpaque(false);
		 outputPanel.setMaximumSize(new Dimension(screenWidth/2, 500));
		 outputPanel.setPreferredSize(new Dimension(screenWidth/2 ,500));
		 outputPanel.setMinimumSize(new Dimension(screenWidth/2,500));
		 outputPanel.setLayout(new BorderLayout());		 
		 areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 outputPanel.add(new JScrollPane(textArea));
		 textArea.setEditable(false);


		 conPanel.setOpaque(false);
		 conPanel.setLayout(new BoxLayout(conPanel, BoxLayout.LINE_AXIS));
		 disconnect.setEnabled(false);
		 conPanel.add(connect);
		 conPanel.add(disconnect);



			send.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String message=textfieldentermess.getText();
					textArea.setText("You: "+message+"\n");
					if(comlt!=null){
						comlt.getConnection().sendMessage(message);
					}else{
						clt.getConnection().sendMessage(message);
					}
				}
				
			});
			
			
			connect.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					ip=textfieldIP.getText();
					textArea.setText(ip +"\n");
					connect.setEnabled(false);
					disconnect.setEnabled(true);
					
					
					try{
						Caller c=new Caller(ip);
						Connection con=c.call();
						if(con!=null){
							send.setEnabled(true);
							
							comlt=new CommandListenerThread(con);
							comlt.addObserver((Observer) Form.this);//!!!
							comlt.start();
					}else{
						 textArea.setText("Error!" +"\n");
						 
						 connect.setEnabled(true);
						 disconnect.setEnabled(false);
						 apply.setEnabled(true);
					}
				}catch(UnknownHostException e1){
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			});
			
			
			apply.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Connection.NickName=textfieldloclogin.getText();
					apply.setEnabled(false);
				}
				
			});

			
			
			disconnect.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					send.setEnabled(false);
					connect.setEnabled(true);
					apply.setEnabled(true);
				}
				
			});
		    
		    frame1.setLocationRelativeTo(null);
			  
		    panel1.add(accept);
		    panel1.add(reject);
			    
		    forText3.setText("Somebody trying to get connection with you" + "\n");
		    frame1.add(forText3);
			    
		    frame1.setSize(200, 100);
		    frame1.add(panel1, BorderLayout.SOUTH);
		    frame1.setAlwaysOnTop(true);
		    frame1.setVisible(false); //(?)
			    
			    

			    
			    
			    ioPanel.setPreferredSize(new Dimension(screenWidth/2,600));
			    ioPanel.setMaximumSize(new Dimension(screenWidth/2,600));
			    ioPanel.setMinimumSize(new Dimension(screenWidth/2,600));
			    
			    ioPanel.add(outputPanel, BorderLayout.CENTER);
			    ioPanel.add(inputPanel, BorderLayout.SOUTH);
			    frame.add(mainPanel);
			    mainPanel.add(ioPanel, BorderLayout.CENTER);
			    mainPanel.add(panel2, BorderLayout.EAST);
			    
			    mainPanel.add(nickip, BorderLayout.NORTH);
			    nickip.add(nickPanel);
			    nickip.add(ipPanel);
			    ipPanel.add(conPanel);
			    
			    
			    frame.pack();
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    frame.setVisible(true);

	}
	
public void update(Observable o, Object arg){
	send.setEnabled(true);
    connect.setEnabled(false);
    

    MessageCommand mc;
    Command com;


    if(arg instanceof MessageCommand){
        mc=(MessageCommand) arg;

        textArea.setText(mc.getMessage());
    }
    if(arg instanceof Command){
        com =(Command) arg;
        if (com.getType()==CommandType.ACCEPT){
        	textArea.setText("Accepted");
            connect.setEnabled(false);
            disconnect.setEnabled(true);
            send.setEnabled(true);
            apply.setEnabled(false);
        }

        if (com.getType()==CommandType.REJECT){
            textArea.setText("Declined");
            connect.setEnabled(true);
            disconnect.setEnabled(false);
            send.setEnabled(false);
            apply.setEnabled(true);
        }

        if (com.getType()==CommandType.DISCONNECT){
            textArea.setText("Disconnected");
            connect.setEnabled(true);
            disconnect.setEnabled(false);
            send.setEnabled(false);
            apply.setEnabled(true);
        }





    }
}

}
