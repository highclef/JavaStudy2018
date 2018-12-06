import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

	public static void main(String[] args) {
		try(ServerSocket server = new ServerSocket()) {
			InetSocketAddress ipep = new InetSocketAddress(7070);
			server.bind(ipep);
			
			System.out.println("Port number : " + ipep.getPort() + " - Initialize complete");
			
			Socket client = server.accept();
			System.out.println("Connection");
			
			try(OutputStream sender = client.getOutputStream();
				InputStream receiver = client.getInputStream();) {
				String message = "hello world";
				byte[] data = message.getBytes();
				sender.write(data, 0, data.length);
				
				data = new byte[2];
				receiver.read(data, 0, data.length);
				
				message = new String(data);
				String out = String.format("receive - %s", message);
				System.out.println(out);
								
			}
		} catch(Throwable e) {
			e.printStackTrace();
		}

	}

}
