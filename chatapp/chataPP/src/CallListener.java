import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CallListener
{

    private ServerSocket s;
    private final int port = 9999;
    private Socket fromclient;
    
    public CallListener()
    {
      try {
            s = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(Caller.class.getName()).log(Level.SEVERE, null, ex);         
        }   
        
    }
     
    
        public void Wait()
    {
        System.out.println("Start...");
        try {
            fromclient = s.accept();
        } catch (IOException ex) {
            Logger.getLogger(Caller.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println("");  
    }   
    
    
}
