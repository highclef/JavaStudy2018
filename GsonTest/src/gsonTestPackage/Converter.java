package gsonTestPackage;

import java.util.ArrayList;

import com.google.gson.Gson;

import gsonTestPackage.Member.MemberInfo;

public class Converter {

	public static void main(String[] args) {
		Member member = Member.getMemberDummy();
		
		System.out.println("Generating JSON From Java Objects\n");
		String generatedJson = new Gson().toJson(member);
        System.out.println(generatedJson);
        
        System.out.println("\nParsing JSON Into Java Objects\n");
        Gson gson = new Gson();
        Member parsedJson = gson.fromJson(generatedJson, Member.class);
        printMemberObject(parsedJson);
        
//        Member convertedJson = new Gson().fromJson(jsonString, Member.class);
//        printMemberObject(convertedJson);
	}
	
	private static void printMemberObject(Member member) {
    	ArrayList<Member.MemberInfo> memberInfoList = member.getMembers();
    	
//    	System.out.println(memberInfoList);
    	
        for (MemberInfo memberInfo : memberInfoList) {
            System.out.println(memberInfo);
        }
    }

}
