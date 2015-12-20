
public class Contact {
    private boolean isFav,isOnline;
    private String nick;
    private ContactsViewModel contactsViewModel;


    public Contact(ContactsViewModel cvm,String nick){
        this.nick=nick;
        isFav=false;
        contactsViewModel=cvm;
        isOnline=false;
    }

    public void setContactsViewModel(ContactsViewModel contactsViewModel) {
        this.contactsViewModel = contactsViewModel;
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


    public void changeFav(ContactPanel contactPanel){
        if (isFav){
            contactsViewModel.removeContact(this,contactPanel);
            isFav=false;
            contactsViewModel.add(this);
        }
        else {
            contactsViewModel.removeContact(this,contactPanel);
            isFav=true;
            contactsViewModel.add(this);
        }
    }

    public boolean equals(Object object){
        Contact tmp = (Contact) object;
        if (nick.equals(tmp.nick)) return true;
        else return false;
    }

    public void remove(ContactPanel tmp){
        contactsViewModel.removeContact(this,tmp);
    }

    @Override
    public int hashCode() {
        return nick.hashCode();
    }

    public void setFav(Boolean b){
        isFav=b;
    }

}
