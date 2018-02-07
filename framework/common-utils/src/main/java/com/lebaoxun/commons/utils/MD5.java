package com.lebaoxun.commons.utils;

import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;

public class MD5 {

//	public static String md51(String str) {
//		try {
//			MessageDigest md = MessageDigest.getInstance("MD5");
//			md.update(str.getBytes());
//			byte b[] = md.digest();
//
//			int i;
//
//			StringBuffer buf = new StringBuffer("");
//			for (int offset = 0; offset < b.length; offset++) {
//				i = b[offset];
//				if (i < 0)
//					i += 256;
//				if (i < 16)
//					buf.append("0");
//				buf.append(Integer.toHexString(i));
//			}
//			str = buf.toString();
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		}
//		return str;
//	}
	
	public static String getMD5Sign(TreeMap<String, Object> map, String scrit) {
		String secret = "";
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			secret += entry.getKey();
			secret += entry.getValue();
		}
		return md5(secret + scrit);
	}
	
	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return str;
	}
	
	/**
	 * @author sx
	 * @since 2017/1/10
	 * 新增支持UTF-8编码字符串的MD5算法
	 * */
	public final static String md5(String s, String charset) {
        try {
            byte[] btInput = s.getBytes(charset);
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < md.length; i++) {
                int val = ((int) md[i]) & 0xff;
                if (val < 16){
                	sb.append("0");
                }
                sb.append(Integer.toHexString(val));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

	public static void main(String[] args) {
		System.out.println(md5("31119@qq.com" + "123456"));
		System.out.println(md5("1"));
	}
}
