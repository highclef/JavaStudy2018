package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	static final SimpleDateFormat dayTime = new SimpleDateFormat("HH:mm:ss:SSS");
	
	public static void log(String log) {
		long time = System.currentTimeMillis();
		String strDT = dayTime.format(new Date(time)); 
		StackTraceElement[] stacks = new Throwable().getStackTrace();
//		StackTraceElement beforeStack = stacks[ 1 ];
		System.out.println("[" + strDT + "][" + stacks[1] + "] : " + log);
	}
}
