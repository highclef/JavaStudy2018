package database;

import java.util.ArrayList;
import java.util.Scanner;

public class Query extends Member {
	
	static String SQL;
	
	public static class QueryInfo {
		Scanner input = new Scanner(System.in);
		
		String Headers;
		String Values;
		String tableName;

		public String getHeaders() {
			return Headers;
		}

		public void setHeaders(String headers) {
			Headers = headers;
		}

		public String getValues() {
			return Values;
		}

		public void setValues(String values) {
			Values = values;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
		}
		
		public String getTableName() throws Exception {
			try {
				System.out.println("Enter table name: ");
				this.tableName = input.next();
				
				tableExistence(tableName);
			} 
			catch (Exception e) {
				System.out.println("Table Name Error: " + e.getMessage());
			}
			return tableName;
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
	}
	
	public void select (String objectData, String targetData, String sourceData) {
		try {
			Query.QueryInfo queryInfo = new QueryInfo();
			String tableName = queryInfo.getTableName();
			
			System.out.println("Search Process\n");
			
			SQL = "SELECT " + objectData + " FROM " + tableName + " WHERE " + targetData + " = '" + sourceData + "'";
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
		Member member = Member.getMemberDummy();
		
		try {
			Query.QueryInfo queryInfo = new QueryInfo();
			String tableName = queryInfo.getTableName();
			System.out.println("Insert Process");
			
			
			queryInfo.setHeaders("username, password, name, age, gender, email");
			
			ArrayList<Member.MemberInfo> memberInfoList = member.getMembers();
			
			for (MemberInfo memberInfo : memberInfoList) {
				queryInfo.setValues(memberInfo.DBValue());
				
				System.out.print("Saving Data: " + queryInfo.getValues());
				
				SQL = "INSERT INTO " + tableName + " (" + queryInfo.getHeaders() + ") VALUES (" + queryInfo.getValues() + ")";
	            System.out.println(memberInfo);
	            
	            int count = st.executeUpdate(SQL);
				
				if( count == 0 ){
				System.out.println("Data Insert Failure");
				}
				else{
					System.out.println("Data Insert Success\n");
				}
	        }
		}
		catch(Exception e) {
			System.out.println("Database Insert Error: " + e.getMessage());
		}
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}
	
}
