package com.caiqianyi.commons.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 登录密码处理
 * @author lonyee
 */
public class PwdUtil {
	/** 密钥 **/
	private static String key = "A3E395B4AF684660AE8336B08528F1C2";
	/** 编码格式 **/
	private static String charset = "UTF-8";
	/** 偏移位 **/
	private static char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * 用户密码加密处理
	 */
	public static String getMd5Password(String account, String pwd) {
		if (account==null || account.isEmpty() || pwd==null || pwd.isEmpty()) {
			throw new RuntimeException("账号或密码不能为空");
		}
		try {
			byte[] btAccount = account.trim().getBytes(charset);
			byte[] btPwd = pwd.trim().getBytes(charset);
			byte[] btKey = key.getBytes(charset);
			int length = btAccount.length + btPwd.length + btKey.length;
			byte[] btCrypt = new byte[length];
			btCrypt[0] = btAccount[0];
			for (int i=0; i< btKey.length; i++) {
				btCrypt[i+1] = btKey[i];
			}
			int blng = btKey.length+1;
			btCrypt[blng] = btPwd[0];
			int accLength = btAccount.length;
			int pwdLength = btPwd.length;
			int lng = accLength> pwdLength? accLength: pwdLength;
			int n = 0;
			for (int i=1; i< lng; i++) {
				if (i< accLength) {
					btCrypt[blng + n + i] = btAccount[i];
				}
				if (i< pwdLength) {
					n += (i< accLength)? 1: 0;
					btCrypt[blng + n + i] = btPwd[i];
				}
			}
			return getMD5(btCrypt);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
			throw new RuntimeException("账户密码加密失败", ex);
		}
	}
	
	/**
	 * 用户密码校验
	 */
	public static boolean verifyPassword(String account, String pwd, String md5Pwd) {
		String md5 = getMd5Password(account, pwd);
		return md5.equals(md5Pwd);
	}
	
	
	/**
	 * MD5加密
	 */
	private static String getMD5(byte[] btCrypt) throws NoSuchAlgorithmException {
		StringBuilder result = new StringBuilder();
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(btCrypt);
		byte[] b = digest.digest();
		for (int i = 0; i < b.length; i++) {
			char[] ob = new char[2];
			ob[0] = digit[(b[i] >>> 4) & 0X0F];
			ob[1] = digit[b[i] & 0X0F];
			result.append(new String(ob));
		}
		return result.toString();
	}
	
	/**
	 * 校验密码规则
	 * @param pwd
	 * @return false true
	 */
	public static boolean isCorrectPwd(String password){
		boolean flag = false;
		try {
			Pattern regex = Pattern.compile("^(?=.*[0-9].*)(?=.*[A-Z].*)(?=.*[a-z].*).{6,16}$");
			Matcher matcher = regex.matcher(password);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	public static void main(String[] args) {
		System.out.println(PwdUtil.getMd5Password("15010602718", "cqy010203"));
	}
}
