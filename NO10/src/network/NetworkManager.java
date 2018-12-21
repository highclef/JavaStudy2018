package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import application.ListsetController;
import javafx.application.Platform;
import model.MemberModel;
import util.Logger;

public class NetworkManager {
	private final String serverIp = "35.241.181.46";
	private final int serverPort = 7070;
	Socket socket;
	private LinkedList<ByteBuffer> sendBuffers;
	private byte readBuffers[];
	
	private ListsetController listSetController;
	
	private static NetworkManager instance = null;
	
	public static NetworkManager getInstance() {
		if (instance == null) {
			instance = new NetworkManager();
		}
		return instance;
	}
	public NetworkManager() {
		sendBuffers = new LinkedList<ByteBuffer>();
//		readBuffers = new LinkedList<ByteBuffer>();
	}
	
	public void setListSetController(ListsetController c) {
		listSetController = c;
	}
	public boolean connect() {
		try {
			socket = new Socket(serverIp, serverPort);
			System.out.println("Connected Sever. SERVER IP :" + serverIp);
			Thread sender = new Thread(new SocketSender(socket));
			Thread receiver = new Thread(new SocketReceiver(socket));
			sender.start();
			receiver.start();
			
			return true;
		} catch (ConnectException ce) {
			ce.printStackTrace();
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isConnected() {
		if (socket == null) {
			return false;
		}
		return !socket.isClosed() && socket.isConnected();
	}
	public void send(ByteBuffer bb) {
		sendBuffers.add(bb);
	}
	
	public void addReadData(byte[] b) {
		readBuffers = new byte[b.length];
	}
	public void read(byte[] b) {
		int dataSize = b.length;
		int readSize = 0;
		
		while ((dataSize - readSize) > NetworkData.getNeedDataSize()) {
			ByteBuffer bb = ByteBuffer.allocate(dataSize);
			bb.put(b, readSize, dataSize-readSize);
			NetworkData d = new NetworkData(bb);
			d.unPack();
			d.printData();
			messageHandler(d);
			Logger.log("length : " + d.getDataSize());
			readSize += d.getDataSize();
//			dataSize -= readSize;
			Logger.log("read size : " + readSize);
		}
		Logger.log("read finished");
	}
	public void messageHandler(NetworkData d) {		
		Logger.log("read message id : " + d.getMessageID());
		if (d.getMessageID() == MessageIDs.ADDMEMBERLIST_RES) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					MemberModel mm = new MemberModel();
					mm = d.dataFromJson(mm);
					listSetController.addMember(mm);
				}
			});
		}	
	}
	
	public LinkedList<ByteBuffer> getSendBuffers() {
		return sendBuffers;
	}
	public class SocketSender extends Thread {
		Socket socket;
		DataOutputStream out;
		String name;

		SocketSender(Socket socket) {
			this.socket = socket;
			try {
				out = new DataOutputStream(socket.getOutputStream());
			} catch (Exception e) {
			}
		}

		public void run() {
			try {
				while (out != null) {
//					out.writeUTF("[" + name + "]" + scanner.nextLine());
					if (isConnected() == false) {
						Logger.log("disconnected");
						return;
					}
					
					if (sendBuffers.isEmpty()) {
						
					} else {
						byte b[] = new byte[1024];
						b = sendBuffers.poll().array();
						out.write(b);
						out.flush();
						Logger.log("write data");
					}
					try {
					Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
			}
		}
	}

	public class SocketReceiver extends Thread {
		Socket socket;
		DataInputStream in;

		SocketReceiver(Socket socket) {
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());

			} catch (IOException e) {
			}
		}

		public void run() {
			while (in != null) {
				try {
//					System.out.println(in.readUTF());
					if (isConnected() == false) {
						Logger.log("disconnected");
						return;
					}
					byte readByte[] = new byte[1024];
					int result = in.read(readByte);
					if (result == -1) {
						Logger.log("socket error, scoket close");
						socket.close();
						return;
					}
					Logger.log("result : " + result);
					byte b[] = new byte[result];
					System.arraycopy(readByte, 0, b, 0, result);
					read(b);
					Logger.log("read data : " + b.length);
				} catch (IOException e) {

				}
			}
		}
	}
}
