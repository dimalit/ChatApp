import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Vector;


public class ChatForm extends JFrame {
    private JPanel rootPanel;
    private JButton SendButton;
    private JButton ButtonChangeLocalNick;
    public static String LocalNick;
    private JButton  Disconnect;
    private JButton Connect;
    private JTextField textFieldIp;
    private JTextField textFieldNick;
    private JTextField textFieldLocalNick;
    private JTextArea MessageStory;
    private JTextArea MyText;
    Connection connection = null;


    Caller caller = null;
    CallListener callListener = null;
    CommandListenerThread commandListenerServer = null;
    CommandListenerThread commandListenerClient = null;






    public ChatForm() {

        super();
        setContentPane(rootPanel);
        setSize(700, 500);
        try {
            Connect.setEnabled(false);
            Disconnect.setEnabled(false);
            textFieldIp.setEnabled(false);
            textFieldNick.setEnabled(false);

            MyText.setEnabled(false);
            SendButton.setEnabled(false);
        }catch (NullPointerException e){

        }



        try{
            Connect.addActionListener (e -> {
                MyText.setEnabled(true);
                if (connection != null)
                    try {
                        connection.SendNickHello( LocalNick);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                else {
                    new Thread(() -> {
                        caller = new Caller(LocalNick, textFieldIp.getText());
                        try {
                            try {
                                connection = caller.call();
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        } catch (NullPointerException e1) {
                            e1.printStackTrace();
                        }
                        commandListenerClient = new CommandListenerThread(connection);
                        try {
                            try {
                                connection.SendNickHello( LocalNick);
                            } catch (NullPointerException e1) {
                                e1.printStackTrace();
                            }
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        commandListenerClient.start();
                    }).start();
                }
            });}catch (NullPointerException e){

        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);







        //Change local nick and activate next field
        ButtonChangeLocalNick.addActionListener(e -> {
            if (!textFieldLocalNick.getText().isEmpty()) {
                LocalNick = textFieldLocalNick.getText();
                Connect.setEnabled(true);
                textFieldIp.setEnabled(true);
                textFieldNick.setEnabled(true);


                textFieldLocalNick.setEnabled(false);
                ButtonChangeLocalNick.setEnabled(false);


                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {


                        try {
                            callListener = new CallListener();
                            callListener.setLocalNick(LocalNick);
                            commandListenerServer = new CommandListenerThread(callListener.getConnection());

                            commandListenerServer.start();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                };
                new Thread(runnable).start();
            }
        });


        SendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String messenger = MyText.getText();

                long curTime = System.currentTimeMillis();
                String time = new SimpleDateFormat("HH:mm:ss").format(curTime);

                MessageStory.append("\n"  + "   " + LocalNick + " " + time + ":" + "\n" + " " + messenger + "\n");
                try {
                    //callThread.getConnection().sendMessage(messenger);
                    if(caller!=null){
                        callListener.getConnection().sendMessage(messenger);}
                    else{
                        callListener.getConnection().sendMessage(messenger);
                    }
                } catch (IOException e1) {

                    e1.printStackTrace();
                }
            }
        });
        textFieldLocalNick.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == '\n')
                    ButtonChangeLocalNick.doClick();
                super.keyPressed(e);
            }
        });
    }




}