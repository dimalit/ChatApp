public class Protocol {
    public static final int PORT_NUMBER =28411;
    public static final String GREETING = "ChatApp 2015 user ";
    public static final String ACCEPTED = "Accepted";
    public static final String REJECTED = "Rejected";
    public static final String MESSAGE = "Message";
    public static final String DISCONNECT = "Disconnect";
    public static final String DISCONNECT_FROM_USER = "Disconnect from ";
    public static final String SERVER_IP = "178.151.143.46";
    public static final String HELLO_SERVER = "Hello server";
    public static final String HELLO_CLIENT = "Hello ChatApp2015";
    public static final String CALL = "Call";
    public static final String LOGIN = "Login";
    public static final String SIGNUP = "Signup";
    public static final String LOGOUT = "Logout";
    public static final String CONTACTS = "Contacts ";
    public static final String GET_CONTACTS = "Get Contacts";


    public static String encode(String string){
        return string.replace("\n",":&:");
    }

    public static boolean isNickValid(String nick){
        if (nick.matches(".*[a-zA-Z0-9_\\-].*")) {
            return true;
        }
        return false;
    }

    public static boolean isMessageValid(String message){
        if (message.matches(".*\\S.*")) {
            return true;
        }
        return false;
    }

    public static String decode(String string){
        return string.replace(":&:","\n");
    }
}
