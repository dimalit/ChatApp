
public class NickCommand extends Command {

	String NickName;
	boolean busy;
	
	
	NickCommand(CommandType type){
		super(CommandType.NICK);
	}
	
	
	NickCommand(String s){
		super(CommandType.NICK);
		if(s.contains("busy")){
			busy=true;
		}
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
