import javax.swing.*;
import javax.swing.plaf.metal.MetalScrollBarUI;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainGUI extends JFrame {

    final byte BTN_WIDTH = 97;
    final byte BTN_HEIGHT = 27;

    JLabel logged = new JLabel("Logged as");
    JLabel talking = new JLabel("Talking to");
    JLabel localNick = new JLabel("EvGe22");
    JLabel remoteNick = new JLabel("");

    JTextArea messageField = new JTextArea();
    JTextArea historyView = new JTextArea();

    JLabel logoutBtn = new JLabel("");
    JLabel disconnectBtn = new JLabel("");
    //JLabel optionsBtn = new JLabel("");
    JLabel sendBtn = new JLabel("");

    ContactsView contactsView = new ContactsView();

    JScrollPane contactsPane = new JScrollPane(contactsView);
    JScrollPane historyPane = new JScrollPane(historyView);

    JPanel leftTopPanel = new JPanel();
    JPanel leftBottomPanel = new JPanel();

    Font bigFont,smallFont;


    public MainGUI(){
        super("ChatApp 2015");
        createGUI();
    }

    private void createGUI(){

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new Dimension(860,550));
        setLayout(null);
        getContentPane().setBackground(Color.white);

        try {
            smallFont =  Font.createFont(Font.TRUETYPE_FONT, new File("src/font/roboto-thin.ttf")).deriveFont(15f);
            bigFont =  Font.createFont(Font.TRUETYPE_FONT, new File("src/font/roboto-thin.ttf")).deriveFont(18f);

        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        leftTopPanel.setBackground(Colors.softGreen);
        leftTopPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Colors.midGreen));
        leftTopPanel.setBounds(-1,-1,280,123);
        leftTopPanel.setLayout(null);
        add(leftTopPanel);

        leftBottomPanel.setBackground(Colors.softGreen);
        leftBottomPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Colors.midGreen));
        leftBottomPanel.setBounds(-1,121,280,getHeight()-150);
        leftBottomPanel.setLayout(null);
        add(leftBottomPanel);

        logged.setBounds(25,10,150,40);
        logged.setFont(bigFont);
        leftTopPanel.add(logged);

        localNick.setBounds(25,32,150,40);
        localNick.setFont(bigFont);
        leftTopPanel.add(localNick);

        talking.setBounds(153,10,150,40);
        talking.setFont(bigFont);
        leftTopPanel.add(talking);

        remoteNick.setBounds(153,32,150,40);
        remoteNick.setFont(bigFont);
        leftTopPanel.add(remoteNick);

        logoutBtn.setIcon(new ImageIcon("src/images/logoutN.png"));
        logoutBtn.setDisabledIcon(new ImageIcon("src/images/logoutD.png"));
        logoutBtn.setBounds(20,80,BTN_WIDTH,BTN_HEIGHT);
        leftTopPanel.add(logoutBtn);

        disconnectBtn.setIcon(new ImageIcon("src/images/disconN.png"));
        disconnectBtn.setDisabledIcon(new ImageIcon("src/images/disconD.png"));
        disconnectBtn.setEnabled(false);
        disconnectBtn.setBounds(155,80,BTN_WIDTH,BTN_HEIGHT);
        leftTopPanel.add(disconnectBtn);

        messageField.setBounds(getWidth()-544,getHeight()-122,430,50);
        messageField.setFont(smallFont);
        messageField.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Colors.lightGreen));
        messageField.setBackground(Colors.softGreen);
        add(messageField);
        messageField.setEnabled(false);

        sendBtn.setIcon(new ImageIcon("src/images/sendN.png"));
        sendBtn.setDisabledIcon(new ImageIcon("src/images/sendD.png"));
        sendBtn.setEnabled(false);
        sendBtn.setBounds(getWidth()-96,getHeight()-124, 51,51);
        add(sendBtn);

        /*optionsBtn.setIcon(new ImageIcon("src/images/optionsN.png"));
        optionsBtn.setBounds(getWidth()-47,getHeight()-70,24,24);
        add(optionsBtn);*/

        historyPane.getVerticalScrollBar().setPreferredSize(new Dimension(15, Integer.MAX_VALUE));
        historyPane.getVerticalScrollBar().setUI(new MyScrollbarUI());
        historyPane.setBackground(Color.white);
        historyPane.setBorder(null);

        add(historyPane);

        contactsPane.getVerticalScrollBar().setPreferredSize(new Dimension(15,Integer.MAX_VALUE));
        contactsPane.getVerticalScrollBar().setUI(new MyScrollbarUI());
        contactsPane.setBounds(25, 18, 240, leftBottomPanel.getHeight() - 50);
        contactsPane.setBorder(null);
        contactsPane.setBackground(Colors.softGreen);
        contactsView.setLabelFont(bigFont);
        leftBottomPanel.add(contactsPane);


        historyView.setFont(smallFont);
        historyView.setEditable(false);
        historyView.setText("\n\n\n\n\nasd\nafsdfsf\nafwasd\nasdfgd\naefasdf\nasdfsdrsetsd\naefsdfgste\nasfdsetsd\nsdfdgsersfg\nsdfgsgse\nserdfsd\naserfsdf\nsdfnn\n\n\n\\n\n\n\n\n\\n\n\n\n\n\n\n\n\n\n\n\n\nsdfsdf");


        resize();
        addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        resize();
                    }
                });
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        resize();
                    }
                });
            }
        });
    }

    public void resize(){
        leftBottomPanel.setBounds(-1,121,280,getHeight()-150);
        contactsPane.setBounds(25, 18, 240, leftBottomPanel.getHeight() - 50);
        historyPane.setBounds(321,40,getWidth()-370,getHeight()-195);
        sendBtn.setBounds(getWidth()-96,getHeight()-124, 51,51);
        //optionsBtn.setBounds(getWidth()-47,getHeight()-70,24,24);
        messageField.setBounds(320,getHeight()-122,getWidth()-430 /*430*/,50);

    }

   /* public JLabel getOptionsBtn() {
        return optionsBtn;
    } */

    public JLabel getDisconnectBtn() {
        return disconnectBtn;
    }

    public JLabel getLogoutBtn() {
        return logoutBtn;
    }

    public JTextArea getHistoryView() {
        return historyView;
    }

    public JTextArea getMessageField() {
        return messageField;
    }

    public JLabel getRemoteNick() {
        return remoteNick;
    }

    public JLabel getLocalNick() {
        return localNick;
    }

    public JLabel getSendBtn() {
        return sendBtn;
    }

    public ContactsView getContactsView() {
        return contactsView;
    }

    public void setConnected(){
        disconnectBtn.setEnabled(true);
        sendBtn.setEnabled(true);
        messageField.setEnabled(true);
        logoutBtn.setEnabled(false);
        messageField.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Colors.midGreen));
    }

    public void setDisconnected(){
        disconnectBtn.setEnabled(false);
        sendBtn.setEnabled(false);
        messageField.setEnabled(false);
        logoutBtn.setEnabled(true);
        messageField.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Colors.lightGreen));
    }

    public void setLocalNick(String nick){
        localNick.setText(nick);
    }

    private static class MyScrollbarUI extends MetalScrollBarUI {

        private Image imageThumb, imageTrack;
        private JButton b = new JButton() {

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(0, 0);
        }

        };

        MyScrollbarUI() {
            imageThumb = PlainImage.create(15, 15, Colors.mainGreen);
            imageTrack = PlainImage.create(15, 15, Colors.darkGreen);
        }

    @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
             g.translate(thumbBounds.x, thumbBounds.y);
             g.setColor( Color.red );
             g.drawRect( 0, 0, thumbBounds.width - 2, thumbBounds.height - 1 );
             AffineTransform transform = AffineTransform.getScaleInstance((double)thumbBounds.width/imageThumb.getWidth(null),(double)thumbBounds.height/imageThumb.getHeight(null));
             ((Graphics2D)g).drawImage(imageThumb, transform, null);
             g.translate(-thumbBounds.x, -thumbBounds.y );
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            g.translate(trackBounds.x, trackBounds.y);
            ((Graphics2D)g).drawImage(imageTrack,AffineTransform.getScaleInstance(1,(double)trackBounds.height/imageTrack.getHeight(null)),null);
            g.translate(-trackBounds.x, -trackBounds.y );
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return b;
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return b;
        }
    }

    private static class PlainImage {

        static public Image create(int w, int h, Color c) {
            BufferedImage bi = new BufferedImage(
                    w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bi.createGraphics();
            g2d.setPaint(c);
            g2d.fillRect(0, 0, w, h);
            g2d.dispose();
            return bi;
        }
    }

}
