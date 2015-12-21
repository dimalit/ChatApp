import java.util.ArrayList;

public class MyContactsCommand extends Command {

    ArrayList<Contact> arrayList;

    public MyContactsCommand(){
        super(CommandType.MY_CONTACTS);
    }

    public MyContactsCommand(ArrayList<Contact> result) {
        super(CommandType.MY_CONTACTS);
        arrayList = result;
    }

    public ArrayList<Contact> getArrayList() {
        return arrayList;
    }
}
