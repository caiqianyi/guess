package com.lebaoxun.commons.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

/**
 * 一些常用的工具类
 * @author xhl
 *
 */

public class CommonUtil {
	 
	/**
	 * 获得远程充值接口sign
	 * @param map
	 * @param scrit
	 * @return
	 */
	public static String getMD5Sign(TreeMap<String,Object> map,String scrit){
		String secret="";
		for (Map.Entry<String, Object> entry : map.entrySet()) {  
			secret+=entry.getKey();
			secret+=entry.getValue();
		}
		
		return MD5.md5(secret+scrit);		
	}

	/**
	 * 获得远程充值接口sign--无序
	 * @param map
	 * @param scrit
	 * @return
	 */
	public static String getMD5SignNoSeq(Map<String,Object> map,String scrit){
		String secret="";
		for (Map.Entry<String, Object> entry : map.entrySet()) {  
			secret+=entry.getKey();
			secret+=entry.getValue();
		}
		return MD5.md5(secret+scrit);		
	}
	
	/**
	 * 得到md5加密密码
	 * @param str
	 * @return
	 */
	public static String getMd5Password(String str){
		if(!"".equals(str) && str!=null){
			return MD5.md5(str);
		}
		return null;
	}
	
	/**
	 * 
	 * @Title:getVerCodeGrade
	 * @Descript:TODO 得到验证码等级 
	 * @param verCodeErrTime
	 * @return 
	 * @author Uray
	 * @CreateTime:2017年6月20日 下午3:10:16
	 */
	public static int getVerCodeGrade(int verCodeErrTime) {
		int verCodeGrade = 0;
		if (verCodeErrTime >= 3 && verCodeErrTime < 6) {
			verCodeGrade = 1;
		} else if (verCodeErrTime < 9) {
			verCodeGrade = 2;
		} else if (verCodeErrTime < 12) {
			verCodeGrade = 3;
		} else {
			verCodeGrade = 4;
		}
		return verCodeGrade;
	}
	
	/**
	 * 
	 * @Title:getOnlyCode
	 * @Descript:TODO 获取验证码值
	 * @param verCodeGrade
	 * @return 
	 * @author Uray
	 * @CreateTime:2017年6月20日 下午2:48:54
	 */
	public static Map<String, String> getOnlyCode(ByteArrayOutputStream output, int verCodeGrade) {
		String verifyCode = "";
    	Map<String, String> verCodeMap = new HashMap<String, String>();
    	switch (verCodeGrade) {
		case 0:
			//数字验证码
			verifyCode = generateVerifyCode();
			verCodeMap.put("verifyCode", verifyCode);
			break;
		case 1:
			//数字字母验证码
			verifyCode = getForgetVerifyCode(output);
			verCodeMap.put("verifyCode", verifyCode);
			break;
		case 2:
			//表达式验证码
			verCodeMap = VerifyCodeUtils.expressionVerifyCode();
			break;
		case 3:
			//中文字符验证码
			verifyCode = VerifyCodeUtils.chineseVerCodeST(4);
			verCodeMap.put("verifyCode", verifyCode);
			break;
		case 4:
			//随机验证码
			verCodeMap = getOnlyCode(output, new Random().nextInt(2) + 2);
			break;
		}
    	return verCodeMap;
	}
	
	
	/**
	 * 获得登录验证码
	 * @param output
	 * @return
	 */
    public static String getVerifyCode(ByteArrayOutputStream output, int verCodeGrade) {
        try {
        	Map<String, String> verCodeMap = getOnlyCode(output, verCodeGrade);
        	String verifyCode = verCodeMap.get("verifyCode");
        	String expression = verCodeMap.get("expression");
        	//生成图片  
        	int w = 200, h = 80;  
			VerifyCodeUtils.outputImage(w, h, output, expression == null ? verifyCode : expression);
			return verifyCode;
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return null;
	}
    
    public static String generateVerifyCode() {
		Random r = new Random();
		String s = "0123456789";
		String code = "";
		for (int i = 0; i < 4; i++) {
			code += s.charAt(r.nextInt(s.length()));
		}
		return code;
	}

	// 获取混合验证码
	public static String getBlend() {
		Random r = new Random();
		String s = "0123456789";
		String val = "";
		for (int i = 0; i < 4; i++) {
			String charOrNum = r.nextInt(s.length()) % 2 == 0 ? "char" : "num";
			if ("char".equalsIgnoreCase(charOrNum)) {
				int choice = r.nextInt(s.length()) % 2 == 0 ? 65 : 97;
				val += (char) (choice + r.nextInt(s.length())%10);
			} else if ("num".equalsIgnoreCase(charOrNum)) { //
				val += String.valueOf(r.nextInt(s.length()) % 10);
			}
		}
		return val;
	}
	/**
	 * 获得找回密码的验证码
	 * @param output
	 * @return
	 */
    public static String getForgetVerifyCode(ByteArrayOutputStream output) {
    	try {
        	//生成随机字串  
        	String verifyCode = VerifyCodeUtils.generateVerifyCode(4);  
        	//String verifyCode = getBlend(); 
        	//生成图片  
        	int w = 200, h = 80;  
			VerifyCodeUtils.outputImage(w, h, output, verifyCode);
			return verifyCode;
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return null;
	}
	/**
	 * 获取ip地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			return request.getRemoteAddr();
		}
		byte[] ipAddr = addr.getAddress();
		String ipAddrStr = "";
		for (int i = 0; i < ipAddr.length; i++) {
			if (i > 0) {
				ipAddrStr += ".";
			}
			ipAddrStr += ipAddr[i] & 0xFF;
		}
		return ipAddrStr;
	}
	//获得客户端真实IP地址的方法二：
	public static String getClientIpAddr(HttpServletRequest request) {  
	   String ip = request.getHeader("x-forwarded-for");  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }
	    //System.out.println("ip========= "+ip);
	    return ip;  
	}

	public static void main(String[] args){
	}
}
