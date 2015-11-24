import javax.swing.*;

public class UltimateGUI extends JFrame{

    public UltimateGUI(String nick){
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //todo Универсальная GUI с кнопкой по середине и лейблом с извещением над ней, который будет передаваться в конструкторе;
    }

    private void exit(){
        dispose();
    }
}
