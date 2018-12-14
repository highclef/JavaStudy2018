package network;

import java.nio.ByteBuffer;
import java.util.zip.CRC32;

import com.google.gson.Gson;

import util.Logger;

public class NetworkData implements Cloneable{
	private static final String HEADERNAME = "KESSEN";
	private String header;
	private ByteBuffer dataBuffer;
	private int messageID;
	private String data;
	private int size;
	private long crc32;
	
	public NetworkData() {
		// TODO Auto-generated constructor stub
	}
	public NetworkData(ByteBuffer b) {
		dataBuffer = b;
	}
	public NetworkData(int messageID) {
		this(messageID, "");
	}
	public <T> NetworkData(int messageID, T data) {
		setMessageID(messageID);
		setData(data);
		setSize();
	}
	
	public void pack() {
		setbyteData();
		Logger.log(Integer.toString(dataBuffer.capacity()));
	}
	public void unPack() {
		Logger.log(Integer.toString(dataBuffer.capacity()));
		// get header
		byte[] b = new byte[HEADERNAME.length()];
		dataBuffer.flip();
		dataBuffer.get(b, 0, HEADERNAME.length());
		header = new String(b);
		//get messageID
		messageID = dataBuffer.getInt();
		//get size
		size = dataBuffer.getInt();
		//get data
		byte[] bb = new byte[size];
		dataBuffer.get(bb, 0, size);
		data = new String(bb);
		//get crc32
		crc32 = dataBuffer.getLong();
	}
	public void setMessageID(int id) {
		messageID = id;
	}
	public void setData(String data) {
		this.data = data;
	}
	public <T> void setData(T data) {
		Gson gson = new Gson();
		String jsonData = gson.toJson(data);
		setData(jsonData);
		Logger.log(jsonData);
	}
	public void setSize() {
		size = data.length();
		setbyteData();
	}
	
	public void setbyteData() {
		int byteSize = (HEADERNAME.length()) + 
				(Integer.BYTES) + 
				(data.length()) +
				(Integer.BYTES) +
				(Long.BYTES);
		Logger.log("header length : " + HEADERNAME.length());
		Logger.log("data length : " + data.length());
		Logger.log("Integer.Bytes : " + Integer.BYTES);
		Logger.log("byte size : " + byteSize);
		byte []byteData = new byte[byteSize];
		dataBuffer = ByteBuffer.wrap(byteData);
		
		//header
		dataBuffer.put(HEADERNAME.getBytes());
		//messageID
		dataBuffer.putInt(getMessageID());
		//data size
		dataBuffer.putInt(getSize());
		//data
		dataBuffer.put(data.getBytes());
		//crc32
		dataBuffer.putLong(getCrc32Value());
	}
	
	public void setHeader(String h) {
		header = h;
	}
	public long getCrc32Value() {
		CRC32 c = new CRC32();
		byte []byteArray = dataBuffer.array();
		c.update(byteArray);
		crc32 = c.getValue();
		return crc32;
	}
	
	public int getMessageID() {
		return messageID;
	}
	public int getSize() {
		return size;
	}
	public String getHeader() {
		return header;
	}
	
	public ByteBuffer getByteBuffer() {
		return dataBuffer;
	}
	public <T> T dataFromJson(T d) {
		Gson gson = new Gson();
		T data = (T)gson.fromJson(this.data, d.getClass());
		return (T)data;
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public void printData() {
		Logger.log("header : " + header);
		Logger.log("messageID : " + Integer.toString(messageID));
		Logger.log("size : " + Integer.toString(size));
		Logger.log("data : " + data);
		Logger.log("crc32 : " + Long.toString(crc32));
		
	}
}
