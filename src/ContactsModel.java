import java.util.ArrayList;
import java.util.List;



public class ContactsModel {
	private String nick;
	private boolean isOnline;
	ContactsModel(String nick,boolean online)
	{this.nick=nick;
	this.isOnline=online;
	}
	public String getNick()
	{
		return nick;
	}
	public boolean isOnline()
	{
		return isOnline;
	}
	public String toString()
	{
	if (isOnline())
		return getNick()+" online";
	else
		return getNick();
	}
}
