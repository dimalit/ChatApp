
public class NickCommand extends Command {

	String NickName;
	boolean busy;
	
	
	NickCommand(CommandType type, String NickName){
		super(type);
		this.NickName=NickName;
	}
	
	
	NickCommand(String s){
		super(CommandType.NICK);
		if(s.contains("busy")){
			busy=true;
			s=s.replace(" busy", "");
		}
		s=s.replace("ChatApp 2015", "");
		NickName=s;
	}
	
	
	void setNickName(String NickName){
		this.NickName=NickName;
	}
	
	
	String getNickName(){
		return NickName;
	}
	
	
	boolean isBusy(){
		return busy;
	}

}
