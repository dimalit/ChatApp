import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryViewModel {
    /*
    Logic logic;
    List<String> Messagelist = new ArrayList<String>();
    HistoryView historyView;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("[kk:mm:ss dd.MM.yy]");

    public HistoryViewModel(Logic logic){
        this.logic = logic;
    }

    private String composeString(String message){
        return new StringBuilder(simpleDateFormat.format(new Date(System.currentTimeMillis()))).append(" ").append(message).toString();
    }

    public void addRemoteMessage(String message){
        Messagelist.add(new StringBuilder(logic.getRemoteNick()).append(" ").append(composeString(message)).toString());
        notifyHistoryView();
    }

    public void addLocalMessage(String message){
        Messagelist.add(new StringBuilder(logic.getLocalNick()).append(" ").append(composeString(message)).toString());
        notifyHistoryView();
    }

    public void addSystemMessage(String message){
        Messagelist.add(composeString(message));
        notifyHistoryView();
    }

    public void notifyHistoryView(){
        historyView.updateYourself();
    }

    public int getSize(){
        return Messagelist.size();
    }

    public String get(int i){
        return Messagelist.get(i);
    }

    public void setHistoryView(HistoryView historyView){
        this.historyView = historyView;
    }

    public void writeHistoryFile(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("[kk.mm.ss dd.MM.yy]");
        FileWriter out = null;
        try {
            out = new FileWriter(new StringBuilder(simpleDateFormat.format(
                    new Date(System.currentTimeMillis()))).append(logic.getRemoteNick()).append(".txt").toString());
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
        historyView.clear();
    }

*/
}
