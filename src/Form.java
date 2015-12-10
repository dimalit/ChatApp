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
	
	final JFrame frame = new JFrame("ChatApp2015");
	
	
	final JLabel TextNick = new JLabel();
	final JLabel TextIP = new JLabel();
	final JLabel forText3 = new JLabel();
	final JLabel forText4 = new JLabel();	
	
	
	final JFrame frame1 = new JFrame(); //всплывающее окно
	final JPanel mainPanel = new JPanel();
	final JPanel panel2 = new JPanel(); //ник айпи(?)
	final JPanel nickPanel = new JPanel();
	final JPanel ipPanel = new JPanel(); 
	final JPanel inputPanel = new JPanel(); 
	final JPanel conPanel = new JPanel(); 
	final JPanel outputPanel = new JPanel(); 
	final JPanel panel8 = new JPanel(); //сплывающа€ панель не работает
	final JPanel ioPanel = new JPanel();
	final JPanel panel10 = new JPanel();
	
	
	final TextField textfieldloclogin = new TextField(35);
	final TextField textfieldIP = new TextField(35);
	final JTextArea textArea = new JTextArea(); 
	final JTextArea textfieldentermess = new JTextArea();
	
	
	final JButton disconnect = new JButton("Disconnect");
	final JButton accept = new JButton("Accept");
	final JButton reject = new JButton("Reject");
	final JButton apply = new JButton("Apply");
	final JButton connect = new JButton("Connect");
	final JButton send = new JButton("Send");

	final JScrollPane areaScrollPane = new JScrollPane(textArea);

	
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



		    //вверхн€€ панель дл€ ввода ника и ip друга
		 panel2.setOpaque(false);
		 panel2.setMaximumSize(new Dimension(screenWidth, screenHeight));
		 panel2.setPreferredSize(new Dimension(screenWidth /2 ,screenHeight / 7));
		 panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));



		    //панель дл€ своего ника
		 nickPanel.setOpaque(false);
		 nickPanel.add(Box.createVerticalStrut(15));
		 nickPanel.setLayout(new BoxLayout(nickPanel, BoxLayout.Y_AXIS));

		 nickPanel.setPreferredSize(new Dimension(screenWidth/8,screenHeight / 8));
		 nickPanel.setMaximumSize(new Dimension(screenWidth/4,screenHeight / 4));
		 nickPanel.setMinimumSize(new Dimension(screenWidth/8,screenHeight / 8));
		 nickPanel.setOpaque(false);

		 TextNick.setText("My nickname: ");
		 nickPanel.add(TextNick);
		 textfieldloclogin.setMaximumSize(new Dimension(200 , 25));
		 nickPanel.add(textfieldloclogin);
		 nickPanel.add(apply);





		    //панель дл€  ip друга
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





		    //панель ввода текста
		 inputPanel.setOpaque(false);
		 inputPanel.setMaximumSize(new Dimension(screenWidth /2, screenHeight / 25));
		 inputPanel.setPreferredSize(new Dimension(screenWidth /2 ,screenHeight / 25));
		 inputPanel.setMinimumSize(new Dimension(screenWidth/2,screenHeight / 25));

		 inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

		 inputPanel.add(new JScrollPane(textfieldentermess));
		 send.setEnabled(false);
		 inputPanel.add(send);




		    //панель дл€ вывода сообщений на экран
		 outputPanel.setOpaque(false);
		 outputPanel.setMaximumSize(new Dimension(screenWidth/2, 500));
		 outputPanel.setPreferredSize(new Dimension(screenWidth/2 ,500));
		 outputPanel.setMinimumSize(new Dimension(screenWidth/2,500));

		 outputPanel.setLayout(new BorderLayout());
		 
		 areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		 outputPanel.add(new JScrollPane(textArea));
		 textArea.setEditable(false);




		    //панель дл€ кнопочек
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
					textArea.append("You: "+message+"\n");
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
					textArea.append(ip +"\n");
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
						 textArea.append("could not connect ip: " + ip +"\n");
						 
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
			  
			    panel8.add(accept);
			    panel8.add(reject);
			    
			    forText3.setText("User " + Connection.NickName + " tries to connect to you" + "\n");
			    frame1.add(forText3);
			    
			    frame1.setSize(200, 100);
			    frame1.add(panel8, BorderLayout.SOUTH);
			    frame1.setAlwaysOnTop(true);
			    frame1.setVisible(false); //будет тру когда звон€т
			    
			    
			    accept.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {

			        		//если соединилс€ 
			        }
			    });
			    
			    reject.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {

			        		//если отказалс€
			        }
			    });
			    
			    
			    //дл€ ввода и вывода сообщений
			    ioPanel.setPreferredSize(new Dimension(screenWidth/2,600));
			    ioPanel.setMaximumSize(new Dimension(screenWidth/2,600));
			    ioPanel.setMinimumSize(new Dimension(screenWidth/2,600));
			    
			    ioPanel.add(outputPanel, BorderLayout.CENTER);
			    ioPanel.add(inputPanel, BorderLayout.SOUTH);
			    frame.add(mainPanel);
			    mainPanel.add(ioPanel, BorderLayout.CENTER);
			    mainPanel.add(panel10, BorderLayout.EAST);
			    
			    mainPanel.add(panel2, BorderLayout.NORTH);
			    panel2.add(nickPanel);
			    panel2.add(ipPanel);
			    ipPanel.add(conPanel);
			    
			    
			    frame.pack();
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    frame.setVisible(true);

	}
	
public void update(Observable o, Object arg){
	send.setEnabled(true);
    connect.setEnabled(false);
    

    NickCommand nc;
    MessageCommand mc;
    Command com;

    if(arg instanceof NickCommand){
        nc=(NickCommand) arg;
        nc.getNickName();
        textArea.append("Connected to: "+NickName+"\n");
    }
    if(arg instanceof MessageCommand){
        mc=(MessageCommand) arg;

        long curTime = System.currentTimeMillis();
        String time = new SimpleDateFormat("HH:mm:ss").format(curTime);

        textArea.append("\n"  +/* "   " +NickName+*/ " " + time + ":" + "\n" + "   " + mc.getMessage() + "\n");
  
    }
    if(arg instanceof Command){
        com =(Command) arg;
        if (com.getType()==CommandType.ACCEPT){
            connect.setEnabled(false);
            disconnect.setEnabled(true);
            send.setEnabled(true);
            apply.setEnabled(false);
        }

        if (com.getType()==CommandType.REJECT){
            textArea.append("Declined");
            connect.setEnabled(true);
            disconnect.setEnabled(false);
            send.setEnabled(false);
            apply.setEnabled(true);
        }

        if (com.getType()==CommandType.DISCONNECT){
            textArea.append("Disconnected");
            connect.setEnabled(true);
            disconnect.setEnabled(false);
            send.setEnabled(false);
            apply.setEnabled(true);
        }





    }
}

}
