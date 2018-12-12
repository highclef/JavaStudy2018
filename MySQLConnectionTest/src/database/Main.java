package database;

public class Main extends DBConnection {

	public static void main(String[] args) throws Exception {
		Query query = new Query();
				
		query.getTableName();
		
		// query.select();
		
		query.insert();
		
		
		con.close();
	}

}
