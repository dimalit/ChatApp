 import java.net.InetSocketAddress;

 
 public class CallListener {
     private String localNick;
     private InetSocketAddress localAddress;
     private boolean isBusy;
    private String remoteNick;
 
     public CallListener(){
         this("Untitled");
     }
 
     public CallListener(String localNick){
         this.localNick = localNick;
     }
 
 
   
    
}
