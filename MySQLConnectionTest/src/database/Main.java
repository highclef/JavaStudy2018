package database;

public class Main extends DBConnection {

	public static void main(String[] args) throws Exception {
		Query query = new Query();
		
		// query.getTableName();
		
		// query.select();
		
		// query.insert();
		
		System.out.println("Connection Close");
		con.close();
	}

}
