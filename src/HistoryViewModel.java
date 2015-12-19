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

    public HistoryViewModel(){

    }

    private String composeString(String message){
        return new StringBuilder(simpleDateFormat.format(new Date(System.currentTimeMillis()))).append(" ").append(message).toString();
    }

    public void addRemoteMessage(String message){
        Messagelist.add(new StringBuilder(remoteNick).append(" ").append(composeString(message)).toString());
        notifyHistoryView();
    }

    public void addLocalMessage(String message){
        Messagelist.add(new StringBuilder(localNick).append(" ").append(composeString(message)).toString());
        notifyHistoryView();
    }

    public void addSystemMessage(String message){
        Messagelist.add(composeString(message));
        notifyHistoryView();
    }

    public void notifyHistoryView(){

    }

    public int getSize(){
        return Messagelist.size();
    }

    public String get(int i){
        return Messagelist.get(i);
    }

    public void setHistoryView(JTextArea historyView){
        this.historyView = historyView;
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

    public void clearView(){
        Messagelist = new ArrayList<String>();
        historyView.setText("");
    }


}
