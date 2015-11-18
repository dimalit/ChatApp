import javax.swing.*;

public class HistoryView extends JTextArea{
    HistoryViewModel historyViewModel;
    int currentSize=0;

    public HistoryView(HistoryViewModel historyViewModel){
        this.historyViewModel=historyViewModel;
        historyViewModel.setHistoryView(this);
    }

    public void updateYourself(){
        int tmp = historyViewModel.getSize();
        for (int i=currentSize;i<tmp;i++){
            this.setText(new StringBuilder(this.getText()).append(historyViewModel.get(i)).append("\n").toString());
        }
        currentSize=tmp;
    }
}
