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
        if (string.contains(Protocol.ACCEPTED)) return new Command(CommandType.ACCEPT);
        if (string.contains(Protocol.DISCONNECT)) return new Command(CommandType.DISCONNECT);
        if (string.contains(Protocol.REJECTED)) return new Command(CommandType.REJECT);
        if (string.contains(Protocol.GREETING)) return  new NickCommand(string);
        if (string.contains(Protocol.MESSAGE)) return new MessageCommand(Protocol.decode(in.nextLine()));
        if (string.contains(Protocol.HELLO_SERVER)) return new Command(CommandType.HELLO_SERVER);
        if (string.contains(Protocol.HELLO_CLIENT)) return new Command(CommandType.HELLO_CLIENT);


        if (string.contains(Protocol.CONTACTS)){
            string = string.replace(Protocol.CONTACTS+" ","");
            ArrayList<Contact> result = new ArrayList<Contact>();
            String[] tmp = string.split(" ");
            for (int i=0;i<tmp.length;i+=2){
                Contact contact = new Contact((ContactsViewModel)null,tmp[i]);
                if (tmp[i+1].equals("true")){
                    contact.setOnline(true);
                }else{
                    contact.setOnline(false);
                }
                result.add(contact);
            }
            return new ContactsCommand(result);
        }


        if (string.contains(Protocol.MY_CONTACTS)){
            string = string.replace(Protocol.MY_CONTACTS,"");
            ArrayList<Contact> result = new ArrayList<Contact>();
            String[] tmp = string.split(" ");
            for (int i=0;i<tmp.length;i+=3){
                Contact contact = new Contact((ContactsViewModel)null,tmp[i]);
                if (tmp[i+1].equals("true")){
                    contact.setOnline(true);
                }else{
                    contact.setOnline(false);
                }
                result.add(contact);
            }
            return new MyContactsCommand(result);
        }


        if (string.contains(Protocol.EMPTYCONTACTS)) return new Command(CommandType.EMPTYCONTACTS);
        if (string.contains(Protocol.EMPTYMYCONTACTS)) return new Command(CommandType.EMPTYMYCONTACTS);

        return null;
    }

}

