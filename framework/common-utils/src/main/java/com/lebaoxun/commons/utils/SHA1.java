package com.lebaoxun.commons.utils;
/* 
* 微信公众平台(JAVA) SDK 
* 
* Copyright (c) 2016, Ansitech Network Technology Co.,Ltd All rights reserved. 
* http://www.ansitech.com/weixin/sdk/ 
* 
* Licensed under the Apache License, Version 2.0 (the "License"); 
* you may not use this file except in compliance with the License. 
* You may obtain a copy of the License at 
* 
*      http://www.apache.org/licenses/LICENSE-2.0 
* 
* Unless required by applicable law or agreed to in writing, software 
* distributed under the License is distributed on an "AS IS" BASIS, 
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
* See the License for the specific language governing permissions and 
* limitations under the License. 
*/  
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
 
/** 
* <p>Title: SHA1算法</p> 
* 
* @author levi 
*/  
public final class SHA1 {  
	
	private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',  
        '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};  

	/** 
	* Takes the raw bytes from the digest and formats them correct. 
	* 
	* @param bytes the raw bytes from the digest. 
	* @return the formatted bytes. 
	*/  
	private static String getFormattedText(byte[] bytes) {  
		int len = bytes.length;  
		StringBuilder buf = new StringBuilder(len * 2);  
		// 把密文转换成十六进制的字符串形式  
		for (int j = 0; j < len; j++) {  
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);  
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);  
		}  
		return buf.toString();  
	}  
	
	public static String encrypt(String str) {  
		if (str == null)
			return null;  
		try {  
			MessageDigest messageDigest = MessageDigest.getInstance("SHA1");  
			messageDigest.update(str.getBytes());  
			return getFormattedText(messageDigest.digest());  
		} catch (Exception e) {  
			throw new RuntimeException(e);  
		}  
	}  
 
   /** 
    * SHA1 安全加密算法 
    * @param maps 参数key-value map集合 
    * @return 
    * @throws DigestException  
    */  
   public static String encrypt(Map<String,Object> maps) throws DigestException {  
       //获取信息摘要 - 参数字典排序后字符串  
       String decrypt = getOrderByLexicographic(maps);  
       try {  
           //指定sha1算法  
           MessageDigest digest = MessageDigest.getInstance("SHA-1");  
           digest.update(decrypt.getBytes());  
           //获取字节数组  
           byte messageDigest[] = digest.digest();  
           // Create Hex String  
           StringBuffer hexString = new StringBuffer();  
           // 字节数组转换为 十六进制 数  
           for (int i = 0; i < messageDigest.length; i++) {  
               String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);  
               if (shaHex.length() < 2) {  
                   hexString.append(0);  
               }  
               hexString.append(shaHex);  
           }  
           return hexString.toString().toUpperCase();  
 
       } catch (NoSuchAlgorithmException e) {  
           e.printStackTrace();  
           throw new DigestException("签名错误！");  
       }  
   }  
   /** 
    * 获取参数的字典排序 
    * @param maps 参数key-value map集合 
    * @return String 排序后的字符串 
    */  
   private static String getOrderByLexicographic(Map<String,Object> maps){  
       return splitParams(lexicographicOrder(getParamsName(maps)),maps);  
   }  
   /** 
    * 获取参数名称 key 
    * @param maps 参数key-value map集合 
    * @return 
    */  
   private static List<String> getParamsName(Map<String,Object> maps){  
       List<String> paramNames = new ArrayList<String>();  
       for(Map.Entry<String,Object> entry : maps.entrySet()){  
           paramNames.add(entry.getKey());  
       }  
       return paramNames;  
   }  
   /** 
    * 参数名称按字典排序 
    * @param paramNames 参数名称List集合 
    * @return 排序后的参数名称List集合 
    */  
   private static List<String> lexicographicOrder(List<String> paramNames){  
       Collections.sort(paramNames);  
       return paramNames;  
   }  
   /** 
    * 拼接排序好的参数名称和参数值 
    * @param paramNames 排序后的参数名称集合 
    * @param maps 参数key-value map集合 
    * @return String 拼接后的字符串 
    */  
   private static String splitParams(List<String> paramNames,Map<String,Object> maps){  
       StringBuilder paramStr = new StringBuilder();  
       for(String paramName : paramNames){  
           paramStr.append(paramName);  
           for(Map.Entry<String,Object> entry : maps.entrySet()){  
               if(paramName.equals(entry.getKey())){  
                   paramStr.append(String.valueOf(entry.getValue()));  
               }  
           }  
       }  
       return paramStr.toString();  
   }  
}  