import java.util.ArrayList;

public class ContactsCommand extends Command {

    ArrayList<Contact> arrayList;

    public ContactsCommand(){
        super(CommandType.CONTACTS);
    }

    public ContactsCommand(ArrayList<Contact> tmp){
        super(CommandType.CONTACTS);
        arrayList=tmp;
    }

    public ArrayList<Contact> getArrayList() {
        return arrayList;
    }
}
