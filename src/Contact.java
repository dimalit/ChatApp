
public class Contact {
    private boolean isFav,isOnline;
    private String nick;


    public Contact(String nick){
        this.nick=nick;
        isFav=false;
        isOnline=false;
    }

    public boolean isFav() {
        return isFav;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }


    public void changeFav(){
        if (isFav){
            isFav=false;
        }
        else {
            isFav=true;
        }
    }

    public boolean equals(Object object){
        Contact tmp = (Contact) object;
        if (nick.equals(tmp.nick)) return true;
        else return false;
    }

    @Override
    public int hashCode() {
        return nick.hashCode();
    }

    public void setFav(Boolean b){
        isFav=b;
    }

}
