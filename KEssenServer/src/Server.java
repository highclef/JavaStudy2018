import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import model.PostingModel;
import network.MessageIDs;
import network.NetworkData;
import util.Logger;

public class Server {

	HashMap clients;
	DBConnection dbConnection = new DBConnection();
	
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
					count = dbConnection.getSt().executeUpdate(SQL);
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
//				PostingModel data = new PostingModel();
//				data = nd.dataFromJson(data);
				
//				Method 1
//				
//				String SQL = "SELECT id FROM kessen.postingmodel";
//				dbConnection.getRs(st.executeQuery(SQL));
//				
//				int rowCount = rs.getInt(1);
				
				
//				Method 2
//				dbConnection.getSt(con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE));
//				dbConnection.getRs(st.executeQuery("SELECT COL_01, COL_02 FROM kessen.postingmodel"));
//				dbConnection.getRs().last();
//				
//				int rowCount = dbConnection.getRs().getRow();
				
//				Method 3
				String SQL = "SELECT * FROM kessen.postingmodel";
				try {
					dbConnection.setRs(dbConnection.getSt().executeQuery(SQL));
				
					dbConnection.getRs().last();
					int rowCount = dbConnection.getRs().getRow();
	
					System.out.println("Row Count: " + rowCount);
					
					SQL = "SELECT * FROM kessen.postingmodel";
					dbConnection.setRs(dbConnection.getSt().executeQuery(SQL));
					
					while(dbConnection.getRs().next()) {
						PostingModel data = new PostingModel();
						int id = dbConnection.getRs().getInt("id");
						data.setId(id);
						Logger.log("Loading ID : " + data.getId());
						
						String username = dbConnection.getRs().getString("username");
						data.setUsername(username);
						Logger.log("Loading Username : " + data.getUsername());
						
						String msg = dbConnection.getRs().getString("msg");
						data.setMsg(msg);
						Logger.log("Loading Msg : " + data.getMsg());
						
						NetworkData nData = new NetworkData(MessageIDs.ADDPOSTRINGDATA_RES, data);
						nData.pack();
						nData.toString();
						send(nData.getByteBuffer());
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
//				for (Iterator i=1;i<count;i++) {
//					SQL = "SELECT id FROM kessen.postingmodel WHERE id = " + i;
//					rs = st.executeQuery(SQL);
//					Int id = rs.getInt(1);
//					data.setId(id);
//					Logger.log("Loading ID : " + data.getId());
//					
//					SQL = "SELECT username FROM kessen.postingmodel WHERE id = " + i;
//					rs = st.executeQuery(SQL);
//					String username = rs.getString(1);
//					data.setUsername(username);
//					Logger.log("Loading Username : " + data.getUsername());
//					
//					SQL = "SELECT msg FROM kessen.postingmodel WHERE id = " + i;
//					rs = st.executeQuery(SQL);
//					String msg = rs.getString(1);
//					data.setMsg(msg);
//					Logger.log("Loading Msg : " + data.getMsg());
//				}
				
//				NetworkData nData = new NetworkData(MessageIDs.ADDPOSTRINGDATA_RES, data);
//				nData.pack();
//				nData.toString();
//				send(nData.getByteBuffer());
			}
		}
		
		public void send(ByteBuffer bb) {
			try {
				Logger.log("");
				byte b[] = new byte[1024];
				b = bb.array();
				out.write(b);
				out.flush();
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
