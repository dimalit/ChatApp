import java.io.*;
import java.net.*;

public class Connection {
  
    private Socket socket;

    public Connection(Socket socet) {
        this.socket = socet;
    }

    public void sendNickHello(String nick) throws IOException {
        socket.getOutputStream().write((Constants.ChatApp_VERSION + " user " + nick + "\n").getBytes(Constants.CHARSET_NAME));
        socket.getOutputStream().flush();
    }

    public void sendNickBusy(String nick) throws IOException {
        socket.getOutputStream().write((Constants.ChatApp_VERSION + " user " + nick + " busy\n").getBytes(Constants.CHARSET_NAME));
        socket.getOutputStream().flush();
    }

    public void accept() throws IOException {
        socket.getOutputStream().write("Accepted\n".getBytes(Constants.CHARSET_NAME));
        socket.getOutputStream().flush();
    }

    public void reject() throws IOException {
        socket.getOutputStream().write("Rejected\n".getBytes(Constants.CHARSET_NAME));
        socket.getOutputStream().flush();
    }

    public void sendMessage(String message) throws IOException {
        socket.getOutputStream().write("Message\n".getBytes(Constants.CHARSET_NAME));
        socket.getOutputStream().write((message + "\n").getBytes(Constants.CHARSET_NAME));
        socket.getOutputStream().flush();
    }

    public void disconnect() throws IOException {
        socket.getOutputStream().write("Disconnect\n".getBytes(Constants.CHARSET_NAME));
        socket.getOutputStream().flush();
    }
}
