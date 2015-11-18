import javax.swing.*;
import java.awt.*;
public class MainGui {
	
	private static final int Height = 600;
	private static final int Widht = 800;

    public MainGui(Logic logic){
        LFrame frame = new LFrame(logic);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Widht, Height);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);
        frame.setTitle("CHATAPP");
        frame.setVisible(true);

    }


}
