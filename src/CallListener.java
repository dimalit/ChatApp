import java.io.IOException;
import java.net.ServerSocket;


public class CallListener {
	
	
	ServerSocket ss;
	String NickName;
	boolean isBusy;
	String ip;
	Command c;
	NickCommand nc;
	
	
	CallListener(String NickName, boolean isBusy) throws IOException{
		ss=new ServerSocket(Connection.port);

		this.NickName=NickName;
		this.isBusy=isBusy;
	}
	
	
	Connection getConnection() throws IOException{
		Connection c=new Connection(ss.accept());
		if(isBusy){
			
			c.userIsBusy(NickName);
			c.disconnect();
			return null;
			
		}else{
			c.chatApp2015(NickName);
			this.c=c.recieve();
			
			if(this.c.type==CommandType.NICK){
				nc=(NickCommand) this.c;
				return c;
			}else{
				return null;
			}
			/**/
		}
	}
	
	
	void setBusy(boolean isBusy){
		this.isBusy=isBusy;
	}
	
	
	String getNickName(){
		return NickName;
	}
	



}
