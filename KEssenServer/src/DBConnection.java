import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
	static private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static private final String DB_URL = "jdbc:mysql://localhost/kessen";
	static private final String SERVERTIMEZONE = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	static private final String USERNAME = "test";
	static private final String PASSWORD = "ehrdlftmxjel";
	
	protected static Connection con;
	protected Statement st;
	protected ResultSet rs;

	public DBConnection() {
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL + SERVERTIMEZONE, USERNAME, PASSWORD);
			st = con.createStatement();
			
			System.out.println("Connection Successful");
		}
		catch(Exception e) {
			System.out.println("Database Connection Error: " + e.getMessage());
		}
	}
}
