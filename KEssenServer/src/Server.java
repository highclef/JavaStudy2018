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

import model.LoginModel;
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
			Logger.log("Server Ver. 0.3");
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
			ByteBuffer bb = ByteBuffer.allocate(b.length);
			bb.put(b);
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
					System.out.println("Data Insert Success");
					NetworkData nData = new NetworkData(MessageIDs.ADDPOSTRINGDATA_RES, data);
					nData.pack();
					nData.toString();
					send(nData.getByteBuffer());
				}
				
			} else if (nd.getMessageID() == MessageIDs.POSTINGDATALIST_REQ) {
				String SQL = "SELECT * FROM kessen.postingmodel";
				try {
					dbConnection.setRs(dbConnection.getSt().executeQuery(SQL));
				
//					dbConnection.getRs().last();
					int rowCount = dbConnection.getRs().getRow();
	
					System.out.println("Row Count: " + rowCount);
					
					while(dbConnection.getRs().next()) {
						PostingModel data = new PostingModel();
						Logger.log("data read from DB");
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
				
			} else if (nd.getMessageID() == MessageIDs.DELPOSTINGDATA_REQ) {
//				Logger.log("DELPOSTINGDATA_REQ");
				int id = 0;
				id = nd.dataFromJson(id);
				Logger.log("Deleting ID : " + id);
				
				String SQL = "DELETE FROM kessen.postingmodel WHERE (id = '" + id + "')";
				
				int count = 0;
				try {
					count = dbConnection.getSt().executeUpdate(SQL);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (count == 0) {
					Logger.log("Data Delete Failure");
				} else {
					Logger.log("Data Delete Success");
					NetworkData nData = new NetworkData(MessageIDs.DELPOSTINGDATA_RES, id);
					nData.pack();
					nData.toString();
					send(nData.getByteBuffer());
				}
				
			} else if (nd.getMessageID() == MessageIDs.UPDATEPOSTINGDATA_REQ) {
//				Logger.log("UPDATEPOSTINGDATA_REQ");
				PostingModel data = new PostingModel();
				data = nd.dataFromJson(data);
				Logger.log("Object ID : " + data.getId());
				Logger.log("Updating Msg to: " + data.getMsg());
				
				String SQL = "UPDATE `kessen`.`postingmodel` SET `msg` = '" + data.getMsg() + "' WHERE (`id` = '" + data.getId() + "')";
				
				int count = 0;
				try {
					count = dbConnection.getSt().executeUpdate(SQL);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (count == 0) {
					Logger.log("Data Update Failure");
				} else {
					Logger.log("Data Update Success");
					NetworkData nData = new NetworkData(MessageIDs.UPDATEPOSTINGDATA_RES, data);
					nData.pack();
					nData.toString();
					send(nData.getByteBuffer());
				}
			} else if (nd.getMessageID() == MessageIDs.LOGIN_REQ) {
//			} else if (nd.getMessageID() == 5) {
				// TODO check id, password from DB
				// has id, password => setLogined true from LoginModel
				// has not id, password => setLogined false from LoginModel
				// send to client with LOGIN_RES with LoginModel
				LoginModel data = new LoginModel();
				data = nd.dataFromJson(data);
				
				Logger.log("Login Session");
				Logger.log("User ID: " + data.getUserId());
				Logger.log("Password: " + data.getPassword());
				
				String SQL = "SELECT * FROM kessen.memberinfo WHERE username = '" + data.getUserId() + "' and password = '" + data.getPassword() + "'";
				
				try {
					dbConnection.setRs(dbConnection.getSt().executeQuery(SQL));
					
//					int loginCount = 0;
//					loginCount = dbConnection.getRs().getRow();
					
					if(dbConnection.getRs().next()) {
						
						Logger.log("Login Success");
						Logger.log("SQL username: " + dbConnection.getRs().getString("username"));
						Logger.log("SQL password: " + dbConnection.getRs().getString("password"));
						data.setLogined(true);
						
					}
					else {
						
						Logger.log("ID or Password Error");
						data.setLogined(false);
						
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
//				finally {
//					try {
//						   dbConnection.getRs().close();
//					   } catch (Exception e) {
//						   
//					   }
//					   try {
//						   dbConnection.getSt().close();
//					   } catch (Exception e) {
//						   
//					   }
//					   try {
//						   DBConnection.getCon().close();
//					   } catch (Exception e) {
//						   
//					   }
//				  }
		
				
				NetworkData nData = new NetworkData(MessageIDs.LOGIN_RES, data);
				nData.pack();
				nData.toString();
				send(nData.getByteBuffer());
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
