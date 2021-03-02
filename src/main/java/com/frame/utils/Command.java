package com.frame.utils;

/**
 * @author
 * 判断电脑是windows还是mac的
 *
 */
public class Command {
	private static String osName = System.getProperty("os.name");


	public static Boolean isMac(){
		if(osName.toLowerCase().contains("mac")){
			return true;
		}
		return false;
	}
	public static Boolean isWin(){
		if(osName.toLowerCase().contains("win")){
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println("osName："+osName);
	}

}
