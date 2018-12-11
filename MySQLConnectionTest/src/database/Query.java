package database;

import java.util.Scanner;


public class Query extends Member {

	Scanner input = new Scanner(System.in);
	
	String tableName;
	String SQL;
	String Headers;
	String Values;
	
	public void getTableName() throws Exception {
		try {
			System.out.println("Enter table name: ");
			this.tableName = input.next();
			
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
	
	public void select () {
		try {
			System.out.println("Search Process");
		
			System.out.println("SELECT: ");
			String objectData = input.next();
			
			System.out.println("FROM " + tableName + " WHERE: ");
			String columnData = input.next();
			
			System.out.println("=");
			String sourceData = input.next();
			
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
	public void insert() {
		Member.getMemberDummy();

		
		try {
			System.out.println("Insert Process");
			
			
			Headers = "username, password, name, age, gender, email";
			Values = "'testusername', '1234', 'test', 22, 'female', 'testemail@kessen.de'";
			
			SQL = "INSERT INTO " + tableName + " (" + Headers + ") VALUES (" + Values + ")";
			
			int count = st.executeUpdate(SQL);
			
			if( count == 0 ){
			System.out.println("Data Insert Failure");
			}
			else{
				System.out.println("Data Insert Success");
			}
		}
		catch(Exception e) {
			System.out.println("Database Insert Error: " + e.getMessage());
		}
		
	    
	}
	
}
