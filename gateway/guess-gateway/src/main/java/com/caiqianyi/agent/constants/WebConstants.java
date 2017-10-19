package com.caiqianyi.agent.constants;

import java.util.UUID;


public class WebConstants {
	
	public static final String SESSION_USER = "login.current.agent";//登录用户信息
	
	public static final String SYS_VERIFYCODE = "verifycode";//验证码
	
	public static final String DES_SECURITY_KEY = "4fc704a1fff14148a23c04adbda987b5";
	
	public static final String DES_OAUTH2_OPENID_KEY = "cb3aa38639044623bba56df528b0dc54";
	public static final String DES_OAUTH2_TOKEN_KEY = "98ed4e35e7c947a7b0169c82ea31deb1";
	
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
	}
	
}
