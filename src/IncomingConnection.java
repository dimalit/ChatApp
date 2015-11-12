import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncomingConnection extends JFrame{

    public IncomingConnection() {
        this.setSize(450,200);
        this.setTitle("Incoming Connection");

        final JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.X_AXIS));

        final JLabel jLabel = new JLabel("User XXX from IP Y.Y.Y.Y wants to chat");

        final JButton jButton = new JButton("Accept");
        final JButton jButton1 = new JButton("Decline");

        class ButtonListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {

            }
        }

        jPanel.add(jLabel);
        jPanel.add(jButton);
        jPanel.add(jButton1);
        jLabel.setAlignmentX(CENTER_ALIGNMENT);

        this.add(jPanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        IncomingConnection incomingConnection = new IncomingConnection();
    }
}
