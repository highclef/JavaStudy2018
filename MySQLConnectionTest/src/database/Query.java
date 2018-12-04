package database;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Query extends DBConnection {
	InputStream in = System.in;
	InputStreamReader reader = new InputStreamReader(in);
	BufferedReader br = new BufferedReader(reader);
	
	String tableName;
	String SQL;
	
	public void getTableName() throws Exception {
		try {
			System.out.println("Enter table name: ");
			this.tableName = br.readLine();
			
			tableExistence(tableName);
		} 
		catch (Exception e) {
			System.out.println("Table Name Error: " + e.getMessage());
		}
	}
	
	public void tableExistence(String tableName) {
		try {
			SQL = "SELECT * FROM " + tableName;
			rs = st.executeQuery(SQL);
			System.out.println("Table Existence Confirmed");
		}
		catch(Exception e) {
			System.out.println("Table Existence Error: " + e.getMessage());
		}
	}
	
	public void search () {
		try {
			System.out.println("Search Process");
		
			System.out.println("SELECT: ");
			String objectData = br.readLine();
			
			System.out.println("FROM " + tableName + " WHERE: ");
			String columnData = br.readLine();
			
			System.out.println("=");
			String sourceData = br.readLine();
			
			SQL = "SELECT " + objectData + " FROM " + tableName + " WHERE " + columnData + " = '" + sourceData + "'";
			rs = st.executeQuery(SQL);
			if(rs.next()) {
				System.out.println(rs.getString(1));
			}
			System.out.println("Name Search Success");			
			}
		catch(Exception e) {
			System.out.println("Database Search Error: " + e.getMessage());
		}
	}
}
