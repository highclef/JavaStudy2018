import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
	
        try {
            String serverIp = "35.241.181.46";
            System.out.println("Conneting Server.... SERVER IP :" + serverIp);
            
            Socket socket = new Socket(serverIp, 7070);
           
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            DataInputStream dis = new DataInputStream(in);
            DataOutputStream dos = new DataOutputStream(out);

            System.out.println("Starting chat with Server");
           
        	
       
            while(true) {
            	Scanner scanner = new Scanner(System.in);
            	String message = scanner.nextLine();
            	dos.writeUTF(message);
            	
            	System.out.println("\nServer : " + dis.readUTF());
            	
            	if("QUIT".equals(message)) {
            		System.out.println("Disconnected");
            		break;
            	}
            }
            
        } catch (ConnectException ce) {
            ce.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 

	
	}


