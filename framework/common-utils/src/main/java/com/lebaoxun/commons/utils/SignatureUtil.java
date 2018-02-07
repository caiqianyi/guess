package com.lebaoxun.commons.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 签名工具
 * <br/>
 * Created by yangyamin on 16/9/27.
 */
public class SignatureUtil {
    //protected static Logger log = LoggerFactory.getLogger(SignatureUtil.class);

    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    public String bytesToHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public byte[] hexStringToBytes(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * 使用指定算法生成消息摘要，默认是md5
     *
     * @param strSrc
     *            , a string will be encrypted; <br/>
     * @param encName
     *            , the algorithm name will be used, dafault to "MD5"; <br/>
     * @return
     */
    public String digest(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            if (encName == null || encName.equals("")) {
                encName = "MD5";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytesToHexString(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            /*log.error("Invalid algorithm: " + encName);*/
        	System.out.println("encName:"+encName);
        	e.printStackTrace();
            return null;
        }
        return strDes;
    }


    /**
     * 生成MD5信息摘要
     * @param source
     * @return
     */
    public String digestMD5(String source) {
        return this.digest(source,"MD5");
    }


    
}
