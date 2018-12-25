import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
	static private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
//	static private final String DB_URL = "jdbc:mysql://35.241.181.46/kessen";
	static private final String DB_URL = "jdbc:mysql://localhost/kessen";
	static private final String SERVERTIMEZONE = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static private final String AUTORECONNECTION = "&autoReconnect=true";
	static private final String USERNAME = "test";
	static private final String PASSWORD = "ehrdlftmxjel";
	

	protected static Connection con;
	protected Statement st;
	protected ResultSet rs;


	public static Connection getCon() {
		return con;
	}

	public static void setCon(Connection con) {
		DBConnection.con = con;
	}

	
	public Statement getSt() {
		return st;
	}

	public void setSt(Statement st) {
		this.st = st;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	
	public DBConnection() {
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL + SERVERTIMEZONE + AUTORECONNECTION, USERNAME, PASSWORD);
			st = con.createStatement();
			
			System.out.println("Connection Successful");
		}
		catch(Exception e) {
			System.out.println("Database Connection Error: " + e.getMessage());
		}
	}
}
