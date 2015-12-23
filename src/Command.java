import java.util.ArrayList;
import java.util.Scanner;

public class Command {
    protected CommandType type ;

    public Command(){}

    public Command(CommandType type){
        this.type=type;
    }

    public static Command getCommand(Scanner in){
        String string ="";
        if (in.hasNextLine()) {
            string = in.nextLine();
        }
        else{
            return null;
        }
        if (string.startsWith(Protocol.ACCEPTED)) return new Command(CommandType.ACCEPT);
        if (string.startsWith(Protocol.DISCONNECT)) return new Command(CommandType.DISCONNECT);
        if (string.startsWith(Protocol.REJECTED)) return new Command(CommandType.REJECT);
        if (string.startsWith(Protocol.GREETING)) return  new NickCommand(string);
        if (string.startsWith(Protocol.MESSAGE)) return new MessageCommand(Protocol.decode(in.nextLine()));
        if (string.startsWith(Protocol.HELLO_SERVER)) return new Command(CommandType.HELLO_SERVER);
        if (string.startsWith(Protocol.HELLO_CLIENT)) return new Command(CommandType.HELLO_CLIENT);

        if (string.startsWith(Protocol.CONTACTS)){
            string = string.replace(Protocol.CONTACTS+" ","");
            ArrayList<Contact> result = new ArrayList<Contact>();
            String[] tmp = string.split(" ");
            for (int i=0;i<tmp.length;i+=2){
                Contact contact = new Contact(null,tmp[i]);
                contact.setOnline(Boolean.parseBoolean(tmp[i+1]));
                result.add(contact);
            }
            return new ContactsCommand(result);
        }

        if (string.startsWith(Protocol.MY_CONTACTS)){
            string = string.replace(Protocol.MY_CONTACTS+" ","");
            ArrayList<Contact> result = new ArrayList<Contact>();
            String[] tmp = string.split(" ");
            for (int i=0;i<tmp.length;i+=3){
                Contact contact = new Contact(null,tmp[i]);
                contact.setFav(Boolean.parseBoolean(tmp[i+1]));
                contact.setOnline(Boolean.parseBoolean(tmp[i + 2]));
                result.add(contact);
            }
            return new MyContactsCommand(result);
        }

        if (string.startsWith(Protocol.BUSY)) return new Command(CommandType.BUSY);
        if (string.startsWith(Protocol.OFFLINE)) return new Command(CommandType.OFFLINE);
        if (string.startsWith(Protocol.EMPTYCONTACTS)) return new Command(CommandType.EMPTYCONTACTS);
        if (string.startsWith(Protocol.EMPTYMYCONTACTS)) return new Command(CommandType.EMPTYMYCONTACTS);
        if (string.startsWith(Protocol.CALL)) return new CallCommand(string.replace(Protocol.CALL+" ",""));
        if (string.startsWith(Protocol.DISCONNECT_FROM_USER)) return new Command(CommandType.DISCONNECT_FROM_USER);
        if (string.startsWith(Protocol.ONLINE_CONTACTS)) {
            string = string.replace(Protocol.ONLINE_CONTACTS+" ","");
            String[] tmp = string.split(" ");
            ArrayList<Boolean> onlines = new ArrayList<Boolean>();
            for (String s : tmp){
                onlines.add(Boolean.parseBoolean(s));
            }
            return new OnlineCommand(onlines);
        }

        return null;
    }

}

