import java.util.Observable;


public class CommandListenerThread extends Observable implements Runnable{

	Connection c;
	volatile boolean b;
	volatile Command com;
	
	public CommandListenerThread(Connection c) {
		this.c=c;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.addObserver(Form.obj);
		while(b!=true){
			synchronized(this){
				this.com=c.recieve();
				 setChanged();
				 this.notifyObservers(com);
				 
			}
		}
	}

	public void start() {
		this.b=false;
		Thread t=new Thread(this);
		t.start();
	}
	
	
	public void stop(){
		b=true;
	}
	
	
	Connection getConnection(){
		return this.c;
	}

}
