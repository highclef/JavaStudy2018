import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import model.PostingModel;
import network.MessageIDs;
import network.NetworkData;
import util.Logger;

public class Server extends DBConnection {

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
			System.out.println(getTime() + "waiting connection..");
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

//
//	void sendToAll(String msg) {
//		Iterator it = clients.keySet().iterator();
//		while (it.hasNext()) {
//			try {
//				DataOutputStream out = (DataOutputStream) clients.get(it.next());
//				out.writeUTF(msg);
//			} catch (IOException e) {
//
//			}
//		}
//	}
	
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

		public void messageHandler(byte[] b) {
			Logger.log("byte length : " + b.length);
			ByteBuffer bb = ByteBuffer.allocate(b.length);
			bb.put(b);
			Logger.log("capacity : " + bb.capacity());
			NetworkData nd = new NetworkData(bb);
			nd.unPack();
			nd.printData();

			if (nd.getMessageID() == MessageIDs.ADDPOSTINGDATA_REQ) {
				PostingModel data = new PostingModel();
				data = nd.dataFromJson(data);
				Logger.log("posting model msg : " + data.getMsg());

				String SQL = "INSERT INTO `kessen`.`postingmodel` (`username`, `msg`) " + "VALUES ('" + data.getUsername()
						+ "', '" + data.getMsg() + "')";

				int count = 0;
				try {
					count = st.executeUpdate(SQL);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (count == 0) {
					System.out.println("Data Insert Failure");
				} else {
					System.out.println("Data Insert Success\n");
					NetworkData nData = new NetworkData(MessageIDs.ADDPOSTRINGDATA_RES, data);
					nData.pack();
					nData.toString();
					send(nData.getByteBuffer());
				}
			} else if (nd.getMessageID() == MessageIDs.POSTINGDATALIST_REQ) {
				PostingModel data = new PostingModel();
				data = nd.dataFromJson(data);
				
				// TODO: Message Log Return
				
				Int id;
				String username;
				String msg;
				
				String SQL = "SELECT * FROM kessen.postingmodel";
				rs = st.executeQuery(SQL);
				
				int count = rs.getInt(1);
				System.out.println("Row Count: " + count);
				
				data.setId(id);
				Logger.log("Loading ID : " + data.getId());

				data.setUsername(username);
				Logger.log("Loading Username : " + data.getUsername());
				
				data.setMsg(msg);
				Logger.log("Loading Msg : " + data.getMsg());
				
				NetworkData nData = new NetworkData(MessageIDs.ADDPOSTRINGDATA_RES, data);
				nData.pack();
				nData.toString();
				send(nData.getByteBuffer());
			}
		}
		
		public void send(ByteBuffer bb) {
			try {
				out.write(bb.array());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void run() {
			String name = "";
			try {
				clients.put(1, out);
				System.out.println("Connected : " + clients.size() + "users");
				while (in != null) {
//					sendToAll(in.readUTF());
					byte b[] = new byte[1024];
					in.read(b);
					messageHandler(b);
				}
			} catch (IOException e) {
			} finally {
//				sendToAll("#" + name + "has disconnected");
				clients.remove(name);
				System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "]" + "has disconnected");
				System.out.println("There are currently " + clients.size() + "users");
			}
		}
	}
}
