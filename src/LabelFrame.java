import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.border.*;


public class LabelFrame extends JFrame implements Observer{
	JPanel panel = new JPanel();	
	JTextArea textArea=new JTextArea();  
	JTextArea textAreaMessage=new JTextArea();
	JButton button1;
	JButton button2;
    JButton button3;
    JButton button4;
	
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

	public static Observer obj;
	
	
	public static void main(String aggs[]){
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
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
                
        button3 = new JButton("Apply");
		button3.setFont(font);
		button3.setBounds(90, 50, 115, 25);
        button3.setForeground(Color.blue);
		
		lable3 = new JLabel();
        lable3.setBounds(45, 100, 505, 200);
		lable3.setBorder(linebord);
		
		button2 = new JButton("Connect");
        button2.setBounds(435, 22, 115, 25);
		button2.setFont(font);
        button2.setForeground(Color.blue);
                
        button4 = new JButton("Disconnect");
        button4.setBounds(435, 50, 115, 25);
		button4.setFont(font);
        button4.setForeground(Color.blue);
		
		button1 = new JButton("Send");
        button1.setBounds(450, 315, 100, 25);
        button1.setFont(font);
        button1.setForeground(Color.blue);
		
		panel.add(lable1);
        panel.add(lable2);
        panel.add(lable3);
        panel.add(lable4);
		panel.add(textfieldlogin);
        panel.add(textfieldIP);
        panel.add(textfieldloclogin);
        panel.add(textfieldentermess);
		panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);

		this.add(panel);
		
		


		
		button1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String message=textfieldentermess.getText();
				long time=System.currentTimeMillis();
				String Stime = new SimpleDateFormat("HH:mm:ss").format(time);
				textArea.append("\n"  + "   " + NickName + " " + Stime + ":" + "\n" + "   " + message + "\n");
				textAreaMessage.setText("");
				if(comlt!=null){
					comlt.getConnection().sendMessage(message);
				}else{
					clt.getConnection().sendMessage(message);
				}
			}
			
		});
		
		
		button2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ip=textfieldIP.getText();
				button2.setEnabled(false);
				button4.setEnabled(true);
				
				try{
					Caller c=new Caller(NickName);
					Connection con=c.call();
					if(con!=null){
						button1.setEnabled(true);
						comlt=new CommandListenerThread(con);
						comlt.addObserver(LabelFrame.this);
						comlt.start();
				}else{
					 textArea.append("  could not connect ip addr: " + ip +"\n");
					 button2.setEnabled(true);
					 button4.setEnabled(false);
					 button3.setEnabled(true);
				}
			}catch(UnknownHostException e1){
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		
		
		button3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NickName=textfieldloclogin.getText();
				button3.setEnabled(false);
			}
			
		});
		clt=new CallListenerThread();
		clt.start();
		
		
		button4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				button1.setEnabled(false);
				button2.setEnabled(false);
				button3.setEnabled(true);
			}
			
		});
		
	
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		button1.setEnabled(true);
		button2.setEnabled(false);
		NickCommand c;
		MessageCommand mc;
		
		if(arg instanceof NickCommand){
            c=(NickCommand) arg;
            //textArea.append(c.intoString()+"\n");
        }
        if(arg instanceof MessageCommand){
            mc=(MessageCommand) arg;
            textArea.append("Message: "+mc.getMessage()+"\n");
        }
	}

}