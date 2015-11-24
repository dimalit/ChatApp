import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

import javax.swing.border.LineBorder;



public class LFrame extends JFrame {
	JPanel panel = new JPanel();
    Logic logic;

    private static final int Height = 600;
    private static final int Widht = 800;
	
	JButton Send;
	JButton Apply;
	JButton Disconnect;
	JButton Connect;

	
	JLabel login;
	JLabel addr;
	HistoryView smth;
	
	JTextField textfieldlogin;	
	JTextField EnterIp;
    JTextField mass;
	
	String name = "";

	String name1;
	String text;
	
	int adress;
	
	Font font = new Font("Verdana", Font.BOLD, 13);
	
	

	LineBorder linebord = new LineBorder(Color.BLACK, 1);

	LFrame(final Logic logic){
        this.logic=logic;

		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		login = new JLabel("login:");
		login.setFont(font);
		login.setBounds(35, 40, 60, 30);
		textfieldlogin = new JTextField();
		textfieldlogin.setBounds(80, 40, 115, 30);
		textfieldlogin.setBorder(linebord);
		
		addr = new JLabel("remote addr");
		addr.setFont(font);
		addr.setBounds(400,35,100,50);
		EnterIp = new JTextField();
		EnterIp.setBounds(500,40,150,30);
		EnterIp.setBorder(linebord);
       
		mass = new JTextField();
	    mass.setHorizontalAlignment(JTextField.LEFT);
		mass.setBorder(linebord);
		JTextArea ForMass = new JTextArea();
		ForMass.setEditable(false);     
		ForMass.setLineWrap(true);		  
		ForMass.setWrapStyleWord(true);  
		mass.setBounds(10, 480, 650, 75);


		smth = new HistoryView(logic.getHistoryViewModel());
		smth.setLocation(10, 140);
		smth.setSize(740,300);

        JScrollPane scrollPane = new JScrollPane(smth);
        scrollPane.setLocation(10, 140);
        scrollPane.setSize(740,300);
        scrollPane.setBorder(linebord);
		
		Apply = new JButton("Apply");
		Apply.setLocation(80, 80);
		Apply.setSize(75,30);
		Apply.setFont(font);
        Apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.setLocalNick(textfieldlogin.getText());
            }
        });
		
		Disconnect = new JButton("Disconnect");
		Disconnect.setLocation(600,80);
		Disconnect.setSize(130,30);
		Disconnect.setFont(font);
        Disconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.disconnect();
            }
        });
		
	    Connect  = new JButton("Connect");
	    Connect.setLocation(450,80);
	    Connect.setSize(100, 30);
	    Connect.setFont(font);
        Connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.setRemoteIP(EnterIp.getText());
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        logic.getHistoryViewModel().clearView();
                        logic.call();

                    }
                });
            }
        });

		Send = new JButton("Send");
		Send.setLocation(680, 530);
		Send.setSize(100,30);
		Send.addActionListener(new ActionListener( ) {
        	public void actionPerformed(ActionEvent ae) {
        		int h=0;

        		text = mass.getText();
                logic.sendMessage(text);
            	logic.getHistoryViewModel().addLocalMessage(text);
                mass.setText("");
            	

        		
        	}
        });
		
		
		panel.add(login);
		panel.add(textfieldlogin);
		panel.add(mass);
		panel.add(scrollPane);
		panel.add(EnterIp);
		panel.add(addr);
		panel.add(Send);
		panel.add(Apply);
		panel.add(Disconnect);
        panel.add(Connect);


        this.add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Widht, Height);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("ChatApp");
        setConnected(false);
        setVisible(true);
		
	
	}

    public void setBusy(Boolean busy){

    }

    public void setConnected(boolean b){
        if (b){
            Connect.setEnabled(false);
            Apply.setEnabled(false);
            Send.setEnabled(true);
            Disconnect.setEnabled(true);
            textfieldlogin.setEditable(false);
            EnterIp.setEditable(false);
        }
        else {
            Connect.setEnabled(true);
            Apply.setEnabled(true);
            Send.setEnabled(false);
            Disconnect.setEnabled(false);
            textfieldlogin.setEditable(true);
            EnterIp.setEditable(true);
        }
    }

}
