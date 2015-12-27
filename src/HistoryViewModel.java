import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryViewModel {
    String localNick,remoteNick;
    List<String> Messagelist = new ArrayList<String>();
    JTextArea historyView;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("[kk:mm:ss dd.MM.yy]");


    public HistoryViewModel(JTextArea historyView){
        this.historyView = historyView;
    }

    private String composeString(String message){
        System.out.println(message);
        return new StringBuilder(simpleDateFormat.format(new Date(System.currentTimeMillis()))).append(" ").append(message).toString();
    }

    public void addRemoteMessage(String message){
        System.out.println(remoteNick);
        String tmp = new StringBuilder(remoteNick).append(" ").append(composeString(message)).toString();
        Messagelist.add(tmp);
        historyView.append(tmp+"\n");

    }

    public void addLocalMessage(String message){
        System.out.println(localNick);
        String tmp = new StringBuilder(localNick).append(" ").append(composeString(message)).toString();
        Messagelist.add(tmp);
        historyView.append(tmp+"\n");

    }

    public void addSystemMessage(String message){
        Messagelist.add(composeString(message));
        historyView.append(composeString(message)+"\n");
    }


    public String get(int i){
        return Messagelist.get(i);
    }

    public void writeHistoryFile(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("[kk.mm.ss dd.MM.yy]");
        FileWriter out = null;
        try {
            out = new FileWriter(new StringBuilder(simpleDateFormat.format(
                    new Date(System.currentTimeMillis()))).append(remoteNick).append(".txt").toString());
            for (String string : Messagelist){
                out.write(string+"\n");
                out.flush();
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setHistoryView(JTextArea historyView) {
        this.historyView = historyView;
    }

    public void clearView(){
        Messagelist = new ArrayList<String>();
        historyView.setText("");
    }

    public void setLocalNick(String localNick) {
        this.localNick = localNick;
    }

    public void setRemoteNick(String remoteNick) {
        this.remoteNick = remoteNick;
    }
}
