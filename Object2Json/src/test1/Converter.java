package test1;

import java.util.ArrayList;

import com.google.gson.Gson;

public class Converter {

	public static void main(String[] args) {
		Member member = Member.getMember();
		
		System.out.println("Parsing JSON Into Java Objects\n");
		String jsonString = new Gson().toJson(member);
        System.out.println(jsonString);
        
        System.out.println("\nGenerating JSON From Java Objects\n");
        Member convertedJson = new Gson().fromJson(jsonString, Member.class);
        printMemberObject(convertedJson);
	}
	
	private static void printMemberObject(Member member) {
    	ArrayList<Member.MemberInfo> memberInfoList = member.getMembers();
        for (Member.MemberInfo memberInfo : memberInfoList) {
            System.out.println(memberInfo);
        }
    }

}
