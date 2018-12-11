package database;

import java.util.ArrayList;

public class Member extends Main {
	public ArrayList<MemberInfo> members;
	
	public ArrayList<MemberInfo> getMembers() {
		return members;
	}
	public void setMembers(ArrayList<MemberInfo> members) {
		this.members = members;
	}
	
	public static class MemberInfo {
		public ArrayList<MemberInfo> columnData;
		
		public String userName;
		public String password;
		public String name;
		public int age;
		public String gender;
		public String email;
		
		columnData.add(userName.toString());
		
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
	}
	public static Member getMemberDummy() {
        Member member = new Member();
        
        ArrayList<Member.MemberInfo> memberInfoList = new ArrayList<MemberInfo>();
 
        Member.MemberInfo memberInfo = new MemberInfo();
        
        memberInfo.setUserName("jaehee");
        memberInfo.setPassword("1234");
        memberInfo.setName("kim");
        memberInfo.setAge(24);
        memberInfo.setGender("female");
        memberInfo.setEmail("jaehee@kessen.de");
        memberInfoList.add(memberInfo);
        
        memberInfo = new MemberInfo();
        memberInfo.setUserName("jaein");
        memberInfo.setPassword("1234");
        memberInfo.setName("kim");
        memberInfo.setAge(21);
        memberInfo.setGender("female");
        memberInfo.setEmail("jaein@kessen.de");
        memberInfoList.add(memberInfo); 
        
//      memberInfo = new MemberInfo();
//      memberInfoList.add(memberInfo);
        
        member.setMembers(memberInfoList);
 
        return member;
    }
}
