import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;


public class CallListenerThread extends Observable implements Runnable  {

	
	CallListener cl;
	Connection c;
	String NickName;
	volatile boolean b;
	ServerSocket ss;
	Socket s;
	
	
	@Override
	public void run() {
		try{
			ss=new ServerSocket(Connection.port);

		while(true){
			
				s=ss.accept();
				Connection c=new Connection(s);

			if(c!=null){
				CommandListenerThread clt=new CommandListenerThread(c);
				clt.addObserver(LabelFrame.obj);
				clt.start();
				c.chatApp2015(NickName);
				}
			
		}	
		
		}catch (IOException e) {
            e.printStackTrace();}
        
	}
		
	
	
	
	
	void start(){
		b=false;
		Thread t=new Thread(this);
		t.start();
	}
	
	
	void stop(){
		b=true;
	}
	
	
	Connection getConnection(){
		return this.c;
	}
	
	
}
