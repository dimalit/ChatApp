import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class LabelFrame extends JFrame {
	JPanel panel = new JPanel();	
	
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
	
        String name = "Чат";

	Font font = new Font("Time New Romans", Font.BOLD, 13);
	Color col = new Color(200, 200, 200);
	LineBorder linebord = new LineBorder(Color.BLUE, 1);

	LabelFrame(){
		
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
		
	
	}

}