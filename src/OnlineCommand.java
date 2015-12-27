import java.util.ArrayList;

public class OnlineCommand extends Command {

    ArrayList<Boolean> arrayList;

    public OnlineCommand() {
        super(CommandType.ONLINE_CONTACTS);
    }

    public OnlineCommand(ArrayList<Boolean> onlines) {
        super(CommandType.ONLINE_CONTACTS);
        arrayList = onlines;
    }

    public ArrayList<Boolean> getArrayList() {
        return arrayList;
    }
}
