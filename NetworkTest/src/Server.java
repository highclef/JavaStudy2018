import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Server {

	public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
     
            serverSocket = new ServerSocket(7070);
            System.out.println(getTime() + "READY");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                System.out.println(getTime() + "Loading..");
            
                Socket socket = serverSocket.accept();
                System.out.println("Port: " + socket.getPort());
                System.out.println("LocalPort: " + socket.getLocalPort());
                System.out.println(getTime() + socket.getInetAddress());
               
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                DataInputStream dis = new DataInputStream(in);
                DataOutputStream dos = new DataOutputStream(out);
                
                System.out.println("Connected Client");
                
            	
                while(true) {
                	String msg = dis.readUTF();
                	System.out.println("Client : " + msg);
                	dos.writeUTF(msg);
                }
              
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    static String getTime() {
        SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
        return f.format(new Date());
    }
}


