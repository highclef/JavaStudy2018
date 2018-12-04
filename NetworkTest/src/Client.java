import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		try(Socket client = new Socket()) {
			InetSocketAddress ipep = new InetSocketAddress("35.241.181.46", 7070);
			client.connect(ipep);
			
			try(OutputStream sender = client.getOutputStream();
				InputStream receiver = client.getInputStream();) {
				byte[] data = new byte[11];
				receiver.read(data, 0, 11);
				
				String message = new String(data);
				String out = String.format("receive - %s", message);
				System.out.println(out);
				
				message = "OK";
				data = message.getBytes();
				sender.write(data, 0, data.length);
				
			}
		} catch(Throwable e) {
			e.printStackTrace();
		}

	}

}
