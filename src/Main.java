import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main extends JFrame {

    MainGUI mainFrame;

    public Main() {
        mainFrame = new MainGUI();

        mainFrame.getDisconnectBtn().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getDisconnectBtn().setIcon(new ImageIcon("src/images/disconP.png"));
                    }
                });
            }

            @Override
            public void mousePressed(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getDisconnectBtn().setIcon(new ImageIcon("src/images/disconP.png"));
                    }
                });
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getDisconnectBtn().setIcon(new ImageIcon("src/images/disconN.png"));
                    }
                });
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getDisconnectBtn().setIcon(new ImageIcon("src/images/disconH.png"));
                    }
                });
            }

            @Override
            public void mouseExited(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getDisconnectBtn().setIcon(new ImageIcon("src/images/disconN.png"));
                    }
                });
            }
        });

        mainFrame.getLogoutBtn().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getLogoutBtn().setIcon(new ImageIcon("src/images/logoutP.png"));
                    }
                });
            }

            @Override
            public void mousePressed(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getLogoutBtn().setIcon(new ImageIcon("src/images/logoutP.png"));
                    }
                });
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getLogoutBtn().setIcon(new ImageIcon("src/images/logoutN.png"));
                    }
                });
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getLogoutBtn().setIcon(new ImageIcon("src/images/logoutH.png"));
                    }
                });
            }

            @Override
            public void mouseExited(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getLogoutBtn().setIcon(new ImageIcon("src/images/logoutN.png"));
                    }
                });
            }
        });

        mainFrame.getOptionsBtn().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getOptionsBtn().setIcon(new ImageIcon("src/images/optionsP.png"));
                    }
                });
            }

            @Override
            public void mousePressed(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getOptionsBtn().setIcon(new ImageIcon("src/images/optionsP.png"));
                    }
                });
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getOptionsBtn().setIcon(new ImageIcon("src/images/optionsN.png"));
                    }
                });
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getOptionsBtn().setIcon(new ImageIcon("src/images/optionsH.png"));
                    }
                });
            }

            @Override
            public void mouseExited(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getOptionsBtn().setIcon(new ImageIcon("src/images/optionsN.png"));
                    }
                });
            }
        });

        mainFrame.getSendBtn().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getSendBtn().setIcon(new ImageIcon("src/images/sendP.png"));
                    }
                });
            }

            @Override
            public void mousePressed(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getSendBtn().setIcon(new ImageIcon("src/images/sendP.png"));
                    }
                });
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getSendBtn().setIcon(new ImageIcon("src/images/sendN.png"));
                    }
                });

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getSendBtn().setIcon(new ImageIcon("src/images/sendH.png"));
                    }
                });

            }

            @Override
            public void mouseExited(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getSendBtn().setIcon(new ImageIcon("src/images/sendN.png"));
                    }
                });

            }
        });

        mainFrame.getMessageField().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        mainFrame.setConnected();


    }
    public static void main(String[] args) {
        Main main = new Main();
    }
}