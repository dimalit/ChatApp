/*import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.border.*;


public class LabelFrame extends JFrame implements Observer{
	/**
	 * 
	 */
/*	private static final long serialVersionUID = -4789525072053991912L;
	
	JFrame frame=new JFrame("ChatApp");
	JFrame upfr=new JFrame();
	
	JPanel panel = new JPanel();
	
	JTextArea textArea=new JTextArea();  
	JTextArea textAreaMessage=new JTextArea();
	
	JButton send;	//send
	JButton connect;	//connect
    JButton apply;	//apply
    JButton disconnect;	//disconnect
    
    JPanel main=new JPanel();
    JPanel nickname_ip=new JPanel();
    JPanel nickname=new JPanel();
    
    
    
	
	JLabel lable1;
	JLabel lable2;
	JLabel lable3;
    JLabel lable4;
    JLabel lable5;
       
	JTextField textfieldlogin;
	JTextField textfieldIP;
    JTextField textfieldloclogin;
    JTextField textfieldentermess;
        
    JScrollPane Scroll = new JScrollPane(textArea);   
     	
    String name = "Чат";

	Font font = new Font("Time New Romans", Font.BOLD, 13);
	Color col = new Color(200, 200, 200);
	LineBorder linebord = new LineBorder(Color.BLUE, 1);
	
	
	String NickName;
	String ip;
	
	CommandListenerThread comlt;
	CallListenerThread clt;

	static Observer obj;
	
	
	public static void main(String aggs[]){
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				try {
					new LabelFrame();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
	}
	

	LabelFrame() throws IOException{
		obj=this;
		
		clt=new CallListenerThread();
		clt.start();
		
		Toolkit kit = Toolkit.getDefaultToolkit();
	    Dimension screenSize = kit.getScreenSize();
	    int screenWidth = screenSize.width;
	    int screenHeight = screenSize.height;
	    frame.setSize(screenWidth / 2, screenWidth / 2);
	    frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		lable1 = new JLabel("Nickname:");
		lable1.setFont(font);
		lable1.setForeground(Color.blue);
		lable1.setBounds(15, 20, 100, 30);
                
        lable2 = new JLabel("IPaddress:");
		lable2.setFont(font);
		lable2.setForeground(Color.blue);
		lable2.setBounds(250, 20, 100, 30);
                
        lable4 = new JLabel("UserNickName:");
		lable4.setFont(font);
		lable4.setForeground(Color.blue);
		lable4.setBounds(220, 50, 120, 30);
                                
        textfieldlogin = new JTextField();
		textfieldlogin.setBounds(90, 22, 115, 25);
		textfieldlogin.setBorder(linebord);
                
        textfieldIP = new JTextField();
		textfieldIP.setBounds(320, 22, 115, 25);
		textfieldIP.setBorder(linebord);
                
        textfieldloclogin = new JTextField();
		textfieldloclogin.setBounds(320, 50, 115, 25);
		textfieldloclogin.setBorder(linebord);
                
        textfieldentermess = new JTextField();
		textfieldentermess.setBounds(45, 315, 400, 25);
		textfieldentermess.setBorder(linebord);
                
        apply = new JButton("Apply");
		apply.setFont(font);
		apply.setBounds(90, 50, 115, 25);
        apply.setForeground(Color.blue);
		
		lable3 = new JLabel();
        lable3.setBounds(45, 100, 505, 200);
		lable3.setBorder(linebord);
		
		connect = new JButton("Connect");
        connect.setBounds(435, 22, 115, 25);
		connect.setFont(font);
        connect.setForeground(Color.blue);
                
        disconnect = new JButton("Disconnect");
        disconnect.setBounds(435, 50, 115, 25);
		disconnect.setFont(font);
        disconnect.setForeground(Color.blue);
		
		send = new JButton("Send");
        send.setBounds(450, 315, 100, 25);
        send.setFont(font);
        send.setForeground(Color.blue);
		
		panel.add(lable1);
        panel.add(lable2);
        panel.add(lable3);
        panel.add(lable4);
		panel.add(textfieldlogin);
        panel.add(textfieldIP);
        panel.add(textfieldloclogin);
        panel.add(textfieldentermess);
		panel.add(send);
        panel.add(connect);
        panel.add(apply);
        panel.add(disconnect);
        panel.add(Scroll);

		this.add(panel);
		//textAreaMessage.setText("aaaaa");
		
		Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(new JScrollPane(textArea));
		textArea.setEditable(false);
		
		send.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String message=textfieldentermess.getText();
				long time=System.currentTimeMillis();
				String Stime = new SimpleDateFormat("HH:mm:ss").format(time);
				String s="\n"  + "   " + NickName + " " + Stime + ":" + "\n" + "   " + message + "\n";
				//textArea.append();
				textAreaMessage.setText(s);
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
				connect.setEnabled(false);
				disconnect.setEnabled(true);
				
				try{
					Caller c=new Caller(NickName);
					Connection con=c.call();
					if(con!=null){
						send.setEnabled(true);
						
						comlt=new CommandListenerThread(con);
						comlt.addObserver(LabelFrame.this);
						comlt.start();
				}else{
					 //textArea.append("  could not connect ip addr: " + ip +"\n");
					 
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
				NickName=textfieldloclogin.getText();
				apply.setEnabled(false);
			}
			
		});

		
		
		disconnect.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				send.setEnabled(false);
				connect.setEnabled(false);
				apply.setEnabled(true);
			}
			
		});
		
	
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		send.setEnabled(true);
		connect.setEnabled(false);
		NickCommand c;
		MessageCommand mc;
		
		if(arg instanceof NickCommand){
            c=(NickCommand) arg;
        }
        if(arg instanceof MessageCommand){
            mc=(MessageCommand) arg;
            textArea.append("Message: "+mc.getMessage()+"\n");
        }
	}

}*/