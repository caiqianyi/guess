package com.caiqianyi.agent.security;

public class GlobalToken {
	
	private static ThreadLocal<String> token = new ThreadLocal<String>();
	
	public static String getCurrentToken(){
		return token.get();
	}
	
	public static void setToken(String value){
		token.set(value);
	}
	
	public static void remove(){
		token.remove();
	}
}
