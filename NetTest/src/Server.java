import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class Server {

	HashMap clients;

	Server() {
		clients = new HashMap();
		Collections.synchronizedMap(clients);
	}

	public void start() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(7070);
			System.out.println(getTime() + "wating connection..");
			while (true) {
				socket = serverSocket.accept();
				System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "]");
				ServerReceiver thread = new ServerReceiver(socket);
				thread.start();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void sendToAll(String msg) {
		Iterator it = clients.keySet().iterator();
		while (it.hasNext()) {
			try {
				DataOutputStream out = (DataOutputStream) clients.get(it.next());
				out.writeUTF(msg);
			} catch (IOException e) {

			}
		}
	}
	static String getTime() {
		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss");
		return f.format(new Date());
	}	

	public static void main(String[] args) {
		new Server().start();
	}

	class ServerReceiver extends Thread {
		Socket socket;
		DataInputStream in;
		DataOutputStream out;

		ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
			}
		}

		public void run() {
			String name = "";
			try {
				name = in.readUTF();
				sendToAll("#" + name + "has joined the room.");
				clients.put(name, out);
				System.out.println("Connected : " + clients.size() + "users");
				while (in != null) {
					sendToAll(in.readUTF());
				}
			} catch (IOException e) {
			} finally {
				sendToAll("#" + name + "has disconnected");
				clients.remove(name);
				System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "]" + "has disconnected");
				System.out.println("There are currently " + clients.size() + "users");
			}
		}
			
	}
}
